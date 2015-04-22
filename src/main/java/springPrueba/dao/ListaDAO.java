package springPrueba.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springPrueba.modelo.Lista;

@Repository
public interface ListaDAO extends CrudRepository<Lista, Long> {
	public Lista getByCodigo(String codigo);
}
