<div align="center">
작성자(버전) | 수정일 / kimyumi(Ver.0.1) / 23th August 2022year
</div>

## War vs Jar

| 파일 포맷                         | 확장자  | 프로젝트     | 설명                                                                                                 |
|-------------------------------|------|----------|----------------------------------------------------------------------------------------------------|
| Jar (Java Archive)            | .jar | 자바 프로젝트  | 자바 클래스 파일과 클래스들이 이용하는 관련 리소스 및 메타데이터를 하나의 파일로 모아서 자바 플랫폼에 응용 소프트웨어나 라이브러리를 배포하기 위한 소프트웨어 패캐지 파일 포맷 |
| War (Web application Archive) | .war | 웹 어플리케이션 | 웹 어플리케이션을 구성하는 자바 클래스와, 자바 서버 페이지, 관련 XML 파일등을 묶은 압축 파일 포맷                                         |

<br/><br/><br/>

## Gradle
빌드 자동화 시스템

<br/>
<img src="https://user-images.githubusercontent.com/42172353/186183078-d3f205ff-def5-4260-b27e-7051fe307865.png" width="80%"/>

<br/><br/><br/><br/><br/>


---
<br/>

## build.gradle

<br/>

```java
plugins {
	id 'org.springframework.boot' version '2.7.1'
	id 'io.spring.dependency-management' version '1.0.11.RELEASE' // 스프링 부트의 의존성들을 관리해주는 플러그인
	id 'java'
	id 'war' // JSP를 실행하기 위해서 Packaging은 war로 설정
}

group = 'com.demo'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'  // 자바 버전

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

// 의존성들을 받는 원격 저장소
repositories {
	mavenCentral()
    jcenter()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'com.h2database:h2'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
	useJUnitPlatform()
}
```
<br/><br/><br/><br/><br/>


---
<br/>

## @SpringBootApplication
- 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
    - @SpringBootApplication 이 있는 위치부터 설정을 읽어감
    - 프로젝트의 최상단에 위치해야 함
    - 해당 파일은 프로젝트의 메인 클래스
- SpringApplication.run : 내장 WAS 실행

<br/>

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링 부트의 자동 설정
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```
<br/><br/><br/><br/><br/>


[참고서적 - 스프링 부트와 AWS로 혼자 구현하는 웹 서비스](http://www.yes24.com/Product/Goods/83849117)  
[참고문서 - 위키백과 Jar](https://ko.wikipedia.org/wiki/JAR_(%ED%8C%8C%EC%9D%BC_%ED%8F%AC%EB%A7%B7))  
[참고문서 - 위키백과 War](https://ko.wikipedia.org/wiki/WAR_(%ED%8C%8C%EC%9D%BC_%ED%8F%AC%EB%A7%B7))
