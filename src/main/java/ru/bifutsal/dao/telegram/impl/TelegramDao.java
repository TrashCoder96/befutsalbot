package ru.bifutsal.dao.telegram.impl;

import ru.bifutsal.aggregator.telegram.TelegramCustomerInfo;
import ru.bifutsal.aggregator.telegram.view.TelegramView;
import ru.bifutsal.dao.telegram.ITelegramDao;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by itimofeev on 26.11.2017.
 */
@Component
public class TelegramDao implements ITelegramDao {

	private static final Logger logger = LoggerFactory.getLogger(TelegramDao.class);

	@Autowired
	private TelegramBot telegramBot;

	@Override
	public void sendView(TelegramView view) {
		SendMessage sendMessage = new SendMessage(TelegramCustomerInfo.getUserInformation().getCustomerId(), view.getText());
		if (view.getKeyboard() != null) {
			sendMessage.replyMarkup(view.getKeyboard());
		}
		logger.info(String.format("Send json to telegram: CustomerId = %s",
				TelegramCustomerInfo.getUserInformation().getCustomerId()));
		if (telegramBot.execute(sendMessage).isOk())
			logger.info("Success");
	}
}
