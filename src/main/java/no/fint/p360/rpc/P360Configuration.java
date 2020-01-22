package no.fint.p360.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class P360Configuration {

    @Bean
    public WebClient p360Client() {

        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .baseUrl("https://test-integrations-no.public360online.com/Biz/v2/api/call/SI.Data.RPC/SI.Data.RPC/").build();
    }
}
