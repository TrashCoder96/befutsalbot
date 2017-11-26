package ru.bifutsal.aggregator.telegram.view;

import ru.bifutsal.aggregator.telegram.TelegramCustomerInfo;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import java.util.List;

/**
 * Created by itimofeev on 26.11.2017.
 */
public class HelloTelegramView extends TelegramView {

	@Override
	public String getText() {
		return String.format("Привет, %s, вот список доступных команд", TelegramCustomerInfo.getUserInformation().getFirstname());
	}

	@Override
	public Keyboard getKeyboard() {
		KeyboardButton[][] keyboardButtons = new KeyboardButton[1][2];
		keyboardButtons[0][0] = new KeyboardButton("Команды");
		keyboardButtons[0][1] = new KeyboardButton("Лиги");
		return new ReplyKeyboardMarkup(keyboardButtons)
				.selective(true);
	}

	@Override
	public List<String> imageUrls() {
		return null;
	}

	@Override
	public List<String> docUrls() {
		return null;
	}
}
