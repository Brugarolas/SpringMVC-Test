package springPrueba.vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springPrueba.negocio.ElementoService;
import springPrueba.negocio.ListaService;
import springPrueba.vista.Mensaje.Tipo;

@Controller
public class ElementoController {
	
	@Autowired
	private ElementoService elementoService;
	
	@Autowired
	private ListaService listaService;
	
	@RequestMapping(value = "/elemento", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getData() {  
		ModelAndView model = new ModelAndView("viewElementos");
		model.addObject("elementos", elementoService.getAll());
		model.addObject("listas", listaService.getAll());
 
		return model; 
	}
	
	@RequestMapping(value = "/elemento/get")
	@ResponseBody
	public ModelAndView get(long id) {
		ModelAndView model = new ModelAndView("viewElemento");
		
		try {
			model.addObject("elemento", elementoService.get(id));			
		} catch (Exception e) {
			return new Mensaje(Tipo.WARNING, "No se ha encontrado o no se ha especificado ningún Elemento.");
		}
		
		model.addObject("listas", listaService.getAll());
		
		return model;
	}
	
	@RequestMapping(value = "/elemento/save")
	@ResponseBody
	public ModelAndView create(String codigo, String etiqueta, long lista) {		
		try {
			elementoService.save(codigo, etiqueta, lista);
		} catch (Exception ex) {
			return new Mensaje(Tipo.WARNING, "Error mientras se creaba el Elemento: " + ex.getMessage());
		}
		
		return new Mensaje(Tipo.SUCCESS, "¡Elemento añadido correctamente!");
	}
	
	@RequestMapping(value = "/elemento/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam(value="id", required=false, defaultValue="-1") long id) {		
		try {
			elementoService.delete(id);
		} catch (Exception ex) {
			return new Mensaje(Tipo.WARNING, "No se ha encontrado o no se ha especificado ningún Elemento a eliminar.");
		}
		
		return new Mensaje(Tipo.SUCCESS, "¡Elemento eliminado correctamente!");
	}
	
	@RequestMapping(value = "/elemento/update")
	@ResponseBody
	public ModelAndView update(long id, String codigo, String etiqueta, long lista) {
		try {
			elementoService.update(id, codigo, etiqueta, lista);
		} catch (Exception ex) {
			return new Mensaje(Tipo.WARNING, "Error mientras se guardaba el Elemento: " + ex.getMessage());
		}
		return new Mensaje(Tipo.SUCCESS, "¡Elemento actualizado correctamente!");
	}
	
	@RequestMapping(value = "/elemento/get-by-codigo")
	@ResponseBody
	public ModelAndView getByCodigo(String codigo) {
		try {
			return new Mensaje(Tipo.INFO, "EL ID del Elemento es " + elementoService.get(codigo).getId());
		} catch (Exception e) {
			return new Mensaje(Tipo.WARNING, "No se ha encontrado o no se ha especificado ningún Elemento.");
		}
	}

}
