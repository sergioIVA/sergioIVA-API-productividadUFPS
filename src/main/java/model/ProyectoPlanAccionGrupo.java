package model;

public class ProyectoPlanAccionGrupo {

	private int year;
	private String semestre;
	private int id_grupo;
	private int id_proyecto;
	
	public ProyectoPlanAccionGrupo() {
		// TODO Auto-generated constructor stub
	}

	public ProyectoPlanAccionGrupo(int year, String semestre, int id_grupo, int id_proyecto) {
		super();
		this.year = year;
		this.semestre = semestre;
		this.id_grupo = id_grupo;
		this.id_proyecto = id_proyecto;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
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

	public int getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}
	
	
}
