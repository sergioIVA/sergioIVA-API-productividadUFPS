package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Dao.GrupoDao;
import model.Grupo;

public class GrupoService {

	final GrupoDao grupoDao = new GrupoDao();

	public GrupoService() {
		// TODO Auto-generated constructor stub
	}

	public Grupo createGrupo(String nombre, String sigla, String ubicacion, String fecha_creacion,
			String codigo_colciencias, int clasificado, String correo, int id_categoria, int id_unidad,
			int director_grupo) throws Exception {
		return this.grupoDao.createGrupo(nombre, sigla, ubicacion, fecha_creacion, codigo_colciencias, clasificado,
				correo, id_categoria, id_unidad, director_grupo);
	}

	public Object getGrupos() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grupo", this.grupoDao.getGrupos());
		return map;
	}

	public Grupo getGrupo(int id) throws Exception {
		return this.grupoDao.getGrupo(id);
	}

	public Grupo updateGrupo(Grupo grupo, String nombre, String sigla, String ubicacion, String fecha_creacion,
			String codigo_colciencias, int clasificado, String correo, int id_categoria, int id_unidad,
			int director_grupo) throws Exception {
		return this.grupoDao.updateGrupo(grupo, nombre, sigla, ubicacion, fecha_creacion, codigo_colciencias,
				clasificado, correo, id_categoria, id_unidad, director_grupo);
	}

	public boolean deleteGrupo(int id)throws Exception {
		return this.grupoDao.deleteGrupo(id);
	}

}
