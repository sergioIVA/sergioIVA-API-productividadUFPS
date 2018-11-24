package model;

public class PlanAccionSemillero {

	private String year;
	private String semestre;
	private int id_semillero;
	
	public PlanAccionSemillero() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public PlanAccionSemillero(String year, String semestre, int id_semillero) {
		super();
		this.year = year;
		this.semestre = semestre;
		this.id_semillero = id_semillero;
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
