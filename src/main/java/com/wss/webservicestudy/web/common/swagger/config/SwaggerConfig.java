package com.wss.webservicestudy.web.common.swagger.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@SuppressWarnings("unchecked")
public class SwaggerConfig {

    // API마다 구분짓기 위한 설정.
    @Bean
    public Docket allApi() {
        return restAPI("전체", Predicates.or(
                PathSelectors.regex("/*.*")));
    }

    @Bean
    public Docket testApi() {
        return restAPI("테스트", Predicates.or(
                PathSelectors.regex("/test.*")));
    }

    @Bean
    public Docket commonApi() {
        return restAPI("공통", Predicates.or(
                PathSelectors.regex("/common.*")));
    }

    @Bean
    public Docket productApi() {
        return restAPI("유저", Predicates.or(
                PathSelectors.regex("/user.*")));
    }

    @Bean
    public Docket feedApi() {
        return restAPI("게시판", Predicates.or(
                PathSelectors.regex("/feed.*")));
    }

    @Bean
    public Docket adminApi() {
        return restAPI("관리페이지", Predicates.or(
                PathSelectors.regex("/manager.*")));
    }

    @Bean
    public Docket matcomApi() {
        return restAPI("매칭", Predicates.or(
                PathSelectors.regex("/matcom.*")));
    }

    @Bean
    public Docket searchApi() {
        return restAPI("배치", Predicates.or(
                PathSelectors.regex("/reservation.*")));
    }

    public Docket restAPI(String groupName, Predicate<String> predicate) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wss.webservicestudy.web"))
                .paths(predicate)
                .apis(RequestHandlerSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.PATCH, getCustomizedResponseMessages());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WSS Spring Boot REST API")
                .version("ver 1.2")
                .description("Web Service Study 프로젝트")
                .build();
    }

    private List<ResponseMessage> getCustomizedResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message("성공").build());
        responseMessages.add(new ResponseMessageBuilder().code(204).message("데이터 미존재").build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message("입력값 오류").build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message("미 로그인").build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message("권한없음").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("경로 오류").build());
        responseMessages.add(new ResponseMessageBuilder().code(405).message("허용되지 않는 메소드").build());
        responseMessages.add(new ResponseMessageBuilder().code(408).message("요청 시간초과").build());
        responseMessages.add(new ResponseMessageBuilder().code(412).message("처리중 오류").build());
        responseMessages.add(new ResponseMessageBuilder().code(444).message("응답 없음").build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message("서버에러").build());
        responseMessages.add(new ResponseMessageBuilder().code(503).message("서버 과부화").build());
        responseMessages.add(new ResponseMessageBuilder().code(505).message("HTTP 지원하지 않는 요청").build());
        responseMessages.add(new ResponseMessageBuilder().code(507).message("용량 부족").build());
        responseMessages.add(new ResponseMessageBuilder().code(511).message("네트워크 인증 필요").build());
        responseMessages.add(new ResponseMessageBuilder().code(520).message("명확하지 않음 에러[Unknown Error]").build());
        return responseMessages;
    }
}
