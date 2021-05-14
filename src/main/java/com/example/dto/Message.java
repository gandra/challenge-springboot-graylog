package com.example.dto;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@lombok.Data
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String message;
    private Date date = new Date();

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Message{")
                .append(message)
                .append(",")
                .append(date)
                .append(",")
                .append(id)
                .append("}")
                .toString();
    }
}
