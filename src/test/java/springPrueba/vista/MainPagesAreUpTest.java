package springPrueba.vista;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static springPrueba.Methods.*;
import springPrueba.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MainPagesAreUpTest {
    @Autowired
    WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in webapp-mode (same config as spring-boot)
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
    public void verifiesHomePageLoads() throws Exception {
    	assertResponseContains(this.mockMvc, "/", "Ver todas las Listas", "Ver todos los Elementos");
    }
    
    @Test
    public void verifiesElementosPageLoads() throws Exception {
    	assertResponseContains(this.mockMvc, "/elemento", "Añadir un nuevo Elemento"); 
    }
    
    @Test
    public void verifiesListasPageLoads() throws Exception {
    	assertResponseContains(this.mockMvc, "/lista", "Añadir una nueva Lista"); 
    }
    
    @Test
    public void verifiesLoginPageLoads() throws Exception {
    	assertResponseContains(this.mockMvc, "/login", "Iniciar sesión");
        
    }
}

