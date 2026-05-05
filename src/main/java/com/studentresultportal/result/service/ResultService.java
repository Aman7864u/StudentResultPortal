package com.studentresultportal.result.service;



import java.util.List;

import com.studentresultportal.result.dto.ResultDTO;

public interface ResultService {

    ResultDTO addResult(ResultDTO resultDTO);

    ResultDTO getResultById(Long id);

    List<ResultDTO> getResultsByStudentId(Long studentId);

    List<ResultDTO> getResultsByRollNumber(String rollNumber);

    List<ResultDTO> getResultsByStudentAndYear(Long studentId, String academicYear);

    List<ResultDTO> getAllResults();

    ResultDTO updateResult(Long id, ResultDTO resultDTO);

    void deleteResult(Long id);

    double calculateCGPA(Long studentId);
}
