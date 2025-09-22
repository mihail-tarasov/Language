package ruMihailTarasov7.Language;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import ruMihailTarasov7.Language.Controllers.MainController;

import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
        org.hamcrest.Matchers.containsString;

@WebMvcTest(MainController.class)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test

    public void firstPageTest() throws Exception{
       mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("firstPage"))
               .andExpect(content().string(containsString("Главная страница")));

    }

}

