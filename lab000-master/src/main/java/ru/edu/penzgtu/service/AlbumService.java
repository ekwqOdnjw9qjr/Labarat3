package ru.edu.penzgtu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.AlbumDto;
import ru.edu.penzgtu.entity.Album;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.AlbumRepository;
import ru.edu.penzgtu.service.mapper.AlbumMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    public List<AlbumDto> findAllAlbum(){
        log.info("Найдены все альбомы в БД");
        return albumMapper.toListDto(albumRepository.findAll());
    }

    public AlbumDto findAlbumById(Long id ) {
        log.info("Найден альбом с id: " + id);
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Альбом с id " + id + " не найдена"));
        return albumMapper.toDto(album);
    }

    public void saveAlbum(AlbumDto albumDto){
        log.info("Альбом сохранен в БД");
        Album album = albumMapper.toEntity(albumDto);
        album.setLocalDateTime(LocalDateTime.now());
        albumRepository.save(album);
    }

    public void updateAlbum(AlbumDto newAlbum) {
        log.info("Альбом обновлен в БД");
        Album oldAlbum = albumRepository.findById(newAlbum.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Album not found"));
        oldAlbum.setName(newAlbum.getName());
        oldAlbum.setReleaseDate(newAlbum.getReleaseDate());
        oldAlbum.setNumberOfSongs(newAlbum.getNumberOfSongs());
        albumRepository.save(oldAlbum);
    }

    public void deleteAlbumById(Long id ){
        log.info("Альбом удален из БД");
        albumRepository.deleteById(id);
    }
}
