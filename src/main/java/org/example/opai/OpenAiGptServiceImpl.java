package org.example.opai;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import com.unfbx.chatgpt.interceptor.OpenAiResponseInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.entity.result.HttpResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service("getService")
public class OpenAiGptServiceImpl implements OpenAiGptService {
    @Override
    public String sendGptRequest(String apiUrl, String apiKey, String prompt) throws IOException {

        // 创建HTTP客户端，设置请求方法和请求头
        HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + apiKey);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");

        // 准备请求正文和文本提示
        String requestBody = String.format("{\"prompt\": \"%s\", \"max_tokens\": 70, \"temperature\": 0, \"model\": \"text-davinci-003\"}", prompt);

        // 设置请求正文，发送HTTP请求
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.getOutputStream().write(requestBody.getBytes(StandardCharsets.UTF_8));

        // 解析返回的参数及信息
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // 返回响应字符串
        return response.toString();
    }

    @Override
    public HttpResult test(@PathVariable String t) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        //！！！！千万别再生产或者测试环境打开BODY级别日志！！！！
        //！！！生产或者测试环境建议设置为这三种级别：NONE,BASIC,HEADERS,！！！
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .proxy(proxy)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new OpenAiResponseInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        OpenAiClient v2 = OpenAiClient.builder()
                //支持多key传入，请求时候随机选择
                .apiKey(Collections.singletonList(
                        "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"
                ))
                //自定义key的获取策略：默认KeyRandomStrategy
                //.keyStrategy(new KeyRandomStrategy())
                .keyStrategy(new KeyRandomStrategy())
                .okHttpClient(okHttpClient)
                //自己做了代理就传代理地址，没有可不不传,(关注公众号回复：openai ，获取免费的测试代理地址)
//                .apiHost("https://自己代理的服务器地址/")
                .build();

        //聊天模型：gpt-3.5
        Message message = Message.builder().role(Message.Role.USER).content(t).build();
        ChatCompletion chatCompletion = ChatCompletion
                .builder()
                .messages(Collections.singletonList(message))
                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                .build();
        ChatCompletionResponse chatCompletionResponse = v2.chatCompletion(chatCompletion);
        System.out.println(chatCompletionResponse.getChoices().toString());
        AtomicReference<Message> message1 = new AtomicReference<>(new Message());
        chatCompletionResponse.getChoices().forEach(e -> {
            message1.set(e.getMessage());
        });

        return HttpResult.success(message1);
    }
}
