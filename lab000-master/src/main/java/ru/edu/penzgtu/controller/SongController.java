package ru.edu.penzgtu.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import ru.edu.penzgtu.baseresponse.BaseResponseService;
import ru.edu.penzgtu.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.dto.SongDto;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.service.SongService;

import java.util.List;
@Validated
@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
@Tag(name = "Песни",description = "Операции над песнями")
public class SongController {


    private final SongService songService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех песен", description = "Позволяет выгрузить все песни из базы данных"
    )

    @GetMapping
    public ResponseWrapper<List<SongDto>> findAllSong() {
        return baseResponseService.wrapSuccessResponse(songService.findAllSong());
    }

    @Operation(
            summary = "Получение песен по ID", description = "Позволяет выгрузить одну песню по ID из базы данных"
    )


    @GetMapping("/song/{id}")
    public ResponseWrapper<SongDto> getSongById(@PathVariable @Min(0) Long id) throws PenzGtuException {
        return baseResponseService.wrapSuccessResponse(songService.findSongById(id));
    }

    @Operation(
            summary = "Создать песню", description = "Позволяет создать новую запись о песне в базе данных"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void  createSong(@RequestBody @Valid SongDto song) {
        songService.saveSong(song);
    }


    @Operation(
            summary = "Обновить данные о песне ", description = "Позволяет обновить информацию о песне в базе данных"
    )
    @PutMapping("/song/")
    public void updateSong( @RequestBody @Valid SongDto song ) {
        songService.updateSong(song);
    }

    @Operation(
            summary = "Удалить песню по ID ", description = "Позволяет удалить песню по ID из базы данных"
    )
    @DeleteMapping("/song/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable @Min(0) Long id) {
        songService.deleteSongById(id);
    }
}
