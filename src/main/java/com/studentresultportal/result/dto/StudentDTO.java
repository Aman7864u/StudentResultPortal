package com.studentresultportal.result.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Roll number is required")
    private String rollNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    private String semester;

    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    private String phoneNumber;

    private double cgpa;

    private int totalSubjects;
}
