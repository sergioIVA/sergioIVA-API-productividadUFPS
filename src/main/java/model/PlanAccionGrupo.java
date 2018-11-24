package model;

public class PlanAccionGrupo {

	private String  year;
	private String semestre;
	private int id_grupo;
	
	public PlanAccionGrupo(String year, String semestre, int id_grupo) {
		super();
		this.year = year;
		this.semestre = semestre;
		this.id_grupo = id_grupo;
	}

	public PlanAccionGrupo() {
		super();
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
