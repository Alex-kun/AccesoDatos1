import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Conexion {
	private static Connection conexion;
	private String user = "root";
	private String pwd = "";
	private String db = "AccesoDatos";
	private String url = "jdbc:mysql://localhost/" + db;
	
	public Conexion(){
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conexion=DriverManager.getConnection(url, user, pwd);
				//System.out.println("- - Conexion correcta - -");
				//System.out.println("- - - - - - - - - - - - - - - -");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	public void menu(){
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		Scanner sc = new Scanner(System.in);
		System.out.println("Programa de consulta de datos de personajes de League of Legends:");
		System.out.println("---------------------------------");
		System.out.println("1: Consultar datos.");
		System.out.println("2: Añadir un personaje.");
		System.out.println("3: Añadir varios personajes a la vez.");
		System.out.println("4: Eliminar un dato en concreto.");
		System.out.println("5: Eliminar todos los datos.");
		System.out.println("6: Salir.");
		System.out.println("---------------------------------");
		System.out.println("Seleccione una opción:");
		int option = sc.nextInt();
		if (option == 1) {
			
			Consulta("SELECT * FROM Personajes", 1);
		}
		else if (option == 2) {
			Añadir();
		}
		else if (option == 3) {
			AñadirVarios();
		}
		else if (option == 4) {
			BorrarOne();
		}
		else if (option == 5) {
			BorrarAll();
		}
		else if (option == 6) {
			System.out.println("Hasta luego :)");
		}
		else {
			System.out.println("Seleccione una opción válida.");
		}
	}
	public void Consulta(String query, int columna){
		try{
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			HashMap<String, Personaje> personajes = new HashMap<String, Personaje>();
			while(rset.next()){
					int id = rset.getInt(1);
					String nombre = rset.getString(2);
					String rol = rset.getString(3);
					String tipo = rset.getString(4);
					
					Personaje perso = new Personaje(id, nombre, rol, tipo);
					personajes.put(rset.getString(columna), perso);
					
					System.out.println(perso);
					
			}
			//System.out.println(personajes.values());
			rset.close();
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		menu();
	}
	public void Añadir(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserte el nombre del personaje: ");
		String name = sc.next();
		
		System.out.println("Inserte el rol del personaje: ");
		String role = sc.next();
		
		System.out.println("Inserte el tipo de daño del personaje (Fisico/Magico): ");
		String type = sc.next();
		
		String query = "INSERT INTO `Personajes`( `Nombre`, `Rol`, `Daño`) VALUES ('"+name+"', '"+role+"', '"+type+"')";
		try {
			Statement stmt = conexion.createStatement();
			int rset = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Operación correcta!");
		menu();
	}
	public void AñadirVarios(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("¿Cuántos personajes desea añadir?");
		int number = sc.nextInt();
		
		for (int i = 1; i <= number; i++) {
			System.out.println("Inserte el nombre del personaje: ["+i+"]");
			String name = sc.next();
			
			System.out.println("Inserte el rol del personaje: ["+i+"]");
			String role = sc.next();
			
			System.out.println("Inserte el tipo de daño del personaje (Fisico/Magico): ["+i+"]");
			String type = sc.next();
			
			String query = "INSERT INTO `Personajes`( `Nombre`, `Rol`, `Daño`) VALUES ('"+name+"', '"+role+"', '"+type+"')";
			try {
				Statement stmt = conexion.createStatement();
				int rset = stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Operación correcta!");
		}
		

		menu();
	}
	public void BorrarAll(){
		Scanner sc = new Scanner(System.in);
		String query = "DELETE FROM `Personajes`";
		Statement stmt;
		
		System.out.println("¿Seguro que quiere borrar TODOS los datos?(Si/No)");
		String choose = sc.next();
		
		if (choose.equals("Si") || choose.equals("si")) {
			try {
				stmt = conexion.createStatement();
				int rset = stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (choose.equals("No") || choose.equals("no")) {
			System.out.println("Operación cancelada.");
		}
		menu();
	}
	public void BorrarOne(){
		
		Scanner sc = new Scanner(System.in);
		//Scanner snumber = new Scanner(System.in);
		
		Statement stmt;
		System.out.println("Seleccione el ID del dato que quiere eliminar");
		int number = sc.nextInt();
		String query = "DELETE FROM `Personajes` WHERE `id` = "+number;
		
		System.out.println("¿Seguro que quiere borrar el dato con ID numero "+number+"?(Si/No)");
		String choose = sc.next();
		
		if (choose.equals("Si") || choose.equals("si")) {
			try {
				stmt = conexion.createStatement();
				int rset = stmt.executeUpdate(query);
				System.out.println("Operacion realizada.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (choose.equals("No") || choose.equals("no")) {
			System.out.println("Operación cancelada.");
		}
		menu();
	}

}
