package ru.edu.penzgtu.service;

import lombok.extern.slf4j.Slf4j;
import ru.edu.penzgtu.service.mapper.CookMapper;
import ru.edu.penzgtu.dto.CookDto;
import ru.edu.penzgtu.entity.Cook;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.CookRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CookService {
    private final CookRepository cookRepository;
    private final CookMapper cookMapper;

    public List<CookDto> findAllCooks(){
        log.info("Найдены все повара в базе данных");
        return cookMapper.toListDto(cookRepository.findAll());
    }

    public CookDto findCookById(Long id)  {
        log.info("Найден повар с id: " + id);
        Cook cook = cookRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Cook with id " + id + " not found"));
        return cookMapper.toDto(cook);
    }

    public void saveCook(CookDto cookDto){
        log.info("Повар сохранен в базе данных");
        Cook cook = cookMapper.toEntity(cookDto);
        cook.setLocalDateTime(LocalDateTime.now());
        cookRepository.save(cook);
    }

    public void updateCook(CookDto newCook) {
        log.info("Повар обновлен в базе данных");
        Cook oldCook = cookRepository.findById(newCook.getId())
                .orElseThrow(() ->new PenzGtuException(ErrorType.NOT_FOUND,"Cook not found"));
        oldCook.setName(newCook.getName());
        oldCook.setAge(newCook.getAge());
        oldCook.setNationality(newCook.getNationality());
        cookRepository.save(oldCook);

    }

    public void deleteCookById(Long id ) {
        log.info("Повара удален из базы данных");
        cookRepository.deleteById(id);
    }

}
