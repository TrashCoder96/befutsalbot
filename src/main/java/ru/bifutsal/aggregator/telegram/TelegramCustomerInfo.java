package ru.bifutsal.aggregator.telegram;

import ru.bifutsal.aggregator.AbstractCustomerInfo;

/**
 * Created by itimofeev on 08.10.2017.
 */
public class TelegramCustomerInfo extends AbstractCustomerInfo {

	private static final ThreadLocal<TelegramCustomerInfo> threadLocalScope = new ThreadLocal<>();

	public final static TelegramCustomerInfo getThreadLocalScope() {
		return threadLocalScope.get();
	}

	public final static void setThreadLocalScope(TelegramCustomerInfo telegramCustomerInfo) {
		threadLocalScope.set(telegramCustomerInfo);
	}
}
