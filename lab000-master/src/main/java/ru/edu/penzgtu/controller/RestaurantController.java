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
import ru.edu.penzgtu.dto.RestaurantDto;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.service.RestaurantService;

import java.util.List;
@Validated
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@Tag(name = "Рестораны",description = "Операции над ресторанами")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final BaseResponseService baseResponseService;


    @Operation(
            summary = "Получение всех ресторанов", description = "Позволяет выгрузить все рестораны из БД"
    )

    @GetMapping
    public ResponseWrapper<List<RestaurantDto>> findAllRestaurants() {
        return baseResponseService.wrapSuccessResponse(restaurantService.findAllRestaurants());
    }

    @Operation(
            summary = "Получение ресторана по ID", description = "Позволяет выгрузить один ресторан по ID из БД"
    )

    @GetMapping("/restaurant/{id}")
    public ResponseWrapper<RestaurantDto> getRestaurantById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(restaurantService.findRestaurantById(id));
    }

    @Operation(
            summary = "Создать ресторан ", description = "Позволяет создать новую запись о ресторане в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurant(@RequestBody @Valid RestaurantDto restaurantDto) {
        restaurantService.saveRestaurant(restaurantDto);
    }


    @Operation(
            summary = "Обновить данные о ресторане ", description = "Позволяет обновить информацию о ресторане в БД"
    )
    @PutMapping("/restaurant/")
    public void updateRestaurant( @RequestBody @Valid RestaurantDto restaurantDto ) {
        restaurantService.updateRestaurant(restaurantDto);
    }

    @Operation(
            summary = "Удалить ресторан по ID ", description = "Позволяет удалить ресторан по ID из БД"
    )
    @DeleteMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable @Min(0) Long id) {
        restaurantService.deleteRestaurantById(id);
    }
}
