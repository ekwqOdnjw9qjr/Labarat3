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
import java.util.List;

@Data
@Builder
@Schema(description = "Информация о лейбле")
public class LabelDto {

    @JsonProperty("id")
    @Schema(description = "ID лейбла в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название лейбла", example = "АнТроп")
    private String name;

    @JsonProperty("foundedDate")
    @Schema(description = "Дата основания лейбла", example = "1996")
    @Positive
    private Long foundedDate;

    @JsonProperty("localDateAndTime")
    @Schema(description = "Дата и время создания лейбла в БД")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("country")
    @NotBlank
    @Schema(description = "Страна в которой расположен лейбл", example = "Россия")
    private String country;

    @JsonProperty("album")
    @Schema(description = "Название альбома выпущенного лейблом")
    private List<String> albums;
}
