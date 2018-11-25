package model;

public class Plan_accion_capacitacion {

	private int id_capacitacion;
	private String year;
	private String semestre;
	private int id_semillero;
	
	public Plan_accion_capacitacion() {
		// TODO Auto-generated constructor stub
	}

	public Plan_accion_capacitacion(int id_capacitacion, String year, String semestre, int id_semillero) {
		super();
		this.id_capacitacion = id_capacitacion;
		this.year = year;
		this.semestre = semestre;
		this.id_semillero = id_semillero;
	}

	public int getId_capacitacion() {
		return id_capacitacion;
	}

	public void setId_capacitacion(int id_capacitacion) {
		this.id_capacitacion = id_capacitacion;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public int getId_semillero() {
		return id_semillero;
	}

	public void setId_semillero(int id_semillero) {
		this.id_semillero = id_semillero;
	}
	
	
	

}
