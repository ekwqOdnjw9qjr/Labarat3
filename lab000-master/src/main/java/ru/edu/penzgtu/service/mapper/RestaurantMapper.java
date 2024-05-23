package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.RestaurantDto;
import ru.edu.penzgtu.entity.Cook;
import ru.edu.penzgtu.entity.Restaurant;
import ru.edu.penzgtu.repo.CookRepository;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RestaurantMapper {
    private final CookRepository cookRepository;



    public List<RestaurantDto> toListDto(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::toDto).toList();
    }

    public RestaurantDto toDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .openingDate(restaurant.getOpeningDate())
                .country(restaurant.getCountry())
                .localDateTime(restaurant.getLocalDateTime())
                .cooks(restaurant.getCook().stream()
                        .map(Cook::getName)
                        .toList())
                .build();
    }

    public Restaurant toEntity(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();

        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setCountry(restaurantDto.getCountry());
        restaurant.setOpeningDate(restaurantDto.getOpeningDate());
        restaurant.setLocalDateTime(restaurantDto.getLocalDateTime());
        restaurant.setCook((Collections.singletonList
                (cookRepository.findByName(String.valueOf(restaurantDto.getCooks().stream().toList())))));


        return restaurant;
    }

}
