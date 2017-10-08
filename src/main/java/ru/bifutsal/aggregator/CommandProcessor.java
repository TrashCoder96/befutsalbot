package ru.bifutsal.aggregator;

/**
 * Created by itimofeev on 05.10.2017.
 */
public interface CommandProcessor {

	void acceptCommand(AbstractCustomerInfo info, String command);

}
