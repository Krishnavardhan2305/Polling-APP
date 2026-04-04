package com.example.pollingApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pollingApp.Entity.Option;
import com.example.pollingApp.Entity.Poll;
import com.example.pollingApp.Entity.User;
import com.example.pollingApp.Repository.PollRepository;
import com.example.pollingApp.Repository.UserRepository;


@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= CREATE POLL =================
    public Poll createPoll(Long userId, Poll poll) {

        // Get user from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set user to poll
        poll.setUser(user);
        for (Option option : poll.getOptions()) {
            option.setPoll(poll);
        }

        return pollRepository.save(poll);
    }
}