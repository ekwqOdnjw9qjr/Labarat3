package ru.edu.penzgtu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.entity.Shawarma;
@Repository
public interface ShawarmaRepository extends JpaRepository<Shawarma, Long> {
    Shawarma findByName(String name);
}
