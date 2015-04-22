package springPrueba.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springPrueba.modelo.Elemento;

@Repository
public interface ElementoDAO extends CrudRepository<Elemento, Long> {
	public Elemento getByCodigo(String codigo);
}
