package model;

public class Tipologia {
	
	private int id;
	private String nombre;
	
	public Tipologia() {
		// TODO Auto-generated constructor stub
	}
	
	public Tipologia(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
