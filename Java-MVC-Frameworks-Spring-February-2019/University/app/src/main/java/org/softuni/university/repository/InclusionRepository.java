package org.softuni.university.repository;

import org.softuni.university.domain.entities.Inclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InclusionRepository extends JpaRepository<Inclusion, String> {
    List<Inclusion> findAllByUser_Username(String username);
}
