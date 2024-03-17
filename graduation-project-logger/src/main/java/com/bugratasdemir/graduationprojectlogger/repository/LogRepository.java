package com.bugratasdemir.graduationprojectlogger.repository;

import com.bugratasdemir.graduationprojectlogger.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
