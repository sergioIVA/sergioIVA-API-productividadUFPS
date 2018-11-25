package model;

public class Articulo {
	
	private int id_producto, tipo_referencia;
	private String nombre_revista, titulo_articulo, autores, anio, mes, volumen, numero, paginas_ini, paginas_final, ISSN, paginaWeb, DOI;
	
	public Articulo() {
		// TODO Auto-generated constructor stub
	}

	public Articulo(int id_producto, int tipo_referencia, String nombre_revista, String titulo_articulo, String autores,
			String anio, String mes, String volumen, String numero, String paginas_ini, String paginas_final,
			String iSSN, String paginaWeb, String dOI) {
		super();
		this.id_producto = id_producto;
		this.tipo_referencia = tipo_referencia;
		this.nombre_revista = nombre_revista;
		this.titulo_articulo = titulo_articulo;
		this.autores = autores;
		this.anio = anio;
		this.mes = mes;
		this.volumen = volumen;
		this.numero = numero;
		this.paginas_ini = paginas_ini;
		this.paginas_final = paginas_final;
		ISSN = iSSN;
		this.paginaWeb = paginaWeb;
		DOI = dOI;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getTipo_referencia() {
		return tipo_referencia;
	}

	public void setTipo_referencia(int tipo_referencia) {
		this.tipo_referencia = tipo_referencia;
	}

	public String getNombre_revista() {
		return nombre_revista;
	}

	public void setNombre_revista(String nombre_revista) {
		this.nombre_revista = nombre_revista;
	}

	public String getTitulo_articulo() {
		return titulo_articulo;
	}

	public void setTitulo_articulo(String titulo_articulo) {
		this.titulo_articulo = titulo_articulo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getVolumen() {
		return volumen;
	}

	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPaginas_ini() {
		return paginas_ini;
	}

	public void setPaginas_ini(String paginas_ini) {
		this.paginas_ini = paginas_ini;
	}

	public String getPaginas_final() {
		return paginas_final;
	}

	public void setPaginas_final(String paginas_final) {
		this.paginas_final = paginas_final;
	}

	public String getISSN() {
		return ISSN;
	}

	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public String getDOI() {
		return DOI;
	}

	public void setDOI(String dOI) {
		DOI = dOI;
	}
	
}
