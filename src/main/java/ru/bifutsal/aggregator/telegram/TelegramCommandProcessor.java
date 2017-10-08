package ru.bifutsal.aggregator.telegram;

import ru.bifutsal.aggregator.AbstractCustomerInfo;
import ru.bifutsal.aggregator.CommandProcessor;
import ru.bifutsal.aggregator.telegram.command.AnalogCommand;
import ru.bifutsal.aggregator.telegram.command.TelegramCommand;
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
	private List<? extends TelegramCommand> telegramCommands;

	@Autowired
	private AnalogCommand analogCommand;

	@Override
	public void acceptCommand(AbstractCustomerInfo info, String command) {
		boolean executed = false;
		for (TelegramCommand tc: telegramCommands) {
			Map<String, String> parameters = tc.check(command);
			if (parameters != null) {
				parameters.put("customerFirstname", info.getFirstname());
				parameters.put("customerId", info.getCustomerId());
				tc.execute(parameters);
				executed = true;
			}
		}
		if (!executed) {
			Map<String, String> parameters = new HashMap<>();
			parameters.put("customerFirstname", info.getFirstname());
			parameters.put("customerId", info.getCustomerId());
			analogCommand.execute(parameters);
		}
	}

}
