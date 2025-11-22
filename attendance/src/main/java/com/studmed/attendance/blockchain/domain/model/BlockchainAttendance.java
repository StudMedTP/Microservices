package com.studmed.attendance.blockchain.domain.model;

public class BlockchainAttendance {
    public long professorId;
    public long studentId;
    public Double latitude;
    public Double longitude;
    public String timestamp;

    public BlockchainAttendance(long professorId, long studentId, Double latitude, Double longitude, String ts) {
        this.professorId = professorId;
        this.studentId = studentId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = ts;
    }
}
