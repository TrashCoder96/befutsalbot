package ru.bifutsal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by itimofeev on 01.10.2017.
 */

@Entity
@Table(name = "keys")
public class KeyDto extends AbstractDto {

	@Id
	private String name;

	@Column
	private String value;

	public String getName() {
		return name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
