package model;

public class Objetivo {

	private int id;
	private String nombre;
	private int id_proyecto;
	
	public Objetivo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Objetivo(int id, String nombre, int id_proyecto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.id_proyecto = id_proyecto;
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

	public int getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}
	
	

}
