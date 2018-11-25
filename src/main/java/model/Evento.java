package model;

public class Evento {

	private int id_evento;
	private String year;
	private String semestre;
	private int id_grupo;
	
	public Evento() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public Evento(int id_evento, String year, String semestre, int id_grupo) {
		super();
		this.id_evento = id_evento;
		this.year = year;
		this.semestre = semestre;
		this.id_grupo = id_grupo;
	}




	public int getId_evento() {
		return id_evento;
	}

	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
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
