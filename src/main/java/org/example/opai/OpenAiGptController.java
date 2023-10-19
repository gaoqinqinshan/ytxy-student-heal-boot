package org.example.opai;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.result.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class OpenAiGptController {
    @Autowired
    OpenAiGptService openAiGptService;

    @Autowired
    OpenAiGptEntity openAiGptEntity;

    @GetMapping("/api/goChatGpt/{t}")
    public HttpResult goChatGpt(@PathVariable String t){
        return openAiGptService.test(t);
    }

}
