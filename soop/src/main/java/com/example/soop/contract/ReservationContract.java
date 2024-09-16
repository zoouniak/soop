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
import org.web3j.tuples.generated.Tuple6;
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
    public static final String BINARY = "\"bytecode\": \"0x608060405234801562000010575f80fd5b5060405162000fb938038062000fb9833981810160405281019062000036919062000129565b8260018190555081600281905550805f806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505062000182565b5f80fd5b5f819050919050565b620000a48162000090565b8114620000af575f80fd5b50565b5f81519050620000c28162000099565b92915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f620000f382620000c8565b9050919050565b6200010581620000e7565b811462000110575f80fd5b50565b5f815190506200012381620000fa565b92915050565b5f805f606084860312156200014357620001426200008c565b5b5f6200015286828701620000b2565b93505060206200016586828701620000b2565b9250506040620001788682870162000113565b9150509250925092565b610e2980620001905f395ff3fe608060405260043610610085575f3560e01c8063a035b1fe11610058578063a035b1fe14610138578063ae5e6cf414610162578063c70c26cd1461018a578063f437bc59146101b4578063f7217235146101de57610085565b8063067cf832146100895780630c3562ba146100ca5780634a57e1c9146100f4578063819b25ba1461011c575b5f80fd5b348015610094575f80fd5b506100af60048036038101906100aa91906108d7565b610206565b6040516100c19695949392919061096a565b60405180910390f35b3480156100d5575f80fd5b506100de610284565b6040516100eb91906109c9565b60405180910390f35b3480156100ff575f80fd5b5061011a600480360381019061011591906108d7565b61028a565b005b610136600480360381019061013191906108d7565b6103dc565b005b348015610143575f80fd5b5061014c610564565b60405161015991906109c9565b60405180910390f35b34801561016d575f80fd5b50610188600480360381019061018391906108d7565b61056a565b005b348015610195575f80fd5b5061019e610732565b6040516101ab91906109c9565b60405180910390f35b3480156101bf575f80fd5b506101c8610738565b6040516101d591906109e2565b60405180910390f35b3480156101e9575f80fd5b5061020460048036038101906101ff91906108d7565b61075b565b005b6003602052805f5260405f205f91509050805f015490806001015490806002015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020160149054906101000a900460ff16908060020160159054906101000a900460ff16908060020160169054906101000a900460ff16905086565b60025481565b5f60035f8381526020019081526020015f2090505f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461032b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161032290610a55565b60405180910390fd5b5f606460025460015461033e9190610aa0565b6103489190610b0e565b6001546103559190610b3e565b90505f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc8290811502906040515f60405180830381858888f193505050501580156103b9573d5f803e3d5ffd5b5060018260020160166101000a81548160ff021916908315150217905550505050565b6001543414610420576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161041790610bbb565b60405180910390fd5b5f6203f480826104309190610bd9565b90506040518060c001604052808381526020018281526020013373ffffffffffffffffffffffffffffffffffffffff1681526020015f151581526020015f151581526020015f151581525060035f60045481526020019081526020015f205f820151815f0155602082015181600101556040820151816002015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160020160146101000a81548160ff02191690831515021790555060808201518160020160156101000a81548160ff02191690831515021790555060a08201518160020160166101000a81548160ff02191690831515021790555090505060045f81548092919061055b90610c0c565b91905055505050565b60015481565b5f60035f8381526020019081526020015f209050806002015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461060f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161060690610c9d565b60405180910390fd5b8060010154421115610656576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161064d90610d05565b60405180910390fd5b8060020160159054906101000a900460ff16156106a8576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161069f90610d6d565b60405180910390fd5b806002015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc60015490811502906040515f60405180830381858888f19350505050158015610710573d5f803e3d5ffd5b5060018160020160156101000a81548160ff0219169083151502179055505050565b60045481565b5f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b5f60035f8381526020019081526020015f2090505f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146107fc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107f390610dd5565b60405180910390fd5b5f606460025460015461080f9190610aa0565b6108199190610b0e565b90505f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc8290811502906040515f60405180830381858888f1935050505015801561087d573d5f803e3d5ffd5b5060018260020160146101000a81548160ff021916908315150217905550505050565b5f80fd5b5f819050919050565b6108b6816108a4565b81146108c0575f80fd5b50565b5f813590506108d1816108ad565b92915050565b5f602082840312156108ec576108eb6108a0565b5b5f6108f9848285016108c3565b91505092915050565b61090b816108a4565b82525050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f61093a82610911565b9050919050565b61094a81610930565b82525050565b5f8115159050919050565b61096481610950565b82525050565b5f60c08201905061097d5f830189610902565b61098a6020830188610902565b6109976040830187610941565b6109a4606083018661095b565b6109b1608083018561095b565b6109be60a083018461095b565b979650505050505050565b5f6020820190506109dc5f830184610902565b92915050565b5f6020820190506109f55f830184610941565b92915050565b5f82825260208201905092915050565b7f4f6e6c792074686520686f73742063616e2066696e616c697a650000000000005f82015250565b5f610a3f601a836109fb565b9150610a4a82610a0b565b602082019050919050565b5f6020820190508181035f830152610a6c81610a33565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f610aaa826108a4565b9150610ab5836108a4565b9250828202610ac3816108a4565b91508282048414831517610ada57610ad9610a73565b5b5092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601260045260245ffd5b5f610b18826108a4565b9150610b23836108a4565b925082610b3357610b32610ae1565b5b828204905092915050565b5f610b48826108a4565b9150610b53836108a4565b9250828203905081811115610b6b57610b6a610a73565b5b92915050565b7f496e636f7272656374207061796d656e7420616d6f756e7400000000000000005f82015250565b5f610ba56018836109fb565b9150610bb082610b71565b602082019050919050565b5f6020820190508181035f830152610bd281610b99565b9050919050565b5f610be3826108a4565b9150610bee836108a4565b9250828201905080821115610c0657610c05610a73565b5b92915050565b5f610c16826108a4565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610c4857610c47610a73565b5b600182019050919050565b7f4f6e6c79207468652072657365727665722063616e2063616e63656c000000005f82015250565b5f610c87601c836109fb565b9150610c9282610c53565b602082019050919050565b5f6020820190508181035f830152610cb481610c7b565b9050919050565b7f43616e63656c6c6174696f6e20646561646c696e6520686173207061737365645f82015250565b5f610cef6020836109fb565b9150610cfa82610cbb565b602082019050919050565b5f6020820190508181035f830152610d1c81610ce3565b9050919050565b7f5265736572766174696f6e20616c72656164792063616e63656c6c65640000005f82015250565b5f610d57601d836109fb565b9150610d6282610d23565b602082019050919050565b5f6020820190508181035f830152610d8481610d4b565b9050919050565b7f4f6e6c792074686520686f73742063616e20617070726f7665000000000000005f82015250565b5f610dbf6019836109fb565b9150610dca82610d8b565b602082019050919050565b5f6020820190508181035f830152610dec81610db3565b905091905056fea264697066735822122082c53f0e9c6229e6bb7d7c428cb5cfb99557c42e8f94c1efc7ad14b1a683482b64736f6c63430008140033\"";

    private static String librariesLinkedBinary;

    public static final String FUNC_DEPOSITPERCENTAGE = "depositPercentage";

    public static final String FUNC_HOST = "host";

    public static final String FUNC_NEXTRESERVATIONID = "nextReservationId";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_RESERVATIONS = "reservations";

    public static final String FUNC_RESERVE = "reserve";

    public static final String FUNC_APPROVERESERVATION = "approveReservation";

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

    public RemoteFunctionCall<BigInteger> nextReservationId() {
        final Function function = new Function(FUNC_NEXTRESERVATIONID, 
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

    public RemoteFunctionCall<Tuple6<BigInteger, BigInteger, String, Boolean, Boolean, Boolean>> reservations(
            BigInteger param0) {
        final Function function = new Function(FUNC_RESERVATIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, BigInteger, String, Boolean, Boolean, Boolean>>(function,
                new Callable<Tuple6<BigInteger, BigInteger, String, Boolean, Boolean, Boolean>>() {
                    @Override
                    public Tuple6<BigInteger, BigInteger, String, Boolean, Boolean, Boolean> call()
                            throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, BigInteger, String, Boolean, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> reserve(BigInteger _reservationDate,
            BigInteger weiValue) {
        final Function function = new Function(
                FUNC_RESERVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reservationDate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> approveReservation(BigInteger _reservationId) {
        final Function function = new Function(
                FUNC_APPROVERESERVATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reservationId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelReservation(BigInteger _reservationId) {
        final Function function = new Function(
                FUNC_CANCELRESERVATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reservationId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> finalizeTransaction(BigInteger _reservationId) {
        final Function function = new Function(
                FUNC_FINALIZETRANSACTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reservationId)), 
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
