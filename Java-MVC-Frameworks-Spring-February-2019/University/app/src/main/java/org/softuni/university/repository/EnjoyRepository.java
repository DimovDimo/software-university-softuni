package org.softuni.university.repository;

import org.softuni.university.domain.entities.Enjoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnjoyRepository extends JpaRepository<Enjoy, String> {
    List<Enjoy> findAllByUser_Username(String username);
}
