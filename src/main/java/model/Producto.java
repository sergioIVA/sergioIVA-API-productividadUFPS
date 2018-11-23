package model;

public class Producto {

	private int id, id_proyecto, id_tipo_producto;
	String nombre, descripcion;
	
	public Producto(int id, int id_proyecto, int id_tipo_producto, String nombre, String descripcion) {
		super();
		this.id = id;
		this.id_proyecto = id_proyecto;
		this.id_tipo_producto = id_tipo_producto;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Producto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public int getId_tipo_producto() {
		return id_tipo_producto;
	}

	public void setId_tipo_producto(int id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
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
	
	
}
