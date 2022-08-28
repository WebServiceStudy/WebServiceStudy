# **Web Service Study**

<div align="center">
<img src="https://user-images.githubusercontent.com/66407386/183276924-b75b2150-827f-4657-aef4-6f9f7c82478a.png" width="700" height="340"/>

</div>

## 📝 목표
- Spring Boot 기초부터 세부적인 내용 정리
- AWS 경험과 이해
- 코드 작성 및 그에 대한 코드 리뷰

## 🗓 기간
- 일정 : 2022.08.21(일) ~ 2022.10.10(일)

## 🚗 오프라인
- 장소 : 강남역 근처 스터디 카페

## 📈 진행
- 본인의 Repository를 생성하며 해당 Web_Servuce_Study Repository를 각자의 Branch 작업 후 진행
    - 초기 Repository에 환경설정 및 공통 작업 후 개인 Branch로 뻗어나가 추가적인 작업들을 진행
    - Repository에 그주 각자 작성한 내용 중에 검색 혹은 관련된 내용 공유는 추가적으로 공유
        - 책에 나오는 코드를 이렇게 수정한 이유와 주석
        - 책에서 이해한 부분들의 참고 서적 혹은 내용 정리
- "SpringBoot와 AWS로 혼자 구현하는 웹 서비스"책을 보고 작성하며<br/>자신의 주석 코드 제작 (주석 방식은 아래 스터디 방식 참조)
- 코드에 대해서 이해가 안 되거나 혹은 해결이 안되는 경우들을 사진과 함께 첨부하여<br/>이슈에 작성하여 팀원들과 공유 혹은 Q&A로 진행
- 각자 공부한 내용은 문서정리에 "${week}/${name}.md"에 작성하여 스터디원들끼리 해당 주차에<br/>서로 공유 및 발표

## 👥 팀원소개
<div align="center">
  <table>
    <tr>
        <td align="center"></td>
        <td align="center">
            <a href="https://github.com/hongcoding94"><img width="75px" src="https://avatars.githubusercontent.com/u/66407386?v=4" /></a>
        </td>
        <td align="center">
            <a href="https://github.com/rlorloc47"><img width="75px" src="https://avatars.githubusercontent.com/u/89571328?v=4" /></a>
        </td>
        <td align="center">
            <a href="https://github.com/je-pa"><img width="75px" src="https://avatars.githubusercontent.com/u/76720692?v=4" /></a>
        </td>
        <td align="center">
            <a href="https://github.com/tkfmxh"><img width="75px" src="https://avatars.githubusercontent.com/u/60864023?v=4" /></a>
        </td>
        <td align="center">
            <a href="https://github.com/tkdgus97"><img width="75px" src="https://avatars.githubusercontent.com/u/106962275?v=4" /></a>
        </td>
        <td align="center">
            <a href="https://github.com/gkfgran5037"><img width="75px" src="https://avatars.githubusercontent.com/u/42172353?v=4" /></a>
        </td>
        <td align="center">
            <a href="https://github.com/dnwlsrla40"><img width="75px" src="https://avatars.githubusercontent.com/u/23308642?v=4" /></a>
        </td>
      </tr>
    <tr>
        <td align="center">name<br/>(ID)</td>
        <td align="center">정진홍<br/>(hongcoding94)</td>
        <td align="center">조은<br/>(rlorloc47)</td>
        <td align="center">박지은<br/>( je-pa )</td>
        <td align="center">홍석인<br/>(tkfmxh)</td>
        <td align="center">전상현<br/>(tkdgus97)</td>
        <td align="center">김유미<br/>(gkfgran5037)</td>
        <td align="center">김우진<br/>(dnwlsrla40)</td>
    </tr>
</table>
</div>

## 🔲 **스터디 진행 방식**
- 상세 진행방식
  ### **PR 규칙**

  - PR 제목 및 내용 템플릿
  ```text 
  * 등록
   - 제목 : [INSERT.${본인이름 혹은 본인 아이디}] ${주차. 제목}
      - ex) [INSERT.hongcoding94] 1주차. 터디 팀원소개 및 “스프링부트와 AWS로 혼자 구현하는 웹 서비스”의 공통 셋팅화 시키기
      
   - 내용 : ${등록한 일자}
           ${등록한 폴더 경로}
           ${등록한 간결한내용}
      - ex) 2022.08.07
            WSS/chater/~
            md내용 추가 
      
  * 수정
   - 제목 : [UPDATE.${본인이름 혹은 본인 아이디}] ${해당주차 제목}
         - ex) [UPDATE.정진홍] 1주차. 터디 팀원소개 및 “스프링부트와 AWS로 혼자 구현하는 웹 서비스”의 공통 셋팅화 시키기
         
   - 내용 : ${수정한 일자}
           ${수정한 폴더 경로}
           ${수정한 간결한내용}
       - ex) 2022.08.07
            WSS/chater/~
            md내용 수정 
  ```

    - Code 주석
  ```java
  @Controller
  public class TextController() {
    /** 
      * 팀원들과 상의 후 수정 예정
      */
    public Map<String,String>() {
        // ~ 내용 생략 ~ //
    }
  }
  ```

  ### 스터디 시간표
  | 주차 | 진행일 | 진행률 | 진행 여부 | ETC |
  |:---:|:---:|:---:|:---:|---|
  |1주차|2022-08-21 (일)|“스프링부트와 AWS로 혼자 구현하는 웹 서비스”의<br/>공통 셋팅화 시키기 및 앞으로 진행 사항 토론|✔️|없음|
  |2주차|2022-08-28 (일)|"스프링부트와 AWS로 혼자 구현하는 웹 서비스"<br/>Data JPA 테스트코드 작성<br/>JPA 기초 공부영상 공부|준비 중 |없음|
  |3주차|2022-09-04 (일)|Insert,Update,Delete 각팀 구현<br/>MySQL 설정 및 세팅|준비 중 |없음|
  |4주차|2022-09-11 (일)|대한민국의 명절 추석(9월9일 - 9월12일)으로<br/>인한 해당 주차는 쉬어갑니다.<br/>🌕Public holiday in korea(Korean Thanksgiving Day)🌕<br/>즐거운 한가위 되세요.|🛑|없음|
  |5주차|2022-09-18 (일)|Insert,Update,Delete 각팀 구현|준비 중 |없음|
ss