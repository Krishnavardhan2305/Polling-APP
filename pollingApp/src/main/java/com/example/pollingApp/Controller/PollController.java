package com.example.pollingApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pollingApp.Entity.Poll;
import com.example.pollingApp.Service.PollService;


@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    // ================= CREATE POLL =================
    @PostMapping("/create/{userId}")
    public Poll createPoll(@PathVariable Long userId, @RequestBody Poll poll) {
        return pollService.createPoll(userId, poll);
    }
}