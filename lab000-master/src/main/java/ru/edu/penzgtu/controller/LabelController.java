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
import ru.edu.penzgtu.dto.LabelDto;
import ru.edu.penzgtu.service.LabelService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/label")
@RequiredArgsConstructor
@Tag(name = "Лейблы",description = "Операции над леблами")
public class LabelController {
    private final LabelService labelService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех лейблов", description = "Позволяет выгрузить все лейблы из базы данных"
    )

    @GetMapping
    public ResponseWrapper<List<LabelDto>> findAll(){
        return baseResponseService.wrapSuccessResponse(labelService.findAllLabel());
    }

    @Operation(
            summary = "Получение лейбла по ID", description = "Позволяет выгрузить один лейбл по ID из базы данных"
    )

    @GetMapping("/label/{id}")
    public ResponseWrapper<LabelDto> getById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(labelService.findLabelById(id));
    }

    @Operation(
            summary = "Создать лейбл", description = "Позволяет создать новую запись о лейбле в базе данных"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLabel(@RequestBody @Valid LabelDto labelDto){
        labelService.saveLabel(labelDto);
    }

    @Operation(
            summary = "Обновить данные о лейбле", description = "Позволяет обновить информацию о лейбле в базе данных"
    )

    @PutMapping("/label/")
    public void updateLabel(@RequestBody  @Valid LabelDto labelDto) {
        labelService.updateLabel(labelDto);
    }

    @Operation(
            summary = "Удалить лейбл по ID", description = "Позволяеть удалить лейбл по ID из базы данных"
    )

    @DeleteMapping("/label/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable @Min(0) Long id) {
        labelService.deleteLabelById(id);
    }

}