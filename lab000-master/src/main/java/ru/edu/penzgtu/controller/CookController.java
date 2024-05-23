package ru.edu.penzgtu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import ru.edu.penzgtu.baseresponse.BaseResponseService;
import ru.edu.penzgtu.baseresponse.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.dto.CookDto;
import ru.edu.penzgtu.service.CookService;

import java.util.List;
@Validated
@RestController
@RequestMapping("/cook")
@RequiredArgsConstructor
@Tag(name = "Повара",description = "Операции над поварами")
public class CookController {
    private final CookService cookService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех поваров", description = "Позволяет выгрузить всех поваров из БД"
    )

    @GetMapping
    public ResponseWrapper<List<CookDto>> findAllCooks(){
        return baseResponseService.wrapSuccessResponse(cookService.findAllCooks());
    }

    @Operation(
            summary = "Получение повара по ID", description = "Позволяет выгрузить одного повара по ID из БД"
    )

    @GetMapping("/cook/{id}")
    public ResponseWrapper<CookDto> getCookById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(cookService.findCookById(id));
    }

    @Operation(
            summary = "Создать повара", description = "Позволяет создать новую запись о поваре в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCook(@RequestBody @Valid CookDto cookDto){
        cookService.saveCook(cookDto);
    }

    @Operation(
            summary = "Обновить данные о поваре", description = "Позволяет обновить информацию о поваре в БД"
    )

    @PutMapping("/cook/")
    public void updateCook(@RequestBody  @Valid CookDto cookDto) {
        cookService.updateCook(cookDto);
    }

    @Operation(
            summary = "Удалить повара по ID", description = "Позволяеть удалить повара по ID из БД"
    )

    @DeleteMapping("/cook/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCook(@PathVariable @Min(0) Long id) {
        cookService.deleteCookById(id);
    }


 }
