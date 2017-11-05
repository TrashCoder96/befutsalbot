package ru.bifutsal.aggregator.telegram.command;

import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;
import com.pengrad.telegrambot.model.request.Keyboard;
import org.springframework.stereotype.Component;

/**
 * Created by itimofeev on 09.10.2017.
 */

@Component
public class AnalogView extends TelegramView {

	@Override
	public TelegramDialogStatusEnum getStatus() {
		return TelegramDialogStatusEnum.UNKNOWN;
	}

	@Override
	public String getText() {
		return "Не знаю такой опции";
	}

	@Override
	public Keyboard buildKeyboard() {
		return null;
	}

	@Override
	public boolean check(String commandText, TelegramDialogStatusEnum lastCustomerDialogStatus) {
		return false;
	}

	@Override
	public void execute(String customerId, String command) {
		telegramAggregator.navigateTo(customerId, AnalogView.class);
	}
}
