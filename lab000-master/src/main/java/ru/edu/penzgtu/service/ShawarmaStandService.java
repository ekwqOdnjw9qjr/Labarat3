package ru.edu.penzgtu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.ShawarmaStandDto;
import ru.edu.penzgtu.entity.ShawarmaStand;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.ShawarmaStandRepository;
import ru.edu.penzgtu.service.mapper.ShawarmaStandMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShawarmaStandService {
    private final ShawarmaStandRepository shawarmaStandRepository;
    private final ShawarmaStandMapper shawarmaStandMapper;

    public List<ShawarmaStandDto> findAllShawarmaStands(){
        log.info("Найдены все киоски с шаурмой в базе данных");
        return shawarmaStandMapper.toListDto(shawarmaStandRepository.findAll());
    }

    public ShawarmaStandDto findShawarmaStandById(Long id ) {
        log.info("Найден киоск с шаурмой с id: " + id);
        ShawarmaStand shawarmaStand = shawarmaStandRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"ShawarmaStand with id " + id + " not found"));
        return shawarmaStandMapper.toDto(shawarmaStand);
    }

    public void saveShawarmaStand (ShawarmaStandDto shawarmaStandDto){
        log.info("Киоск с шаурмой сохранен в базе данных");
        ShawarmaStand shawarmaStand = shawarmaStandMapper.toEntity(shawarmaStandDto);
        shawarmaStand.setLocalDateTime(LocalDateTime.now());
        shawarmaStandRepository.save(shawarmaStand);
    }

    public void updateShawarmaStand(ShawarmaStandDto newShawarmaStand) {
        log.info("Киоск с шаурмой обновлен в базе данных");
        ShawarmaStand oldShawarmaStand = shawarmaStandRepository.findById(newShawarmaStand.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"ShawarmaStand not found"));
        oldShawarmaStand.setName(newShawarmaStand.getName());
        oldShawarmaStand.setOwner(newShawarmaStand.getOwner());
        oldShawarmaStand.setAddress(newShawarmaStand.getAddress());
        shawarmaStandRepository.save(oldShawarmaStand);
    }

    public void deleteShawarmaStandById(Long id ){
        log.info("Киоск с шаурмой удален из базы данных");
        shawarmaStandRepository.deleteById(id);
    }
}
