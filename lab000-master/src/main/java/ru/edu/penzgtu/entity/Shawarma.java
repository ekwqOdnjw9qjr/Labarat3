package ru.edu.penzgtu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "shawarma")
public class Shawarma {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(name = "price")
    @Positive
    private Long price;

    @Column(name = "size")
    @Positive
    private Long size;

    @Column(name = "local_date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "shawarmaStand_id")
    private ShawarmaStand shawarmaStand;

}
