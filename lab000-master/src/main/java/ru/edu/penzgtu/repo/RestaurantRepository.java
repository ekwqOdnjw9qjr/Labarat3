package ru.edu.penzgtu.repo;

import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
    Restaurant findByName(String name);
}