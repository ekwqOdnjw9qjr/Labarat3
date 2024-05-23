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


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о шаурме")
public class ShawarmaDto {

    @JsonProperty("id")
    @Schema(description = "ID шаурмы в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название шаурмы", example = "Фирменная")
    private String name;

    @JsonProperty("price")
    @Schema(description = "Цена шаурмы", example = "200")
    @Positive
    private Long price;

    @JsonProperty("size")
    @Schema(description = "Размер шаурмы", example = "21")
    @Positive
    private Long size;

    @JsonProperty("localDateAndTime")
    @Schema(description = "Дата и время создания шаурмы в Базе данных")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("shawarmaStand")
    @Schema(description = "Киоск где продается шаурма")
    private String shawarmaStand;
}

