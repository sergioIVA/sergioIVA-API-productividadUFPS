package model;

public class Libro {

   private int id;
   private String titulo;
   private String ISBN;
   private String fecha_publica;
   private String autores;
   private String editorial;
   private String lugar_publica;
   private String certificacion_entidad;
   private String curriculo;
   private int tipo_desarrollo;
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Libro(int id, String titulo, String iSBN, String fecha_publica, String autores, String editorial,
			String lugar_publica, String certificacion_entidad, String curriculo, int tipo_desarrollo) {
		super();
		this.id = id;
		this.titulo = titulo;
		ISBN = iSBN;
		this.fecha_publica = fecha_publica;
		this.autores = autores;
		this.editorial = editorial;
		this.lugar_publica = lugar_publica;
		this.certificacion_entidad = certificacion_entidad;
		this.curriculo = curriculo;
		this.tipo_desarrollo = tipo_desarrollo;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getFecha_publica() {
		return fecha_publica;
	}

	public void setFecha_publica(String fecha_publica) {
		this.fecha_publica = fecha_publica;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getLugar_publica() {
		return lugar_publica;
	}

	public void setLugar_publica(String lugar_publica) {
		this.lugar_publica = lugar_publica;
	}

	public String getCertificacion_entidad() {
		return certificacion_entidad;
	}

	public void setCertificacion_entidad(String certificacion_entidad) {
		this.certificacion_entidad = certificacion_entidad;
	}

	public String getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(String curriculo) {
		this.curriculo = curriculo;
	}

	public int getTipo_desarrollo() {
		return tipo_desarrollo;
	}

	public void setTipo_desarrollo(int tipo_desarrollo) {
		this.tipo_desarrollo = tipo_desarrollo;
	}
	
	

}
