package model;

public class LineaInvestigacion {
	
	private int id, id_tipo_linea;
	private String nombre, descripcion, lider_linea;
	
	public LineaInvestigacion() {
		// TODO Auto-generated constructor stub
	}

	public LineaInvestigacion(int id, int id_tipo_linea, String nombre, String descripcion, String lider_linea) {
		super();
		this.id = id;
		this.id_tipo_linea = id_tipo_linea;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.lider_linea = lider_linea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_tipo_linea() {
		return id_tipo_linea;
	}

	public void setId_tipo_linea(int id_tipo_linea) {
		this.id_tipo_linea = id_tipo_linea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLider_linea() {
		return lider_linea;
	}

	public void setLider_linea(String lider_linea) {
		this.lider_linea = lider_linea;
	}

}
