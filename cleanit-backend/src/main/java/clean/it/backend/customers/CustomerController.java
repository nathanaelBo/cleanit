package clean.it.backend.customers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("http://localhost:3000")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getByName(@RequestParam String name) {
        if (name.isEmpty()) {
            name = null;
        }
        return ResponseEntity.ok(customerRepository.findByName(name));
    }
}
