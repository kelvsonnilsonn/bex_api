package com.ecommerce.bex.event;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="event_store")
@NoArgsConstructor
public class EventStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aggregateType;

    private Long aggregateId;
    private String eventType;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Object eventData;

    private LocalDateTime createdAt;

    public EventStore(String aggregateType, Long aggregateId, String eventType, Object eventData){
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.eventData = eventData;
        this.createdAt = LocalDateTime.now();
    }
}
