package clinetApp.example.demo.repository;

import clinetApp.example.demo.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByNameIgnoreCase(String name);
}