package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.ShawarmaDto;
import ru.edu.penzgtu.entity.Shawarma;
import ru.edu.penzgtu.repo.ShawarmaStandRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShawarmaMapper {
    private final ShawarmaStandRepository shawarmaStandRepository;


    public List<ShawarmaDto> toListDto(List<Shawarma> shawarmas) {
        return shawarmas.stream().map(this::toDto).toList();
    }

    public ShawarmaDto toDto(Shawarma shawarma) {
        String  shawarmaName = shawarma.getShawarmaStand() != null ? shawarma.getShawarmaStand().getName() : null;




        return ShawarmaDto.builder()
                .id(shawarma.getId())
                .name(shawarma.getName())
                .price(shawarma.getPrice())
                .size(shawarma.getSize())
                .localDateTime(shawarma.getLocalDateTime())
                .shawarmaStand(shawarmaName)
                .build();
    }

    public Shawarma toEntity(ShawarmaDto shawarmaDto) {
        Shawarma shawarma = new Shawarma();

        shawarma.setId(shawarmaDto.getId());
        shawarma.setName(shawarmaDto.getName());
        shawarma.setSize(shawarmaDto.getSize());
        shawarma.setPrice(shawarmaDto.getPrice());
        shawarma.setLocalDateTime(shawarmaDto.getLocalDateTime());
        shawarma.setShawarmaStand(shawarmaStandRepository.findByName(shawarmaDto.getShawarmaStand()));


        return shawarma;
    }
}
