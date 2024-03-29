package com.geeks.blockchaincourse.lotteryapi.contracts.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class Lottery extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b031916331790556009805460ff1916600117905562093a8042016008556108cf806100486000396000f3fe6080604052600436106100555760003560e01c806311610c251461005a578063195ec9ee1461006457806341c0e1b51461019c5780636fd09816146101b15780638e7ea5b2146101b9578063fc6ecd67146101f6575b600080fd5b61006261023f565b005b34801561007057600080fd5b50610079610336565b6040518080602001806020018060200180602001858103855289818151815260200191508051906020019060200280838360005b838110156100c55781810151838201526020016100ad565b50505050905001858103845288818151815260200191508051906020019060200280838360005b838110156101045781810151838201526020016100ec565b50505050905001858103835287818151815260200191508051906020019060200280838360005b8381101561014357818101518382015260200161012b565b50505050905001858103825286818151815260200191508051906020019060200280838360005b8381101561018257818101518382015260200161016a565b505050509050019850505050505050505060405180910390f35b3480156101a857600080fd5b506100626104f8565b61006261055c565b3480156101c557600080fd5b506101ce610753565b604080516001600160a01b039094168452602084019290925282820152519081900360600190f35b34801561020257600080fd5b5061020b610771565b604080516001600160a01b039586168152939094166020840152828401919091521515606082015290519081900360800190f35b600a600654141561024c57fe5b426008541161028c5760405162461bcd60e51b81526004018080602001828103825260278152602001806108746027913960400191505060405180910390fd5b61029733344261078e565b600654600090815260056020908152604091829020835181546001600160a01b0319166001600160a01b039091161781559083015160018201559082015160028201556060909101516003909101805460ff19169115159190911790556702c68af0bb1400003410610323576006546000908152600560205260409020600301805460ff191660011790555b6006805460010190556007805434019055565b606080606080606060065460405190808252806020026020018201604052801561036a578160200160208202803883390190505b509050606060065460405190808252806020026020018201604052801561039b578160200160208202803883390190505b50905060606006546040519080825280602002602001820160405280156103cc578160200160208202803883390190505b50905060606006546040519080825280602002602001820160405280156103fd578160200160208202803883390190505b50905060005b6006548110156104e95760008181526005602052604090205485516001600160a01b039091169086908390811061043657fe5b6001600160a01b03909216602092830291909101820152600082815260059091526040902060010154845185908390811061046d57fe5b602002602001018181525050600560008281526020019081526020016000206002015483828151811061049c57fe5b602090810291909101810191909152600082815260059091526040902060030154825160ff909116908390839081106104d157fe5b91151560209283029190910190910152600101610403565b50929791965094509092509050565b6000546001600160a01b0316331461054e576040805162461bcd60e51b8152602060048201526014602482015273596f7520646f6e2774206861766520616363657360601b604482015290519081900360640190fd5b6000546001600160a01b0316ff5b6000546001600160a01b031633146105b2576040805162461bcd60e51b8152602060048201526014602482015273596f7520646f6e2774206861766520616363657360601b604482015290519081900360640190fd5b6000600654116105f35760405162461bcd60e51b815260040180806020018281038252604681526020018061082e6046913960600191505060405180910390fd5b6009805460ff19169055600654604080518281526020808402820101909152606091801561063b57816020015b6106286107fa565b8152602001906001900390816106205790505b5090506000805b6006548110156106e25760008181526005602052604090206003015460ff161515600114156106da57600081815260056020908152604091829020825160808101845281546001600160a01b0316815260018201549281019290925260028101549282019290925260039091015460ff161515606082015283518490849081106106c857fe5b60209081029190910101526001909101905b600101610642565b5060006106f04244846107bd565b90508281815181106106fe57fe5b60209081029190910101518051600180546001600160a01b0319166001600160a01b03909216919091179055606001516004805460ff1916911515919091179055505060075460029081900490555042600355565b6001546007546008546001600160a01b039092169260029091049190565b6000546008546009546001600160a01b0390921692309260ff1690565b6107966107fa565b61079e6107fa565b6001600160a01b03949094168452506020830191909152604082015290565b604080516020808201869052818301859052825180830384018152606090920190925280519101206000908290816107f157fe5b06949350505050565b604051806080016040528060006001600160a01b031681526020016000815260200160008152602001600015158152509056fe546865206c6f747465727920646f65736e27742068617665207061727469636970616e74732120546865206c6f7474657279206861736e27742066696e697368656420796574536f7272792c206275742074696d65206f66206c6f7474657279206861732066696e6973686564a265627a7a723058202db0ed12bb5473e4be42fc3c5d304cb9878ebfee1711b674ba5657b48d3edf9264736f6c63430005090032";

    public static final String FUNC_BET = "bet";

    public static final String FUNC_GETALLPARTICIPANTS = "getAllParticipants";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_CLOSELOTTERY = "closeLottery";

    public static final String FUNC_GETWINNER = "getWinner";

    public static final String FUNC_GETINFORMATIONABOUTLOTTERY = "getInformationAboutLottery";

    @Deprecated
    protected Lottery(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Lottery(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Lottery(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Lottery(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> bet(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BET, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple4<List<String>, List<BigInteger>, List<BigInteger>, List<Boolean>>> getAllParticipants() {
        final Function function = new Function(FUNC_GETALLPARTICIPANTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Bool>>() {}));
        return new RemoteCall<Tuple4<List<String>, List<BigInteger>, List<BigInteger>, List<Boolean>>>(
                new Callable<Tuple4<List<String>, List<BigInteger>, List<BigInteger>, List<Boolean>>>() {
                    @Override
                    public Tuple4<List<String>, List<BigInteger>, List<BigInteger>, List<Boolean>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<List<String>, List<BigInteger>, List<BigInteger>, List<Boolean>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()), 
                                convertToNative((List<Bool>) results.get(3).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeLottery(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CLOSELOTTERY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> getWinner() {
        final Function function = new Function(FUNC_GETWINNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple4<String, String, BigInteger, Boolean>> getInformationAboutLottery() {
        final Function function = new Function(FUNC_GETINFORMATIONABOUTLOTTERY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple4<String, String, BigInteger, Boolean>>(
                new Callable<Tuple4<String, String, BigInteger, Boolean>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    @Deprecated
    public static Lottery load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Lottery(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Lottery load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Lottery(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Lottery load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Lottery(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Lottery load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Lottery(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Lottery> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Lottery.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Lottery> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Lottery.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Lottery> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Lottery.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Lottery> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Lottery.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
