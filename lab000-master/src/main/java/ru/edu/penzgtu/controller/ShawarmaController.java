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
import ru.edu.penzgtu.dto.ShawarmaDto;
import ru.edu.penzgtu.service.ShawarmaService;


import java.util.List;

@Validated
@RestController
@RequestMapping("/shawarma")
@RequiredArgsConstructor
@Tag(name = "Шаурма",description = "Операции над шаурмой")
public class ShawarmaController {
    private final ShawarmaService shawarmaService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всей шаурмы", description = "Позволяет выгрузить всю шаурму из БД"
    )

    @GetMapping
    public ResponseWrapper<List<ShawarmaDto>> findAllShawarma(){
        return baseResponseService.wrapSuccessResponse(shawarmaService.findAllShawarma());
    }

    @Operation(
            summary = "Получение шаурмы по ID", description = "Позволяет выгрузить одну шаурму по ID из БД"
    )

    @GetMapping("/shawarma/{id}")
    public ResponseWrapper<ShawarmaDto> getShawarmaById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(shawarmaService.findShawarmaById(id));
    }

    @Operation(
            summary = "Создать шаурму", description = "Позволяет создать новую запись о шаурме в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createShawarma(@RequestBody @Valid ShawarmaDto shawarmaDto){
        shawarmaService.saveShawarma(shawarmaDto);
    }

    @Operation(
            summary = "Обновить данные о шаурме", description = "Позволяет обновить информацию о шаурме в БД"
    )

    @PutMapping("/shawarma/")
    public void updateShawarma(@RequestBody  @Valid ShawarmaDto shawarmaDto) {
        shawarmaService.updateShawarma(shawarmaDto);
    }

    @Operation(
            summary = "Удалить шаурму по ID", description = "Позволяеть удалить шаурму по ID из БД"
    )

    @DeleteMapping("/shawarma/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShawarma(@PathVariable @Min(0) Long id) {
        shawarmaService.deleteShawarmaById(id);
    }


}
