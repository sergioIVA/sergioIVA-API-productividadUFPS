package model;

public class LineaGrupo {

	private int id_grupo, id_linea;

	public LineaGrupo(int id_grupo, int id_linea) {
		super();
		this.id_grupo = id_grupo;
		this.id_linea = id_linea;
	}

	public LineaGrupo() {
		super();
	}

	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

	public int getId_linea() {
		return id_linea;
	}

	public void setId_linea(int id_linea) {
		this.id_linea = id_linea;
	}

}
