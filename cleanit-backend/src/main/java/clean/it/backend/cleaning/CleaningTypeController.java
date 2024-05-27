package clean.it.backend.cleaning;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cleaning-types")
@CrossOrigin("http://localhost:3000")
public class CleaningTypeController {
    private final CleaningTypeRepository cleaningTypeRepository;

    public CleaningTypeController(CleaningTypeRepository cleaningTypeRepository) {
        this.cleaningTypeRepository = cleaningTypeRepository;
    }

    @GetMapping
    public ResponseEntity<List<CleaningType>> getAll() {
        return ResponseEntity.ok(cleaningTypeRepository.findAll());
    }
}
