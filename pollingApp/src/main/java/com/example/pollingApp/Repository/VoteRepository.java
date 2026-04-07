package com.example.pollingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pollingApp.Entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByUserIdAndPollId(Long userId, Long pollId);
}