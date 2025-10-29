package clinetApp.example.demo.service;

import clinetApp.example.demo.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RegionService {
    Page<Region> findAll(Pageable pageable);
    Optional<Region> findById(Long id);
    Region save(Region r);
    void deleteById(Long id);
}
