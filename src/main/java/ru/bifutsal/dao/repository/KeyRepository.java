package ru.bifutsal.dao.repository;

import ru.bifutsal.dao.KeyDto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by itimofeev on 01.10.2017.
 */
public interface KeyRepository extends JpaRepository<KeyDto, String> {

}
