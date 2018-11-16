package model;

public class CategoriaProducto {

	private int id;
	private int id_tipo_producto;
	private int id_subtipo_producto;
	private String nombre;
	
	public CategoriaProducto() {
		// TODO Auto-generated constructor stub
	}
	
	public CategoriaProducto(int id, int id_tipo_producto, int id_subtipo_producto, String nombre) {
		super();
		this.id = id;
		this.id_tipo_producto = id_tipo_producto;
		this.id_subtipo_producto = id_subtipo_producto;
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public int getId_subtipo_producto() {
		return id_subtipo_producto;
	}
	
	public int getId_tipo_producto() {
		return id_tipo_producto;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId_subtipo_producto(int id_subtipo_producto) {
		this.id_subtipo_producto = id_subtipo_producto;
	}
	
	public void setId_tipo_producto(int id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
