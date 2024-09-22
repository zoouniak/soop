package com.example.soop.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.1.
 */
@SuppressWarnings("rawtypes")
public class ReservationContract extends Contract {
    public static final String BINARY = "0x608060405234801562000010575f80fd5b5060405162000fc038038062000fc0833981810160405281019062000036919062000129565b8260018190555081600281905550805f806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505062000182565b5f80fd5b5f819050919050565b620000a48162000090565b8114620000af575f80fd5b50565b5f81519050620000c28162000099565b92915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f620000f382620000c8565b9050919050565b6200010581620000e7565b811462000110575f80fd5b50565b5f815190506200012381620000fa565b92915050565b5f805f606084860312156200014357620001426200008c565b5b5f6200015286828701620000b2565b93505060206200016586828701620000b2565b9250506040620001788682870162000113565b9150509250925092565b610e3080620001905f395ff3fe60806040526004361061007a575f3560e01c8063a035b1fe1161004d578063a035b1fe1461012c578063ae5e6cf414610156578063dad072bc1461017e578063f437bc59146101a85761007a565b8063067cf8321461007e5780630c3562ba146100be5780634a57e1c9146100e8578063819b25ba14610110575b5f80fd5b348015610089575f80fd5b506100a4600480360381019061009f9190610884565b6101d2565b6040516100b5959493929190610917565b60405180910390f35b3480156100c9575f80fd5b506100d261023d565b6040516100df9190610968565b60405180910390f35b3480156100f3575f80fd5b5061010e60048036038101906101099190610884565b610243565b005b61012a60048036038101906101259190610884565b61042d565b005b348015610137575f80fd5b50610140610610565b60405161014d9190610968565b60405180910390f35b348015610161575f80fd5b5061017c60048036038101906101779190610884565b610616565b005b348015610189575f80fd5b50610192610824565b60405161019f9190610968565b60405180910390f35b3480156101b3575f80fd5b506101bc61082a565b6040516101c99190610981565b60405180910390f35b6003602052805f5260405f205f91509050805f015490806001015490806002015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020160149054906101000a900460ff16908060020160159054906101000a900460ff16905085565b60025481565b806004548110610288576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027f906109f4565b60405180910390fd5b5f60035f8481526020019081526020015f2090505f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610329576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161032090610a5c565b60405180910390fd5b8060020160159054906101000a900460ff161561037b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161037290610ac4565b60405180910390fd5b5f606460025460015461038e9190610b0f565b6103989190610b7d565b6001546103a59190610bad565b90505f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc8290811502906040515f60405180830381858888f19350505050158015610409573d5f803e3d5ffd5b5060018260020160156101000a81548160ff02191690831515021790555050505050565b6001543414610471576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161046890610c2a565b60405180910390fd5b5f6203f480826104819190610c48565b90506040518060a001604052808381526020018281526020013373ffffffffffffffffffffffffffffffffffffffff1681526020015f151581526020015f151581525060035f60045481526020019081526020015f205f820151815f0155602082015181600101556040820151816002015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160020160146101000a81548160ff02191690831515021790555060808201518160020160156101000a81548160ff0219169083151502179055509050505f60646002546001546105859190610b0f565b61058f9190610b7d565b90505f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc8290811502906040515f60405180830381858888f193505050501580156105f3573d5f803e3d5ffd5b5060045f81548092919061060690610c7b565b9190505550505050565b60015481565b80600454811061065b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610652906109f4565b60405180910390fd5b5f60035f8481526020019081526020015f209050806002015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610700576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106f790610d0c565b60405180910390fd5b8060010154421115610747576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161073e90610d74565b60405180910390fd5b8060020160149054906101000a900460ff1615610799576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161079090610ddc565b60405180910390fd5b806002015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc60015490811502906040515f60405180830381858888f19350505050158015610801573d5f803e3d5ffd5b5060018160020160146101000a81548160ff021916908315150217905550505050565b60045481565b5f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b5f80fd5b5f819050919050565b61086381610851565b811461086d575f80fd5b50565b5f8135905061087e8161085a565b92915050565b5f602082840312156108995761089861084d565b5b5f6108a684828501610870565b91505092915050565b6108b881610851565b82525050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f6108e7826108be565b9050919050565b6108f7816108dd565b82525050565b5f8115159050919050565b610911816108fd565b82525050565b5f60a08201905061092a5f8301886108af565b61093760208301876108af565b61094460408301866108ee565b6109516060830185610908565b61095e6080830184610908565b9695505050505050565b5f60208201905061097b5f8301846108af565b92915050565b5f6020820190506109945f8301846108ee565b92915050565b5f82825260208201905092915050565b7f496e76616c6964207265736572766174696f6e496400000000000000000000005f82015250565b5f6109de60158361099a565b91506109e9826109aa565b602082019050919050565b5f6020820190508181035f830152610a0b816109d2565b9050919050565b7f4f6e6c7920686f73742063616e2066696e616c697a65000000000000000000005f82015250565b5f610a4660168361099a565b9150610a5182610a12565b602082019050919050565b5f6020820190508181035f830152610a7381610a3a565b9050919050565b7f416c72656164792066696e616c697a65640000000000000000000000000000005f82015250565b5f610aae60118361099a565b9150610ab982610a7a565b602082019050919050565b5f6020820190508181035f830152610adb81610aa2565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f610b1982610851565b9150610b2483610851565b9250828202610b3281610851565b91508282048414831517610b4957610b48610ae2565b5b5092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601260045260245ffd5b5f610b8782610851565b9150610b9283610851565b925082610ba257610ba1610b50565b5b828204905092915050565b5f610bb782610851565b9150610bc283610851565b9250828203905081811115610bda57610bd9610ae2565b5b92915050565b7f496e636f7272656374207061796d656e740000000000000000000000000000005f82015250565b5f610c1460118361099a565b9150610c1f82610be0565b602082019050919050565b5f6020820190508181035f830152610c4181610c08565b9050919050565b5f610c5282610851565b9150610c5d83610851565b9250828201905080821115610c7557610c74610ae2565b5b92915050565b5f610c8582610851565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610cb757610cb6610ae2565b5b600182019050919050565b7f4f6e6c792072657365727665722063616e2063616e63656c00000000000000005f82015250565b5f610cf660188361099a565b9150610d0182610cc2565b602082019050919050565b5f6020820190508181035f830152610d2381610cea565b9050919050565b7f43616e63656c20646561646c696e6520706173736564000000000000000000005f82015250565b5f610d5e60168361099a565b9150610d6982610d2a565b602082019050919050565b5f6020820190508181035f830152610d8b81610d52565b9050919050565b7f416c72656164792063616e63656c6c65640000000000000000000000000000005f82015250565b5f610dc660118361099a565b9150610dd182610d92565b602082019050919050565b5f6020820190508181035f830152610df381610dba565b905091905056fea2646970667358221220031c80bea77df885b086b5e0bf5e8bf0fe8e18c6586de7ad6d6de9a2d556fe1364736f6c63430008140033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DEPOSITPERCENTAGE = "depositPercentage";

