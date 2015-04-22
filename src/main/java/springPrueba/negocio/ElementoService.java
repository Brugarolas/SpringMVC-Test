package springPrueba.negocio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import springPrueba.dao.ElementoDAO;
import springPrueba.modelo.Elemento;

@Service
@Transactional
public class ElementoService {
	@Autowired
	private ElementoDAO elementoDAO;
	
	public List<Elemento> getAll() {
		return Lists.newArrayList(elementoDAO.findAll());
	}
	
	public Elemento get(long id) {
		return elementoDAO.findOne(id);
	}
	
	public Elemento get(String codigo) {
		return elementoDAO.getByCodigo(codigo);
	}
	
	public void save(String codigo, String etiqueta, long lista) {
		Elemento elemento = new Elemento(codigo, etiqueta);
		elemento.setLista(lista);
		
		elementoDAO.save(elemento);
	}
	
	public void delete(long id) {
		elementoDAO.delete(id);
	}
	
	public void update(long id, String codigo, String etiqueta, long lista) {
		Elemento elemento = elementoDAO.findOne(id);
		elemento.setCodigo(codigo);
		elemento.setEtiqueta(etiqueta);
		elemento.setLista(lista);
		elementoDAO.save(elemento);
	}
}
