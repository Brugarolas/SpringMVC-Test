package springPrueba.vista;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
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
import springPrueba.modelo.Lista;
import springPrueba.negocio.ListaService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListaControllerTest {
	@Autowired
    WebApplicationContext wac;
	
	@Autowired
	ListaService listaService;
	
    private MockMvc mockMvc;
    
    private Lista l;
    private long id = -1;
    
    @Before
    public void setUp(){        
        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in webapp-mode (same config as spring-boot)
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
        l = new Lista("TEST", "Test lista");
    }
    
    public long getId() {
    	if (id == -1) {
    		id = listaService.get(l.getCodigo()).getId();
    	}
    	return id;
    }
    
    @Test
    public void TestACheckListWorks() throws Exception {
    	//Comprobamos que la página principal funciona
    	assertResponseContains(this.mockMvc, "/lista");
    	
    	//Comprobamos que no exista ya una lista con el mismo código
    	String findUrl = "/lista/get-by-codigo?codigo=" + l.getCodigo();
        assertResponseContains(this.mockMvc, findUrl, "No se ha encontrado o no se ha especificado ninguna Lista.");
    }
    
    @Test
    public void TestBCheckSaveList() throws Exception {
    	//Comprobamos que no haya ningún error al añadir la lista
    	String urlAdd = "/lista/save?codigo=" + l.getCodigo() + "&nombre=" + l.getNombre();
    	assertResponseContains(this.mockMvc, urlAdd, "¡Lista añadida correctamente!");
    	
    	//Comprobamos que la lista se ha añadido correctamente
    	String getUrl = "/lista/get?id=" + getId();
    	assertResponseContains(this.mockMvc, getUrl, l.getCodigo(), l.getNombre());
    }
    
    @Test
    public void TestCCheckUpdateList() throws Exception {  
    	//Comprobamos que no haya ningún error al actualizar la lista
    	String nuevoNombre = "Nuevo nombre de prueba";
    	String updateUrl = "/lista/update?id=" + getId() + "&codigo=" + l.getCodigo() + "&nombre=" + nuevoNombre;
    	assertResponseContains(this.mockMvc, updateUrl, "¡Lista actualizada correctamente!");
    	
    	//Comrpobamos que la lista se ha actualizado correctamente
    	String getUrl = "/lista/get?id=" + getId();
    	assertResponseContains(this.mockMvc, getUrl, l.getCodigo(), nuevoNombre);
    }
    
    @Test
    public void TestDCheckDeleteList() throws Exception {
    	//Comprobamos que no hay ningún error al eliminar la lista
        String urlDelete = "/lista/delete?id=" + getId();
        assertResponseContains(this.mockMvc, urlDelete, "¡Lista eliminada correctamente!");
        
        //Comprobamos que la lista ya no existe
        String findUrl = "/lista/get-by-codigo?codigo=" + l.getCodigo();
        assertResponseContains(this.mockMvc, findUrl, "No se ha encontrado o no se ha especificado ninguna Lista.");
    }
    
}
