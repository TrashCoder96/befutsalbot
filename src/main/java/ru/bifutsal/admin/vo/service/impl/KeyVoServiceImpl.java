package ru.bifutsal.admin.vo.service.impl;

import ru.bifutsal.admin.vo.KeyVo;
import ru.bifutsal.admin.vo.service.KeyVoService;
import ru.bifutsal.admin.vo.transformer.VoTransformer;
import ru.bifutsal.dao.KeyDto;
import ru.bifutsal.dao.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by itimofeev on 07.10.2017.
 */

@Service
@Transactional
public class KeyVoServiceImpl implements KeyVoService {

	@Autowired
	private VoTransformer<KeyDto, KeyVo> voVoTransformer;

	@Autowired
	private KeyRepository keyRepository;

	@Override
	public void saveOrUpdateKey(String name, String value) {
		KeyVo keyVo = new KeyVo();
		keyVo.setName(name);
		keyVo.setValue(value);
		keyRepository.save(voVoTransformer.toDto(keyVo));
	}

	@Override
	public List<KeyVo> geAllKeys() {
		return voVoTransformer.toVoList(keyRepository.findAll());
	}

	@Override
	public void deleteKey(String name) {
		keyRepository.delete(name);
	}
}
