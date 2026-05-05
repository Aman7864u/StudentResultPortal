package com.studentresultportal.result.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.studentresultportal.result.dto.ResultDTO;
import com.studentresultportal.result.service.ResultService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<ResultDTO> addResult(@Valid @RequestBody ResultDTO resultDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resultService.addResult(resultDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResultDTO>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getResultById(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.getResultById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ResultDTO>> getResultsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(resultService.getResultsByStudentId(studentId));
    }

    @GetMapping("/roll/{rollNumber}")
    public ResponseEntity<List<ResultDTO>> getResultsByRollNumber(@PathVariable String rollNumber) {
        return ResponseEntity.ok(resultService.getResultsByRollNumber(rollNumber));
    }

    @GetMapping("/student/{studentId}/year/{academicYear}")
    public ResponseEntity<List<ResultDTO>> getResultsByStudentAndYear(
            @PathVariable Long studentId,
            @PathVariable String academicYear) {
        return ResponseEntity.ok(resultService.getResultsByStudentAndYear(studentId, academicYear));
    }

    @GetMapping("/student/{studentId}/cgpa")
    public ResponseEntity<Map<String, Double>> getCGPA(@PathVariable Long studentId) {
        double cgpa = resultService.calculateCGPA(studentId);
        return ResponseEntity.ok(Map.of("cgpa", cgpa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> updateResult(@PathVariable Long id,
                                                  @Valid @RequestBody ResultDTO resultDTO) {
        return ResponseEntity.ok(resultService.updateResult(id, resultDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
