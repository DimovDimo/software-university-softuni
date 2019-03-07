package sotfuni.exodia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sotfuni.exodia.domain.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, String> {
}
