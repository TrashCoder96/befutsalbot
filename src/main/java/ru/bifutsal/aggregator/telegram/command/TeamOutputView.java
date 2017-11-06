package ru.bifutsal.aggregator.telegram.command;

import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.bifutsal.aggregator.telegram.TelegramDialogCommandConstants;
/**
 * Created by vsharanin on 06.11.2017.
 */
@Component
public class TeamOutputView extends TelegramView {

	private List<KeyboardButton> startButtons = new ArrayList<>(2);

	@PostConstruct
	private void post() {
		startButtons.add(new KeyboardButton(TelegramDialogCommandConstants.ADD));
		startButtons.add(new KeyboardButton(TelegramDialogCommandConstants.NEXT));
	}

	@Override
	public TelegramDialogStatusEnum getStatus() {
		return TelegramDialogStatusEnum.LISTENING_KEYBOARD_BUTTON_AFTER_SAVING_TEAM;
	}

	@Override
	public String getText() {
		return String.format("Команда  добавлена в список. Хотите добавить еще команды?");
	}

	@Override
	public Keyboard buildKeyboard() {
		return new	ReplyKeyboardMarkup(new KeyboardButton[][] { { startButtons.get(0) }, { startButtons.get(1) } })
					.selective(true);
	}

	@Override
	public boolean check(String commandText, TelegramDialogStatusEnum lastCustomerDialogStatus) {
		return !commandText.equals(TelegramDialogCommandConstants.NEXT) && lastCustomerDialogStatus.toString().equals(TelegramDialogStatusEnum.LISTENING_KEYBOARD_BUTTON_ON_TEAM.toString());
	}

	@Override
	public void execute(String customerId, String command) {
		telegramAggregator.navigateTo(customerId, TeamOutputView.class);
	}
}
