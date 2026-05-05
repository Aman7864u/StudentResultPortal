package com.studentresultportal.result.service;



import java.util.List;

import com.studentresultportal.result.dto.StudentDTO;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Long id);

    StudentDTO getStudentByRollNumber(String rollNumber);

    List<StudentDTO> getAllStudents();

    List<StudentDTO> getStudentsByDepartment(String department);

    List<StudentDTO> searchStudentsByName(String name);

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudent(Long id);
}
