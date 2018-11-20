package model;

public class Semillero {

	private int id;
	private String codigo;
	private String nombre;
	private String sigla;
	private String ubicacion;
	private String fecha_creacion;
	private int id_grupo;
	private String correo;
	private int id_linea_grupo;
	private int id_director;
	
	public Semillero() {
		super();
	}

	public Semillero(int id, String codigo, String nombre, String sigla, String ubicacion, String fecha_creacion,
			int id_grupo, String correo, int id_linea_grupo, int id_director) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.sigla = sigla;
		this.ubicacion = ubicacion;
		this.fecha_creacion = fecha_creacion;
		this.id_grupo = id_grupo;
		this.correo = correo;
		this.id_linea_grupo = id_linea_grupo;
		this.id_director = id_director;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getId_linea_grupo() {
		return id_linea_grupo;
	}

	public void setId_linea_grupo(int id_linea_grupo) {
		this.id_linea_grupo = id_linea_grupo;
	}

	public int getId_director() {
		return id_director;
	}

	public void setId_director(int id_director) {
		this.id_director = id_director;
	}
	
	
}
