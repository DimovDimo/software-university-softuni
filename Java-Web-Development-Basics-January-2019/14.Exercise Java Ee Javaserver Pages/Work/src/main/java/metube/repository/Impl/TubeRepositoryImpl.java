package metube.repository.Impl;

import metube.domain.entities.Tube;
import metube.repository.Interfaces.TubeRepository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metube")
                .createEntityManager();
    }

    @Override
    public Optional<Tube> findByName(String name) {
        return Optional.of(this.entityManager
                .createQuery("" +
                        "SELECT t " +
                        "FROM tubes AS t " +
                        "WHERE t.name =:name", Tube.class)
                .setParameter("name", name)
                .getSingleResult());
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public List<Tube> findAll() {
        return this.entityManager
                .createQuery("" +
                        "SELECT t " +
                        "FROM tubes AS t ", Tube.class)
                .getResultList();
    }

    @Override
    public Optional<Tube> findById(String id) {
        return Optional.of(this.entityManager
                .createQuery("" +
                        "SELECT t " +
                        "FROM tubes AS t " +
                        "WHERE t.id =:id", Tube.class)
                .setParameter("id", id)
                .getSingleResult());
    }
}
