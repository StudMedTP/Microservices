package com.studmed.attendance.attendance.domain.model;

public class BlockchainAttendance {
    public long professorId;
    public long studentId;
    public long latitude;
    public long longitude;
    public String timestamp;

    public BlockchainAttendance(long professorId, long studentId, long latitude, long longitude, String ts) {
        this.professorId = professorId;
        this.studentId = studentId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = ts;
    }
}
