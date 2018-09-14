package com.imooc.security;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public  void upload() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes()))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }



    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
               //.param("username","aa")
                .param("age","13")
                .param("ageTo","13-20")
              /*  .param("size","3")
               .param("page","2")
                .param("sort","age,desc")
                .param("sort","username,asc")*/
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));

    }

    @Test
    public void whenCreateSuccess() throws Exception {

        String content = "{\"age\":\"17\",\"username\":1,\"ageTo\":null}";
        String reuslt = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(reuslt);
    }

}
