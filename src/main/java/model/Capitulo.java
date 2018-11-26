package model;

public class Capitulo {

	private int producto_id, tipo_desarrollo_id;
	private String titulo_libro, titulo_capitulo, ISBN_librol, fecha_publica, autores, editorial, lugar_publica, certificacion_entidad, curriculo_capitulo;
	
	public Capitulo() {}
	
	public Capitulo(int producto_id, int tipo_desarrollo_id, String titulo_libro, String titulo_capitulo,
			String iSBN_librol, String fecha_publica, String autores, String editorial, String lugar_publica,
			String certificacion_entidad, String curriculo_capitulo) {
		super();
		this.producto_id = producto_id;
		this.tipo_desarrollo_id = tipo_desarrollo_id;
		this.titulo_libro = titulo_libro;
		this.titulo_capitulo = titulo_capitulo;
		ISBN_librol = iSBN_librol;
		this.fecha_publica = fecha_publica;
		this.autores = autores;
		this.editorial = editorial;
		this.lugar_publica = lugar_publica;
		this.certificacion_entidad = certificacion_entidad;
		this.curriculo_capitulo = curriculo_capitulo;
	}

	public int getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}

	public int getTipo_desarrollo_id() {
		return tipo_desarrollo_id;
	}

	public void setTipo_desarrollo_id(int tipo_desarrollo_id) {
		this.tipo_desarrollo_id = tipo_desarrollo_id;
	}

	public String getTitulo_libro() {
		return titulo_libro;
	}

	public void setTitulo_libro(String titulo_libro) {
		this.titulo_libro = titulo_libro;
	}

	public String getTitulo_capitulo() {
		return titulo_capitulo;
	}

	public void setTitulo_capitulo(String titulo_capitulo) {
		this.titulo_capitulo = titulo_capitulo;
	}

	public String getISBN_librol() {
		return ISBN_librol;
	}

	public void setISBN_librol(String iSBN_librol) {
		ISBN_librol = iSBN_librol;
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

	public String getCurriculo_capitulo() {
		return curriculo_capitulo;
	}

	public void setCurriculo_capitulo(String curriculo_capitulo) {
		this.curriculo_capitulo = curriculo_capitulo;
	}
	
}
