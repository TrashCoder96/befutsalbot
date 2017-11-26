package ru.bifutsal.config;

import ru.bifutsal.aggregator.AbstractCustomerInfo;
import ru.bifutsal.aggregator.telegram.TelegramViewProcessor;
import ru.bifutsal.aggregator.telegram.TelegramCustomerInfo;
import ru.bifutsal.dao.repository.CustomerRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.UpdatesListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by itimofeev on 26.11.2017.
 */
@Configuration
public class TelegramConfig {

	@Value("${telegram.key}")
	private String telegramKey;

	@Autowired
	private TelegramViewProcessor telegramViewProcessor;

	@Bean
	@Transactional
	public TelegramBot telegramBot() {
		TelegramBot bot = TelegramBotAdapter.build(telegramKey);
		bot.setUpdatesListener(list -> {
			list.forEach(update -> {
				AbstractCustomerInfo info = new TelegramCustomerInfo();
				info.setFirstname(update.message().from().firstName());
				info.setLastname(update.message().from().lastName());
			    info.setCustomerId(update.message().chat().id().toString());
				TelegramCustomerInfo.setUserInformation(info);
				TelegramCustomerInfo.setUserCommand(update.message().text() != null ? update.message().text() : "");
				telegramViewProcessor.acceptCommand(update.message().text() != null ? update.message().text() : "");
			});
			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});
		return bot;
	}

}
