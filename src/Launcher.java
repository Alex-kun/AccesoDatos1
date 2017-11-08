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
			opcion = sc.nextInt();
			if(opcion == 1){
				intento++;
				AccesoBBDD conBBDD = new AccesoBBDD();
				conBBDD.menu();
			} else if(opcion == 2){
				intento++;
				AccesoHibernate conH = new AccesoHibernate();
				conH.menu();
			} else{
				System.out.println("Escriba una opción valida.");
			}
		} while(intento == 0 && (opcion != 1 || opcion != 2));
		
	}
}