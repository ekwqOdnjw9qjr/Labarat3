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
import ru.edu.penzgtu.dto.AlbumDto;
import ru.edu.penzgtu.service.AlbumService;


import java.util.List;

@Validated
@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
@Tag(name = "Альбомы",description = "Операции над альбомами")
public class AlbumController {
    private final AlbumService albumService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех альбомов", description = "Позволяет выгрузить все альбомы из базы данных"
    )

    @GetMapping
    public ResponseWrapper<List<AlbumDto>> findAll(){
        return baseResponseService.wrapSuccessResponse(albumService.findAllAlbum());
    }

    @Operation(
            summary = "Получение альбома по ID", description = "Позволяет выгрузить один альбом по ID из базы данных"
    )

    @GetMapping("/album/{id}")
    public ResponseWrapper<AlbumDto> getById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(albumService.findAlbumById(id));
    }

    @Operation(
            summary = "Создать альбом", description = "Позволяет создать новую запись о альбоме в базе данных"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAlbum(@RequestBody @Valid AlbumDto albumDto){
        albumService.saveAlbum(albumDto);
    }

    @Operation(
            summary = "Обновить данные о альбоме", description = "Позволяет обновить информацию о альбоме в базе данных"
    )

    @PutMapping("/album/")
    public void updateAlbum(@RequestBody  @Valid AlbumDto albumDto) {
        albumService.updateAlbum(albumDto);
    }

    @Operation(
            summary = "Удалить альбом по ID", description = "Позволяеть удалить альбои по ID из базы данных"
    )

    @DeleteMapping("/album/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable @Min(0) Long id) {
        albumService.deleteAlbumById(id);
    }


}
