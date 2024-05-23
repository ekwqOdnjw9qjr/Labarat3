package ru.edu.penzgtu.service;

import lombok.extern.slf4j.Slf4j;
import ru.edu.penzgtu.dto.RestaurantDto;
import ru.edu.penzgtu.entity.Restaurant;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.RestaurantRepository;
import ru.edu.penzgtu.service.mapper.RestaurantMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public List<RestaurantDto> findAllRestaurants(){
        log.info("Найдены все рестораны в базе данных");
        return restaurantMapper.toListDto(restaurantRepository.findAll());
    }

    public RestaurantDto findRestaurantById(Long id ) {
        log.info("Найден ресторан с id: " + id);
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Restaurant with id " + id + " not found"));
        return restaurantMapper.toDto(restaurant);
    }

    public void  saveRestaurant(RestaurantDto restaurantDto){
        log.info("Ресторан сохранен в базе данных");
        Restaurant restaurant = restaurantMapper.toEntity(restaurantDto);
        restaurant.setLocalDateTime(LocalDateTime.now());
        restaurantRepository.save(restaurant);
    }

    public void updateRestaurant(RestaurantDto newRestaurant) {
        log.info("Ресторан обновлен в базе данных");
        Restaurant oldRestaurant = restaurantRepository.findById(newRestaurant.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Restaurant not found"));
        oldRestaurant.setName(newRestaurant.getName());
        oldRestaurant.setCountry(newRestaurant.getCountry());
        oldRestaurant.setOpeningDate(newRestaurant.getOpeningDate());
        restaurantRepository.save(oldRestaurant);
    }

    public void deleteRestaurantById(Long id ){
        log.info("Рестораны удален из базы данных");
        restaurantRepository.deleteById(id);
    }
}