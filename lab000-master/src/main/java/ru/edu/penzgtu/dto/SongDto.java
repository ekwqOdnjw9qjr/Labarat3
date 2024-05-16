package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Информация о песне")
public class SongDto {

    @JsonProperty("id")
    @Schema(description = "ID песни в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название песни", example = "Группа крови на рукаве")
    private String name;

    @JsonProperty("language")
    @NotBlank
    @Schema(description = "Язык на котором написана песня", example = "Русский")
    private String language;

    @JsonProperty("genre")
    @NotBlank
    @Schema(description = "Жанр песни", example = "Русский")
    private String genre;

    @JsonProperty("localDateAndTime")
    @Schema(description = "Дата и время создания песни в БД")
    @NotNull
    private LocalDateTime localDateTime;


    @JsonProperty("musicians")
    @Schema(description = "Имя испольнителя песни")
    private String musician;
}
