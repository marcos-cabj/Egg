package com.redsocial.persistencia;

import com.redsocial.dominio.mascota.Mascota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class MascotaDAO {

	// EL PARAMETRO DE ESTE ATRIBUTO, ES COMO SE NOMBRA MI ARCHIVO PERSISTENCIA (PERSISTENCE.XML, PERSISTENCIA UNIT NAME)
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAModeloBasePU");
	private final EntityManager em = emf.createEntityManager();

	public void guardarMascota(Mascota mascota) throws Exception { // ESTE METODO ES PARA INGRESAR 
		em.getTransaction().begin();
		em.persist(mascota); // PARA GUARDAR - ENVIO EL OBJETO COMPLETO
		em.getTransaction().commit();
	}

	public void modificarMascota(Mascota mascota) throws Exception { // ESTE METODO ES PARA INGRESAR O MODIFICAR
		em.getTransaction().begin();
		em.merge(mascota); // PARA MODIFICAR UN OBJETO - SOBREESCRIBE LOS ATRIBUTOS
		em.getTransaction().commit();
	}

	public void eliminarMascota(String id) throws Exception {
		Mascota mascota = buscarMascotaPorId(id);
		em.getTransaction().begin(); // INICIO   
		em.remove(mascota);
		em.getTransaction().commit(); // FIN (SI ALGO FALLO ... VUELVE PA ATRAS)    
	}

	public Mascota buscarMascotaPorId(String id) throws Exception {
		Mascota mascota = em.find(Mascota.class, id); // PARA TRAER OBJETO CON EL ID
		return mascota;
	}

	// CONSULTA SIN PARAMETROS
	public List<Mascota> listarMascotas() throws Exception {
		List<Mascota> mascotas = em.createQuery("SELECT d FROM Mascota d")
			.getResultList();
		return mascotas;
	}

	// CONSULTA CON PARAMETROS
	public List<Mascota> listarMascotasRaza(String raza) throws Exception {
		List<Mascota> mascotasFiltradas = em.createQuery("SELECT d "
			+ " FROM Mascota d"
			+ " WHERE d.raza = :raza").
			setParameter("raza", raza)
			.getResultList();
		return mascotasFiltradas;
	}

}
