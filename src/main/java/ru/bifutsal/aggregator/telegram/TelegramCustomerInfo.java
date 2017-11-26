package ru.bifutsal.aggregator.telegram;

import ru.bifutsal.aggregator.AbstractCustomerInfo;

/**
 * Created by itimofeev on 08.10.2017.
 */
public class TelegramCustomerInfo extends AbstractCustomerInfo {

	private static final ThreadLocal<AbstractCustomerInfo> threadLocalScope = new ThreadLocal<>();

	private static final ThreadLocal<String> threadLocalScopeCommand = new ThreadLocal<>();

	public final static AbstractCustomerInfo getUserInformation() {
		return threadLocalScope.get();
	}

	public final static void setUserInformation(AbstractCustomerInfo info) {
		threadLocalScope.set(info);
	}

	public final static String getUserCommand() {
		return threadLocalScopeCommand.get();
	}

	public final static void setUserCommand(String info) {
		threadLocalScopeCommand.set(info);
	}
}
