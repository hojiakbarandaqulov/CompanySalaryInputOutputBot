package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class TelegramWebhookController {

    @PostMapping
    public ResponseEntity<String> handleUpdate(@RequestBody Map<String, Object> update) {
        // Telegramdan kelgan xabarni qayta ishlash
        System.out.println("Received update: " + update);

        // Xabarni tahlil qilish
        if (update.containsKey("message")) {
            Map<String, Object> message = (Map<String, Object>) update.get("message");
            String chatId = String.valueOf(((Map<String, Object>) message.get("chat")).get("id"));
            String text = (String) message.get("text");

            // Foydalanuvchiga javob yuborish
            sendMessage(chatId, "Sizning xabaringiz: " + text);
        }

        return ResponseEntity.ok("Update processed");
    }

    private void sendMessage(String chatId, String text) {
        String apiUrl = "https://api.telegram.org/bot<7834457453:AAE6uYUnl1wYPw11J9bFjcuwJCj3gyaMKq4>/sendMessage";
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> request = new HashMap<>();
        request.put("chat_id", chatId);
        request.put("text", text);

        restTemplate.postForObject(apiUrl, request, String.class);
    }
}

