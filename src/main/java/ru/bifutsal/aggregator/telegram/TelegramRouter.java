package ru.bifutsal.aggregator.telegram;

import ru.bifutsal.aggregator.telegram.view.AnalogTelegramView;
import ru.bifutsal.aggregator.telegram.view.HelloTelegramView;
import ru.bifutsal.aggregator.telegram.view.TeamsTelegramView;
import ru.bifutsal.aggregator.telegram.view.TelegramView;
import org.springframework.stereotype.Component;

/**
 * Created by itimofeev on 26.11.2017.
 */
@Component
public class TelegramRouter {

	public TelegramView getView(String command) {
		if ("/start".equals(command)) {
			return new HelloTelegramView();
		} else if ("Команды".equals(command) || "Предыдущая страница".equals(command) || "Следующая страница".endsWith(command)) {
			return new TeamsTelegramView();
		} else {
			return new AnalogTelegramView();
		}
	}

}
