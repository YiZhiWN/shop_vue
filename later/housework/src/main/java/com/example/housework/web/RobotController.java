package com.example.housework.web;

import com.example.housework.Service.AskService;
import com.example.housework.vo.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RobotController {
    @Autowired
    AskService askService;

    @GetMapping("/robot/talk/{word}")
    public Bean findTalk(@PathVariable("word") String word){
        return askService.ask(word);
    }
}
