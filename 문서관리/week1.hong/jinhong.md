<div align="center">
작성자(버전) | 수정일 / jinhong(Ver.0.4.) / 29th August 2022year
</div>

# 환경세팅

> Web_Service_Study에서 셋팅 혹은 개발을 진행하다 발생하는 원인 그리고<br/>
> 해결한 방안을 찾고 또한 다음에는 이러한 일을 시간 소모를 최소화 하기 위하여 작성하며<br/>
>
> 프로젝트에 있어 셋팅은 시작에 있어서 가장 중요한 작업 중 하나이다.<br/>
> 뼈대를 발라내는 작업도 있겠지만 신규로 작성할 때 비슷한 문서가 있다면 보다 편리하게<br/>
> 관리 할 수 있을 것이다.
  <details markdown="1">
  <summary>Tool Setting</summary>

- 소프트웨어 개발 키트(Software Development Kit)
    - JDK 1.8.0.2.1

- 개발 툴(Devolver Tool)
    - IntelliJ IDEA
    - DB (MySQL)
   
- 형상관리(Version Control Revision Control)
    - GitHub

  </details>

  <details markdown="1">
  <summary>Spring boot initializr 생성</summary>

1. "https://start.spring.io/" 접속

2. 아래와 같이 설정
  ```text
  - Project
    ❌Maven Project ✔️Gradle Project
  -Language
    ✔️Java ❌Kotliin ❌ Groovy
  - Spring Boot
    ❌3.0.0 (SNAPSHOT) ❌3.0.0 (M4) ❌2.7.4 (SNAPSHOT) ✔️2.7.3 
    ❌2.6.12 (SNAPSHOT) ❌2.6.11
  - Project Metadata
    Group       : com.wss
    Artifact    : wss
    Name        : wss
    Description : Web_Service_Study
    Package name: com.wss
    Packaging   ✔️Jar   ❌War    
    Java ❌18 ❌17 ✔️11 ❌8
  ```

  <div align="center">
    <img src="https://user-images.githubusercontent.com/66407386/185818578-3916e267-572a-4327-b7b7-304c8537bf19.png" width="500" height="" />
  </div>

3. 아래와 같이 설정이 끝나면 저장(Generate)

4. 알집으로 다운로드된 파일 압축 풀기

5. IntelliJ IDEA 열고 "Open File or Project"에서 파일 불러오기
  </details>

  <details markdown="1">
  <summary>github</summary>

1. "터미널"을 이용한 Commit and push 혹은 "git add test.md"를 했을 때 아래와 같은 경고창으로 인한 해결 방법
    - Alert 내용
      ```
       - 영어 -
       warning: LF will be replaced by CRLF in bora.txt.
       The file will have its original line endings in your working directory
         
       - 한글 -
       경고 : bora.txt에서 LF는CRLF로 대체됩니다.
       파일은 작업 디렉토리에 원래 줄 끝이 있습니다.
      ```
    - 발생 원인
      > 띄워쓰기(\n)으로 인한 발생되는 경고 에러
    - 해결 방안

      A. Window
      ```text
      git config --global core.autocrlf true
      ```
      b. Linux, Mac
      ```text
      git config --global core.autocrlf input
      ```

  </details>

  <details markdown="1">
  <summary>IntelliJ IDEA</summary>

1. Intellij JUnit Test 실패  No tests found for given includes
    - consle.log 내용
      ```text
      Execution failed for task ':test'.
      > No tests found for given includes: [com.wss.web.HelloControllerTest.hello](--tests filter)
      * Try:
      > Run with --stacktrace option to get the stack trace.
      > Run with --info or --debug option to get more log output.
      > Run with --scan to get full insights.
      ```

    - 발생 원인
      > 작업 : 'test'를 실행하지 못했습니다.
    - 해결 방안
      > Settings > Build,Execution,Deployment > Build Tools > Gradle > "Run tests using:  IntelliJ IDEA"
    <div align="center">
        <img src="https://user-images.githubusercontent.com/66407386/186916849-d3d5b1fd-e559-45ad-9b83-0570cfa772c0.png" width="500" height="" />
    </div>
2. [Spring Boot, Lombok] Lombok gradle 5.x 이상 version dependency 설정 Error(Variable not initialized in the default constructor Error)

    - console.log내용
        ```text
        Error : variable name not initialized in the default construcor private fonal String name;
        ```

    - 발생 원인

      > 5.X 기준 이상으로 버전이 높기 때문에 발생되는 원인
    - 해결 방안
      ```shell
      dependencies {
        // 5.X ↓ 에서 사용
        implementation 'org.projectlombok:lombok'
        // 5.X ↑ 에서 사용
        compileOnly 'org.projectlombok:lombok'
        annotationProcessor 'org.projectlombok:lombok'
      } 
      ```
