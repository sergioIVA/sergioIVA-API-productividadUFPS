package model;

public class Externo {

	private int id_investigador; //id_persona
	private String institucion_empresa;
	private String cargo;
	private String profesion;
	
	private Persona me;
	
	public Externo() {
		// TODO Auto-generated constructor stub
	}

	public Externo(int id_investigador, String institucion_empresa, String cargo, String profesion, Persona me) {
		super();
		this.id_investigador = id_investigador;
		this.institucion_empresa = institucion_empresa;
		this.cargo = cargo;
		this.profesion = profesion;
		this.me = me;
	}

	public int getId_investigador() {
		return id_investigador;
	}

	public void setId_investigador(int id_investigador) {
		this.id_investigador = id_investigador;
	}

	public String getInstitucion_empresa() {
		return institucion_empresa;
	}

	public void setInstitucion_empresa(String institucion_empresa) {
		this.institucion_empresa = institucion_empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public Persona getMe() {
		return me;
	}

	public void setMe(Persona me) {
		this.me = me;
	}

}
