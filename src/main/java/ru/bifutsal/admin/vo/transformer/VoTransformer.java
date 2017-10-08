package ru.bifutsal.admin.vo.transformer;

import ru.bifutsal.admin.vo.AbstractVo;
import ru.bifutsal.dao.AbstractDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by itimofeev on 07.10.2017.
 */
public abstract class VoTransformer<DTO extends AbstractDto, VO extends AbstractVo> {

	public abstract DTO toDto(VO vo);

	public abstract VO toVo(DTO dto);

	public List<DTO> toDtoList(List<VO> voList) {
		return voList.stream().map(this::toDto).collect(Collectors.toList());
	}

	public List<VO> toVoList(List<DTO> dtoList) {
		return dtoList.stream().map(this::toVo).collect(Collectors.toList());
	}

	public abstract DTO createDto();

	public abstract VO createVo();


}
