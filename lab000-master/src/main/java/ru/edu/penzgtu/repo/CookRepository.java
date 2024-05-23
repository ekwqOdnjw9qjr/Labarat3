package ru.edu.penzgtu.repo;
import ru.edu.penzgtu.entity.Cook;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CookRepository extends JpaRepository<Cook, Long> {
    Cook findByName(String name);
}

