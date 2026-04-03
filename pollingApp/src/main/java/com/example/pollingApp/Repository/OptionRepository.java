package com.example.pollingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pollingApp.Entity.Option;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByPollId(Long pollId);
}
