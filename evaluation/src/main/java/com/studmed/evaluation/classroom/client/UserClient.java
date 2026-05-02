package com.studmed.evaluation.classroom.client;

import com.studmed.evaluation.classroom.domain.model.client.MedicalCenterResource;
import com.studmed.evaluation.classroom.domain.model.client.StudentResource;
import com.studmed.evaluation.classroom.domain.model.client.TeacherResource;
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

    @GetMapping("/teachers/user/{id}")
    ResponseEntity<Map<String, TeacherResource>> getTeacherByUserId(@PathVariable Long id);

    @GetMapping("/medical-centers/{id}")
    ResponseEntity<MedicalCenterResource> getMedicalCenterById(@PathVariable Long id);
}
