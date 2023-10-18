package org.example.opai;

import org.example.entity.result.HttpResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

public interface OpenAiGptService {
    public String sendGptRequest(String apiUrl, String apiKey,String prompt) throws IOException;

    HttpResult test(String t);
}
