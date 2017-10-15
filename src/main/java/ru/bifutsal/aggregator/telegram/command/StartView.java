package ru.bifutsal.aggregator.telegram.command;

import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.bifutsal.aggregator.telegram.TelegramCustomerInfo;
import ru.bifutsal.aggregator.telegram.TelegramCommands;
/**
 * Created by vsharanin on 15.10.2017.
 */
@Component
public class StartView extends TelegramView {

	private List<KeyboardButton> startButtons = new ArrayList<>(3);

	@PostConstruct
	private void post() {
		startButtons.add(new KeyboardButton(TelegramCommands.TEAM));
		startButtons.add(new KeyboardButton(TelegramCommands.LIGA));
		startButtons.add(new KeyboardButton(TelegramCommands.SKIP));
	}

	@Override
	public TelegramDialogStatusEnum getStatus() {
		return TelegramDialogStatusEnum.HELLO;
	}

	@Override
	public String getText() {
		return TelegramCustomerInfo.getThreadLocalScope().getFirstname() + ", хотите установить настройки расписания матчей?";
	}

	@Override
	public Keyboard buildKeyboard() {
		return new ReplyKeyboardMarkup(new KeyboardButton[][] { { startButtons.get(0) }, { startButtons.get(1) }, { startButtons.get(2) } })
				.selective(true);
	}

	@Override
	public boolean check(String commandText) {
		return commandText.equals(TelegramCommands.START);
	}

	@Override
	public void execute(String customerId, String command) {
		telegramAggregator.navigateTo(customerId, StartView.class);
	}
}
