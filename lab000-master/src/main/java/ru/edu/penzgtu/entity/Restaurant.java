package ru.edu.penzgtu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(name = "country",nullable = false,length = 100)
    private String country;

    @Column(name = "openingDate")
    @Positive
    private Long openingDate;

    @Column(name = "local_date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Cook> cook;
}
