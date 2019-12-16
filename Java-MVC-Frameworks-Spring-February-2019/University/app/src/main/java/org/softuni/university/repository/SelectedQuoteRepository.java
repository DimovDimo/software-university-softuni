package org.softuni.university.repository;

import org.softuni.university.domain.entities.SelectedQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedQuoteRepository extends JpaRepository<SelectedQuote, String> {
}
