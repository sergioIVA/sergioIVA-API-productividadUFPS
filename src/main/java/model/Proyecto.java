package model;

public class Proyecto {

	private int id;
	private String n_contrato;
	private String titulo;
	private String fecha_inicio;
	private String fecha_final;
	private String tiempo_ejecucion;
	private int costoTotal;
	private int porcentaje_cumplimiento;
	private String resumen;
	private String justifiacion;
	private String objetivo_general;
	private String resultados_esperados;
	private String documento_proyecto;
	private int tipo_participacion;
	private int estado;
	private int id_tipo;
	private int id_linea;
	
	 
	public Proyecto() {
		super();
	}

	
	public Proyecto(int id,int costoTotal, int id_tipo, int id_linea,String tiempo_ejecucion,
			String titulo,String fecha_inicio, String fecha_final,String 
	resultados_esperados, String n_contrato,String resumen,String objetivoGeneral,int estado) {
		
		 this.id=id;
		 this.n_contrato=n_contrato;
		 this.titulo=titulo;
		 this.fecha_inicio=fecha_inicio;
		 this.fecha_final=fecha_final;
		 this.tiempo_ejecucion=tiempo_ejecucion;
		 this.costoTotal=costoTotal;
		 this.resumen=resumen;
		 this.objetivo_general=objetivoGeneral;
		 this.resultados_esperados=resultados_esperados;
		 this.estado=estado;
		 this.id_tipo=id_tipo;
		 this.id_linea=id_linea;
		
	}
	


	public Proyecto(int id, String n_contrato, String titulo, String fecha_inicio, String fecha_final,
			String tiempo_ejecucion, int costoTotal, int porcentaje_cumplimiento, String resumen, String justifiacion,
			String objetivo_general, String resultados_esperados, String documento_proyecto, int tipo_participacion,
			int estado, int id_tipo, int id_linea) {
		super();
		this.id = id;
		this.n_contrato = n_contrato;
		this.titulo = titulo;
		this.fecha_inicio = fecha_inicio;
		this.fecha_final = fecha_final;
		this.tiempo_ejecucion = tiempo_ejecucion;
		this.costoTotal = costoTotal;
		this.porcentaje_cumplimiento = porcentaje_cumplimiento;
		this.resumen = resumen;
		this.justifiacion = justifiacion;
		this.objetivo_general = objetivo_general;
		this.resultados_esperados = resultados_esperados;
		this.documento_proyecto = documento_proyecto;
		this.tipo_participacion = tipo_participacion;
		this.estado = estado;
		this.id_tipo = id_tipo;
		this.id_linea = id_linea;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getN_contrato() {
		return n_contrato;
	}


	public void setN_contrato(String n_contrato) {
		this.n_contrato = n_contrato;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getFecha_inicio() {
		return fecha_inicio;
	}


	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}


	public String getFecha_final() {
		return fecha_final;
	}


	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}


	public String getTiempo_ejecucion() {
		return tiempo_ejecucion;
	}


	public void setTiempo_ejecucion(String tiempo_ejecucion) {
		this.tiempo_ejecucion = tiempo_ejecucion;
	}


	public int getCostoTotal() {
		return costoTotal;
	}


	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}


	public int getPorcentaje_cumplimiento() {
		return porcentaje_cumplimiento;
	}


	public void setPorcentaje_cumplimiento(int porcentaje_cumplimiento) {
		this.porcentaje_cumplimiento = porcentaje_cumplimiento;
	}


	public String getResumen() {
		return resumen;
	}


	public void setResumen(String resumen) {
		this.resumen = resumen;
	}


	public String getJustifiacion() {
		return justifiacion;
	}


	public void setJustifiacion(String justifiacion) {
		this.justifiacion = justifiacion;
	}


	public String getObjetivo_general() {
		return objetivo_general;
	}


	public void setObjetivo_general(String objetivo_general) {
		this.objetivo_general = objetivo_general;
	}


	public String getResultados_esperados() {
		return resultados_esperados;
	}


	public void setResultados_esperados(String resultados_esperados) {
		this.resultados_esperados = resultados_esperados;
	}


	public String getDocumento_proyecto() {
		return documento_proyecto;
	}


	public void setDocumento_proyecto(String documento_proyecto) {
		this.documento_proyecto = documento_proyecto;
	}


	public int getTipo_participacion() {
		return tipo_participacion;
	}


	public void setTipo_participacion(int tipo_participacion) {
		this.tipo_participacion = tipo_participacion;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public int getId_tipo() {
		return id_tipo;
	}


	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}


	public int getId_linea() {
		return id_linea;
	}


	public void setId_linea(int id_linea) {
		this.id_linea = id_linea;
	}
	
	


	
}
