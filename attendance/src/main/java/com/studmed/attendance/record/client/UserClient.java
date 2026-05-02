package com.studmed.attendance.record.client;

import com.studmed.attendance.record.domain.model.client.TeacherResource;
import com.studmed.attendance.record.domain.model.client.StudentResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/students/{id}")
    ResponseEntity<StudentResource> getStudentById(@PathVariable Long id);

    @GetMapping("/students/user/{id}")
    ResponseEntity<Map<String, StudentResource>> getStudentByUserId(@PathVariable Long id);

    @GetMapping("/teachers/{id}")
    ResponseEntity<TeacherResource> getTeacherById(@PathVariable Long id);
}
