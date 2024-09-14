package com.eventmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventmanager.models.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository< Attendee, Long > {

}
