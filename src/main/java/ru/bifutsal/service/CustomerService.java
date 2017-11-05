package ru.bifutsal.service;

import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;

public interface CustomerService {

    TelegramDialogStatusEnum getLastTelegramDialogStatus(String customerId);

    void saveNewTelegramDialogStatus(String customerId, TelegramDialogStatusEnum status);
}
