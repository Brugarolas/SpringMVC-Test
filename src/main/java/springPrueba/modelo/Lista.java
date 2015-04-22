package springPrueba.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Lista")
public class Lista {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@NotNull
	public String codigo;
	
	@NotNull
	public String nombre;
	
	@OneToMany(mappedBy="lista", targetEntity = Elemento.class, orphanRemoval = true,
			cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	public Set<Elemento> elementos;
	
	public Lista() {}
	
	public Lista(long id) { this.id = id; }
	
	public Lista(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public Lista(long id, String codigo, String nombre) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		if (!(obj instanceof Lista)) {
			return false;
		}
		Lista other = (Lista) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return id + ". " + codigo + ": " + nombre + ".";
	}
}
