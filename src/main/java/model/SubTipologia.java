package model;

public class SubTipologia {

	private int id;
	private int id_tipologia;
	private String nombre;
	
	public SubTipologia() {
		// TODO Auto-generated constructor stub
	}
	
	public SubTipologia(int id, int id_tipologia, String nombre) {
		super();
		this.id = id;
		this.id_tipologia = id_tipologia;
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public int getId_tipologia() {
		return id_tipologia;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId_tipologia(int id_tipologia) {
		this.id_tipologia = id_tipologia;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
