package com.studmed.attendance.blockchain.application.internal.service;

import com.studmed.attendance.blockchain.domain.model.BlockchainAttendance;
import com.studmed.attendance.blockchain.domain.model.BlockchainAttendanceContract;
import com.studmed.attendance.blockchain.domain.service.BlockchainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlockchainServiceImpl implements BlockchainService {

    @Value("${blockchain.rpc}")
    private String rpcUrl;

    @Value("${blockchain.private}")
    private String privateKey;

    @Value("${blockchain.contract}")
    private String contractAddress;

    private BlockchainAttendanceContract contract;

    @Override
    public TransactionReceipt recordAttendance(long attendanceId, long professorId, long studentId, Double latitude, Double longitude) {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);
        contract = BlockchainAttendanceContract.load(
                contractAddress,
                web3j,
                credentials,
                new DefaultGasProvider()
        );

        RemoteFunctionCall<TransactionReceipt> remoteFunctionCall = contract.recordAttendance(
                BigInteger.valueOf(attendanceId),
                BigInteger.valueOf(professorId),
                BigInteger.valueOf(studentId),
                BigInteger.valueOf((long) (latitude * 1_000_000)),
                BigInteger.valueOf((long) (longitude * 1_000_000))
        );

        try {
            return remoteFunctionCall.send();
        } catch (Exception e) {
            throw new RuntimeException("Failed to call blockchain: " + e.getMessage(), e);
        }
    }

    @Override
    public List<BlockchainAttendance> getByStudent(long studentId) {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);
        contract = BlockchainAttendanceContract.load(
                contractAddress,
                web3j,
                credentials,
                new DefaultGasProvider()
        );

        RemoteFunctionCall<List<BlockchainAttendanceContract.AttendanceDTO>> remoteFunctionCall = contract.getAttendanceByStudent(
                BigInteger.valueOf(studentId)
        );

        try {
            List<BlockchainAttendanceContract.AttendanceDTO> records = remoteFunctionCall.send();

            List<BlockchainAttendance> result = new ArrayList<>();
            for (BlockchainAttendanceContract.AttendanceDTO record : records) {
                result.add(new BlockchainAttendance(
                        record.professorId.longValue(),
                        record.studentId.longValue(),
                        record.latitude.doubleValue() / 1_000_000,
                        record.longitude.doubleValue() / 1_000_000,
                        Instant.ofEpochSecond(record.timestamp.longValue()).toString()
                ));
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Failed to call blockchain: " + e.getMessage(), e);
        }
    }

    @Override
    public List<BlockchainAttendance> getByAttendance(long attendanceId) {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);
        contract = BlockchainAttendanceContract.load(
                contractAddress,
                web3j,
                credentials,
                new DefaultGasProvider()
        );

        RemoteFunctionCall<List<BlockchainAttendanceContract.AttendanceDTO>> remoteFunctionCall = contract.getAttendanceByAttendance(
                BigInteger.valueOf(attendanceId)
        );

        try {
            List<BlockchainAttendanceContract.AttendanceDTO> records = remoteFunctionCall.send();

            List<BlockchainAttendance> result = new ArrayList<>();
            for (BlockchainAttendanceContract.AttendanceDTO record : records) {
                result.add(new BlockchainAttendance(
                        record.professorId.longValue(),
                        record.studentId.longValue(),
                        record.latitude.doubleValue() / 1_000_000,
                        record.longitude.doubleValue() / 1_000_000,
                        Instant.ofEpochSecond(record.timestamp.longValue()).toString()
                ));
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Failed to call blockchain: " + e.getMessage(), e);
        }
    }
}