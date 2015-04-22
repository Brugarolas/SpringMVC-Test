package springPrueba.vista;

import static springPrueba.Methods.assertResponseContains;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import springPrueba.Application;
import springPrueba.modelo.Elemento;
import springPrueba.modelo.Lista;
import springPrueba.negocio.ElementoService;
import springPrueba.negocio.ListaService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElementoControllerTest {
	@Autowired
    WebApplicationContext wac;
	
	@Autowired
	ListaService listaService;
	
	@Autowired
	ElementoService elementoService;
	
	private MockMvc mockMvc;
	
	private Lista l;
	private Elemento e1;
	private Elemento e2;
	
	@Before
    public void setUp(){        
        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in webapp-mode (same config as spring-boot)
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
        l = new Lista("TEST", "Test lista");
        e1 = new Elemento("ELE1", "Etiqueta del primer elemento");
        e2 = new Elemento("ELE2", "Etiqueta elemento 2");
	}
	
	@Test
    public void TestACheckElementWorks() throws Exception {
    	//Comprobamos que las páginas principales funcionan
    	assertResponseContains(this.mockMvc, "/lista");
    	assertResponseContains(this.mockMvc, "/elemento");
    	
    	//Comprobamos que no exista ya una lista ni elementos con los mismos códigos
    	String findUrlLista = "/lista/get-by-codigo?codigo=" + l.getCodigo();
        assertResponseContains(this.mockMvc, findUrlLista, "No se ha encontrado o no se ha especificado ninguna Lista.");
        
        String findUrlElem1 = "/elemento/get-by-codigo?codigo=" + e1.getCodigo();
        assertResponseContains(this.mockMvc, findUrlElem1, "No se ha encontrado o no se ha especificado ningún Elemento.");
        
        String findUrlElem2 = "/elemento/get-by-codigo?codigo=" + e2.getCodigo();
        assertResponseContains(this.mockMvc, findUrlElem2, "No se ha encontrado o no se ha especificado ningún Elemento.");
    }
	
	@Test
	public void TestBCheckSaveElement() throws Exception {
		//Comprobamos que no haya ningún error al añadir la lista ni los elementos
    	String urlAddLista = "/lista/save?codigo=" + l.getCodigo() + "&nombre=" + l.getNombre();
    	assertResponseContains(this.mockMvc, urlAddLista, "¡Lista añadida correctamente!");
    	
    	long idLista = listaService.get(l.getCodigo()).getId();
    	
    	String urlAddElem1 = "/elemento/save?codigo=" + e1.getCodigo() + "&etiqueta=" + e1.getEtiqueta() + "&lista=" + idLista;
    	assertResponseContains(this.mockMvc, urlAddElem1, "¡Elemento añadido correctamente!");
    	
    	String urlAddElem2 = "/elemento/save?codigo=" + e2.getCodigo() + "&etiqueta=" + e2.getEtiqueta() + "&lista=" + idLista;
    	assertResponseContains(this.mockMvc, urlAddElem2, "¡Elemento añadido correctamente!");
    	
    	//Comprobamos que la lista y los elementos se hayan añadido correctamente
    	String getUrlLista = "/lista/get?id=" + idLista;
    	assertResponseContains(this.mockMvc, getUrlLista, l.getCodigo(), l.getNombre());
    	
    	long idElem1 = elementoService.get(e1.getCodigo()).getId();    	
    	String getUrlElem1 = "/elemento/get?id=" + idElem1;
    	System.out.println("ELEM " + getUrlElem1);
    	assertResponseContains(this.mockMvc, getUrlElem1, e1.getCodigo(), e1.getEtiqueta());
    	
    	long idElem2 = elementoService.get(e2.getCodigo()).getId();
    	String getUrlElem2 = "/elemento/get?id=" + idElem2;
    	assertResponseContains(this.mockMvc, getUrlElem2, e2.getCodigo(), e2.getEtiqueta());    	
	}
	
	@Test
	public void TestCCheckUpdateElemento() throws Exception {
		//Comprobamos que no haya ningún error al actualizar el segundo elemento
		String nuevaEtiqueta = "Etiqueta del segundo elemento";
		long idElem2 = elementoService.get(e2.getCodigo()).getId();
		long idLista = listaService.get(l.getCodigo()).getId();
		String updateUrl = "/elemento/update?id=" + idElem2 + "&codigo=" + e2.getCodigo() + "&etiqueta=" + nuevaEtiqueta + "&lista=" + idLista;
		assertResponseContains(this.mockMvc, updateUrl, "¡Elemento actualizado correctamente!");
		
		//Comprobamos que se haya actualizado correctamente
		String getUrlElem2 = "/elemento/get?id=" + idElem2;
		assertResponseContains(this.mockMvc, getUrlElem2, e2.getCodigo(), nuevaEtiqueta);
	}
	
	@Test
	public void TestDCheckDeleteElemento() throws Exception {
		//Comprobamos que no haya ningún error al borrar el segundo elemento
		long idElem2 = elementoService.get(e2.getCodigo()).getId();
		String deleteUrl = "/elemento/delete?id=" + idElem2;
		assertResponseContains(this.mockMvc, deleteUrl, "¡Elemento eliminado correctamente!");
		
		//Comprobamos que se ha eliminado correctamente
		String findUrlElem = "/elemento/get-by-codigo?codigo=" + e2.getCodigo();
        assertResponseContains(this.mockMvc, findUrlElem, "No se ha encontrado o no se ha especificado ningún Elemento.");
	}
	
	@Test
	public void TestECheckDeleteLista() throws Exception {
		//Comprobamos que no hay ningún error al eliminar la lista
		long idLista = listaService.get(l.getCodigo()).getId();
        String urlDelete = "/lista/delete?id=" + idLista;
        assertResponseContains(this.mockMvc, urlDelete, "¡Lista eliminada correctamente!");
        
        //Comprobamos que la lista ya no existe
        String findUrl = "/lista/get-by-codigo?codigo=" + l.getCodigo();
        assertResponseContains(this.mockMvc, findUrl, "No se ha encontrado o no se ha especificado ninguna Lista.");
        
	    //Comprobamos que el elemento tampoco existe (se ha borrado con la lista)
	    String findUrlElem = "/elemento/get-by-codigo?codigo=" + e1.getCodigo();
	    assertResponseContains(this.mockMvc, findUrlElem, "No se ha encontrado o no se ha especificado ningún Elemento.");
	}
	
}
