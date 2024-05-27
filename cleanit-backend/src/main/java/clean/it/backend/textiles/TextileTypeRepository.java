package clean.it.backend.textiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextileTypeRepository extends JpaRepository<TextileType, Long> {
}
