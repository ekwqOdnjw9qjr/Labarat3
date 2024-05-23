package ru.edu.penzgtu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.ShawarmaDto;
import ru.edu.penzgtu.entity.Shawarma;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.ShawarmaRepository;
import ru.edu.penzgtu.service.mapper.ShawarmaMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShawarmaService {
    private final ShawarmaRepository shawarmaRepository;
    private final ShawarmaMapper shawarmaMapper;

    public List<ShawarmaDto> findAllShawarma(){
        log.info("Найдена вся шаурма в базе данных");
        return shawarmaMapper.toListDto(shawarmaRepository.findAll());
    }

    public ShawarmaDto findShawarmaById(Long id ) {
        log.info("Найдена шаурма с id: " + id);
        Shawarma shawarma = shawarmaRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Shawarma with id " + id + " not found"));
        return shawarmaMapper.toDto(shawarma);
    }

    public void saveShawarma(ShawarmaDto shawarmaDto){
        log.info("Шаурма сохранена в базе данных");
        Shawarma shawarma = shawarmaMapper.toEntity(shawarmaDto);
        shawarma.setLocalDateTime(LocalDateTime.now());
        shawarmaRepository.save(shawarma);
    }

    public void updateShawarma(ShawarmaDto newShawarma) {
        log.info("Шаурма обновлена в базе данных");
        Shawarma oldShawarma = shawarmaRepository.findById(newShawarma.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Shawarma not found"));
        oldShawarma.setName(newShawarma.getName());
        oldShawarma.setPrice(newShawarma.getPrice());
        oldShawarma.setSize(newShawarma.getSize());
        shawarmaRepository.save(oldShawarma);
    }

    public void deleteShawarmaById(Long id ){
        log.info("Шаурма удалена из базы данных");
        shawarmaRepository.deleteById(id);
    }
}
