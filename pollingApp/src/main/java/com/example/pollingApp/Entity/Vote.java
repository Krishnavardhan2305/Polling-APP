package com.example.pollingApp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "votes",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "poll_id"}))
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long pollId;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }
}