package ru.edu.penzgtu.repo;
import ru.edu.penzgtu.entity.Album;
import ru.edu.penzgtu.entity.Musician;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {
    Musician findByName(String name);
}

