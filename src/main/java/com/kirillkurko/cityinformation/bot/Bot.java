package com.kirillkurko.cityinformation.bot;

import com.kirillkurko.cityinformation.bot.services.implementations.DataServiceImplementation;
import com.kirillkurko.cityinformation.bot.services.interfaces.DataService;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

    private final int RECONNECT_PAUSE = 10000;
    private final DataService dataService;

    private String userName;
    private String token;

    public Bot() {
        dataService = new DataServiceImplementation();
    }

    public Bot(String userName, String token) {
        dataService = new DataServiceImplementation();
        this.userName = userName;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String response = "Привет, просто напиши название города";
        String text = update.getMessage().getText();
        if (!text.equals("/start")) {
            response = dataService.getCityInformation(text);
        }
        sendResponseMessage(message.getChatId().toString(), response);
    }

    private synchronized void sendResponseMessage(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        }
        catch (TelegramApiException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    public void connect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        }
        catch (TelegramApiRequestException telegramApiRequestException) {
            try {
                Thread.sleep(RECONNECT_PAUSE);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
                return;
            }
            connect();
        }
    }
}
