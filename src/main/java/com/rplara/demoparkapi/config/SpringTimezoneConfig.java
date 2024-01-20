package com.rplara.demoparkapi.config;


import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

// anotação para definir uma classe de configuração
@Configuration
public class SpringTimezoneConfig {

    // método para definição do timezone
    @PostConstruct
    public void timezoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}