3. [Spring boot, gradle] build.gradle compile() Error

    - console.log내용
      ```text
      A problem occurred evaluating root project *
      > Could not find method compile() for arguments [org.rpingframework.boot.spring-boot:spring-boot-starter-data-jpa] on object of type org.gradel.api.internal.artifacts.dsl.dependencied.DefaultDependencyHandler.
      * Try :
      > Run with —info or —debug option to get more log output
      > Run with —scan to get full insights.
      * Exception is :
       Org.gradle.api.GradleScriptException : A problem occurred evaluating root project *
         at org.jetbrains.plugins.gradle.model.ProjectImportAction.execute(ProjectImportAction.java:116)
         at org.jetbrains.plugins.gradle.model.ProjectImportAction.execute(ProjectImportAction.java:42)
       Caused by : org.gradle.internal.metaobject.AbstractDynamicObject$CustomMessageMissingMethodException : Could not find method compile() for arguments [org.springframework.boot:spring-boot-starter-data-jpa] on object of type org.gradle.]
         at build_2zngfa9af8333mbfub8quooqi$_run_closure2.doCall*
         at build_2zngfa9af8333mbfub8quooqi.run *
         … 177more
       ```

    - 발생 원인
      > 원인은 아래 사진과 같이 compile, runtime, testCompile, testRuntime이 Gradle 4.10버전 부터 쓰이지 않게 되었고,<br/>
      > Gradle 7.0 버전부터는 아예 삭제되어 각각 implementation, runtimeOnly, testImplementation, testRuntimeOnly로 대체 되었다고 한다.<br/>
      > 실제로 내 프로젝트의 gradle 버전을 확인해보니 7.5 버전이었다.
      ```text
      gradle/gradle-wrapper.properties
      distributionUrl= ···/gradle-7.5-bin.zip
      ```
      <div align="center">
          <img src="https://user-images.githubusercontent.com/66407386/186916637-5767bad2-5370-48bc-8453-c9ca5d467dfb.png" width="500" height="" />
      </div>

    - 해결 방안
      ```shell
      dependencies {
        // 변경 전
        compile('org.springframework.boot:spring-boot-starter-data-jpa')
        compile('com.h2database:h2')
    
        // 변경 후
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        implementation 'com.h2database:h2'
      }
      ```

