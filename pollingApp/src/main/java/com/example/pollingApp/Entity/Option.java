package com.example.pollingApp.Entity;
import jakarta.persistence.*;

@Entity // Marks this class as a JPA entity (mapped to DB table)
@Table(name = "options") // Maps this entity to "options" table in MySQL
public class Option {

    @Id // Marks this field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // Auto-incremented ID (handled by MySQL)
    private Long id;

    private String text; 
    // Stores the option text (e.g., "Java", "Python")

    private int votes = 0; 
    // Stores number of votes for this option (default = 0)

    @ManyToOne 
    // Many options belong to one poll
    @JoinColumn(name = "poll_id") 
    // Foreign key column in "options" table referencing "polls" table
    private Poll poll;

    // ==================== GETTERS & SETTERS ====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}