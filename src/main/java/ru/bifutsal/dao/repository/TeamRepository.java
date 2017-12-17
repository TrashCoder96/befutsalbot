package ru.bifutsal.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bifutsal.dao.TeamDto;

/**
 * Created by asus-pc on 17.12.2017.
 */
public interface TeamRepository extends JpaRepository<TeamDto, String> {

    TeamDto findByExternalId(String externalId);

}
