package com.cloutier.piaapi.data;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonParser;
import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadTables {

//    @Value("${json.config.folder}")
//    String jsonConfigFolder;

    @PostConstruct
    public void init() throws IOException {
        try {
            JsonParser jParser = new JsonFactory()
                    .createParser(new File("/Users/bcloutier/piaapi/src/main/resources/db/tables.json"));
            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jParser.getCurrentName();
                if("file".equals(fieldname)){
                    jParser.nextToken();
                    System.out.println(jParser.getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
