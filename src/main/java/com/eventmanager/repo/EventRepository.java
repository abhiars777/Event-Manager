package com.eventmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventmanager.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
