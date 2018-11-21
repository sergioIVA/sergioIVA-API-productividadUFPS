package model;

public class Proyecto {

	private int id, costoTotal, porcentaje_cumplimiento, id_tipo, id_linea, duracion, tiempo_total_ejecucion, id_facultad, tipo_participacion_id, estado;
	private String titulo, fecha_inicio, fecha_final, valor_financiado, institucion, resultados_esperados, representante_facultad, documento_proyecto, n_contrato;
	
	public Proyecto() {
		super();
	}

	public Proyecto(int id, int costoTotal, int porcentaje_cumplimiento, int id_tipo, int id_linea, int duracion,
			int tiempo_total_ejecucion, int id_facultad, int tipo_participacion_id, int estado, String titulo,
			String fecha_inicio, String fecha_final, String valor_financiado, String institucion,
			String resultados_esperados, String representante_facultad, String documento_proyecto, String n_contrato) {
		super();
		this.id = id;
		this.costoTotal = costoTotal;
		this.porcentaje_cumplimiento = porcentaje_cumplimiento;
		this.id_tipo = id_tipo;
		this.id_linea = id_linea;
		this.duracion = duracion;
		this.tiempo_total_ejecucion = tiempo_total_ejecucion;
		this.id_facultad = id_facultad;
		this.tipo_participacion_id = tipo_participacion_id;
		this.estado = estado;
		this.titulo = titulo;
		this.fecha_inicio = fecha_inicio;
		this.fecha_final = fecha_final;
		this.valor_financiado = valor_financiado;
		this.institucion = institucion;
		this.resultados_esperados = resultados_esperados;
		this.representante_facultad = representante_facultad;
		this.documento_proyecto = documento_proyecto;
		this.n_contrato = n_contrato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getTiempo_total_ejecucion() {
		return tiempo_total_ejecucion;
	}

	public void setTiempo_total_ejecucion(int tiempo_total_ejecucion) {
		this.tiempo_total_ejecucion = tiempo_total_ejecucion;
	}

	public int getId_facultad() {
		return id_facultad;
	}

	public void setId_facultad(int id_facultad) {
		this.id_facultad = id_facultad;
	}

	public int getTipo_participacion_id() {
		return tipo_participacion_id;
	}

	public void setTipo_participacion_id(int tipo_participacion_id) {
		this.tipo_participacion_id = tipo_participacion_id;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
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

	public String getValor_financiado() {
		return valor_financiado;
	}

	public void setValor_financiado(String valor_financiado) {
		this.valor_financiado = valor_financiado;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getResultados_esperados() {
		return resultados_esperados;
	}

	public void setResultados_esperados(String resultados_esperados) {
		this.resultados_esperados = resultados_esperados;
	}

	public String getRepresentante_facultad() {
		return representante_facultad;
	}

	public void setRepresentante_facultad(String representante_facultad) {
		this.representante_facultad = representante_facultad;
	}

	public String getDocumento_proyecto() {
		return documento_proyecto;
	}

	public void setDocumento_proyecto(String documento_proyecto) {
		this.documento_proyecto = documento_proyecto;
	}

	public String getN_contrato() {
		return n_contrato;
	}

	public void setN_contrato(String n_contrato) {
		this.n_contrato = n_contrato;
	}
	
	
}
