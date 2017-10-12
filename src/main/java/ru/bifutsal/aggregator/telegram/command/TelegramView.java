package ru.bifutsal.aggregator.telegram.command;

import ru.bifutsal.aggregator.telegram.TelegramAggregator;
import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;
import ru.bifutsal.dao.CustomerDto;
import ru.bifutsal.dao.repository.CustomerRepository;
import com.pengrad.telegrambot.model.request.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by itimofeev on 06.10.2017.
 */
public abstract class TelegramView {

	@Autowired
	protected TelegramAggregator telegramAggregator;

	@Autowired
	protected CustomerRepository customerRepository;

	public abstract TelegramDialogStatusEnum getStatus();

	public abstract String getText();

	public abstract Keyboard buildKeyboard();

	public abstract boolean check(String commandText);

	public abstract void execute(String customerId, String command);

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
