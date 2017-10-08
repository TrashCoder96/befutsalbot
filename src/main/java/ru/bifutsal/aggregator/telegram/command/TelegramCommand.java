package ru.bifutsal.aggregator.telegram.command;

import ru.bifutsal.aggregator.TelegramAggregator;
import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;
import ru.bifutsal.dao.CustomerDto;
import ru.bifutsal.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by itimofeev on 06.10.2017.
 */
public abstract class TelegramCommand {

	@Autowired
	protected TelegramAggregator telegramAggregator;

	@Autowired
	protected CustomerRepository customerRepository;

	public abstract String getName();

	public abstract Map<String, String> check(String command);

	public abstract void execute(Map<String, String> parameters);

	@Transactional
	public void saveStatus(String customerId, TelegramDialogStatusEnum status) {
		CustomerDto customerDto = customerRepository.findByExternalId(customerId);
		if (customerDto == null) {
			customerDto = new CustomerDto();
			customerDto.setExternalId(customerId);
		}
		customerDto.setDialogStatus(status);
		customerRepository.save(customerDto);
	}

}
