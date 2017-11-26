package ru.bifutsal.service;

import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bifutsal.dao.CustomerDto;
import ru.bifutsal.dao.repository.CustomerRepository;
/**
 * Created by vsharanin on 05.11.2017.
 */
@Service("CustomerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	protected CustomerRepository customerRepository;

	@Override
	public void saveNewTelegramDialogStatus(String customerId, TelegramDialogStatusEnum status) {
		CustomerDto customerDto = customerRepository.findByExternalId(customerId);
		if (customerDto == null) {
			customerDto = new CustomerDto();
			customerDto.setExternalId(customerId);
			logger.info(String.format("Try save new customer with id '%s'", customerId));
		}
		customerDto.setDialogStatus(status);
		logger.info(String.format("Try set dialog status '%s' for customer with id '%s'", status, customerId));
	}

	@Override
	public TelegramDialogStatusEnum getLastTelegramDialogStatus(String customerId) {
		CustomerDto customerDto = customerRepository.findByExternalId(customerId);
		return (customerDto != null) ? customerDto.getDialogStatus() : null;
	}
}
