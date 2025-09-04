package ruMihailTarasov7.Language;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
<<<<<<< HEAD:src/test/java/ru/mihail/tarasov7/Language/MainControllerTest.java
import ru.mihail.tarasov7.Language.Controllers.MainController;
=======
import ruMihailTarasov7.Language.Controllers.MainController;
>>>>>>> 2f982be0e844f0f88e81853a9e801357bcd4150b:src/test/java/ruMihailTarasov7/Language/MainControllerTest.java

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
<<<<<<< HEAD:src/test/java/ru/mihail/tarasov7/Language/MainControllerTest.java
    public void firstPageTest() throws Exception{
       mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("firstPage"))
               .andExpect(content().string(containsString("Главная страница")));
=======
    public void mainPageTest()throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("firstPage"))
                .andExpect(content().string(containsString("Главная страница")))
        ;
>>>>>>> 2f982be0e844f0f88e81853a9e801357bcd4150b:src/test/java/ruMihailTarasov7/Language/MainControllerTest.java
    }

}

