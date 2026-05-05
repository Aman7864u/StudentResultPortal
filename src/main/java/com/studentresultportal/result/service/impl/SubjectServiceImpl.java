package com.studentresultportal.result.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentresultportal.result.dto.SubjectDTO;
import com.studentresultportal.result.entity.Subject;
import com.studentresultportal.result.exception.ResourceNotFoundException;
import com.studentresultportal.result.repository.SubjectRepository;
import com.studentresultportal.result.service.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public SubjectDTO createSubject(SubjectDTO dto) {
        if (subjectRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Subject code already exists: " + dto.getCode());
        }
        Subject subject = mapToEntity(dto);
        return mapToDTO(subjectRepository.save(subject));
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectDTO getSubjectById(Long id) {
        return mapToDTO(subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectDTO getSubjectByCode(String code) {
        return mapToDTO(subjectRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "code", code)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> getSubjectsByDepartment(String department) {
        return subjectRepository.findByDepartment(department).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public SubjectDTO updateSubject(Long id, SubjectDTO dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        subject.setName(dto.getName());
        subject.setCredits(dto.getCredits());
        subject.setDepartment(dto.getDepartment());
        subject.setSemester(dto.getSemester());
        return mapToDTO(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subject", "id", id);
        }
        subjectRepository.deleteById(id);
    }

    private Subject mapToEntity(SubjectDTO dto) {
        return Subject.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .credits(dto.getCredits())
                .department(dto.getDepartment())
                .semester(dto.getSemester())
                .build();
    }

    private SubjectDTO mapToDTO(Subject subject) {
        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .code(subject.getCode())
                .credits(subject.getCredits())
                .department(subject.getDepartment())
                .semester(subject.getSemester())
                .build();
    }
}
