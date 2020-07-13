package com.gary.controller;

import com.gary.model.ResponseEntity;
import com.gary.service.TelegramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramController  {

    private static final Logger logger = LoggerFactory.getLogger(TelegramController.class);

    @Autowired
    private TelegramService telegramService;

    /**
     * send message to channel by telegram bot
     * @param msg
     * @return
     */
    @GetMapping("/message/{msg}")
    public ResponseEntity sendMessage(@PathVariable("msg") String msg){
        try{
            telegramService.sendMessage(msg);
            return new ResponseEntity(HttpStatus.OK.value(), "success", null);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "failed", null);
        }
    }

    /**
     * send message to channel by telegram bot
     * @return
     */
    @GetMapping("/photo")
    public ResponseEntity sendPhoto(){
        try{
            telegramService.sendPhoto();
            return new ResponseEntity(HttpStatus.OK.value(), "success", null);
        }catch (Exception ex){
            logger.info(ex.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "failed", null);
        }
    }

}
