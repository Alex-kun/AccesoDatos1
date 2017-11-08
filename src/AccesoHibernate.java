import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

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
			Consulta("SELECT * FROM Personajes", 1);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Añadir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AñadirVarios() {
		// TODO Auto-generated method stub
		
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