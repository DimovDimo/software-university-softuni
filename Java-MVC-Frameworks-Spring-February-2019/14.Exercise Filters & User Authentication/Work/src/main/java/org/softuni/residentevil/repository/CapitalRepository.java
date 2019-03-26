package org.softuni.residentevil.repository;

import org.softuni.residentevil.domain.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {

    @Query("SELECT capital FROM org.softuni.residentevil.domain.entities.Capital AS capital ORDER BY capital.name")
    List<Capital> findAllOrderByName();

    Optional<Capital> findById(String id);
}
