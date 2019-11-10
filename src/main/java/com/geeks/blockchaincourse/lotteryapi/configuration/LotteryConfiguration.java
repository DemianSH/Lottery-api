package com.geeks.blockchaincourse.lotteryapi.configuration;

import com.geeks.blockchaincourse.lotteryapi.contracts.generated.Lottery;
import com.geeks.blockchaincourse.lotteryapi.service.LotteryService;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;

@Profile({"local", "ropsten"})
@Configuration
public class LotteryConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(LotteryConfiguration.class);

//    @Value("${lottery.contract.owner-address}")
//    private String ownerAddress;

    @Value("${web3j.client-address}")
    private String clientAddress;

    @Value("${private.key}")
    private String privateKey;

    private LotteryContractConfiguration contractConfig;

    public LotteryConfiguration(LotteryContractConfiguration contractConfig) {
        this.contractConfig = contractConfig;
    }

    @Bean
    public String contractAddress() {
        return new String();
    }

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(clientAddress, new OkHttpClient.Builder().build()));
    }

    @Bean
    public LotteryService contract(Web3j web3j, @Value("${lottery.contract.address:}") String contractAddress) {
        if (StringUtils.isEmpty(contractAddress)) {
            Lottery lottery = deployContract(web3j);

            return initLotteryService(lottery.getContractAddress(), web3j);
        }
        return initLotteryService(contractAddress, web3j);
    }

    private LotteryService initLotteryService(String contractAddress, Web3j web3j) {
        return new LotteryService(contractAddress, web3j, contractConfig);
    }

    private Lottery deployContract(Web3j web3j) {
        Lottery contract = null;
        try {
            LOG.info("About to deploy new contract...");
            contract = Lottery.deploy(web3j, getCredentials(privateKey), contractConfig.gas()).send();
            LOG.info("Deployed new contract with address '{}' ", contract.getContractAddress());
        } catch (Exception e) {
            LOG.error("Something went wrong : Exception" + e.getMessage());
        }
        return contract;
    }

//    private TransactionManager txManager(Web3j web3j) {
//        return new ClientTransactionManager(web3j, ownerAddress);
//    }

    private Credentials getCredentials(String key) {
        return Credentials.create(key);
    }
}
