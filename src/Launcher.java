import java.util.Scanner;

public class Launcher {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion;
		int intento = 0;
		do{
			System.out.println("¿Con que sistema quieres acceder al programa?");
			System.out.println("1. SQL.");
			System.out.println("2. Hibernate.");
			System.out.println("3. JSON Remoto.");
			System.out.println("4. Mongo.");
			opcion = sc.nextInt();
			if(opcion == 1){
				intento++;
				AccesoBBDD conBBDD = new AccesoBBDD();
				conBBDD.menu();
			} else if(opcion == 2){
				intento++;
				AccesoHibernate conH = new AccesoHibernate();
				conH.menu();
			} else if(opcion == 3){
				intento++; 
				AccesoPHP php = new AccesoPHP();
				php.menu();
				//ABRO PARTE PHP
			}else if(opcion == 4){
				intento++; 
				AccesoMongo mongo = new AccesoMongo();
				mongo.menu();
				//ABRO PARTE PHP
			} else{
				System.out.println("Escriba una opción valida.");
			}
		} while(intento == 0 && (opcion != 1 || opcion != 2));
		
	}
}