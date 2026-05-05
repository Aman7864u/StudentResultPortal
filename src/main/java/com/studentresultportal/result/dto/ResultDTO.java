package com.studentresultportal.result.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO {

    private Long id;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    private String studentName;

    private String rollNumber;

    @NotNull(message = "Subject ID is required")
    private Long subjectId;

    private String subjectName;

    private String subjectCode;

    @Min(value = 0, message = "Marks cannot be negative")
    @Max(value = 100, message = "Marks cannot exceed 100")
    private double marksObtained;

    private double totalMarks;

    private String grade;

    private double gradePoint;

    private double percentage;

    private String examType;

    private String academicYear;

    private String status; // PASS / FAIL
}
