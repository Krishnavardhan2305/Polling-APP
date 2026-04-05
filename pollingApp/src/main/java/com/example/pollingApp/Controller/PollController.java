package com.example.pollingApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pollingApp.Entity.Poll;
import com.example.pollingApp.Service.PollService;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "http://localhost:5173")
public class PollController {

    @Autowired
    private PollService pollService;

    // ================= CREATE POLL =================
    @PostMapping("/create/{userId}")
    public Poll createPoll(@PathVariable Long userId, @RequestBody Poll poll) {
        return pollService.createPoll(userId, poll);
    }

    // ================= GET ALL POLLS =================
    @GetMapping("/getallpolls")
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    // ================= VOTE =================
    @PostMapping("/vote/{optionId}")
    public String vote(@PathVariable Long optionId) {
        pollService.vote(optionId);
        return "Voted successfully";
    }
}