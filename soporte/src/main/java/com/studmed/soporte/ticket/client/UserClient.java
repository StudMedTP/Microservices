package com.studmed.soporte.ticket.client;

import com.studmed.soporte.ticket.domain.model.client.StudentResource;
import com.studmed.soporte.ticket.domain.model.client.TeacherResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/students/{id}")
    ResponseEntity<StudentResource> getStudentById(@PathVariable Long id);

    @GetMapping("/teachers/{id}")
    ResponseEntity<TeacherResource> getTeacherById(@PathVariable Long id);
}
