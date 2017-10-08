package ru.bifutsal.dao.repository;

import ru.bifutsal.dao.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by itimofeev on 08.10.2017.
 */
public interface CustomerRepository extends JpaRepository<CustomerDto, Long> {

	CustomerDto findByExternalId(String externalId);

}
