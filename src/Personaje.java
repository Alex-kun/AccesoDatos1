
public class Personaje {
	private int id;
	private String nombre;
	private String rol;
	private String tipo;
	private Aspecto aspecto;
	
	Personaje(int id, String nombre, String rol, String tipo){
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
		this.tipo = tipo;
		
	}
	Personaje(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	 public String toString(){
		 String texto;
		 texto =this.id + " | " +  this.nombre + " | " + this.rol + " | " + this.tipo;
		 
		 
		 return texto;
	 }
	
}
