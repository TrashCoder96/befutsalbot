package ru.bifutsal.channel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bifutsal.aggregator.telegram.TelegramAggregator;
import ru.bifutsal.channel.ro.EventRo;
import ru.bifutsal.channel.ro.EventTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by itimofeev on 24.10.2017.
 */

@RestController
public class NewPostController {

	@Value("${vk.confirmationString}")
	private String confirmationString;

	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger logger = LoggerFactory.getLogger(NewPostController.class);

	@Autowired
	private TelegramAggregator telegramAggregator;

	@RequestMapping(value = "/receive", method = RequestMethod.POST)
	public String receive(@RequestBody EventRo request) throws JsonProcessingException {

		logger.info(String.format("Поступило событие с Callback API: %s", objectMapper.writeValueAsString(request)));

		if (request.getType().equals(EventTypeEnum.CONFIRMATION.value())) {
			return confirmationString;
		} else if (request.getType().equals(EventTypeEnum.WALL_POST_NEW.value())) {
			//отправляем пост в канал, только если

			Map<String,List<String>> mediaAttachements = new HashMap<String,List<String>>(4);

			//prepare images
			List<String> imagesUrls = request.getObject().getAttachments().stream()
					.map(attachmentRo -> attachmentRo.getPhoto().getPhoto_807()).collect(Collectors.toList());
			mediaAttachements.put("imagesUrls",imagesUrls);

			//prepare audios
			List<String> audiosUrls = request.getObject().getAttachments().stream()
					.map(attachmentRo -> attachmentRo.getAudio().getUrl()).collect(Collectors.toList());
			mediaAttachements.put("audiosUrls", audiosUrls);

			//prepare videos
			List<String> videosUrls = request.getObject().getAttachments().stream()
					.map(attachmentRo -> attachmentRo.getVideo().getPhoto_130()).collect(Collectors.toList()); // getPlayer приходит пустым..
			mediaAttachements.put("videosUrls",videosUrls);

			//prepare urls
			List<String> linksUrls = request.getObject().getAttachments().stream()
					.map(attachmentRo -> attachmentRo.getLink().getUrl()).collect(Collectors.toList());
			mediaAttachements.put("linksUrls",linksUrls);

			telegramAggregator.sendPostToChannel(request.getObject().getText(), mediaAttachements);

			logger.info("Корректная обработка, на сервера vk возвращается статус 200 OK");
			return "ok";
		} else {
			logger.info("Плохой ответ");
			return "not ok";
		}
	}

}
