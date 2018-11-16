package model;

public class Programa {

	private int id;
	private String nombre;
	private int codigo;
	private int id_facultad;
	
	
	public Programa() {
		// TODO Auto-generated constructor stub
	}
	

	public Programa(int id, String nombre,int codigo,int id_facultad) {
		this.id=id;
		this.nombre=nombre;
		this.codigo=codigo;
		this.id_facultad=id_facultad;
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getId_facultad() {
		return id_facultad;
	}

	public void setId_facultad(int id_facultad) {
		this.id_facultad = id_facultad;
	}
	
	
	
	
}
