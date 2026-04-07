package com.example.pollingApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pollingApp.Entity.Option;
import com.example.pollingApp.Entity.Poll;
import com.example.pollingApp.Entity.User;
import com.example.pollingApp.Entity.Vote;
import com.example.pollingApp.Repository.OptionRepository;
import com.example.pollingApp.Repository.PollRepository;
import com.example.pollingApp.Repository.UserRepository;
import com.example.pollingApp.Repository.VoteRepository;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= CREATE POLL =================
    public Poll createPoll(Long userId, Poll poll) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        poll.setUser(user);

        if (poll.getOptions() != null) {
            for (Option option : poll.getOptions()) {
                option.setPoll(poll);
            }
        }

        return pollRepository.save(poll);
    }

    // ================= GET ALL POLLS =================
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private VoteRepository voteRepository;

    // ================= VOTE =================
    public void vote(Long optionId, Long userId) {

        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new RuntimeException("Option not found"));

        Long pollId = option.getPoll().getId();

        if (voteRepository.existsByUserIdAndPollId(userId, pollId)) {
            throw new RuntimeException("You already voted for this poll");
        }

        option.setVotes(option.getVotes() + 1);
        optionRepository.save(option);

        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setPollId(pollId);

        voteRepository.save(vote);
    }
}