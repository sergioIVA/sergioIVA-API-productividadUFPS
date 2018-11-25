package model;

public class Actividad_plan_semillero {

	private int id_actividad;
	private String year;
	private String semestre;
	private int id_semillero;
	
	public Actividad_plan_semillero() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Actividad_plan_semillero(int id_actividad, String year, String semestre, int id_semillero) {
		super();
		this.id_actividad = id_actividad;
		this.year = year;
		this.semestre = semestre;
		this.id_semillero = id_semillero;
	}




	public int getId_actividad() {
		return id_actividad;
	}

	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
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
