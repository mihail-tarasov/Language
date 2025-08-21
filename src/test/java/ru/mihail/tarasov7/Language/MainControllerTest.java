package ru.mihail.tarasov7.Language;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.mihail.tarasov7.Language.Controllers.HomeController;

import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
        org.hamcrest.Matchers.containsString;

@WebMvcTest(HomeController.class)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void homePageTest()throws Exception{
        mockMvc.perform(get("/first"))
                .andExpect(status().isOk())
                .andExpect(view().name("firstPage"))
                .andExpect(content().string(containsString("Главная страница")));
    }

}

