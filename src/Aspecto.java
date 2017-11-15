
public class Aspecto {
	private int id;
	private String NombreAspecto;
	private Personaje idPersonaje;
	
	public Aspecto(int id, String nombreAspecto, Personaje idPersonaje) {
		this.id = id;
		NombreAspecto = nombreAspecto;
		this.idPersonaje = idPersonaje;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreAspecto() {
		return NombreAspecto;
	}
	public void setNombreAspecto(String nombreAspecto) {
		NombreAspecto = nombreAspecto;
	}
	public Personaje getIdPersonaje() {
		return idPersonaje;
	}
	public void setIdPersonaje(Personaje idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
	
}
