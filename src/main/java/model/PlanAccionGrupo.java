package model;

public class PlanAccionGrupo {

	private int year;
	private int semestre;
	private int id_grupo;
	
	public PlanAccionGrupo(int year, int semestre, int id_grupo) {
		super();
		this.year = year;
		this.semestre = semestre;
		this.id_grupo = id_grupo;
	}

	public PlanAccionGrupo() {
		super();
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
