package ru.bifutsal.admin.vo.service;

import ru.bifutsal.admin.vo.KeyVo;

import java.util.List;

/**
 * Created by itimofeev on 07.10.2017.
 */
public interface KeyVoService {

	void saveOrUpdateKey(String name, String value);

	List<KeyVo> geAllKeys();

	void deleteKey(String name);

}
