package com.geeks.blockchaincourse.lotteryapi.service;

import com.geeks.blockchaincourse.lotteryapi.configuration.LotteryContractConfiguration;
import com.geeks.blockchaincourse.lotteryapi.contracts.generated.Lottery;
import com.geeks.blockchaincourse.lotteryapi.controller.request.RequestModel;
import com.geeks.blockchaincourse.lotteryapi.model.LotteryInfo;
import com.geeks.blockchaincourse.lotteryapi.model.MemberLottery;
import com.geeks.blockchaincourse.lotteryapi.model.WinnerLottery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;


public class LotteryService {

    private static final Logger LOG = LoggerFactory.getLogger(LotteryService.class);

    private final String contractAddress;
    private final Web3j web3j;
    private final LotteryContractConfiguration contractConfig;

    public LotteryService(String contractAddress, Web3j web3j, LotteryContractConfiguration contractConfig) {
        this.contractAddress = contractAddress;
        this.web3j = web3j;
        this.contractConfig = contractConfig;
    }

    public LotteryInfo getInform() {
        Lottery lottery = loadContract(contractAddress);
        Tuple4<String, String, BigInteger, Boolean> result = null;
        try {
            result = lottery.getInformationAboutLottery().send();
        } catch (Exception e) {
            LOG.error("Something went wrong : Exception" + e.getMessage());
        }
        return LotteryInfo.builder()
                .addressOwner(result.getValue1())
                .addressContract(result.getValue2())
                .deadLine(convertToDataTime(result.getValue3()))
                .status(result.getValue4())
                .description("This is test of lottery in ethereum!")
                .build();
    }

    public List<MemberLottery> getActiveMembers() {
        return getAllMembers().stream()
                .filter(f -> f.isStatus())
                .collect(Collectors.toList());
    }

    public List<MemberLottery> getInactiveMembers() {
        return getAllMembers().stream()
                .filter(f -> !f.isStatus())
                .collect(Collectors.toList());
    }

    public List<MemberLottery> getAll() {
        return getAllMembers();
    }

    private List<MemberLottery> getAllMembers() {
        Lottery lottery = loadContract(contractAddress);
        Tuple4<List<String>, List<BigInteger>, List<BigInteger>, List<Boolean>> result = null;
        try {
            result = lottery.getAllParticipants().send();
        } catch (Exception e) {
            LOG.error("Something went wrong : Exception" + e.getMessage());
        }
        List<MemberLottery> members = new ArrayList<>();

        List<String> addressList = result.getValue1();
        List<BigInteger> valueList = result.getValue2();
        List<BigInteger> timeList = result.getValue3();
        List<Boolean> statusList = result.getValue4();

        for (int i = 0; i < result.getValue1().size(); i++) {
            members.add(MemberLottery.builder()
                    .address(addressList.get(i))
                    .txValue(convertToEther(valueList.get(i)))
                    .txTime(convertToDataTime(timeList.get(i)))
                    .status(statusList.get(i))
                    .build());
        }
        return members;
    }

    public WinnerLottery getWinner() {
        Lottery lottery = loadContract(contractAddress);
        Tuple3<String, BigInteger, BigInteger> result = null;
        try {
            result = lottery.getWinner().send();
        } catch (Exception e) {
            LOG.error("Something went wrong : Exception" + e.getMessage());
        }
        return WinnerLottery.builder()
                .address(result.getValue1())
                .amount(convertToEther(result.getValue2()))
                .txTimeClose(convertToDataTime(result.getValue3()))
                .build();
    }

    public String betLottery(RequestModel request) {
        String transactionHash = null;
        Lottery lottery = loadContractWithCredentials(request);
        try {
            LOG.info("Create transaction for method Bet...");
            transactionHash = lottery.bet(convertToWei(request.getValueEther())).send().getTransactionHash();
            LOG.info("Hash of transaction of method Bet : " + transactionHash);
        } catch (Exception e) {
            LOG.error("Something went wrong : Exception" + e.getMessage());
        }
        return transactionHash;
    }

    public String closeLottery(RequestModel request) {
        String transactionHash = null;
        Lottery lottery = loadContractWithCredentials(request);
        try {
            LOG.info("Create transaction for method Close lottery...");
            transactionHash = lottery.closeLottery(convertToWei(request.getValueEther())).send().getTransactionHash();
            LOG.info("Hash of transaction of method Close lottery : " + transactionHash);
        } catch (Exception e) {
            LOG.error("Something went wrong : Exception" + e.getMessage());
        }
        return transactionHash;
    }

    public String killLottery(RequestModel request) {
        String transactionHash = null;
        Lottery lottery = loadContractWithCredentials(request);
        try {
            LOG.info("Create transaction for method Kill contract...");
            transactionHash = lottery.kill().send().getTransactionHash();
            LOG.info("Hash of transaction of method Kill contract : " + transactionHash);
        } catch (Exception e) {
            LOG.error("Something went wrong : Exception" + e.getMessage());
        }
        return transactionHash;
    }

    private Lottery loadContract(String accountAddress) {
        return Lottery.load(contractAddress, web3j, txManager(accountAddress), contractConfig.gas());
    }

    private Lottery loadContractWithCredentials(RequestModel request) {
        Credentials credentials = getCredentials(request.getPrivateKey());
        LOG.info("Load contract...");
        Lottery lottery = Lottery.load(contractAddress, web3j, credentials, contractConfig.gas());
        LOG.info("Contract successfully loaded...");
        return lottery;
    }

    private Credentials getCredentials(String key) {
        return Credentials.create(key);
    }


    private TransactionManager txManager(String accountAddress) {
        return new ClientTransactionManager(web3j, accountAddress);
    }

    private BigInteger convertToWei(BigDecimal ether) {
        return Convert.toWei(ether, Convert.Unit.ETHER).toBigInteger();
    }

    private BigDecimal convertToEther(BigInteger wei) {
        return Convert.fromWei(new BigDecimal(wei), Convert.Unit.ETHER);
    }

    /**
     * @param time in ethereum timestamp represented in seconds
     * @return value of time in LocalDataTime type
     */
    private LocalDateTime convertToDataTime(BigInteger time) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(String.valueOf(time))),
                TimeZone.getDefault().toZoneId());
    }
}
