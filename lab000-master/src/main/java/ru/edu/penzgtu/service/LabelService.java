package ru.edu.penzgtu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.LabelDto;
import ru.edu.penzgtu.entity.Label;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.LabelRepository;
import ru.edu.penzgtu.service.mapper.LabelMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;

    public List<LabelDto> findAllLabel(){
        log.info("Найдены все лейблы в БД");
        return labelMapper.toListDto(labelRepository.findAll());
    }

    public LabelDto findLabelById(Long id ) {
        log.info("Найден лейбл с id: " + id);
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Лейбл с id " + id + " не найден"));
        return labelMapper.toDto(label);
    }

    public void saveLabel (LabelDto labelDto){
        log.info("Лейбл сохранен в БД");
        Label label = labelMapper.toEntity(labelDto);
        label.setLocalDateTime(LocalDateTime.now());
        labelRepository.save(label);
    }

    public void updateLabel(LabelDto newLabel) {
        log.info("Лейбл обновлен в БД");
        Label oldLabel = labelRepository.findById(newLabel.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Label not found"));
        oldLabel.setName(newLabel.getName());
        oldLabel.setFoundedDate(newLabel.getFoundedDate());
        oldLabel.setCountry(newLabel.getCountry());
        labelRepository.save(oldLabel);
    }

    public void deleteLabelById(Long id ){
        log.info("Лейбл удален из БД");
        labelRepository.deleteById(id);
    }
}
