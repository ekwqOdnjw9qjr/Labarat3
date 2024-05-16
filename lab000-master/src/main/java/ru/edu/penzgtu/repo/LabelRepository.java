package ru.edu.penzgtu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.penzgtu.entity.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {
    Label findByName(String name);
}
