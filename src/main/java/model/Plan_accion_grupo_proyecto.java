package model;

public class Plan_accion_grupo_proyecto {

	private int id_proyecto;
	private String year;
	private String semestre;
	private int id_grupo;
	
	
	public Plan_accion_grupo_proyecto() {
		// TODO Auto-generated constructor stub
	}


	public Plan_accion_grupo_proyecto(int id_proyecto, String year, String semestre, int id_grupo) {
		super();
		this.id_proyecto = id_proyecto;
		this.year = year;
		this.semestre = semestre;
		this.id_grupo = id_grupo;
	}


	public int getId_proyecto() {
		return id_proyecto;
	}


	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
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
