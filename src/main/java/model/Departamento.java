package model;

public class Departamento {

	
	private int id;
	private String nombre;
	private int codigo;
	
	
	public Departamento() {
		
	}


	public Departamento(int id, String nombre, int codigo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
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
	
	
	
	
	

}
