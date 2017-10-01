package ru.bifutsal.aggregator;

/**
 * Created by itimofeev on 01.10.2017.
 */
public interface IAggregator<T> {

	void sendAll();

	void send(T customerId);

}
