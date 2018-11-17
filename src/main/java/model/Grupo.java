package model;

public class Grupo {

	private int id;
	private String nombre;
	private String sigla;
	private String ubicacion;
	private String fecha_creacion;
	private String codigo_colciencias;
	private int clasificado;
	private String correo;
	private int id_categoria;
	private int id_unidad;
	private int director_grupo;
	
	public Grupo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Grupo(int id, String nombre, String sigla, String ubicacion, String fecha_creacion,
			String codigo_colciencias, int clasificado, String correo, int id_categoria, int id_unidad,
			int director_grupo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sigla = sigla;
		this.ubicacion = ubicacion;
		this.fecha_creacion = fecha_creacion;
		this.codigo_colciencias = codigo_colciencias;
		this.clasificado = clasificado;
		this.correo = correo;
		this.id_categoria = id_categoria;
		this.id_unidad = id_unidad;
		this.director_grupo = director_grupo;
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

	public String getCodigo_colciencias() {
		return codigo_colciencias;
	}

	public void setCodigo_colciencias(String codigo_colciencias) {
		this.codigo_colciencias = codigo_colciencias;
	}

	public int getClasificado() {
		return clasificado;
	}

	public void setClasificado(int clasificado) {
		this.clasificado = clasificado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public int getId_unidad() {
		return id_unidad;
	}

	public void setId_unidad(int id_unidad) {
		this.id_unidad = id_unidad;
	}

	public int getDirector_grupo() {
		return director_grupo;
	}

	public void setDirector_grupo(int director_grupo) {
		this.director_grupo = director_grupo;
	}
	
	

}
