package com.wss.webservicestudy.web.domain.home;

import com.wss.webservicestudy.web.home.controller.HelloController;
import com.wss.webservicestudy.web.home.dto.HelloResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void 롬복_기능_테스트() {
        String name = "Jeongjinhong";
        int amount = 10000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        /**
         * then
         * assertThat(dto.getName()).isEqualTo(name);
         * assertThat(dto.getAmount()).isEqualTo(amount);
         */
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "jeongjinhong";
        int amount = 10000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
