<div align="center">
  작성자 / 작성일 혹은 수정일 - woojin / 23th August 2022year
</div>

# 2주차. 개인 정리 내용

### 목차
- [TDD와 단위 테스트의 차이점]()
    - [TDD]()
    - [단위 테스트]()
- [Test Code 분석]()

# TDD와 단위 테스트의 차이점

## TDD(Test-Driven-Development)

TDD는 테스트가 주도하는 개발로 디자인 패턴 중 하나로 테스트가 주도한다는 것은 실제 로직을 작성하기 전 Test code를 먼저 작성하면서 개발을 진행하는 것을 말합니다. 이때 Test code 작성은 아래와 같은 흐름으로 진행됩니다.

<div align="center">
    <img src="https://www.icterra.com/wp-content/uploads/2020/01/Test_01-1.webp" width="600" height=""  alt="TDD-process"/>
    <p>TDD의 Red-Green-Refactor Cycle</P>
</div>

- Red
  <details markdown="1">
  <summary>write a test that fails</summary>
    소프트웨어의 동작에 대해 생각하고 간단히 테스트를 작성합니다. </br> 
    이 테스트를 작성하는 동안은 자신이 개발자보단 코드의 클라이언트로 두고 테스트를 진행합니다.
  </details>

- Green
  <details markdown="1">
  <summary>make only enough code for it to pass</summary>
    이번에는 성공할 수 있는 코드를 작성합니다. </br> 
    이 테스트 코드는 꼭 완벽할 필요가 없습니다. </br>
    심지어는 하드 코딩된 code를 작성해도 괜찮습니다.
  </details>
- Refactor
  <details markdown="1">
  <summary>improve code quality</summary>
    Green에서 코드가 성공임을 확인했으니 코드에 대한 리팩토링을 수행합니다. </br> 
    Green의 코드를 읽기 쉽고 확장 가능하며 효율적으로 만듭니다.
  </details>

## 단위 테스트

단위 테스트는 TDD의 첫 번째 단계인 기능 단위의 테스트 코드를 작성하는 행위를 말합니다.  
TDD와 달리 테스트 코드를 꼭 먼저 작성하지 않아도 되고 리팩토링도 포함되지 않습니다.  
순수하게 테스트 코드만 작성하는 것을 이야기합니다.

# Test Code 분석

### 예시 코드

```java
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)                                            // ························ 1)
@WebMvcTest(controllers = HelloController.class)                        // ························ 2)
public class HelloControllerTest {

  @Autowired
  private MockMvc mvc;                                                  // ························ 3)

  @Test
  public void hello가_리턴된다() throws Exception {
    String hello = "hello";

    mvc.perform(get("/hello"))                                          // ························ 4)
            .andExpect(status().isOk())                                 // ························ 5)
            .andExpect(content().string(hello));                        // ························ 6)
  }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)                            // ························ 7)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))                // ························ 8)
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
```

## 코드 분석

1) @Runwith(SpringRunner.class)
   > @Runwith  
   > @SpringBootTest도 지원하지만 이는 application context를 전부 로딩해서 자칫 잘못하면 무거운 Test를 지원하게됩니다.  
   > Junit이 지원하는 @Runwith(SpringRunner.class)를 사용하면, @Autowired,@MockBean에 해당되는 것들만 application context를 로딩하게 되므로 가벼운 테스트를 진행할 수 있습니다.
   > 그러므로 가능하면 필요한 조건에 맞춰서 @RunWith를 이용해 Test를 진행하는 것이 좋습니다.
   >
   > SpringRunner.class  
   > SpringRunner는 SpringJUnit4ClassRunner라고도 하며 JUnit Test 라이브러리를 Spring Test Context Framework와 결합합니다.
   >
   > 즉, @RunWith(SpringRunner.class)을 사용하면 Spring Boot Test와 JUnit을 연결하여 Spring Boot Test시 JUnit의 기능을 사용하게 됩니다.

   > @RunWith과 @ExtendWith  
   > SpringBoot 2.1 이전에는 Test 코드를 위해 @RunWith(SpringRunner.class)를 지정해주어야 했지만, SpringBoot 2.1 이후에는 Junit 버전이 5.x로 변경되었고, @Runwith의 역할을 하는 어노테이션이 @ExtendWith로 변경되었다.
   > 그리고 이 어노테이션은 @SpringBootTest 어노테이션에 포함되므로 @SpringBootTest를 사용하는 경우 따로 별도로 @RunWith 혹은 @ExtendWith을 정의할 필요 없다.

