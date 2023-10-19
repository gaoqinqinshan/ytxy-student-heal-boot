package org.example.opai;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * chatgpt实体类
 */
@Component
@Data
@PropertySource(value = "classpath:openAI.yml")
public class OpenAiGptEntity {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;
    @Value("${apiPrompt}")
    private String prompt;

}
