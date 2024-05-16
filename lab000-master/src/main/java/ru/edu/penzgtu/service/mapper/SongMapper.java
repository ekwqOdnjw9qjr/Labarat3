package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.MusicianDto;
import ru.edu.penzgtu.dto.SongDto;
import ru.edu.penzgtu.entity.Musician;
import ru.edu.penzgtu.entity.Song;
import ru.edu.penzgtu.repo.MusicianRepository;
import ru.edu.penzgtu.repo.SongRepository;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SongMapper {
    private final MusicianRepository musicianRepository;

    public List<SongDto> toListDto(List<Song> songs) {
        return songs.stream().map(this::toDto).toList();
    }

    public SongDto toDto(Song song) {
        String  musicianName = song.getMusician() != null ? song.getMusician().getName() : null;

        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .language(song.getLanguage())
                .genre(song.getGenre())
                .localDateTime(song.getLocalDateTime())
                .musician(musicianName)
                .build();

    }

    public Song toEntity(SongDto songDto) {
        Song song = new Song();

        song.setId(songDto.getId());
        song.setName(songDto.getName());
        song.setGenre(songDto.getGenre());
        song.setLanguage(songDto.getLanguage());
        song.setLocalDateTime(songDto.getLocalDateTime());
        song.setMusician(musicianRepository.findByName(songDto.getMusician()));

        return song;
    }

}
