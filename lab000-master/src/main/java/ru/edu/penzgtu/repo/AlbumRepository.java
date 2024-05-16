package ru.edu.penzgtu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.penzgtu.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByName(String name);
}