    public static final String FUNC_HOST = "host";

    public static final String FUNC_NEXTRESID = "nextResId";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_RESERVATIONS = "reservations";

    public static final String FUNC_RESERVE = "reserve";

    public static final String FUNC_CANCELRESERVATION = "cancelReservation";

    public static final String FUNC_FINALIZETRANSACTION = "finalizeTransaction";

    @Deprecated
    protected ReservationContract(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ReservationContract(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ReservationContract(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ReservationContract(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> depositPercentage() {
        final Function function = new Function(FUNC_DEPOSITPERCENTAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> host() {
        final Function function = new Function(FUNC_HOST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> nextResId() {
        final Function function = new Function(FUNC_NEXTRESID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> price() {
        final Function function = new Function(FUNC_PRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple5<BigInteger, BigInteger, String, Boolean, Boolean>> reservations(
            BigInteger param0) {
        final Function function = new Function(FUNC_RESERVATIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple5<BigInteger, BigInteger, String, Boolean, Boolean>>(function,
                new Callable<Tuple5<BigInteger, BigInteger, String, Boolean, Boolean>>() {
                    @Override
                    public Tuple5<BigInteger, BigInteger, String, Boolean, Boolean> call() throws
                            Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, BigInteger, String, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> reserve(BigInteger _resDate,
            BigInteger weiValue) {
        final Function function = new Function(
                FUNC_RESERVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resDate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelReservation(BigInteger _resId) {
        final Function function = new Function(
                FUNC_CANCELRESERVATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> finalizeTransaction(BigInteger _resId) {
        final Function function = new Function(
                FUNC_FINALIZETRANSACTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_resId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ReservationContract load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ReservationContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ReservationContract load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ReservationContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ReservationContract load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ReservationContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ReservationContract load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ReservationContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ReservationContract> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, BigInteger _price,
            BigInteger _depositPercentage, String _host) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_depositPercentage), 
                new org.web3j.abi.datatypes.Address(160, _host)));
        return deployRemoteCall(ReservationContract.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<ReservationContract> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            BigInteger _price, BigInteger _depositPercentage, String _host) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_depositPercentage), 
                new org.web3j.abi.datatypes.Address(160, _host)));
        return deployRemoteCall(ReservationContract.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ReservationContract> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, BigInteger _price,
            BigInteger _depositPercentage, String _host) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_depositPercentage), 
                new org.web3j.abi.datatypes.Address(160, _host)));
        return deployRemoteCall(ReservationContract.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ReservationContract> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            BigInteger _price, BigInteger _depositPercentage, String _host) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_depositPercentage), 
                new org.web3j.abi.datatypes.Address(160, _host)));
        return deployRemoteCall(ReservationContract.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
