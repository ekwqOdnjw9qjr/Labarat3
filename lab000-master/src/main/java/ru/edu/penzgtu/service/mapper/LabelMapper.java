package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.LabelDto;
import ru.edu.penzgtu.entity.Album;
import ru.edu.penzgtu.entity.Label;
import ru.edu.penzgtu.repo.AlbumRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelMapper {
    private final AlbumRepository albumRepository;


    public List<LabelDto> toListDto(List<Label> labels) {
        return labels.stream().map(this::toDto).toList();
    }

    public LabelDto toDto(Label label) {
        return LabelDto.builder()
                .id(label.getId())
                .name(label.getName())
                .country(label.getCountry())
                .foundedDate(label.getFoundedDate())
                .localDateTime(label.getLocalDateTime())
                .albums(label.getAlbums().stream()
                        .map(Album::getName)
                        .toList())
                .build();
    }

    public Label toEntity(LabelDto labelDto) {
        Label label = new Label();

        label.setId(labelDto.getId());
        label.setName(labelDto.getName());
        label.setCountry(labelDto.getCountry());
        label.setLocalDateTime(label.getLocalDateTime());
        label.setFoundedDate(labelDto.getFoundedDate());
        label.setAlbums((Collections.singletonList
                (albumRepository.findByName(String.valueOf(labelDto.getAlbums().stream().toList())))));

        return label;
    }
}
