package com.studentresultportal.result.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.studentresultportal.result.dto.ResultDTO;
import com.studentresultportal.result.entity.Result;
import com.studentresultportal.result.entity.Student;
import com.studentresultportal.result.entity.Subject;
import com.studentresultportal.result.exception.ResourceNotFoundException;
import com.studentresultportal.result.repository.ResultRepository;
import com.studentresultportal.result.repository.StudentRepository;
import com.studentresultportal.result.repository.SubjectRepository;
import com.studentresultportal.result.service.ResultService;
import com.studentresultportal.result.util.GradeCalculator;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GradeCalculator gradeCalculator;

    @Override
    public ResultDTO addResult(ResultDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", dto.getStudentId()));

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", dto.getSubjectId()));

        double total = dto.getTotalMarks() > 0 ? dto.getTotalMarks() : 100.0;
        String grade = gradeCalculator.calculateGrade(dto.getMarksObtained(), total);
        double gradePoint = gradeCalculator.calculateGradePoint(grade);

        Result result = Result.builder()
                .student(student)
                .subject(subject)
                .marksObtained(dto.getMarksObtained())
                .totalMarks(total)
                .grade(grade)
                .gradePoint(gradePoint)
                .examType(dto.getExamType())
                .academicYear(dto.getAcademicYear())
                .build();

        return mapToDTO(resultRepository.save(result));
    }

    @Override
    @Transactional(readOnly = true)
    public ResultDTO getResultById(Long id) {
        return mapToDTO(resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result", "id", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultDTO> getResultsByStudentId(Long studentId) {
        return resultRepository.findByStudentId(studentId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultDTO> getResultsByRollNumber(String rollNumber) {
        return resultRepository.findByStudentRollNumber(rollNumber)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultDTO> getResultsByStudentAndYear(Long studentId, String academicYear) {
        return resultRepository.findByStudentIdAndAcademicYear(studentId, academicYear)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultDTO> getAllResults() {
        return resultRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ResultDTO updateResult(Long id, ResultDTO dto) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result", "id", id));

        double total = dto.getTotalMarks() > 0 ? dto.getTotalMarks() : result.getTotalMarks();
        String grade = gradeCalculator.calculateGrade(dto.getMarksObtained(), total);

        result.setMarksObtained(dto.getMarksObtained());
        result.setTotalMarks(total);
        result.setGrade(grade);
        result.setGradePoint(gradeCalculator.calculateGradePoint(grade));
        result.setExamType(dto.getExamType());
        result.setAcademicYear(dto.getAcademicYear());

        return mapToDTO(resultRepository.save(result));
    }

    @Override
    public void deleteResult(Long id) {
        if (!resultRepository.existsById(id)) {
            throw new ResourceNotFoundException("Result", "id", id);
        }
        resultRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public double calculateCGPA(Long studentId) {
        Double cgpa = resultRepository.calculateCGPAByStudentId(studentId);
        return cgpa != null ? Math.round(cgpa * 100.0) / 100.0 : 0.0;
    }

    private ResultDTO mapToDTO(Result result) {
        double percentage = gradeCalculator.calculatePercentage(
                result.getMarksObtained(), result.getTotalMarks());

        return ResultDTO.builder()
                .id(result.getId())
                .studentId(result.getStudent().getId())
                .studentName(result.getStudent().getName())
                .rollNumber(result.getStudent().getRollNumber())
                .subjectId(result.getSubject().getId())
                .subjectName(result.getSubject().getName())
                .subjectCode(result.getSubject().getCode())
                .marksObtained(result.getMarksObtained())
                .totalMarks(result.getTotalMarks())
                .grade(result.getGrade())
                .gradePoint(result.getGradePoint())
                .percentage(percentage)
                .examType(result.getExamType())
                .academicYear(result.getAcademicYear())
                .status(gradeCalculator.determineStatus(result.getMarksObtained(), result.getTotalMarks()))
                .build();
    }
}
