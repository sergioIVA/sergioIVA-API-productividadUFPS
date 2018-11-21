package model;

public class ActividadPlanAccionGrupo {

	private int id_actividad;
	private int year;
	private int semestre;
	private int id_grupo;
	
	public ActividadPlanAccionGrupo() {
		// TODO Auto-generated constructor stub
	}

	public ActividadPlanAccionGrupo(int id_actividad, int year, int semestre, int id_grupo) {
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}
	
	
}
