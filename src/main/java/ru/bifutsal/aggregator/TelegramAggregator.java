package ru.bifutsal.aggregator;

import ru.bifutsal.config.KeyNames;
import ru.bifutsal.dao.repository.KeyRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.UpdatesListener;
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

	@PostConstruct
	private void postInit() {
		bot = TelegramBotAdapter.build(keyRepository.findOne(KeyNames.TELEGRAMBOTKEY).getValue());
		bot.setUpdatesListener(list -> {
			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});
	}

	public void reloadBot(String newKey) {
		keyRepository.findOne(KeyNames.TELEGRAMBOTKEY).setValue(newKey);
		postInit();
	}

	public String getCurrentKeyValue() {
		return keyRepository.findOne(KeyNames.TELEGRAMBOTKEY).getValue();
	}



}
