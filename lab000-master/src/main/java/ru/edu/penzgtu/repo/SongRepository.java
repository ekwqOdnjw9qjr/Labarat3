package ru.edu.penzgtu.repo;

import ru.edu.penzgtu.entity.Album;
import ru.edu.penzgtu.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SongRepository extends JpaRepository<Song, Long>{
    Song findByName(String name);
}