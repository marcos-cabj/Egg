package com.redsocial.dominio.usuario;

import com.redsocial.dominio.direccion.Direccion;
import com.redsocial.dominio.direccion.DireccionService;
import com.redsocial.dominio.mascota.Mascota;
import com.redsocial.dominio.mascota.MascotaService;
import com.redsocial.enumeraciones.Rol;
import com.redsocial.persistencia.UsuarioDAO;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class UsuarioService {
// RECORDAR QUE SIEMPRE, LA LOGICA DEBE ESTAR EN EL SERVICIO CORRESPONDIENTE

	private final UsuarioDAO daoUsuario;
	private final DireccionService direccionService;
	private final MascotaService mascotaService;

	public UsuarioService() {
		this.daoUsuario = new UsuarioDAO();
		this.direccionService = new DireccionService();
		this.mascotaService = new MascotaService();
	}

	public void crearUsuario(String correoElectronico, String clave, String dni, Date nacimiento, String pais,
		String provincia) throws Exception {

		try {
			// VALIDAMOS
			if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
				throw new Exception("Debe indicar el correo electrónico");
			}
			if (correoElectronico.contains("@") == false) {
				throw new Exception("El correo electrónico es incorrecto");
			}
			if (clave == null || clave.trim().isEmpty()) {
				throw new Exception("Debe indicar la clave");
			}
			if (clave.length() < 6) {
				throw new Exception("La clave no puede tener menos de 6 caracteres");
			}

			// CREAMOS EL USUARIO
			Usuario usuario = new Usuario();

			Direccion nuevaDire = direccionService.crearDireccion(pais, provincia);

			usuario.setCorreoElectronico(correoElectronico);
			usuario.setClave(clave);
			usuario.setDni(dni);
			usuario.setNacimiento(nacimiento);
			usuario.setDireccion(nuevaDire);
			usuario.setRol(Rol.ADMIN);
			usuario.setId(UUID.randomUUID().toString());//CADENA DE CARACTERES

			// DEJO INDEPENDIENTE EL SET DE CARGAR MASCOTAS, NO OBLIGO QUE CUANDO SE LOG CARGUE SU MASCOTA
			daoUsuario.guardarUsuario(usuario);
		} catch (Exception e) {
			System.out.println("No se creeo el usuario" + e.getMessage());
		}
	}

	public void modificarClaveUsuario(String id, String claveActual, String nuevaClave) throws Exception {

		try {
			// VALIDAMOS
			if (id == null || id.trim().isEmpty()) {
				throw new Exception("Debe indicar el usuario");
			}
			if (claveActual == null || claveActual.trim().isEmpty()) {
				throw new Exception("Debe indicar la clave actual");
			}
			if (nuevaClave == null || nuevaClave.trim().isEmpty()) {
				throw new Exception("Debe indicar la clave nueva");
			}
			// BUSCAMOS
			Usuario usuario = buscarUsuarioPorId(id);

			// VALIDAMOS
			if (!usuario.getClave().equals(claveActual)) {
				throw new Exception("La clave actual no es la regsitrada en el sistema para el correo "
					+ "electrónico indicado");
			}
			// MODIFICAMOS
			usuario.setClave(nuevaClave);
			daoUsuario.modificarUsuario(usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
	}

	public void eliminarUsuario(String correoElectronico) throws Exception {

		try {
			// VALIDAMOS 
			if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
				throw new Exception("Debe indicar el correo electrónico");
			}
			daoUsuario.eliminarUsuarioCorreo(correoElectronico);
			System.out.println("USUARIO ELIMINADO CON EXITO");
		} catch (Exception e) {
			throw e;
		}
	}

	public Usuario buscarUsuarioPorCorreoElectronico(String correoElectronico) throws Exception {
		try {

			// VALIDAMOS
			if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
				throw new Exception("Debe indicar el correo electrónico");
			}

			Usuario usuario = daoUsuario.buscarUsuarioPorCorreoElectronico(correoElectronico);
			return usuario;
		} catch (Exception e) {
			throw e;
		}
	}

	public Usuario buscarUsuarioPorId(String id) throws Exception {

		try {
			// VALIDAMOS
			if (id == null) {
				throw new Exception("Debe indicar el id del usuario");
			}
			Usuario usuario = daoUsuario.buscarUsuarioPorId(id);
			return usuario;
		} catch (Exception e) {
			throw e;
		}
	}

	public Collection<Usuario> listaUsuarios() throws Exception {

		try {
			Collection<Usuario> usuarios = daoUsuario.listarUsuarios();
			return usuarios;
		} catch (Exception e) {
			throw e;
		}
	}

	public void imprimirUsuarios() throws Exception {
		try {
			// LISTAMOS LOS USUARIOS
			Collection<Usuario> usuarios = listaUsuarios();

			// IMPRIMIMOS LOS USUARIOS - SOLO ALGUNOS ATRIBUTOS....
			if (usuarios.isEmpty()) {
				throw new Exception("No existen usuarios para imprimir");
			} else {
				for (Usuario u : usuarios) {
					System.out.println("*****************************************");
					System.out.println(" Correo Electronico:" + u.getCorreoElectronico()
						+ "\n DNI:" + u.getDni()
						+ "\n Fecha Nacimiento:" + u.getNacimiento().getDate() + "/"
						+ u.getNacimiento().getMonth() + "/" + u.getNacimiento().getYear()
						+ "\n Mascota:" + u.getMascota().getApodo());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
	}

	public void imprimirUnUsuario(String correoElectronico) throws Exception {
		System.out.println(daoUsuario.buscarUsuarioPorCorreoElectronico(correoElectronico));
	}

	public void cargarMascotaUsuario(String apodo, String raza, String idUsuario) throws Exception {
		Usuario usuario = daoUsuario.buscarUsuarioPorId(idUsuario);
		Mascota nueva = mascotaService.crearMascota(apodo, raza);
		usuario.setMascota(nueva);
		daoUsuario.modificarUsuario(usuario); // UTILIZO EL METODO DEL DAO           
	}
}
