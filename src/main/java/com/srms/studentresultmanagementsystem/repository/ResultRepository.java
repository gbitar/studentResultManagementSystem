package com.srms.studentresultmanagementsystem.repository;

import com.srms.studentresultmanagementsystem.api.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {

}