

import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;



public class AccesoMongo{

	MongoClient mongoClient;
	MongoCollection<Document> collection;
	MongoDatabase db;

	public AccesoMongo() {
		try {
			// PASO 1: Conexión al Server de MongoDB Pasandole el host y el
			// puerto
			mongoClient = new MongoClient("localhost", 27017);

			// PASO 2: Conexión a la base de datos
			db = mongoClient.getDatabase("AccesoDatos");
			System.out.println("Conectado a BD MONGO");
			collection = db.getCollection("Personajes");


		} catch (Exception e) {
			System.out.println("Error leyendo la BD MONGO: " + e.getMessage());
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}

	}
	public void menu(){
		System.out.println("Que quieres hacer?");
		System.out.println("1. Leer Datos");
		System.out.println("2. Escribir un personaje");
		System.out.println("3. Eliminar personaje en concreto");
		System.out.println("4. Actualizar personaje en concreto");
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		if(opcion == 1){
			Leer();
		} else if(opcion == 2){
			Insertar();
		} else if(opcion == 3){
			Eliminar();
		}
	}
	public void Leer(){
		
		int numPersonajes = (int) collection.count();
		System.out.println("Número de personajes (registros) en la colección personajes: " + numPersonajes + "\n");

		MongoCursor<Document> cursor = collection.find().iterator();

		while (cursor.hasNext()) {
			Document rs = cursor.next();
			ObjectId id = rs.getObjectId("_id");
			String nombre = rs.getString("nombre");
			String damage = rs.getString("damage");
			String role = rs.getString("role");
			System.out.println("ID: "+id);
			System.out.println("Nombre: "+nombre);
			System.out.println("Damage: "+damage);
			System.out.println("Rol: "+role);
			System.out.println("- - - - - - - - - - - - - - -");
		}
		menu();
	}
	public void Insertar(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Escriba el NOMBRE del personaje: ");
		String nombre = sc.next();
		
		System.out.println("Escriba el DAÑO del personaje: ");
		String damage = sc.next();
		
		System.out.println("Escriba el ROL del personaje: ");
		String role = sc.next();
		
		Document document = new Document();
		document.put("nombre", nombre);
		document.put("damage", damage);
		document.put("role", role);

		collection.insertOne(document);
		menu();
	}
	public void Eliminar(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserte el nombre del personaje que quiera eliminar: ");
		String nombre = sc.next();
		Document del = collection.findOneAndDelete(Filters.eq("nombre", nombre));
		collection.deleteOne(del);
		System.out.println("Personaje "+ nombre+ " eliminado correctamente.");
		//menu
		menu();
	}




}
