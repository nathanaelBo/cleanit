package clean.it.backend.textiles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/textile-types")
@CrossOrigin("http://localhost:3000")
public class TextileTypeController {
    private final TextileTypeRepository textileTypeRepository;

    public TextileTypeController(TextileTypeRepository textileTypeRepository) {
        this.textileTypeRepository = textileTypeRepository;
    }

    @GetMapping
    public ResponseEntity<List<TextileType>> getAll() {
        return ResponseEntity.ok(textileTypeRepository.findAll());
    }
}
