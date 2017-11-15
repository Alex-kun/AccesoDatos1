import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;



public class AccesoHibernate implements Interface{
	Session session;
	
	public AccesoHibernate() {
		HibernateUtil util = new HibernateUtil();
		session = util.getSessionFactory().openSession();
	}

	@Override
	public void menu() {
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		Scanner sc = new Scanner(System.in);
		System.out.println("Programa de consulta de datos de personajes de League of Legends:");
		System.out.println("---------------------------------");
		System.out.println("1: Consultar datos.");
		System.out.println("2: Añadir un personaje.");
		System.out.println("3: Añadir varios personajes a la vez.");
		System.out.println("4: Añadir datos desde un fichero.");
		System.out.println("5: Eliminar un dato en concreto.");
		System.out.println("6: Eliminar todos los datos.");
		System.out.println("7: Descargar fichero desde BBDD.");
		System.out.println("8: Salir.");
		System.out.println("---------------------------------");
		System.out.println("Seleccione una opción:");
		int option = sc.nextInt();
		if (option == 1) {
			Consulta("select p from Personaje p", 1);
		} else if (option == 2) {
			Añadir();
		} else if (option == 3) {
			AñadirVarios();
		} else if (option == 4) {
			AñadirFileData();
		} else if (option == 5) {
			BorrarOne();
		} else if (option == 6) {
			BorrarAll();
		} else if (option == 7) {
			try {
				DataToFile();
			} catch (IOException e) {
				System.out.println("Error");
			}
		} else if (option == 8) {
			System.out.println("Hasta luego :)");
		} else {
			System.out.println("Seleccione una opción válida.");
		}
	}

	@Override
	public void Consulta(String query, int columna) {
		System.out.println("Inicio Consulta Simple Personajes");

		Query q = session.createQuery(query);
		List results = q.list();

		Iterator personajesIterator = results.iterator();

		while (personajesIterator.hasNext()) {
			Personaje personaje = (Personaje) personajesIterator.next();

			System.out.println("		Id: " + personaje.getId() + " - Nombre: " + personaje.getNombre());
		}

		System.out.println("Fin Consulta Personajes");

	}

	@Override
	public void Añadir() {
		
		Personaje p1 = new Personaje();
		p1.setNombre("Zoe");
		p1.setRol("Mid");
		p1.setTipo("Magico");
		
		Aspecto a1 = new Aspecto();
		a1.setIdPersonaje(p1);
		a1.setNombreAspecto("Zoe Hacker");
		
		
		session.beginTransaction();

		session.save(p1);
		session.save(a1);
		
		session.getTransaction().commit();

		System.out.println("Fin Inserción");
		
	}

	@Override
	public void AñadirVarios() {
		Personaje p1 = new Personaje();
		p1.setNombre("Kayn");
		p1.setRol("Jungla");
		p1.setTipo("Fisico");
		
		Aspecto a1 = new Aspecto();
		a1.setIdPersonaje(p1);
		a1.setNombreAspecto("Kayn segador de almas");
		
		Personaje p2 = new Personaje();
		p1.setNombre("Ornn");
		p1.setRol("Top");
		p1.setTipo("Magico");
		
		Aspecto a2 = new Aspecto();
		a1.setIdPersonaje(p1);
		a1.setNombreAspecto("Ornn señor del trueno");
		
		
		session.beginTransaction();

		session.save(p1);
		session.save(a1);
		
		session.save(p2);
		session.save(a2);
		
		session.getTransaction().commit();

		System.out.println("Fin Inserción Multiple");
		
	}

	@Override
	public void AñadirFileData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BorrarAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BorrarOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DataToFile() throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}
}
