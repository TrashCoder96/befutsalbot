package ru.bifutsal.aggregator.telegram;

import ru.bifutsal.aggregator.AbstractCustomerInfo;
import ru.bifutsal.aggregator.telegram.command.TelegramView;
import ru.bifutsal.config.KeyNames;
import ru.bifutsal.dao.repository.KeyRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.SendMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by itimofeev on 04.10.2017.
 */

@Component
@Transactional
public class TelegramAggregator {

	private TelegramBot bot;

	@Autowired
	private KeyRepository keyRepository;

	@Autowired
	private TelegramCommandProcessor telegramCommandProcessor;

	@Autowired
	private List<? extends TelegramView> telegramViews;

	@Value("${telegram.channelId}")
	private String channelId;

	@PostConstruct
	private void postInit() {
		if (!checkKeyOnExists(KeyNames.TELEGRAMBOTKEY))
			return;

		bot = TelegramBotAdapter.build(keyRepository.findOne(KeyNames.TELEGRAMBOTKEY).getValue());
		bot.setUpdatesListener(list -> {
			list.forEach(update -> {
				AbstractCustomerInfo info = new TelegramCustomerInfo();
				info.setFirstname(update.message().from().firstName());
				info.setLastname(update.message().from().lastName());
				info.setCustomerId(update.message().chat().id().toString());
				TelegramCustomerInfo.setThreadLocalScope((TelegramCustomerInfo) info);
				telegramCommandProcessor.acceptCommand(info, update.message().text() != null ? update.message().text() : "");
			});
			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});
	}

	public void reloadBot() {
		postInit();
	}

	public String getCurrentKeyValue() {
		if (!checkKeyOnExists(KeyNames.TELEGRAMBOTKEY))
			return null;
		return keyRepository.findOne(KeyNames.TELEGRAMBOTKEY).getValue();
	}

	public void navigateTo(String customerId, Class<? extends TelegramView> viewClass) {
		TelegramView telegramView = telegramViews.stream().filter(v -> v.getClass().getSuperclass().equals(viewClass)).findFirst().get();
		SendMessage request = null;
		if (telegramView.buildKeyboard() != null) {
			request = new SendMessage(customerId, telegramView.getText()).replyMarkup(telegramView.buildKeyboard());
		} else {
			request = new SendMessage(customerId, telegramView.getText()).replyMarkup(new ReplyKeyboardRemove());
		}
		bot.execute(request);
	}

	public void sendMessage(String customerId, String text) {
		SendMessage request = new SendMessage(customerId, text);
		 bot.execute(request);
	}

	private boolean checkKeyOnExists(String keyName){
		return keyRepository.findOne(keyName) != null;
	}

	public void sendPostToChannel(String text) {
		SendMessage request = new SendMessage("@" + channelId, text);
		bot.execute(request);
	}

}
