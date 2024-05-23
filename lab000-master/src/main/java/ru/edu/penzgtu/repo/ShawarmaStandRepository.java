package ru.edu.penzgtu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.entity.ShawarmaStand;
@Repository
public interface ShawarmaStandRepository extends JpaRepository<ShawarmaStand, Long> {
    ShawarmaStand findByName(String name);
}
