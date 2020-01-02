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
                field("content"),
                field("url"),
                field("urltoimage"),
                field("publishat")
        ).values(
                uuid,
                "source",
                articles.getAuthor(),
                articles.getTitle(),
                articles.getContent(),
                articles.getUrl(),
                articles.getUrlToImage(),
                articles.getPublishedAt()
        ).onConflictDoNothing()
        .execute();

        OutboundNewsResponse outboundNewsResponse = getNews(uuid);

        return outboundNewsResponse;

    }

   OutboundNewsResponse getNews(UUID uuid) {
        OutboundNewsResponse newsResponses =
        jooq.selectFrom(table("news"))
                .where(field("feed").eq(uuid)).orderBy(field("publishat").desc()).fetchOneInto(OutboundNewsResponse.class);
        return newsResponses;
    }

    List<OutboundNewsResponse> getAllNews() {
        List<OutboundNewsResponse> newsResponses =
                jooq.selectFrom(table("news")).where(field("sentiment").isNotNull()).orderBy(field("publishat").desc())
                        .fetchInto(OutboundNewsResponse.class);
        return newsResponses;
    }

}
