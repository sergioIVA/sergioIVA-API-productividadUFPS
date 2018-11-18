package model;

public class Estudiante {

	private int id_estudiante; //id_persona
	private String codigo; 
	private int semestre;
	private int id_programa;
	
	private Persona me;
	
	public Estudiante() {
		// TODO Auto-generated constructor stub
	}

	public Estudiante(int id_estudiante, String codigo, int semestre, int id_programa, Persona me) {
		super();
		this.id_estudiante = id_estudiante;
		this.codigo = codigo;
		this.semestre = semestre;
		this.id_programa = id_programa;
		this.me = me;
	}

	public int getId_estudiante() {
		return id_estudiante;
	}

	public void setId_estudiante(int id_estudiante) {
		this.id_estudiante = id_estudiante;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getId_programa() {
		return id_programa;
	}

	public void setId_programa(int id_programa) {
		this.id_programa = id_programa;
	}
	
	public Persona getMe() {
		return me;
	}
	
	public void setMe(Persona me) {
		this.me = me;
	}
}
