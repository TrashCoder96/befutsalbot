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
 * Created by vsharanin on 31.10.2017.
 */
@Component
public class TeamKeyboardButtonView extends TelegramView {

	private List<KeyboardButton> startButtons = new ArrayList<>(3);

	@PostConstruct
	private void post() {
		startButtons.add(new KeyboardButton("Газмяс"));
		startButtons.add(new KeyboardButton("Зенит"));
		startButtons.add(new KeyboardButton("Спартак"));
	}

	@Override
	public TelegramDialogStatusEnum getStatus() {
		return TelegramDialogStatusEnum.LISTENING_KEYBOARD_BUTTON_ON_TEAM;
	}

	@Override
	public String getText() {
		return String.format("Первые 10 команд");
	}

	@Override
	public Keyboard buildKeyboard() {
		return new	ReplyKeyboardMarkup(new KeyboardButton[][] { { startButtons.get(0) }, { startButtons.get(1) }, { startButtons.get(2) } })
					.selective(true);
	}

	@Override
	public boolean check(String commandText, TelegramDialogStatusEnum lastCustomerDialogStatus) {
		return lastCustomerDialogStatus.toString().equals(TelegramDialogStatusEnum.LISTENING_CUSTOM_KEYBOARD_ON_TEAM.toString())
				|| (commandText.equals(TelegramDialogCommandConstants.NEXT) && lastCustomerDialogStatus.toString().equals(TelegramDialogStatusEnum.LISTENING_KEYBOARD_BUTTON_ON_TEAM.toString()));
	}

	@Override
	public void execute(String customerId, String command) {
		telegramAggregator.navigateTo(customerId, TeamKeyboardButtonView.class);
	}
}
