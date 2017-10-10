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

/**
 * Created by itimofeev on 08.10.2017.
 */
@Component
public class HelloView extends TelegramView {

	private List<KeyboardButton> helloButtons = new ArrayList<>();

	@PostConstruct
	private void post() {
		helloButtons.add(new KeyboardButton("hello1"));
		helloButtons.add(new KeyboardButton("hello2"));
		helloButtons.add(new KeyboardButton("hello3"));
	}

	@Override
	public TelegramDialogStatusEnum getStatus() {
		return TelegramDialogStatusEnum.HELLO;
	}

	@Override
	public String getText() {
		return "hello?";
	}

	@Override
	public Keyboard buildKeyboard() {
		return new ReplyKeyboardMarkup(new KeyboardButton[][] { { helloButtons.get(0) }, { helloButtons.get(1) }, { helloButtons.get(2) } })
				.selective(true);
	}

	@Override
	public boolean check(String commandText) {
		return commandText.equals("hello1") || commandText.equals("hello2") || commandText.equals("hello3");
	}

	@Override
	public void execute(String customerId, String command) {
		telegramAggregator.navigateTo(customerId, HelloView.class);
	}
}
