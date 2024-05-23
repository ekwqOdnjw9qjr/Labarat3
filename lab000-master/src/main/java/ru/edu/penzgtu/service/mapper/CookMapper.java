package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.CookDto;
import ru.edu.penzgtu.entity.Cook;
import ru.edu.penzgtu.repo.RestaurantRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CookMapper {
    private final RestaurantRepository restaurantRepository;

    public List<CookDto> toListDto(List<Cook> cooks) {
        return cooks.stream().map(this::toDto).toList();
    }

    public CookDto toDto(Cook cook) {
        String restaurantName = cook.getRestaurant() != null ? cook.getRestaurant().getName() : null;



        return CookDto.builder()
                .id(cook.getId())
                .name(cook.getName())
                .age(cook.getAge())
                .nationality(cook.getNationality())
                .localDateTime(cook.getLocalDateTime())
                .restaurant(restaurantName)
                .build();
    }

    public Cook toEntity(CookDto cookDto) {
        Cook cook = new Cook();

        cook.setId(cookDto.getId());
        cook.setName(cookDto.getName());
        cook.setNationality(cookDto.getNationality());
        cook.setAge(cookDto.getAge());
        cook.setLocalDateTime(cookDto.getLocalDateTime());
        cook.setRestaurant(restaurantRepository.findByName(cookDto.getRestaurant()));


        return cook;
    }
}

