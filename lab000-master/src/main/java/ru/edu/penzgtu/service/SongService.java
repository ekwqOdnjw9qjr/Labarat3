package ru.edu.penzgtu.service;

import lombok.extern.slf4j.Slf4j;
import ru.edu.penzgtu.dto.SongDto;
import ru.edu.penzgtu.entity.Song;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.SongRepository;
import ru.edu.penzgtu.service.mapper.SongMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public List<SongDto> findAllSong(){
        log.info("Найдены все песни в БД");
        return songMapper.toListDto(songRepository.findAll());
    }

    public SongDto findSongById(Long id ) {
        log.info("Найдена песня с id: " + id);
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Песня с id " + id + " не найдена"));
        return songMapper.toDto(song);
    }

    public void  saveSong(SongDto songDto){
        log.info("Песня сохранена в БД");
        Song song = songMapper.toEntity(songDto);
        song.setLocalDateTime(LocalDateTime.now());
        songRepository.save(song);
    }

    public void updateSong(SongDto newSong) {
        log.info("Песня обновлена в БД");
        Song oldSong = songRepository.findById(newSong.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Song not found"));
        oldSong.setName(newSong.getName());
        oldSong.setLanguage(newSong.getLanguage());
        oldSong.setGenre(newSong.getGenre());
        songRepository.save(oldSong);
    }

    public void deleteSongById(Long id ){
        log.info("Песня удалена из БД");
        songRepository.deleteById(id);
    }
}