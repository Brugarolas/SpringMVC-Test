package springPrueba.negocio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import springPrueba.dao.ListaDAO;
import springPrueba.modelo.Lista;

@Service
@Transactional
public class ListaService {	
	@Autowired
	private ListaDAO listaDAO;
	
	public List<Lista> getAll() {
		return Lists.newArrayList(listaDAO.findAll());
	}
	
	public Lista get(long id) {
		return listaDAO.findOne(id);
	}
	
	public Lista get(String codigo) {
		return listaDAO.getByCodigo(codigo);
	}
	
	public void save(String codigo, String nombre) {
		listaDAO.save(new Lista(codigo, nombre));
	}
	
	public void delete(long id) {
		listaDAO.delete(id);
	}
	
	public void update(long id, String codigo, String nombre) {
		Lista lista = listaDAO.findOne(id);
		lista.setCodigo(codigo);
		lista.setNombre(nombre);
		listaDAO.save(lista);
	}
}
