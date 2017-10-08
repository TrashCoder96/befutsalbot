package ru.bifutsal.aggregator.telegram.command;

import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * Created by itimofeev on 08.10.2017.
 */
@Component
public class AnalogCommand extends TelegramCommand {

	@Override
	public String getName() {
		return "analog";
	}

	@Override
	public Map<String, String> check(String command) {
		return null;
	}

	@Override
	public void execute(Map<String, String> parameters) {
		String message = String.format("Прости, %s, я таких слов не знаю :(", parameters.get("customerFirstname"));
		telegramAggregator.sendMessage(parameters.get("customerId"), message);
	}
}
