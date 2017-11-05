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
public class TeamCustomKeybordView extends TelegramView {

	@PostConstruct
	private void post() {
	}

	@Override
	public TelegramDialogStatusEnum getStatus() {
		return TelegramDialogStatusEnum.LISTENING_CUSTOM_KEYBOARD_ON_TEAM;
	}

	@Override
	public String getText() {
		return String.format("Введите название команды, чьи матчи выводить в расписании");
	}

	@Override
	public Keyboard buildKeyboard() {
		return null;
	}

	@Override
	public boolean check(String commandText, TelegramDialogStatusEnum lastCustomerDialogStatus) {
		return commandText.equals(TelegramDialogCommandConstants.TEAM);
	}

	@Override
	public void execute(String customerId, String command) {
		telegramAggregator.navigateTo(customerId, TeamCustomKeybordView.class);
	}
}
