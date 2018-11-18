package model;

public class Docente {

	private String codigo; 
	private int id_investigador; //id_persona
	private int id_departamento;
	private int id_modalidad;
	private int id_semillero_director; //0
	
	private Persona me; //Persona
	
	public Docente() {
		// TODO Auto-generated constructor stub
	}

	public Docente(String codigo, int id_investigador, int id_departamento, int id_modalidad,
			int id_semillero_director, Persona me) {
		super();
		this.codigo = codigo;
		this.id_investigador = id_investigador;
		this.id_departamento = id_departamento;
		this.id_modalidad = id_modalidad;
		this.id_semillero_director = id_semillero_director;
		this.me = me;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getId_investigador() {
		return id_investigador;
	}

	public void setId_investigador(int id_investigador) {
		this.id_investigador = id_investigador;
	}

	public int getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}

	public int getId_modalidad() {
		return id_modalidad;
	}

	public void setId_modalidad(int id_modalidad) {
		this.id_modalidad = id_modalidad;
	}

	public int getId_semillero_director() {
		return id_semillero_director;
	}

	public void setId_semillero_director(int id_semillero_director) {
		this.id_semillero_director = id_semillero_director;
	}
	
	public Persona getMe() {
		return me;
	}
	
	public void setMe(Persona me) {
		this.me = me;
	}
	
}
