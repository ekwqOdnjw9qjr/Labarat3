package ru.edu.penzgtu.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "cook")
public class Cook {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(name = "age")
    @Positive
    private Long age;

    @Column(name = "nationality",nullable = false,length = 100)
    private String nationality;

    @Column(name = "local_date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}