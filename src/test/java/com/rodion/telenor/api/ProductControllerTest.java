package com.rodion.telenor.api;

import com.rodion.telenor.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductController productController;

    @Autowired
    private ProductService productService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.productController).build();
    }

    @Test
    void setProductService() {
        Assert.assertNotNull(productService);
    }

    @Test
    void welcome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response", Matchers.is("Welcome to dockerized springboot task")));
    }

    @Test
    void loadData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/data"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response", Matchers.is("Data added to in-memory DB")));
    }

    @Test
    void product() throws Exception {
        //loading data to DB
        mockMvc.perform(MockMvcRequestBuilders.get("/data"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response", Matchers.is("Data added to in-memory DB")));

        //reading data from DB
        mockMvc.perform(MockMvcRequestBuilders.get("/product?type=phone&&city=stoc&&min_price=100&&max_price=1000&&property_color=brun"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0]", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].type", Matchers.is("phone")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].properties", Matchers.is("color:brun")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].type", Matchers.not("subscription")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].city", Matchers.containsStringIgnoringCase("stoc")));

        //reading data which could not be in DB (subscription and color are incompatible)
        mockMvc.perform(MockMvcRequestBuilders.get("/product?type=subscription&&property=color"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.empty()));
    }
}