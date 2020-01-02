package com.cloutier.piaapi.news;

import com.cloutier.piaapi.services.MessageReciever;
import com.cloutier.piaapi.services.MessageSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageSender messageSender;

    public NewsService(NewsRepository newsRepository,SimpMessagingTemplate simpMessagingTemplate, MessageSender messageSender) {
        this.newsRepository = newsRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageSender = messageSender;
    }

public int getNews() {

    final String uri = "https://newsapi.org/v2/top-headlines?country=us&apiKey=1654f224d44d4dc491f416ef7950a051";

    ObjectMapper mapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();

    List<Articles> result = restTemplate.getForObject(uri, InboundNewsResponse.class).getArticles();

//    List<InboundNewsResponse> object = Arrays.asList(result.getBody());
    for (int i = 0; i < result.size(); i++) {
        OutboundNewsResponse newsResponses = newsRepository.addNews(result.get(i));
        if(newsResponses != null) {
            this.simpMessagingTemplate.convertAndSend("/topic/news", newsResponses);
            String uuid = String.valueOf(newsResponses.getFeed());
            messageSender.produceMessage(uuid);
        }
    }

    return 1;

}


    public List<OutboundNewsResponse> getAllNews() {
//        messageSender.recieveMessage("i");
        List<OutboundNewsResponse> newsResponse = newsRepository.getAllNews();

        return newsResponse;
    }

}
