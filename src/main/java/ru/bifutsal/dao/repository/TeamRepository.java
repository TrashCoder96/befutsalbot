package ru.bifutsal.dao.repository;

import ru.bifutsal.dao.TeamDto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by itimofeev on 21.10.2017.
 */
public interface TeamRepository extends JpaRepository<TeamDto, Long> {
}
