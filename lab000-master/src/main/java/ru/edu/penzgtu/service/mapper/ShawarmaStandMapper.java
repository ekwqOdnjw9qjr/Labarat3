package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.ShawarmaStandDto;
import ru.edu.penzgtu.entity.Shawarma;
import ru.edu.penzgtu.entity.ShawarmaStand;
import ru.edu.penzgtu.repo.ShawarmaRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShawarmaStandMapper {
    private final ShawarmaRepository shawarmaRepository;


    public List<ShawarmaStandDto> toListDto(List<ShawarmaStand> shawarmaStands) {
        return shawarmaStands.stream().map(this::toDto).toList();
    }

    public ShawarmaStandDto toDto(ShawarmaStand shawarmaStand) {
        return ShawarmaStandDto.builder()
                .id(shawarmaStand.getId())
                .name(shawarmaStand.getName())
                .address(shawarmaStand.getAddress())
                .owner(shawarmaStand.getOwner())
                .localDateTime(shawarmaStand.getLocalDateTime())
                .shawarma(shawarmaStand.getShawarmas().stream()
                        .map(Shawarma::getName)
                        .toList())
                .build();
    }

    public ShawarmaStand toEntity(ShawarmaStandDto shawarmaStandDto) {
        ShawarmaStand shawarmaStand = new ShawarmaStand();

        shawarmaStand.setId(shawarmaStandDto.getId());
        shawarmaStand.setName(shawarmaStandDto.getName());
        shawarmaStand.setAddress(shawarmaStandDto.getAddress());
        shawarmaStand.setOwner(shawarmaStandDto.getOwner());
        shawarmaStand.setLocalDateTime(shawarmaStand.getLocalDateTime());
        shawarmaStand.setShawarmas((Collections.singletonList
                (shawarmaRepository.findByName(String.valueOf(shawarmaStandDto.getShawarma().stream().toList())))));

        return shawarmaStand;
    }
}
