package com.eventmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventmanager.models.ActivityLog;

@Repository
public interface ActivityLogRepository extends JpaRepository< ActivityLog, Long > {

}
