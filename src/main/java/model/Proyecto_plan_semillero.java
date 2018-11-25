package model;

public class Proyecto_plan_semillero {

	private int id_proyecto;
	private String year;
	private String semestre;
	private int id_semillero;
	
	
	
	public Proyecto_plan_semillero() {
		// TODO Auto-generated constructor stub
	}

	


	public Proyecto_plan_semillero(int id_proyecto, String year, String semestre, int id_semillero) {
		super();
		this.id_proyecto = id_proyecto;
		this.year = year;
		this.semestre = semestre;
		this.id_semillero = id_semillero;
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



	public int getId_semillero() {
		return id_semillero;
	}



	public void setId_semillero(int id_semillero) {
		this.id_semillero = id_semillero;
	}
	
	
	

}
