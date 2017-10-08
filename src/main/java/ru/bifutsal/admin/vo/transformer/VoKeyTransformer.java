package ru.bifutsal.admin.vo.transformer;

import ru.bifutsal.admin.vo.KeyVo;
import ru.bifutsal.dao.KeyDto;
import org.springframework.stereotype.Component;

/**
 * Created by itimofeev on 07.10.2017.
 */
@Component
public class VoKeyTransformer extends VoTransformer<KeyDto, KeyVo> {

	@Override
	public KeyDto toDto(KeyVo vo) {
		KeyDto keyDto = createDto();
		keyDto.setName(vo.getName());
		keyDto.setValue(vo.getValue());
		return keyDto;
	}

	@Override
	public KeyVo toVo(KeyDto dto) {
		KeyVo keyVo = createVo();
		keyVo.setName(dto.getName());
		keyVo.setValue(dto.getValue());
		return keyVo;
	}

	@Override
	public KeyDto createDto() {
		return new KeyDto();
	}

	@Override
	public KeyVo createVo() {
		return new KeyVo();
	}
}
