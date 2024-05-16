package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о музыканте")
public class MusicianDto {

    @JsonProperty("id")
    @Schema(description = "ID музыканта в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Имя музыканта", example = "Виктор Цой")
    private String name;

    @JsonProperty("age")
    @Schema(description = "Возраст музыканта", example = "12")
    @Positive
    private Long age;

    @JsonProperty("nationality")
    @NotBlank
    @Schema(description = "Национальность музыканта",example = "Русский")
    private String nationality;

    @JsonProperty("localDateAndTime")
    @NotNull
    @Schema(description = "Дата и время создания музыканта в БД")
    private LocalDateTime localDateTime;

    @JsonProperty("songs")
    @Schema(description = "Названия песен музыканта")
    private List<String> songs;
}
