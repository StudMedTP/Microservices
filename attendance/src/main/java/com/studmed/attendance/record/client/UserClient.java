package com.studmed.attendance.record.client;

import com.studmed.attendance.record.domain.model.client.MedicalCenterResource;
import com.studmed.attendance.record.domain.model.client.StudentResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/students/{id}")
    ResponseEntity<StudentResource> getStudentById(@PathVariable Long id);

    @GetMapping("/students/user/{id}")
    ResponseEntity<StudentResource> getStudentByUserId(@PathVariable Long id);

    @GetMapping("/medical-centers/{id}")
    ResponseEntity<MedicalCenterResource> getMedicalCenterById(@PathVariable Long id);
}
