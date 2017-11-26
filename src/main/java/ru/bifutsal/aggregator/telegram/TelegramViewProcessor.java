package ru.bifutsal.aggregator.telegram;

import ru.bifutsal.aggregator.telegram.view.TelegramView;
import ru.bifutsal.dao.CustomerDto;
import ru.bifutsal.dao.repository.CustomerRepository;
import ru.bifutsal.dao.telegram.impl.TelegramDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by itimofeev on 05.10.2017.
 */

@Component
public class TelegramViewProcessor {

	private static final Logger logger = LoggerFactory.getLogger(TelegramViewProcessor.class);

	@Autowired
	private TelegramRouter telegramRouter;

	@Autowired
	private TelegramDao telegramDao;

	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public void acceptCommand(String command) {
		CustomerDto customerDto = customerRepository.findByExternalId(TelegramCustomerInfo.getUserInformation().getCustomerId());
		if (customerDto == null) {
			customerDto = new CustomerDto();
			customerDto.setCurrentPage(0);
			customerDto.setExternalId(TelegramCustomerInfo.getUserInformation().getCustomerId());
			customerRepository.save(customerDto);
		}
		TelegramView view = telegramRouter.getView(command);
		telegramDao.sendView(view);
	}

}
