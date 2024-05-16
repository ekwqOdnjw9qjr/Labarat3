package ru.edu.penzgtu.service;

import lombok.extern.slf4j.Slf4j;
import ru.edu.penzgtu.service.mapper.MusicianMapper;
import ru.edu.penzgtu.dto.MusicianDto;
import ru.edu.penzgtu.entity.Musician;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.MusicianRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MusicianService {
    private final MusicianRepository musicianRepository;
    private final MusicianMapper musicianMapper;

    public List<MusicianDto> findAllMusician(){
        log.info("Найдены все музыканты в БД");
        return musicianMapper.toListDto(musicianRepository.findAll());
    }

    public MusicianDto findMusicianById(Long id)  {
        log.info("Найден музыкант с id: " + id);
        Musician musician = musicianRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Музыкант с id " + id + " не найден"));
        return musicianMapper.toDto(musician);
    }

    public void saveMusician(MusicianDto musicianDto){
        log.info("Музыкант сохранен в БД");
        Musician musician = musicianMapper.toEntity(musicianDto);
        musician.setLocalDateTime(LocalDateTime.now());
        musicianRepository.save(musician);
    }

    public void updateMusician(MusicianDto newMusician) {
        log.info("Музыкант обновлен в БД");
        Musician oldMusician = musicianRepository.findById(newMusician.getId())
                .orElseThrow(() ->new PenzGtuException(ErrorType.NOT_FOUND,"Musician not found"));
        oldMusician.setName(newMusician.getName());
        oldMusician.setAge(newMusician.getAge());
        oldMusician.setNationality(newMusician.getNationality());
        musicianRepository.save(oldMusician);

    }

    public void deleteMusicianById(Long id ) {
        log.info("Музыкант удален из БД");
        musicianRepository.deleteById(id);
    }

}
