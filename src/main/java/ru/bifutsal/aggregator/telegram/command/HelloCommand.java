package ru.bifutsal.aggregator.telegram.command;

import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by itimofeev on 08.10.2017.
 */
@Component
public class HelloCommand extends TelegramCommand {

	@Override
	public String getName() {
		return "hello";
	}

	@Override
	public Map<String, String> check(String command) {
		if (command.equals("hello")) {
			return new HashMap<>();
		} else {
			return null;
		}
	}

	@Override
	public void execute(Map<String, String> parameters) {
		String message = String.format("Hello, %s", parameters.get("customerFirstname"));
		telegramAggregator.sendMessage(parameters.get("customerId"), message);
		saveStatus(parameters.get("customerId"), TelegramDialogStatusEnum.HELLO);
	}
}
