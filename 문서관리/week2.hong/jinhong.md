<div align="center">
  작성자 / 작성일 혹은 수정일 - jinhong / 27th August 2022year
</div>

# 2주차. 개인 정리 내용

### 목차
1. [스프링 부트와 AWS로 혼자 구현하는 웹 서비스에서 나오는 "Spring Data"는 무엇인가?](https://github.com/hongcoding94/Web_Service_Study/blob/53a7d6cd7c9692cf31769ea97074f3946980ea79/%EB%AC%B8%EC%84%9C%20%EC%A0%95%EB%A6%AC/week2/jinhong.md#%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8%EC%99%80-aws%EB%A1%9C-%ED%98%BC%EC%9E%90-%EA%B5%AC%ED%98%84%ED%95%98%EB%8A%94-%EC%9B%B9-%EC%84%9C%EB%B9%84%EC%8A%A4%EC%97%90%EC%84%9C-%EB%82%98%EC%98%A4%EB%8A%94-spring-data%EB%8A%94-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)
2. [JSP란 무엇이며 어떻게 구조화 되어 있는가?](https://github.com/hongcoding94/Web_Service_Study/blob/53a7d6cd7c9692cf31769ea97074f3946980ea79/%EB%AC%B8%EC%84%9C%20%EC%A0%95%EB%A6%AC/week2/jinhong.md#jsp%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B4%EB%A9%B0-%EC%96%B4%EB%96%BB%EA%B2%8C-%EA%B5%AC%EC%A1%B0%ED%99%94-%EB%90%98%EC%96%B4-%EC%9E%88%EB%8A%94%EA%B0%80)
3. [JPA란 무엇이며 어떻게 구조화 되어 있는가?](https://github.com/hongcoding94/Web_Service_Study/blob/53a7d6cd7c9692cf31769ea97074f3946980ea79/%EB%AC%B8%EC%84%9C%20%EC%A0%95%EB%A6%AC/week2/jinhong.md#jpa%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B4%EB%A9%B0-%EC%96%B4%EB%96%BB%EA%B2%8C-%EA%B5%AC%EC%A1%B0%ED%99%94-%EB%90%98%EC%96%B4-%EC%9E%88%EB%8A%94%EA%B0%80)
4. [JPA와 JSP의 구조 차이](https://github.com/hongcoding94/Web_Service_Study/blob/53a7d6cd7c9692cf31769ea97074f3946980ea79/%EB%AC%B8%EC%84%9C%20%EC%A0%95%EB%A6%AC/week2/jinhong.md#jpa%EC%99%80-jsp%EC%9D%98-%EA%B5%AC%EC%A1%B0-%EC%B0%A8%EC%9D%B4)
5. [참고자료](https://github.com/hongcoding94/Web_Service_Study/blob/53a7d6cd7c9692cf31769ea97074f3946980ea79/%EB%AC%B8%EC%84%9C%20%EC%A0%95%EB%A6%AC/week2/jinhong.md#%EC%B0%B8%EA%B3%A0%EC%9E%90%EB%A3%8C)

### 스프링 부트와 AWS로 혼자 구현하는 웹 서비스에서 나오는 "Spring Data"는 무엇인가?

- Spring Data란?
  <div align="center">

    <img src="https://user-images.githubusercontent.com/66407386/186048097-4ce6f310-9733-4a58-99ba-f83c2710bdb7.png" width="600" height="" />
    <p>Spring 공식문서 Spring Data의 내용 中</P>
  </div>

    - 기본 데이터 저장소의 특수한 특성을 유지하면서 **데이터 접근을 위한 친숙하고 일관된<br/>Spring기반의 프로그래밍 모델을 제공**하는 프로젝트

    - 아래의 기술들을 보다 쉽게 사용할 수 있도록 지원한다.
        - 데이터 접근 기술
        - Relational and non-relational database
        - map-reduce 프레임워크
        - 클라우드 기반의 서비스

    - 데이터베이스와 관련된 많은 하위 프로젝트를 포함하는 포괄적인 프로젝트(하위 참조)
      |Category|Sub-project|
      |---|---|
      |Relational Database|JPA<br/>JDBCC Extensions|
      |Big Data|Apache Hadoop|
      |Data-Grid|GemFire|
      |HTTP|REST|
      |Key Value Stores|Redis|
      |Document Stores|MongoDB|
      |Graph Databases|Neo4j|
      |Column Stores|HBase|
      |Common infrastrucure|Ccommons<br/>Grails Mapping|

    - 주요 모듈
        - Spring Data Commons
        - Spring Data JDBC
        - Spring Data JDBC Ext
        - Spring Data JPA
        - Spring Data KeyValue
        - Spring Data LDAP
        - Spring Data MongoDB
        - Spring Data Redis
        - Spring Data REST
        - Spring Data for Apache Cassandra
        - Spring Data for Apache Geode
        - Spring Data for VMware Tanzu GemFire

- Spring Data의 특징
    - 강력한 레포지토리 패턴과 커스텀 객체 매핑 추상화
    - 레포지토리 패턴의 메소드 이름으로 동적 쿼리 실행
    - 기본 속성을 제공하는 도메인 기본 클래스 구현
    - 이벤트 상태 지원(생성, 마지막 변경)
    - 커스텀 레포지토리 코드 통합
    - JavaConfig클래스 및 커스텀 XML 네임스페이스를 이용한 스프링 통합 설정 제공
    - 더 나은 Spring MVC 컨트롤러와의 통합
    - 상호간 영속화 지원 (Experimental)

### JSP란 무엇이며 어떻게 구조화 되어 있는가?

- 왜 JPA를 하는데 JSP와 MVC패턴에 대한 내용을 다루는가?
  > JPA와 JSP의 구조는 다르기 때문에 해당 목차에서는 그 차이점을 비교하기 전에<br/>
  > JSP와 MVC 구조를 다시 한번 이해하기 위해서 작성하는 내용이다.
  <div align="center">
    <img src="https://user-images.githubusercontent.com/66407386/186952546-436a6a23-bc95-4f88-ab44-5c9c1a7a0467.jpeg" width="750" height="" />
    <p>MVC 패턴 전체 흐름도</P>
  </div>

- Model, View , Controller 개념
    - MVC 패턴
        - MVC 패턴은 Model, View, Controller 개념이 합쳐지면서 생긴 방식으로<br/>
          소프트웨어 공학에서 사용되는 디자인 패턴

   <div align="center">
     <img src="https://user-images.githubusercontent.com/66407386/186082100-3cc65f80-3ecf-4432-aedb-60c0b53110cf.png" width="300" height="" />
     <p>디자인 패턴</p>
   </div>

    - Model
        - 역할 : Controller에서 받은 데이터를 저장하는 역할

    - View
        - 역할 : Controller에서 전달 받은 Model 데이터를 바탕으로 사용자에게 보여줌

    - Controller
        - 사용자가 접근한 URL에 따라 요청을 파악<br/>
          URL에 맞는 Method를 호출하여 Service와 함께 비지니스 로직을 처리<br/>
          최종적인 결과를 Model에 저장하여 사용자(View) 화면에 보여준다.

- MVC 패턴 종류
    - MVC 1 (Model 1)
  <div align="center">
    <img src="https://user-images.githubusercontent.com/66407386/186081569-a95b3ea5-fc19-41cd-8db1-94f8acbcf0e2.png" width="600" height="" />
    <p>MVC 1 (Model 1)</p>
  </div>

  > Java파일을 <Tag>를 HTML에 모두 작성하여 개발하기 때문에 JSP에서 모든 요청을 처리한다.<br/>
  > 빠른 개발을 할 수 있지만 프로젝트가 덧붙여 질수록 혹은 프로젝트가 규모가 커지게 되면 <br/>
  > 유지보수에 힘들어져서 <s>도망이 답이다.라 읽는다</s> 고생한다는 단점이 있다.


- MVC 2 (Model 2)
  <div align="center">
    <img src="https://user-images.githubusercontent.com/66407386/186081597-72fb06e1-513c-448e-9054-0e627801a9d7.png" width="600" height="" />
    <p>MVC 2 (Model 2)</p>
  </div>
    > Controller, View, Model로 나눠지며 처리 역할이 분명하게 파트별로 나눠져 있다.<br/>
    > 역할을 나눔으로써 개발의 속도는 MVC1 방식 보다 조금 느리지만 프로젝트를 덧붙이거나 혹은<br/>
    > 프로젝트 규모가 커지게 된다면 확장성도 좋고 순탄하게 유지보수도 하기 좋아진다.
- JSP 전체 동작 과정과 JSP서블릿 컴파일 처리 과정
  <div align="center">
    <img src="https://user-images.githubusercontent.com/66407386/186332269-c60825a0-175e-4b2e-b8ea-a32928d57636.png" width="450" height="405" />
    <img src="https://user-images.githubusercontent.com/66407386/186332412-811aeb0d-fa61-472f-95b2-4c41c1b49b8c.png" width="450" height="" />
    <p>JSP 동작 과정(좌) / (우)JSP서블릿 컴파일 처리 과정</p>
  </div>

※ JSP문법을 알고 싶다면 옆에 클릭을 누르세요 ▷ [클릭](https://www.geeksforgeeks.org/introduction-to-jsp/)

### JPA란 무엇이며 어떻게 구조화 되어 있는가?

공부 中
- JPA란?

### JPA와 JSP의 구조 차이


### 참고자료
1. 서적   - [스프링 부트와 AWS로 혼자 구현하는 웹 서비스](http://www.yes24.com/Product/Goods/83849117)
2. 공식문서 - [Spring공식문서 페이지](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
3. 블로그 - [girawhale님의 [JPA] JPA란? JPA를 사용하는 이유](https://girawhale.tistory.com/119)
4. 블로그 - [오리엔탈킴님의 [JPA] JPA란? Spring Data JPA로 간단 예제 프로젝트 구현](https://kim-oriental.tistory.com/20)
5. 블로그 - [neonkid팀의 [SpringData]Spring Data module](https://blog.neonkid.xyz/274)
6. 블로그 - [Aridom님의 [Spring] MVC 패턴 & Spring Framework MVC](https://aridom.tistory.com/61)
7. 블로그 - [한코딩님의 [웹 프로그래밍] JSP 전체 동작 과정 및 기본 실습](https://m.blog.naver.com/3246902/221659042559)
8. 블로그 - [heejeong Kwon님의 [Web] JSP란 (Java Server Pages)](https://gmlwjd9405.github.io/2018/11/03/jsp.html)
9. 블로그 - [dbjh님의 [Spring JPA] JPA 란?](https://dbjh.tistory.com/77)
10. 블로그 - [로그님의 [JAVA]JPA와 MyBatis의 차이(Hibernate, ORM 등)](https://blog.naver.com/PostView.naver?blogId=hj_kim97&logNo=222192334452&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView)
11. 블로그 - [제이브님의 [SpringBoot/Java] JSP를 하다가 JPA를 처음 접하면서 생긴 의문 - JPA의 DTO와 JSP의 DAO의 차이점](https://rimooworld.tistory.com/10)
10. 영상   - [김성렬님(금오공과대학교)의 스프링과 JPA를 이용한 웹개발](http://www.kocw.net/home/cview.do?mty=p&kemId=1428755)
11. 영상   - [메타코딩님의 스프링부트 강좌 59강(블로그 프로젝트) - 스프링작동원리 복습](https://www.youtube.com/watch?v=S7LBQxgoVP0&t=17s)
12. 커뮤니티 - [人CoDOM 커뮤니티의 Spring Data](http://www.incodom.kr/Spring_Boot/Data)
13. 커뮤니티 - [geeksforgeeks커뮤니티의 Introduction to JSP](https://www.geeksforgeeks.org/introduction-to-jsp/)