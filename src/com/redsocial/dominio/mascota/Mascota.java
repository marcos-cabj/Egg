package com.redsocial.dominio.mascota;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // ESTA ANOTACION, AVISA QUE ESTA CLASE SE CONVERTIRA EN TABLA
public class Mascota {
//CADA ATRIBUTO DEFINIDO EN UNA CLASE DEL TIPO ENTIDAD, SE CONVIERTE EN  UNA COLUMNA EN MI TABLA

	@Id // DECLARO QUE EL SIGUIENTE ATRIBUTO SERA EL ID
	@GeneratedValue
	private String id;
	private String apodo;
	private String raza;
	private String atrib1;

	public Mascota() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	@Override
	public String toString() {
		return "Mascota{" + "id=" + id + ", apodo=" + apodo + ", raza=" + raza + '}';
	}

}
