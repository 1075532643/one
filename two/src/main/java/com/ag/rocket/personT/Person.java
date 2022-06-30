package com.ag.rocket.personT;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "person")
@Data
public class Person {

    private String name;
    private Integer height;
}
