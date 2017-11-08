import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Interface {
	public void menu();
	public void Consulta(String query, int columna);
	public void Añadir();
	public void AñadirVarios();
	public void AñadirFileData();
	public void BorrarAll();
	public void BorrarOne();
	public void DataToFile() throws FileNotFoundException, UnsupportedEncodingException;
}
