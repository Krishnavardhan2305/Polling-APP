package com.example.pollingApp.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pollingApp.Entity.Poll;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {

    List<Poll> findByUserId(Long userId);
}