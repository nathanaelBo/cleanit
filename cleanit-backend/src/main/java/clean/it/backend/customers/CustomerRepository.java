package clean.it.backend.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE LOWER(CONCAT(c.firstName, ' ', c.lastName)) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Customer> findByName(String name);
}
