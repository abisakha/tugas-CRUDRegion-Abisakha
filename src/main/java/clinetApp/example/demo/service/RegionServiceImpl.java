package clinetApp.example.demo.service;

import clinetApp.example.demo.model.Region;
import clinetApp.example.demo.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repo;

    @Override
    public Page<Region> findAll(Pageable pageable) { return repo.findAll(pageable); }

    @Override
    public Optional<Region> findById(Long id) { return repo.findById(id); }

    @Override
    public Region save(Region r) { return repo.save(r); }

    @Override
    public void deleteById(Long id) { repo.deleteById(id); }
}