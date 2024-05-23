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


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о поваре")
public class CookDto {

    @JsonProperty("id")
    @Schema(description = "ID повара в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя повара может содержать только буквы")
    @Schema(description = "Имя повара", example = "Виктор")
    private String name;

    @JsonProperty("age")
    @Schema(description = "Возраст повара", example = "50")
    @Positive
    private Long age;

    @JsonProperty("nationality")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Национальность повара может содержать только буквы")
    @Schema(description = "Национальность повара",example = "Русский")
    private String nationality;

    @JsonProperty("localDateAndTime")
    @NotNull
    @Schema(description = "Дата и время создания повара в Базе данных")
    private LocalDateTime localDateTime;

    @JsonProperty("restaurant")
    @Schema(description = "Название ресторана где работает повар")
    private String restaurant;
}
