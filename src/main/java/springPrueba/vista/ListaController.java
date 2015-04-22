package springPrueba.vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springPrueba.negocio.ListaService;
import springPrueba.vista.Mensaje.Tipo;

@Controller
public class ListaController {

	@Autowired
	private ListaService listaService;
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getdata() { 
		ModelAndView model = new ModelAndView("viewListas");
		model.addObject("lists", listaService.getAll());
 
		return model; 
	}

	@RequestMapping(value = "/lista/save")
	@ResponseBody
	public ModelAndView create(String codigo, String nombre) {		
		try {
			listaService.save(codigo, nombre);
		} catch (Exception ex) {
			return new Mensaje(Tipo.WARNING, "Error mientras se creaba la Lista: " + ex.getMessage());
		}
		
		return new Mensaje(Tipo.SUCCESS, "¡Lista añadida correctamente!");
	}

	@RequestMapping(value = "/lista/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam(value="id", required=false, defaultValue="-1") long id) {		
		try {
			listaService.delete(id);
		} catch (Exception ex) {
			return new Mensaje(Tipo.WARNING, "No se ha encontrado o no se ha especificado ninguna Lista a eliminar.");
		}
		
		return new Mensaje(Tipo.SUCCESS, "¡Lista eliminada correctamente!");
	}
	
	@RequestMapping(value = "/lista/get")
	@ResponseBody
	public ModelAndView get(long id) {
		ModelAndView model = new ModelAndView("viewLista");
		try {
			model.addObject("list", listaService.get(id));
		} catch (Exception e) {
			return new Mensaje(Tipo.WARNING, "No se ha encontrado o no se ha especificado ninguna Lista.");
		}
		 
		return model; 
	}
	
	@RequestMapping(value = "/lista/update")
	@ResponseBody
	public ModelAndView update(long id, String codigo, String nombre) {
		try {
			listaService.update(id, codigo, nombre);
		} catch (Exception ex) {
			return new Mensaje(Tipo.WARNING, "Error mientras se guardaba la Lista: " + ex.getMessage());
		}
		return new Mensaje(Tipo.SUCCESS, "¡Lista actualizada correctamente!");
	}
	
	@RequestMapping(value = "/lista/get-by-codigo")
	@ResponseBody
	public ModelAndView getByCodigo(String codigo) {
		try {
			return new Mensaje(Tipo.INFO, "El ID de la Lista es: " + listaService.get(codigo).getId());
		} catch (Exception e) {
			return new Mensaje(Tipo.WARNING, "No se ha encontrado o no se ha especificado ninguna Lista.");
		}		
	}
}
