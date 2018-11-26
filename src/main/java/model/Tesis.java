package model;

public class Tesis {

	private int id_producto;
	private String titulo;
	private String institucion;
	private String anio;
	private String reconocimientos;
	
	public Tesis() {
		// TODO Auto-generated constructor stub
	}

	public Tesis(int id_producto, String titulo, String institucion, String anio, String reconocimientos) {
		super();
		this.id_producto = id_producto;
		this.titulo = titulo;
		this.institucion = institucion;
		this.anio = anio;
		this.reconocimientos = reconocimientos;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getReconocimientos() {
		return reconocimientos;
	}

	public void setReconocimientos(String reconocimientos) {
		this.reconocimientos = reconocimientos;
	}
	
	
	

}
