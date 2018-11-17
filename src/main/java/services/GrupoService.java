package services;

import java.sql.SQLException;

import model.Grupo;

public class GrupoService {

	public GrupoService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Grupo createGrupo(String nombre, String sigla, String ubicacion, String fecha_creacion,
			String codigo_colciencias, int clasificado, String correo, int id_categoria, int id_unidad,
			int director_grupo) throws SQLException {
		return null;
	}
	
	public Object getGrupos()throws SQLException {
		return null;
	}
	
	public Grupo getGrupo(int id)throws SQLException {
		return null;
	}
	
	public Grupo updateGrupo(Grupo grupo,String nombre, String sigla, String ubicacion, String fecha_creacion,
			String codigo_colciencias, int clasificado, String correo, int id_categoria, int id_unidad,
			int director_grupo)throws SQLException {
		return null;
	}
	
	public boolean deleteGrupo(int id) {
		return true;
	}
	
}
