package app;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine implements Runnable{
    private final EntityManager entityManager;
    private Scanner scanner;

    public Engine(EntityManager entityManager, Scanner scanner) {
        this.entityManager = entityManager;
        this.scanner = scanner;
    }

    private void inTransaction(EntityManager entityManager, Runnable runnable) {
        this.entityManager.getTransaction().begin();
        runnable.run();
        this.entityManager.getTransaction().commit();
    }

    public void run() {
        /**
         * Go to folder entities.
         */
    }


}
