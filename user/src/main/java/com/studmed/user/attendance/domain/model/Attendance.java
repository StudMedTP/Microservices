package com.studmed.user.attendance.domain.model;

public class Attendance {
    public long professorId;
    public long studentId;
    public long latitude;
    public long longitude;
    public String timestamp;

    public Attendance(long professorId, long studentId, long latitude, long longitude, String ts) {
        this.professorId = professorId;
        this.studentId = studentId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = ts;
    }
}