4. [Spring boot, gradle] nested exception is org.hibernate.exception.SQLGrammarException: could not prepare statement error

    - console.log내용
       ```text
       org.springframework.dao.InvalidDataAccessResourceUsageException: could not prepare statement; SQL [insert into posts (author, content, title) values (?, ?, ?)]; nested exception is org.hibernate.exception.SQLGrammarException: could not prepare statement
         at org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:259)
         at org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:233)
         at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.translateExceptionIfPossible(AbstractEntityManagerFactoryBean.java:551)
         at org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.java:61)
         at org.springframework.dao.support.DataAccessUtils.translateIfNecessary(DataAccessUtils.java:242)
         at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:152)
         at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
         at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:174)
         at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
         at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)
         at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
         at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215)
         at com.sun.proxy.$Proxy120.save(Unknown Source)
         at com.wss.domain.posts.PostsRepositoryTest.게시글저장_불러오기(PostsRepositoryTest.java:30)
         at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
         at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
         at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         at java.base/java.lang.reflect.Method.invoke(Method.java:566)
         at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
         at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
         at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
         at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
         at org.springframework.test.context.junit4.statements.RunBeforeTestExecutionCallbacks.evaluate(RunBeforeTestExecutionCallbacks.java:74)
         at org.springframework.test.context.junit4.statements.RunAfterTestExecutionCallbacks.evaluate(RunAfterTestExecutionCallbacks.java:84)
         at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
         at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)
         at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
         at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
         at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
         at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:251)
         at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:97)
         at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
         at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
         at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
         at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
         at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
         at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
         at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
         at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
         at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
         at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:190)
         at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
         at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:69)
         at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
         at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
         at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
         at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:235)
         at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:54)
       
       Caused by: org.hibernate.exception.SQLGrammarException: could not prepare statement
         at org.hibernate.exception.internal.SQLExceptionTypeDelegate.convert(SQLExceptionTypeDelegate.java:63)
         at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:37)
         at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:113)
         at org.hibernate.engine.jdbc.internal.StatementPreparerImpl$StatementPreparationTemplate.prepareStatement(StatementPreparerImpl.java:186)
         at org.hibernate.engine.jdbc.internal.StatementPreparerImpl.prepareStatement(StatementPreparerImpl.java:111)
         at org.hibernate.dialect.identity.GetGeneratedKeysDelegate.prepare(GetGeneratedKeysDelegate.java:52)
         at org.hibernate.id.insert.AbstractReturningDelegate.performInsert(AbstractReturningDelegate.java:40)
         at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3279)
         at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3885)
         at org.hibernate.action.internal.EntityIdentityInsertAction.execute(EntityIdentityInsertAction.java:84)
         at org.hibernate.engine.spi.ActionQueue.execute(ActionQueue.java:645)
         at org.hibernate.engine.spi.ActionQueue.addResolvedEntityInsertAction(ActionQueue.java:282)
         at org.hibernate.engine.spi.ActionQueue.addInsertAction(ActionQueue.java:263)
         at org.hibernate.engine.spi.ActionQueue.addAction(ActionQueue.java:317)
         at org.hibernate.event.internal.AbstractSaveEventListener.addInsertAction(AbstractSaveEventListener.java:330)
         at org.hibernate.event.internal.AbstractSaveEventListener.performSaveOrReplicate(AbstractSaveEventListener.java:287)
         at org.hibernate.event.internal.AbstractSaveEventListener.performSave(AbstractSaveEventListener.java:193)
         at org.hibernate.event.internal.AbstractSaveEventListener.saveWithGeneratedId(AbstractSaveEventListener.java:123)
         at org.hibernate.event.internal.DefaultPersistEventListener.entityIsTransient(DefaultPersistEventListener.java:185)
         at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:128)
         at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:55)
         at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:107)
         at org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:756)
         at org.hibernate.internal.SessionImpl.persist(SessionImpl.java:742)
         at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
         at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
         at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         at java.base/java.lang.reflect.Method.invoke(Method.java:566)
         at org.springframework.orm.jpa.SharedEntityManagerCreator$SharedEntityManagerInvocationHandler.invoke(SharedEntityManagerCreator.java:311)
         at com.sun.proxy.$Proxy117.persist(Unknown Source)
         at org.springframework.data.jpa.repository.support.SimpleJpaRepository.save(SimpleJpaRepository.java:666)
         at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
         at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
         at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         at java.base/java.lang.reflect.Method.invoke(Method.java:566)
         at org.springframework.data.repository.core.support.RepositoryMethodInvoker$RepositoryFragmentMethodInvoker.lambda$new$0(RepositoryMethodInvoker.java:289)
         at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:137)
         at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:121)
         at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:530)
         at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:286)
         at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:640)
         at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
         at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:164)
         at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:139)
         at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
         at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:81)
         at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
         at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)
         at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388)
         at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
         at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
         at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137)
         ... 42 more
       ```

    - 발생 원인
       ```text
       버전 이슈로 인한 문제 발생
       버전 상향을 하게 된다면 아래와 같이 해결방안을 처리하면 된다.
       ```

    - 해결 방안
       ```shell
       ## Spring.data.jpa
       spring.jpa.show-sql=true
       ## MySQL
       ## 원인 - 그래들 버전 이슈
       ## spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
       ## 원인해결 - 버전 변경 및 url 경로(본인 h2DB 경로) 설정
       spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
       spring.jpa.properties.hibernate.dialect.storage_engine=innodb
       spring.datasource.hikari.jdbc-url=jdbc:h2:mem://localhost/~/testdb;MODE=MYSQL
       spring.h2.console.enabled=true
       ```

   </details>
   
   <details markdown="1">
   <summary>MySQL 스키마</summary>

   - MySQL 설치
     - Window
         1. [MYSQL 공식홈페이지](www.mysql.com/) 접속

            <div align="center">
                  <img src="https://user-images.githubusercontent.com/66407386/187079915-263d613e-9764-4f93-9453-28cafbdd5d7d.png" width="500" height="" />
            </div>

         2. 공식홈페이지 상단 네비게이션에서 "DOWNLOADS" → MySQL Community(GPL) Downloads → MySQL Community Server → 해당 버전 맞게 설정

            <div align="center">
                  <img src="https://user-images.githubusercontent.com/66407386/187079905-f219b442-453c-4929-afeb-44db1f0e0639.png" width="500" height="" />
            </div>

         3. Developer Default" 선택 후 Next 클릭

            <div align="center">
                  <img src="https://user-images.githubusercontent.com/66407386/187079942-e0c98d46-13a6-4ad9-9439-40eb592949aa.png" width="500" height="" />
            </div>

         4. 포트 설정 

            <div align="center">
                  <img src="https://user-images.githubusercontent.com/66407386/187079993-8ceee9b5-d3a9-4889-9020-d3a3c17873cd.png" width="500" height="" />
            </div>


         5. MYSQL Accounts 설정
         
            <div align="center">
                  <img src="https://user-images.githubusercontent.com/66407386/187080012-7a94e7ca-198d-4555-a2c4-91e040857221.png" width="500" height="" />
            </div>

              > 조금 있다 사용해야하기 때문에 꼭 기억해두자. 

            <div align="center">
                  <img src="https://user-images.githubusercontent.com/66407386/187080059-64a1b46c-7e60-48ee-90e3-d13592d924ae.png" width="500" height="" />
            </div>

              > 위에 Accounts 설정했던 방식으로 설정 내용 작성

         6. MYSQL Workbench 실행
         
            <div align="center">
                  <img src="https://user-images.githubusercontent.com/66407386/187080042-031528cd-82da-48a2-8f34-ff3c26115fb1.png" width="500" height="" />
            </div>
         
         7. 아래 MySQL Setting 스키마 copy 하여 해당 본인에 맞게 설정

     - Linux (Homebrew를 이용한 MySQL 설치)
         1. "터미널" 접속 Homebrew가 설치되었는지 아래와 같이 확인
            ```shell
              // homebrew가 있는지 확인
              brew 
              
              // homebrew의 최신 버전 업데이트 진행
              brew update
            ```
          
         2. MySQL 설치 
            ```shell
              // MySQL 설치 명령어
              brew install mysql" 
              
              // 설치 완료가 뜨게 된다면 
              brew list로 설치가 되었는지 확인
            ```
           
         3. MySQL서버를 실행 및 설정 내용
            ```shell
             // Mysql서버를 실행싵킨다.
             mysql.server start
             
             Would you like to setup VALIDATE PASSWORD component?" - (해석) 비밀번호 관련 가이드 설정에 관한 질문이다.
                Yes = 복잡한 비밀번호 설정
                "No" = 쉬운 비밀번호 설정
             
             "Remove anonymous users?" - (해석) 사용자 설정을 묻는 질문이다.
                "Yes" = "mysql -u root" 처럼 -u 옵션이 필요하다.
                No = "mysql"처럼 -u 옵션이 필요 없다.
             
             "Disallow root login remotely?" - (해석) 다른 IP에서 root 아이디로 원격접속을 설청하는 질문이다.
                "Yes" = 원격접속 불가능
                No = 원격접속 가능
            
            
              "Remove test database and access to it?" - (해석) Test 데이터베이스 관련 설정하는 질문이다.
                "Yes" = Test 데이터베이스 제거
                No = Test 데이터베이스 유지

              "Reload privilege tables now?" - (해석) 변경된 권한을 테이블에 적용하는 설정에 대한 질문이다.
                "Yes" = 적용시킨다.
                No = 적용시키지 않는다.
            ```
           
         4. MySQL 실행 및 아래 MySQL Setting 스키마 copy 하여 해당 본인에 맞게 설정
   
   - MySQL Setting 스키마 - Table 구축은 나중에
      ```mysql
      # CREATE USER '<본인 이름>'@localhost IDENTIFIED BY '****';
      CREATE USER 'jinhong'@localhost IDENTIFIED BY '****';
   
      # GRANT ALL PRIVILEGES ON <web_service_study_DB>.*(하위) '본인이름'@localhost;
      GRANT ALL PRIVILEGES ON web_service_study_DB.* TO 'jinhong'@localhost;
      SHOW DATABASES;
   
      use web_service_study_DB;
   
      # 삭제 건들지마시오.
      # DROP DATABASE WEBSERVICESTUDY_DB;
   
      CREATE DATABASE web_service_study_DB default CHARACTER SET UTF8;
   
      CREATE DATABASE WEBSERVICESTUDY_DB;
      ```
     
   </details>

#### 참고자료 및 출처
1. [윈도우 MYSQL 설정](https://goddaehee.tistory.com/277)

---
<div align="center">
   문제 발생시 이슈에 코멘트를 달아주시면 감사하겠습니다.
</div>
