package ru.edu.penzgtu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.baseresponse.BaseResponseService;
import ru.edu.penzgtu.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.dto.ShawarmaStandDto;
import ru.edu.penzgtu.service.ShawarmaStandService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/shawarmaStand")
@RequiredArgsConstructor
@Tag(name = "Киоски с шаурмой",description = "Операции над киосками с шаурмой")
public class ShawarmaStandController {
    private final ShawarmaStandService shawarmaStandService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех киосков с шаурмой",
            description = "Позволяет выгрузить все киоски с шаурмой из БД"
    )

    @GetMapping
    public ResponseWrapper<List<ShawarmaStandDto>> findAllShawarmaStands(){
        return baseResponseService.wrapSuccessResponse(shawarmaStandService.findAllShawarmaStands());
    }

    @Operation(
            summary = "Получение киоска с шаурмой по ID",
            description = "Позволяет выгрузить один киоск с шаурмой по ID из БД"
    )

    @GetMapping("/shawarmaStand/{id}")
    public ResponseWrapper<ShawarmaStandDto> getShawarmaStandById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(shawarmaStandService.findShawarmaStandById(id));
    }

    @Operation(
            summary = "Создать киоск с шаурмой",
            description = "Позволяет создать новую запись о киоске с шаурмой в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createShawarmaStand(@RequestBody @Valid ShawarmaStandDto shawarmaStandDto){
        shawarmaStandService.saveShawarmaStand(shawarmaStandDto);
    }

    @Operation(
            summary = "Обновить данные о киоске с шаурмой",
            description = "Позволяет обновить информацию о киоске с шаурмой в БД"
    )

    @PutMapping("/shawarmaStand/")
    public void updateShawarmaStand(@RequestBody  @Valid ShawarmaStandDto shawarmaStandDto) {
        shawarmaStandService.updateShawarmaStand(shawarmaStandDto);
    }

    @Operation(
            summary = "Удалить киоск с шаурмой по ID",
            description = "Позволяеть удалить киоск с шаурмой по ID из БД"
    )

    @DeleteMapping("/shawarmaStand/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShawarmaStand(@PathVariable @Min(0) Long id) {
        shawarmaStandService.deleteShawarmaStandById(id);
    }

}