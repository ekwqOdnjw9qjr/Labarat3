package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Schema(description = "Информация о ресторане")
public class RestaurantDto {

    @JsonProperty("id")
    @Schema(description = "ID ресторана в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название ресторана", example = "Claude Monet")
    private String name;

    @JsonProperty("country")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Страна в которой находится ресторан может содержать только буквы")
    @Schema(description = "Страна в которой находится ресторан ", example = "Россия")
    private String country;

    @JsonProperty("openingDate")
    @Positive
    @Schema(description = "Дата открытия ресторана", example = "2007")
    private Long openingDate;

    @JsonProperty("localDateAndTime")
    @Schema(description = "Дата и время создания ресторана в Базе данных")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("cook")
    @Schema(description = "Имя повара который работает в ресторане")
    private List<String> cooks;
}
