package com.kirillkurko.cityinformation.bot;

import org.telegram.telegrambots.ApiContextInitializer;

public class Application {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot bot = new Bot("PenguinTravelerBot", "1466555555:AAGKVSaV4maTFQ-Z-PiPBD6tt8MTGtOH3qg");
        bot.connect();
    }
}
