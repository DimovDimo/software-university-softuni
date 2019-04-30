package org.softuni.university.repository;

import org.softuni.university.domain.entities.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampRepository extends JpaRepository<Camp, String> {
    List<Camp> findAllByUser_Username(String username);
}
