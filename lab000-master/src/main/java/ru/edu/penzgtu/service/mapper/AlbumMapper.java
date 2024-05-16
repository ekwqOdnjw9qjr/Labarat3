package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.AlbumDto;
import ru.edu.penzgtu.entity.Album;
import ru.edu.penzgtu.repo.LabelRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlbumMapper {
    private final LabelRepository labelRepository;


    public List<AlbumDto> toListDto(List<Album> albums) {
        return albums.stream().map(this::toDto).toList();
    }

    public AlbumDto toDto(Album album) {
        String  labelName = album.getLabel() != null ? album.getLabel().getName() : null;




        return AlbumDto.builder()
                .id(album.getId())
                .name(album.getName())
                .numberOfSongs(album.getNumberOfSongs())
                .releaseDate(album.getReleaseDate())
                .localDateTime(album.getLocalDateTime())
                .label(labelName)
                .build();
    }

    public Album toEntity(AlbumDto albumDto) {
        Album album = new Album();

        album.setId(albumDto.getId());
        album.setName(albumDto.getName());
        album.setReleaseDate(albumDto.getReleaseDate());
        album.setNumberOfSongs(albumDto.getNumberOfSongs());
        album.setLocalDateTime(albumDto.getLocalDateTime());
        album.setLabel(labelRepository.findByName(albumDto.getLabel()));


        return album;
    }
}
