package model;

public class Programa {

	private int id_unidad;
	private int id_facultad;
	private String nombre;
	
	public Programa() {
		// TODO Auto-generated constructor stub
	}
	
	public Programa(int id_unidad, int id_facultad, String nombre) {
		super();
		this.id_unidad = id_unidad;
		this.id_facultad = id_facultad;
		this.nombre = nombre;
	}
	
	public int getId_facultad() {
		return id_facultad;
	}
	
	public void setId_facultad(int id_facultad) {
		this.id_facultad = id_facultad;
	}
	
	public int getId_unidad() {
		return id_unidad;
	}
	
	public void setId_unidad(int id_unidad) {
		this.id_unidad = id_unidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
