package com.wss.webservicestudy.web.user.controller;
import com.wss.webservicestudy.WebservicestudyApplication;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import com.wss.webservicestudy.web.user.dto.UserLoginReqDto;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import com.wss.webservicestudy.web.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = WebservicestudyApplication.class)
@AutoConfigureMockMvc
@WithUserDetails(value = "jieun0502@gmail.com")
public class UserControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testUserDetails() throws Exception {
        User user = userRepository.findByEmail("jieun0502@gmail.com");

        mockMvc.perform(get("/api/user/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.result.nickname").value(user.getNickname()));
    }
    @Test
    public void signup() throws Exception{
        // given
        String email = "jeppa@gmail.com";
        String password = "abaac123";
        String name = "지은";

        UserLoginReqDto requestDto = new UserLoginReqDto(email, password);
        HttpEntity<UserLoginReqDto> requestEntity = new HttpEntity<>(requestDto);
        String url = getUrl() + "/" + "signup";
        ResponseEntity<TokenInfo> responseEntity = restTemplate.postForEntity(url, requestDto, TokenInfo.class);
//        User user = userService.findCurrentUser();
//        assertThat(user).isNotNull();
//        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void login() throws Exception{
        // given
        String email = "jeppa@gmail.com";
        String password = "abaac123";
        String name = "지은";

        UserLoginReqDto requestDto = new UserLoginReqDto(email, password);
        HttpEntity<UserLoginReqDto> requestEntity = new HttpEntity<>(requestDto);
        String url = getUrl() + "/" + "login";
        ResponseEntity<TokenInfo> responseEntity = restTemplate.postForEntity(url, requestDto, TokenInfo.class);
    }
    String getUrl(){
        return "http://localhost:" + port + "/api/user";
    }
}
