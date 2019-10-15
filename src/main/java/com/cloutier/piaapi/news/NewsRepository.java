package com.cloutier.piaapi.news;

import com.cloutier.piaapi.weather.OutboundWeatherResponse;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Repository
public class NewsRepository {

    private final DSLContext jooq;

    @Autowired
    public NewsRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    OutboundNewsResponse addNews(Articles articles) {
        UUID uuid = UUID.randomUUID();

        jooq.insertInto(
                table("news"),
                field("feed"),
                field("source"),
                field("author"),
                field("title"),
                field("content")
        ).values(
                uuid,
                "source",
                articles.getAuthor(),
                articles.getTitle(),
                articles.getContent()
        ).onDuplicateKeyIgnore()
        .execute();

        OutboundNewsResponse outboundNewsResponse = getNews(uuid);

        return outboundNewsResponse;

    }

   OutboundNewsResponse getNews(UUID uuid) {
        OutboundNewsResponse newsResponses =
        jooq.selectFrom("news")
                .where(field("feed").eq(uuid)).fetchOneInto(OutboundNewsResponse.class);
        return newsResponses;
    }

    List<OutboundNewsResponse> getAllNews() {
        List<OutboundNewsResponse> newsResponses =
                jooq.selectFrom("news")
                        .fetchInto(OutboundNewsResponse.class);
        return newsResponses;
    }

}
