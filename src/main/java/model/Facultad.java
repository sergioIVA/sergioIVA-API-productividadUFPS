package model;

public class Facultad {
	
	
	private int id;
	private int codigo;
	private String nombre;

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public Facultad() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Facultad(int id,int cod,String nombre) {
		super();
		this.codigo=cod;
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public Facultad(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Facultad(String nombre) {
		this.nombre=nombre;
		
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

}
