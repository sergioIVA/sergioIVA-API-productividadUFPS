package model;

public class JovenInvestigador {

	private int id_investigador; //id_persona
	private String propuesta_desarrollada;
	private String periodo_beca;
	private String modalidad;
	private int tutor;
	
	public JovenInvestigador() {
		super();
	}

	public JovenInvestigador(int id_investigador, String propuesta_desarrollada, String periodo_beca, String modalidad,
			int tutor) {
		super();
		this.id_investigador = id_investigador;
		this.propuesta_desarrollada = propuesta_desarrollada;
		this.periodo_beca = periodo_beca;
		this.modalidad = modalidad;
		this.tutor = tutor;
	}

	public int getId_investigador() {
		return id_investigador;
	}

	public void setId_investigador(int id_investigador) {
		this.id_investigador = id_investigador;
	}

	public String getPropuesta_desarrollada() {
		return propuesta_desarrollada;
	}

	public void setPropuesta_desarrollada(String propuesta_desarrollada) {
		this.propuesta_desarrollada = propuesta_desarrollada;
	}

	public String getPeriodo_beca() {
		return periodo_beca;
	}

	public void setPeriodo_beca(String periodo_beca) {
		this.periodo_beca = periodo_beca;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public int getTutor() {
		return tutor;
	}

	public void setTutor(int tutor) {
		this.tutor = tutor;
	}
	
	
}
