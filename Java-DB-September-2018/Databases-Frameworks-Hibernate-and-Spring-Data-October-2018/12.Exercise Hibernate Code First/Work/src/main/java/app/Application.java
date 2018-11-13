package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        Runnable engine = new Engine(entityManager, scanner);

        engine.run();

        entityManager.close();
        entityManagerFactory.close();
    }
}
