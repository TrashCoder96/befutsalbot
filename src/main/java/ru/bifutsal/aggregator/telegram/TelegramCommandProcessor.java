package ru.bifutsal.aggregator.telegram;

import ru.bifutsal.aggregator.AbstractCustomerInfo;
import ru.bifutsal.aggregator.CommandProcessor;
import ru.bifutsal.aggregator.telegram.command.AnalogView;
import ru.bifutsal.aggregator.telegram.command.TelegramView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.bifutsal.service.CustomerService;
/**
 * Created by itimofeev on 05.10.2017.
 */

@Component
public class TelegramCommandProcessor implements CommandProcessor {

	private static final Logger logger = LoggerFactory.getLogger(TelegramCommandProcessor.class);

    @Autowired
    CustomerService customerService;

	@Autowired
	private List<? extends TelegramView> telegramViews;

	@Autowired
	private AnalogView avalogView;

	@Autowired
	private TelegramAggregator telegramAggregator;

	@Override
	public void acceptCommand(AbstractCustomerInfo info, String command) {
		boolean executed = false;

		TelegramDialogStatusEnum lastCustomerDialogStatus = customerService.getLastTelegramDialogStatus(info.getCustomerId());
		logger.info(String.format("Current command = '%s' previous state = '%s'",command,lastCustomerDialogStatus));

		for (TelegramView tv: telegramViews) {
			if (tv.check(command, lastCustomerDialogStatus) && !(tv instanceof AnalogView)) {

				tv.execute(info.getCustomerId(), command);
				customerService.saveNewTelegramDialogStatus(info.getCustomerId(), tv.getStatus());

				executed = true;
				
			}
		}
		if (!executed) {
			telegramAggregator.navigateTo(info.getCustomerId(), AnalogView.class);
			customerService.saveNewTelegramDialogStatus(info.getCustomerId(), TelegramDialogStatusEnum.UNKNOWN);
		}
	}

}
