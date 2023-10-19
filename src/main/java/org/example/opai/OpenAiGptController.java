package org.example.opai;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.result.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/api/openai")
public class OpenAiGptController {
    @Autowired
    OpenAiGptService openAiGptService;

    @Autowired
    OpenAiGptEntity openAiGptEntity;

    @PostMapping("/test")
    public HttpResult test(@RequestBody Map<String, String> map){
        log.info("参数为：{}",map);
        String question = map.get("question");
        return openAiGptService.test(question);
    }

}
