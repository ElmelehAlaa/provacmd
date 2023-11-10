package AlaaElmeleh.U2W2D3.repositories;
import AlaaElmeleh.U2W2D3.entities.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthorsRepository extends JpaRepository<Author,Long> {
    Optional<Author> findById(long id);
    Optional<Author> findByEmail(String email);
}
