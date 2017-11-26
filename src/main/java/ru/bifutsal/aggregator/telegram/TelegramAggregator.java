package ru.bifutsal.aggregator.telegram;

import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bifutsal.aggregator.AbstractCustomerInfo;
import ru.bifutsal.aggregator.telegram.command.TelegramView;
import ru.bifutsal.config.KeyNames;
import ru.bifutsal.dao.repository.KeyRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.SendMessage;

import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SendVideo;
import com.pengrad.telegrambot.request.SendAudio;
import com.pengrad.telegrambot.request.SendDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by itimofeev on 04.10.2017.
 */

@Component
@Transactional
public class TelegramAggregator {

	private static final Logger logger = LoggerFactory.getLogger(TelegramAggregator.class);

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

	public void sendPostToChannel(String text, Map<String,List<Object>> mediaAttachements) {
		//text
		if (text != null) {
			try {
				SendMessage sendMessage = new SendMessage("@" + channelId, text);
				SendResponse response = bot.execute(sendMessage);
				if (response.isOk()) {
					logger.info(String.format("Успешная отправка сообщения. Код: %s. %s", response.errorCode(), response.description()));
				} else {
					logger.error(String.format("Код ошибки: %s. %s", response.errorCode(), response.description()));
				}
			} catch (Exception ex) {
				logger.error("Can't send text with error: "+ex.getMessage());
			}
		}

		//images
		if (mediaAttachements.get("imagesUrls") != null && mediaAttachements.get("imagesUrls").size() > 0) {
			for (Object url : mediaAttachements.get("imagesUrls")) {
				try {
					SendPhoto sendPhoto = new SendPhoto("@" + channelId, (String) url);
					SendResponse response = bot.execute(sendPhoto);
					if (response.isOk()) {
						logger.info(String.format("Успешная отправка сообщения. Код: %s. %s", response.errorCode(), response.description()));
					} else {
						logger.error(String.format("Код ошибки: %s. %s", response.errorCode(), response.description()));
					}
				} catch (Exception ex) {
					logger.error("Can't send image with error: "+ex.getMessage());
				}
			}
		}

		//audios
		if (mediaAttachements.get("audiosUrls") != null && mediaAttachements.get("audiosUrls").size() > 0) {
			for (Object url : mediaAttachements.get("audiosUrls")) {
				try {
					SendAudio sendAudio = new SendAudio("@" + channelId, (String) url);
					SendResponse response = bot.execute(sendAudio);
					if (response.isOk()) {
						logger.info(String.format("Успешная отправка сообщения. Код: %s. %s", response.errorCode(), response.description()));
					} else {
						logger.error(String.format("Код ошибки: %s. %s", response.errorCode(), response.description()));
					}
				} catch (Exception ex) {
					logger.error("Can't send audio with error: "+ex.getMessage());
				}
			}
		}

		//videos
		if (mediaAttachements.get("videosUrls") != null && mediaAttachements.get("videosUrls").size() > 0) {
			for (Object url : mediaAttachements.get("videosUrls")) {
				try {
					//SendVideo sendVideo = new SendVideo("@" + channelId, url);
					SendPhoto sendVideo = new SendPhoto("@" + channelId, (String) url); //пока можем показать только обложку как фото, player приходит null
					SendResponse response = bot.execute(sendVideo);
					if (response.isOk()) {
						logger.info(String.format("Успешная отправка сообщения. Код: %s. %s", response.errorCode(), response.description()));
					} else {
						logger.error(String.format("Код ошибки: %s. %s", response.errorCode(), response.description()));
					}
				} catch (Exception ex) {
					logger.error("Can't send video with error: "+ex.getMessage());
				}
			}
		}
		

		//links
		if (mediaAttachements.get("linksUrls") != null && mediaAttachements.get("linksUrls").size() > 0) {
			for (Object url : mediaAttachements.get("linksUrls")) {
				if (url != null) {
					try {
						SendMessage sendMessage = new SendMessage("@" + channelId, (String) url);
						SendResponse response = bot.execute(sendMessage);
						if (response.isOk()) {
							logger.info(String.format("Успешная отправка сообщения. Код: %s. %s", response.errorCode(), response.description()));
						} else {
							logger.error(String.format("Код ошибки: %s. %s", response.errorCode(), response.description()));
						}
					} catch (Exception ex) {
						logger.error("Can't send link with error: "+ex.getMessage());
					}
				}
			}
		}

		//docs
		if (mediaAttachements.get("docs") != null && mediaAttachements.get("docs").size() > 0) {
			for (Object obj : mediaAttachements.get("docs")) {
				if (obj != null) {
					try {
						ru.bifutsal.channel.ro.DocRo doc = (ru.bifutsal.channel.ro.DocRo) obj;
						SendDocument sendMessage = new SendDocument("@" + channelId, doc.getUrl());
						SendResponse response = bot.execute(sendMessage);
						if (response.isOk()) {
							logger.info(String.format("Успешная отправка сообщения. Код: %s. %s", response.errorCode(), response.description()));
						} else {
							logger.error(String.format("Код ошибки: %s. %s", response.errorCode(), response.description()));
						}
					} catch (Exception ex) {
						logger.error("Can't send link with error: "+ex.getMessage());
					}
				}
			}
		}

	}

}
