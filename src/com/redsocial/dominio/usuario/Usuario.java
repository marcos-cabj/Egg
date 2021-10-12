package com.redsocial.dominio.usuario;

import com.redsocial.dominio.direccion.Direccion;
import com.redsocial.dominio.mascota.Mascota;
import com.redsocial.enumeraciones.Rol;
import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity // ESTA ANOTACION, AVISA QUE ESTA CLASE SE CONVERTIRA EN TABLA
public class Usuario implements Serializable {
//CADA ATRIBUTO DEFINIDO EN UNA CLASE DEL TIPO ENTIDAD, SE CONVIERTE EN  UNA COLUMNA EN MI TABLA

	@Id
	@GeneratedValue
	private String id;

	@Column(unique = true)
	private String correoElectronico;

	private String clave;

	@NotNull // Que no sea nulo
	@Column(unique = true)// PARA QUE NO PERMITE DUPLICAR ESTE CAMPO EN OTROS REGISTROS.
	private String dni;

	// RELACION DE UNO A UNO
	@OneToOne(cascade = CascadeType.REMOVE)// HACIENDO REFERENCIA A QUE SI SE ELIMINA UN "USUARIO" SE ELIMINE EN CASCADA 
	private Mascota mascota;

	@OneToOne(cascade = CascadeType.REMOVE)// ESTO INDICADO PARA QUE SI ELIMINO AL PADRE,ELIMINO EL VINCULADO
	private Direccion direccion;

	// SIEMPRE QUE MANEJO FECHAS, DEBO UTILIZAR ESTA ANOTACION
	@Temporal(TemporalType.DATE)
	private Date nacimiento;

	// SIEMPRE QUE MANEJO ENUMERACIONES, DEBO UTILIZAR ESTA ANOTACION
	@Enumerated(EnumType.STRING)
	private Rol rol;

	// DEJO MIS CONSTRUCTORES
	public Usuario() {
	}

	public Usuario(String correoElectronico, String clave, String dni, Direccion direccion, Date nacimiento, Rol rol) {
		this.correoElectronico = correoElectronico;
		this.clave = clave;
		this.dni = dni;
		this.direccion = direccion;
		this.nacimiento = nacimiento;
		this.rol = rol;
	}

	// DEJO MIS GET Y SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario{" + "id=" + id + ", correoElectronico=" + correoElectronico + ", clave=" + clave + ", dni=" + dni + ", mascota=" + mascota + ", direccion=" + direccion + ", nacimiento=" + nacimiento + ", rol=" + rol + '}';
	}

}
