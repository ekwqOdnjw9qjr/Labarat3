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
import ru.edu.penzgtu.dto.MusicianDto;
import ru.edu.penzgtu.service.MusicianService;

import java.util.List;
@Validated
@RestController
@RequestMapping("/musician")
@RequiredArgsConstructor
@Tag(name = "Музыканты",description = "Операции над музыкантами")
public class MusicianController {
    private final MusicianService musicianService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех музыкантов", description = "Позволяет выгрузить всех музыкантов из базы данных"
    )

    @GetMapping
    public ResponseWrapper<List<MusicianDto>> findAll(){
        return baseResponseService.wrapSuccessResponse(musicianService.findAllMusician());
    }

    @Operation(
            summary = "Получение музыкантов по ID", description = "Позволяет выгрузить одного музыканта по ID из базы данных"
    )

    @GetMapping("/musician/{id}")
    public ResponseWrapper<MusicianDto> getById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(musicianService.findMusicianById(id));
    }

    @Operation(
            summary = "Создать музыканта", description = "Позволяет создать новую запись о музыканте в базе данных"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMusician(@RequestBody @Valid MusicianDto musician){
        musicianService.saveMusician(musician);
    }

    @Operation(
            summary = "Обновить данные о музыканте", description = "Позволяет обновить информацию о музыканте в базе данных"
    )

    @PutMapping("/musician/")
    public void updateMusician(@RequestBody  @Valid MusicianDto musician) {
        musicianService.updateMusician(musician);
    }

    @Operation(
            summary = "Удалить музыканта по ID", description = "Позволяеть удалить музыканта по ID из базы данных"
    )

    @DeleteMapping("/musician/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMusician(@PathVariable @Min(0) Long id) {
        musicianService.deleteMusicianById(id);
    }


 }
