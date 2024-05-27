package clean.it.backend.cleaning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CleaningTypeRepository extends JpaRepository<CleaningType, Long> {
}
