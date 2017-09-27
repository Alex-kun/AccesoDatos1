import java.util.Scanner;

public class Launcher {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Conexion conexion = new Conexion();
		conexion.menu();
	}
}