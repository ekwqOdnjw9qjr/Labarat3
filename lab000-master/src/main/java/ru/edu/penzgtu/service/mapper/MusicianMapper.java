package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.MusicianDto;
import ru.edu.penzgtu.entity.Musician;
import ru.edu.penzgtu.entity.Song;
import ru.edu.penzgtu.repo.SongRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicianMapper {
    private final SongRepository songRepository;

    public List<MusicianDto> toListDto(List<Musician> musicians) {
        return musicians.stream().map(this::toDto).toList();
    }

    public MusicianDto toDto(Musician musician) {
        return MusicianDto.builder()
                .id(musician.getId())
                .name(musician.getName())
                .age(musician.getAge())
                .nationality(musician.getNationality())
                .localDateTime(musician.getLocalDateTime())
                .songs(musician.getSongs().stream()
                        .map(Song::getName)
                        .toList())
                .build();
    }

    public Musician toEntity(MusicianDto musicianDto) {
        Musician musician = new Musician();

        musician.setId(musicianDto.getId());
        musician.setName(musicianDto.getName());
        musician.setAge(musicianDto.getAge());
        musician.setNationality(musicianDto.getNationality());
        musician.setLocalDateTime(musicianDto.getLocalDateTime());
        musician.setSongs((Collections.singletonList
                (songRepository.findByName(String.valueOf(musicianDto.getSongs().stream().toList())))));
        return musician;
    }
}

