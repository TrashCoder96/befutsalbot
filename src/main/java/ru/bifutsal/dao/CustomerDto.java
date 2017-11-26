package ru.bifutsal.dao;

import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by itimofeev on 07.10.2017.
 */

@Entity
@Table(name = "customers")
public class CustomerDto extends AbstractDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String externalId;

	@Enumerated(EnumType.STRING)
	private TelegramDialogStatusEnum dialogStatus;

	@ElementCollection
	private List<Integer> teams;

	@Column
	private Integer currentPage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public TelegramDialogStatusEnum getDialogStatus() {
		return dialogStatus;
	}

	public void setDialogStatus(TelegramDialogStatusEnum dialogStatus) {
		this.dialogStatus = dialogStatus;
	}

	public List<Integer> getTeams() {
		return teams;
	}

	public void setTeams(List<Integer> teams) {
		this.teams = teams;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
