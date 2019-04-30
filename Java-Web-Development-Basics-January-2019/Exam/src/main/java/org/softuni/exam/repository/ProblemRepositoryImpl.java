package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.Problem;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ProblemRepositoryImpl extends BaseRepository implements ProblemRepository {
    @Inject
    public ProblemRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Problem save(Problem problem) {
        return this.executeTransaction((em) -> {
            em.persist(problem);
            return problem;
        });
    }

    @Override
    public List<Problem> findAll() {
        return this.executeTransaction((em) -> {
            return em.createNativeQuery("SELECT * FROM problems", Problem.class)
                    .getResultList();
        });
    }

    @Override
    public Problem findById(String id) {
        return this.executeTransaction((em) -> {
            return (Problem) em.createNativeQuery("SELECT * FROM problems WHERE id = '" + id + "'", Problem.class)
                    .getSingleResult();
        });
    }
}
