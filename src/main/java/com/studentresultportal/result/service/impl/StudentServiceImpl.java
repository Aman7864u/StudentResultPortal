package com.studentresultportal.result.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentresultportal.result.dto.StudentDTO;
import com.studentresultportal.result.entity.Student;
import com.studentresultportal.result.exception.ResourceNotFoundException;
import com.studentresultportal.result.repository.ResultRepository;
import com.studentresultportal.result.repository.StudentRepository;
import com.studentresultportal.result.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        if (studentRepository.existsByRollNumber(dto.getRollNumber())) {
            throw new IllegalArgumentException("Roll number already exists: " + dto.getRollNumber());
        }
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered: " + dto.getEmail());
        }
        Student student = mapToEntity(dto);
        Student saved = studentRepository.save(student);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return mapToDTO(student);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentByRollNumber(String rollNumber) {
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "rollNumber", rollNumber));
        return mapToDTO(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> searchStudentsByName(String name) {
        return studentRepository.searchByName(name)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(dto.getDepartment());
        student.setSemester(dto.getSemester());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setPhoneNumber(dto.getPhoneNumber());

        return mapToDTO(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student", "id", id);
        }
        studentRepository.deleteById(id);
    }

    // ── Mappers ──────────────────────────────────────────────────────────────

    private Student mapToEntity(StudentDTO dto) {
        return Student.builder()
                .name(dto.getName())
                .rollNumber(dto.getRollNumber())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .semester(dto.getSemester())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    private StudentDTO mapToDTO(Student student) {
        Double cgpa = resultRepository.calculateCGPAByStudentId(student.getId());
        int totalSubjects = resultRepository.findByStudentId(student.getId()).size();

        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .rollNumber(student.getRollNumber())
                .email(student.getEmail())
                .department(student.getDepartment())
                .semester(student.getSemester())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .phoneNumber(student.getPhoneNumber())
                .cgpa(cgpa != null ? Math.round(cgpa * 100.0) / 100.0 : 0.0)
                .totalSubjects(totalSubjects)
                .build();
    }
}
