package model;

public class Plan_accion_grupo_actividad {

	private int id_actividad;
	private String year;
	private String semestre;
	private int id_grupo;
	
	public Plan_accion_grupo_actividad() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Plan_accion_grupo_actividad(int id_actividad, String year, String semestre, int id_grupo) {
		super();
		this.id_actividad = id_actividad;
		this.year = year;
		this.semestre = semestre;
		this.id_grupo = id_grupo;
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

	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

	
	
}
