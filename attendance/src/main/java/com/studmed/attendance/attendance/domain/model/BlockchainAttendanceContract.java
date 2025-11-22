package com.studmed.attendance.attendance.domain.model;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlockchainAttendanceContract extends Contract {

    public static final String BINARY = "";

    public static class AttendanceDTO extends StaticStruct {
        public BigInteger professorId;
        public BigInteger studentId;
        public BigInteger latitude;
        public BigInteger longitude;
        public BigInteger timestamp;

        public AttendanceDTO(Uint256 professorId, Uint256 studentId, Int256 latitude,
                             Int256 longitude, Uint256 timestamp) {
            super(professorId, studentId, latitude, longitude, timestamp);
            this.professorId = professorId.getValue();
            this.studentId = studentId.getValue();
            this.latitude = latitude.getValue();
            this.longitude = longitude.getValue();
            this.timestamp = timestamp.getValue();
        }
    }

    protected BlockchainAttendanceContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        super(BINARY, contractAddress, web3j, credentials, gasProvider);
    }

    public static BlockchainAttendanceContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        return new BlockchainAttendanceContract(contractAddress, web3j, credentials, gasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> recordAttendance(BigInteger professorId, BigInteger studentId, BigInteger latitude, BigInteger longitude) {
        final Function function = new Function(
                "recordAttendance",
                Arrays.asList(new Uint256(professorId), new Uint256(studentId), new Int256(latitude), new Int256(longitude)),
                Collections.emptyList()
        );

        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List<AttendanceDTO>> getAttendanceByStudent(BigInteger studentId) {
        final Function function = new Function(
                "getAttendanceByStudent",
                List.of(new Uint256(studentId)),
                List.of(new TypeReference<DynamicArray<AttendanceDTO>>() {})
        );

        return new RemoteFunctionCall<>(
                function,
                () -> {
                    List<Type> raw = executeCallMultipleValueReturn(function);

                    DynamicArray<AttendanceDTO> array = (DynamicArray<AttendanceDTO>) raw.getFirst();

                    return array.getValue();
                }
        );
    }
}
