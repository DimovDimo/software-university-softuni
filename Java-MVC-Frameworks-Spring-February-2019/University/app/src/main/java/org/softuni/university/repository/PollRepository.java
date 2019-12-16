package org.softuni.university.repository;

import org.softuni.university.domain.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, String> {
    List<Poll> findAllByUser_Username(String username);
}
