package springPrueba.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Elemento")
public class Elemento {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@ManyToOne //(cascade=CascadeType.ALL)
	@JoinColumn(nullable = false, insertable = false, updatable = false)
	public Lista lista;
	
	@NotNull
	@Column(name="lista")
	public long listaID;
	
	@NotNull
	public String codigo;
	
	@NotNull
	public String etiqueta;
	
	public Elemento() {}
	
	public Elemento(long id) { this.id = id; }
	
	public Elemento(String codigo, String etiqueta) {
		this.codigo = codigo;
		this.etiqueta = etiqueta;
	}
	
	public Elemento(long id, String codigo, String etiqueta) {
		this.id = id;
		this.codigo = codigo;
		this.etiqueta = etiqueta;
	}

	public long getId() {
		return id;
	}

	public Lista getLista() {
		return lista;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}
	
	public void setLista(long lista) {
		this.listaID = lista;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Elemento)) {
			return false;
		}
		Elemento other = (Elemento) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return id + ". " + codigo + ": " + etiqueta + ".";
	}
}
