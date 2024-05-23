package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Schema(description = "Информация о киоске с шаурмой")
public class ShawarmaStandDto {

    @JsonProperty("id")
    @Schema(description = "ID киоска с шаурмой в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название киоска с шаурмой может содержать только буквы")
    @Schema(description = "Название киоска с шаурмой", example = "Шаурмафия")
    private String name;

    @JsonProperty("address")
    @Schema(description = "Адрес киоска с шаурмой", example = "ул.Лермонтова")
    private String address;

    @JsonProperty("owner")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя владельца киоска с шаурмой может содержать только буквы")
    @Schema(description = "Имя владельца киоска с шаурмой", example = "Арсен")
    private String owner;

    @JsonProperty("localDateAndTime")
    @Schema(description = "Дата и время создания киоска с шаурмой в Базе данных")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("shawarma")
    @Schema(description = "Шаурма которя продается в киоске ")
    private List<String> shawarma;
}
