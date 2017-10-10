package ru.bifutsal.aggregator.telegram;

import ru.bifutsal.aggregator.AbstractCustomerInfo;
import ru.bifutsal.aggregator.CommandProcessor;
import ru.bifutsal.aggregator.telegram.command.AnalogView;
import ru.bifutsal.aggregator.telegram.command.TelegramView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by itimofeev on 05.10.2017.
 */

@Component
public class TelegramCommandProcessor implements CommandProcessor {

	@Autowired
	private List<? extends TelegramView> telegramViews;

	@Autowired
	private AnalogView avalogView;

	@Autowired
	private TelegramAggregator telegramAggregator;

	@Override
	public void acceptCommand(AbstractCustomerInfo info, String command) {
		boolean executed = false;
		for (TelegramView tv: telegramViews) {
			if (tv.check(command) && !(tv instanceof AnalogView)) {
				tv.execute(info.getCustomerId(), command);
				executed = true;
			}
		}
		if (!executed) {
			telegramAggregator.navigateTo(info.getCustomerId(), AnalogView.class);
		}
	}

}
