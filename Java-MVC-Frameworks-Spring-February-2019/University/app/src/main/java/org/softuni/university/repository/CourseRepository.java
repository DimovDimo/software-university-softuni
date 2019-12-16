package org.softuni.university.repository;

import org.softuni.university.domain.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
     Optional<Course> findByName(String name);
}
