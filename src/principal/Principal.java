package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principal {

	public static void main(String[] args) throws Exception {
		//SOLO PARA CREAR LAS TABLAS SIN NECESIDAD DE CREAR UN OBJETO
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAModeloBasePU");
		EntityManager em = emf.createEntityManager();
		MenuOpciones nuevaEjecucion = new MenuOpciones();
		nuevaEjecucion.menu();
	}
}
