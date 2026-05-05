package com.studentresultportal.result.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.studentresultportal.result.entity.Result;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByStudentId(Long studentId);

    List<Result> findBySubjectId(Long subjectId);

    List<Result> findByStudentIdAndAcademicYear(Long studentId, String academicYear);

    List<Result> findByStudentIdAndExamType(Long studentId, String examType);

    @Query("SELECT AVG(r.gradePoint) FROM Result r WHERE r.student.id = :studentId")
    Double calculateCGPAByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT r FROM Result r WHERE r.student.rollNumber = :rollNumber")
    List<Result> findByStudentRollNumber(@Param("rollNumber") String rollNumber);

    @Query("SELECT COUNT(r) FROM Result r WHERE r.student.id = :studentId AND r.grade = 'F'")
    Long countFailedSubjectsByStudentId(@Param("studentId") Long studentId);
}
