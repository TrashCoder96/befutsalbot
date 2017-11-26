package ru.bifutsal.aggregator.telegram.view;

import com.pengrad.telegrambot.model.request.Keyboard;

import java.util.List;

/**
 * Created by itimofeev on 26.11.2017.
 */

public class AnalogTelegramView extends TelegramView {

	@Override
	public String getText() {
		return "Неизвестная команда";
	}

	@Override
	public Keyboard getKeyboard() {
		return null;
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
