package com.studmed.attendance.record.client;

import com.studmed.attendance.record.domain.model.client.ClassroomResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "evaluation")
public interface EvaluationClient {
    @GetMapping("/classrooms/{id}")
    ResponseEntity<ClassroomResource> getAttendanceById(@PathVariable Long id);
}
