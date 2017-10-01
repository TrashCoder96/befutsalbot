package ru.bifutsal.aggregator;

import ru.bifutsal.dao.repository.KeyRepository;
import com.vk.api.sdk.client.VkApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by itimofeev on 01.10.2017.
 */

@Component
@Transactional
public class VkAggregator implements IAggregator<String> {

	private String appId;

	private String clientSecret;

	@Autowired
	private KeyRepository keyRepository;

	@Autowired
	private VkApiClient vk;

	@PostConstruct
	private void postInit() {
		//if (keyRepository.findAll().size() > 0) {
		//	setAppId(keyRepository.findOne("appId".toUpperCase()).getValue());
		//	setClientSecret(keyRepository.findOne("clientSecret".toUpperCase()).getValue());
		//}
	}

	@Override
	public void sendAll() {

	}

	@Override
	public void send(String customerId) {

	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
