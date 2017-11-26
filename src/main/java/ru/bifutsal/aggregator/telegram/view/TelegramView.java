package ru.bifutsal.aggregator.telegram.view;

import com.pengrad.telegrambot.model.request.Keyboard;

import java.util.List;

/**
 * Created by itimofeev on 26.11.2017.
 */
public abstract class TelegramView {

	public abstract String getText();

	public abstract Keyboard getKeyboard();

	public abstract List<String> imageUrls();

	public abstract List<String> docUrls();

}
