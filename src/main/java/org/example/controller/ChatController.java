//package org.example.controller;
//
//import cn.hutool.http.HttpRequest;
//import cn.hutool.json.JSONUtil;
//import org.example.opai.ChatRequest;
//import org.example.opai.ChatResponse;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ChatController {
//    @PostMapping("/chat")//文档要求使用post请求
//    public ChatResponse chat(String q) {
//        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";//这里看官方文档
//        String ApiKey = "sk-51aef6c9b69a4f1695f34c64d77d3966";//这里换成你自己的ApiKey
//
//        ChatRequest chatRequest = new ChatRequest(q);
//        String json = JSONUtil.toJsonStr(chatRequest);
//        //System.out.println(json);//正式发送给api前,查看请求的主要数据情况
//        String result = HttpRequest.post(url)
//                .header("Authorization", "Bearer " + ApiKey)
//                .header("Content-Type", "application/json")
//                .body(json)
//                .execute().body();
//        System.out.println(result);
//        return JSONUtil.toBean(result, ChatResponse.class);
//    }
//}
