package com.studentresultportal.result.service;



import java.util.List;

import com.studentresultportal.result.dto.SubjectDTO;

public interface SubjectService {

    SubjectDTO createSubject(SubjectDTO subjectDTO);

    SubjectDTO getSubjectById(Long id);

    SubjectDTO getSubjectByCode(String code);

    List<SubjectDTO> getAllSubjects();

    List<SubjectDTO> getSubjectsByDepartment(String department);

    SubjectDTO updateSubject(Long id, SubjectDTO subjectDTO);

    void deleteSubject(Long id);
}
