package com.gary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@PropertySource("classpath:application.properties")
public class TelegramService extends TelegramLongPollingBot {

    @Autowired
    private Environment env;

    /**
     * send message to telegram channel
     * @param text
     */
    public void sendMessage(String text) throws TelegramApiException{
        if (!text.isEmpty()) {
            SendMessage message = new SendMessage() // Create a message object
                    .setChatId(env.getProperty("telegram.channel"))
                    .setText(text);
            execute(message);
        }
    }

    /**
     * send photo to telegram channel
     * @param
     */
    public void sendPhoto() throws TelegramApiException, FileNotFoundException {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(),
                "06_spring_boot_telegram_bot","src", "main", "resources", "photo", "cute.jpg");
        SendPhoto message = new SendPhoto()
                .setChatId(env.getProperty("telegram.channel"))
                .setPhoto("cute", new FileInputStream(new File(filePath.toUri())));
        execute(message);
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return env.getProperty("telegram.bot.name");
    }

    @Override
    public String getBotToken() {
        return env.getProperty("telegram.token");
    }

}
