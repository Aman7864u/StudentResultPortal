package com.studentresultportal.result.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentresultportal.result.entity.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByCode(String code);

    boolean existsByCode(String code);

    List<Subject> findByDepartment(String department);

    List<Subject> findBySemester(String semester);

    List<Subject> findByDepartmentAndSemester(String department, String semester);
}
