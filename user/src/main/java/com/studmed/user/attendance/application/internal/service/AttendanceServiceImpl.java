package com.studmed.user.attendance.application.internal.service;

import com.studmed.user.attendance.domain.model.Attendance;
import com.studmed.user.attendance.domain.model.AttendanceContract;
import com.studmed.user.attendance.domain.service.AttendanceService;
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
public class AttendanceServiceImpl implements AttendanceService {

    @Value("${blockchain.rpc}")
    private String rpcUrl;

    @Value("${blockchain.private}")
    private String privateKey;

    @Value("${blockchain.contract}")
    private String contractAddress;

    private AttendanceContract contract;

    @Override
    public TransactionReceipt recordAttendance(long professorId, long studentId, long latitude, long longitude) {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);
        contract = AttendanceContract.load(
                contractAddress,
                web3j,
                credentials,
                new DefaultGasProvider()
        );
        System.out.println(rpcUrl);
        System.out.println(privateKey);
        System.out.println(contractAddress);
        RemoteFunctionCall<TransactionReceipt> remoteFunctionCall = contract.recordAttendance(
                BigInteger.valueOf(professorId),
                BigInteger.valueOf(studentId),
                BigInteger.valueOf(latitude),
                BigInteger.valueOf(longitude)
        );

        try {
            return remoteFunctionCall.send();
        } catch (Exception e) {
            throw new RuntimeException("Failed to call blockchain: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Attendance> getByStudent(long studentId) {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);
        contract = AttendanceContract.load(
                contractAddress,
                web3j,
                credentials,
                new DefaultGasProvider()
        );
        System.out.println(rpcUrl);
        System.out.println(privateKey);
        System.out.println(contractAddress);
        RemoteFunctionCall<List<AttendanceContract.AttendanceDTO>> remoteFunctionCall = contract.getAttendanceByStudent(
                BigInteger.valueOf(studentId)
        );

        try {
            List<AttendanceContract.AttendanceDTO> records = remoteFunctionCall.send();

            List<Attendance> result = new ArrayList<>();
            for (AttendanceContract.AttendanceDTO record : records) {
                result.add(new Attendance(
                        record.professorId.longValue(),
                        record.studentId.longValue(),
                        record.latitude.longValue(),
                        record.longitude.longValue(),
                        Instant.ofEpochSecond(record.timestamp.longValue()).toString()
                ));
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Failed to call blockchain: " + e.getMessage(), e);
        }
    }
}