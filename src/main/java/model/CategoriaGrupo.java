package model;

public class CategoriaGrupo {

	private int id_categoria;
	private String nombre;
	
	public CategoriaGrupo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CategoriaGrupo(int id_categoria, String nombre) {
		super();
		this.id_categoria = id_categoria;
		this.nombre = nombre;
	}



	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
