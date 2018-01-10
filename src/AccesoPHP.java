import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class AccesoPHP {

	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_PLAYER, SET_PLAYER; // Datos de la conexion

	public AccesoPHP() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost/PHPADAT/BaloncestoJSONServer/";
		GET_PLAYER = "leeJugadores.php";
		SET_PLAYER = "escribirJugador.php";

	}
	public void menu(){
		System.out.println("Que quieres hacer?");
		System.out.println("1. Leer");
		System.out.println("2. Escribir");
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		if(opcion == 1){
			lee();
		} else if(opcion == 2){
			escribir();
		}
	}

	public HashMap<Integer, Personaje> lee() {
		
		HashMap<Integer, Personaje> auxhm = new HashMap<Integer, Personaje>();
		
		try {

			System.out.println("---------- Leemos datos de JSON --------------------");

			System.out.println("Lanzamos peticion JSON para personajes");

			String url = SERVER_PATH + GET_PLAYER; // Sacadas de configuracion

			// System.out.println("La url a la que lanzamos la petici�n es " +
			// url); // Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

			System.out.println(response); // Traza para pruebas

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay alg�n error de parseo (json
										// incorrecto porque hay alg�n caracter
										// raro, etc.) la respuesta ser� null
				System.out.println("El json recibido no es correcto. Finaliza la ejecucion");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay alg�n problema
				String estado = (String) respuesta.get("estado"); 
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) { 
					JSONArray array = (JSONArray) respuesta.get("personajes");

					if (array.size() > 0) {

						// Declaramos variables
						Personaje nuevoPer;
						String nombre;
						String tipo;
						String rol;
						int id;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);
							
							id = Integer.parseInt(row.get("id").toString());
							nombre = row.get("nombre").toString();
							rol = row.get("rol").toString();
							tipo = row.get("damage").toString();

							nuevoPer = new Personaje(id, nombre, rol, tipo);

							auxhm.put(id, nuevoPer);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado HashMap");
						System.out.println();

					} else { // El array de jugadores est� vac�o
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido alg�n error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			} 
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error en la busqueda de datos");
				e.printStackTrace();
				System.exit(-1);
			}
		return auxhm;
	}
	
	public void escribir(){
		//Peticion de datos
		Scanner sc = new Scanner(System.in);
		System.out.println("Escriba los datos del personaje que quiera insertar: ");
		System.out.println("Inserte el NOMBRE del personaje: ");
		String nombre = sc.next();
		System.out.println("Inserte el TIPO DE DAÑO del personaje: (Fisico, Magico)");
		String damage = sc.next();
		System.out.println("Inserte el ROL del personaje: (Top, Jungla, Mid, Adc, Support)");
		String role = sc.next();
		
		//Personaje
		Personaje persAnadir = new Personaje();
		persAnadir.setNombre(nombre);
		persAnadir.setTipo(damage);
		persAnadir.setRol(role);
		JSONObject peticion = new JSONObject();
		JSONObject pers = new JSONObject();
//		HashMap<String, Object> peticion = new HashMap<>();
//		HashMap<String, String> pers = new HashMap<>();

		peticion.put("peticion", "add");
		peticion.put("jugadorAnnadir", pers);
			pers.put("nombre", persAnadir.getNombre());
			pers.put("da\u00f1o", persAnadir.getTipo());
			pers.put("rol", persAnadir.getRol());
			String json = peticion.toJSONString();
		
		
		//Lanzamos peticion
		String url = SERVER_PATH + SET_PLAYER;
		
		System.out.println("La url a la que lanzamos la petición es " + url);
        System.out.println("El json que enviamos es: ");
        System.out.println(json);
        try {
			String response = encargadoPeticiones.postRequest(url, json);
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
}