package model;

public class LineaSemillero {

	private int id_linea, id_semillero;
	
	public LineaSemillero() {
		// TODO Auto-generated constructor stub
	}

	public LineaSemillero(int id_linea, int id_semillero) {
		super();
		this.id_linea = id_linea;
		this.id_semillero = id_semillero;
	}

	public int getId_linea() {
		return id_linea;
	}

	public void setId_linea(int id_linea) {
		this.id_linea = id_linea;
	}

	public int getId_semillero() {
		return id_semillero;
	}

	public void setId_semillero(int id_semillero) {
		this.id_semillero = id_semillero;
	}
	
	
}