2) @WebMvcTest
   > 여러 스프링 테스트 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션입니다.  
   > @Controller, @ControllerAdvice 등을 사용할 수 있고, @Service,@Component,@Repository 등은 사용할 수 없습니다.  
   > 주로 컨트롤러 쪽의 Test를 진행하기위해 사용합니다.

3) MockMvc
   > 웹 API를 테스트할 때 사용합니다.  
   > 이 클래스를 통해 HTTP GET, POST, UPDATE, DELETE 등에 대한 API 테스트를 할 수 잇습니다.

4) mvc.perform(get("/hello"))
   > MockMvc를 통해 HTTP GET 요청을 날립니다. get("URL")로 요청은 URL로 날리게됩니다.  
   > 체이닝이 가능해 여러 검증기능을 이어서 선언할 수 있습니다.

5) mvc.andExpect(status().isOk())
   > mvc.perform의 결과를 검증합니다.  
   > status().isOk()는 mvc.perform()의 결과중 HTTP Header의 Status를 검증합니다.

6) mvc.andExpect(content().string(hello))
   > mvc.perform의 결과를 검증합니다.  
   > content().string()는 mvc.perform()의 결과중 응답 본문을 검증합니다.  
   > 현재는 Controller에서 hello 문자열을 반환하므로 이를 검증하기위해 content().string(hello)로 검사합니다.

7) get().param("name", name)
   > API 테스트 시 사용될 용청 파라미터를 설정합니다.  
   > 단, 값은 String만 허용되므로 형변환을 해줘야 합니다.

8) jsonPath("$.name", is(name))
   > JSON 응답값을 필드별로 검증할 수 있는 메소드입니다.  
   > $는 필드명을 명시합니다.


# Entity에 Setter를 사용하지 않는 이유

가끔 개발자들이 자바빈 규약을 생각하면서 getter/setter를 무작정 생성하는 경우가 있습니다.  
하지만, 이렇게 되면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수 없어, 차후 기능 변경 시 복잡해집니다.
게다가 Entity의 값이 무분별하게 변하는 경우 DB값도 변경시켜 매우 위험합니다.  
그래서 Entity 클래스에서는 **절대 Setter 메소드를 만들지 않습니다.** 대신, 해당 필드 값에 변경이 필요하면 아래와 같이 그 목적과 의도를 명확히 나타낼 수 있는 메소드를 추가합니다.

```java
public class Order {
    public void cancelOrder() {
        this.status = false;
    }
    
    public void 주문서비스의_취소이벤트() {
        order.cancelOrder();
    }
}
```

## Setter없이 Entity에 값을 채워넣는 방법

### 생성자 방식

Setter가 없는 Entity에 값을 채워넣는 기본적인 구조는 생성자를 통해 최종값을 채우는 것입니다.  
또한, 변경이 필요한 경우에는 위에서 말한 이벤트에 맞는 public 메소드를 호출하여 변경해야합니다.

하지만 생성자 방식으로 Entity에 값을 넣는 경우, 원하는 parameter에 argument가 재대로 들어가지 않아도 type이 맞다면 문제점을 찾기 힘듭니다.  
예를 들어 아래와 같이 생성자가 있다면 개발자가 `new Example(a,b)`가 아닌 `new Example(b,a)`로 해도 코드를 실행하기 전까지는 문제를 찾을 수 없습니다.

```java
public Example(String a, String b) {
    this.a = a;
    this.b = b;
}
```

이로인해 많은 개발자들은 Entity에 값을 채워넣을 때 @Builder를 이용한 Builder 패턴을 많이 사용합니다.

### Builder 방식

Builder 방식은 생성자 방식과 크게 다르지 않습니다.  
다만, Builder를 사용할 경우 아래와 같이 어느 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있게됩니다.

```java
Example.builder()
        .a(a)
        .b(b)
        .build();
```

이렇게 값을 할당하는 경우 a에 재대로 a의 값이 들어갔는 지 비교적 쉽게 볼 수 있어 많이 사용합니다.