package ru.bifutsal.aggregator;

import ru.bifutsal.aggregator.telegram.TelegramCommandProcessor;
import ru.bifutsal.aggregator.telegram.TelegramCustomerInfo;
import ru.bifutsal.config.KeyNames;
import ru.bifutsal.dao.repository.KeyRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	private void postInit() {
		bot = TelegramBotAdapter.build(keyRepository.findOne(KeyNames.TELEGRAMBOTKEY).getValue());
		bot.setUpdatesListener(list -> {
			list.forEach(update -> {
				AbstractCustomerInfo info = new TelegramCustomerInfo();
				info.setFirstname(update.message().from().firstName());
				info.setLastname(update.message().from().lastName());
				info.setCustomerId(update.message().chat().id().toString());
				telegramCommandProcessor.acceptCommand(info, update.message().text());
			});
			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});
	}

	public void reloadBot() {
		postInit();
	}

	public String getCurrentKeyValue() {
		return keyRepository.findOne(KeyNames.TELEGRAMBOTKEY).getValue();
	}

	public void sendMessage(String customerId, String text) {
		SendMessage request = new SendMessage(customerId, text);
		SendResponse sendResponse = bot.execute(request);
	}



}
