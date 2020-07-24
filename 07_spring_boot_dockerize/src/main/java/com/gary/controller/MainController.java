package com.gary.controller;

import com.gary.model.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/welcome/{msg}")
    public ResponseEntity welcome(@PathVariable("msg") String msg){
        try{
            return new ResponseEntity(HttpStatus.OK.value(), "success", msg);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "failed", null);
        }
    }

}
