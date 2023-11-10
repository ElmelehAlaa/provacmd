package AlaaElmeleh.U2W2D3.repositories;
import AlaaElmeleh.U2W2D3.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogsRepository extends JpaRepository<Blog,Long> {
    Optional<Blog> findById(long id);
}
