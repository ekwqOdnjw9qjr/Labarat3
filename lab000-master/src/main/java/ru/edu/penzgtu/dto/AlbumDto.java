package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import ru.edu.penzgtu.entity.Label;

import java.time.LocalDateTime;


@Data
@Builder
@Schema(description = "Информация о альбоме")
public class AlbumDto {

    @JsonProperty("id")
    @Schema(description = "ID альбома в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название альбома", example = "Чёрный альбом")
    private String name;

    @JsonProperty("releaseDate")
    @Schema(description = "Дата выпуска альбома", example = "1996")
    @Positive
    private Long releaseDate;

    @JsonProperty("localDateAndTime")
    @Schema(description = "Дата и время создания альбома в БД")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("numberOfSongs")
    @Schema(description = "Количество песен в альбоме")
    private Long numberOfSongs;

    @JsonProperty("label")
    @Schema(description = "Название лейбла")
    private String label;
}

