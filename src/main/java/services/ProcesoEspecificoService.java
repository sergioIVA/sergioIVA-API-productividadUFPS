package services;



import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import Dao.CategoriaGrupoDao;
import Dao.DepartamentoDao;
import Dao.FacultadDao;
import Dao.ProcesoEspecificoDao;
import Dao.ProgramaDao;
import model.CategoriaGrupo;
import model.Departamento;
import model.Facultad;
import model.Programa;

public class ProcesoEspecificoService {

	final ProcesoEspecificoDao procesoEspecificoDao=new ProcesoEspecificoDao();
	final FacultadDao facultadDao=new FacultadDao();
	final CategoriaGrupoDao categoriaGrupoDao=new CategoriaGrupoDao();
	final DepartamentoDao departamentoDao=new DepartamentoDao();
	final ProgramaDao programaDao=new ProgramaDao();
	
	public ProcesoEspecificoService() {
		// TODO Auto-generated constructor stub
	}

	
	public Object getPanelPrincipal(int idGrupo) throws Exception {
		 return this.procesoEspecificoDao.getPanelPrincipal(idGrupo);
	}
	
	public Object getGrupoCategoriaDirector()throws Exception {
		return this.procesoEspecificoDao.getGrupoCategoriaDirector();
		
	}
	
	public Object getdatosCrearGrupo()throws Exception {
		
		
		LinkedHashMap<String, Object> datosEspecificos= new LinkedHashMap<>();
		
		List<CategoriaGrupo> categoria=categoriaGrupoDao.getCategoriaGrupos();
		List<Facultad> facultad=facultadDao.getFacultades();
		List<Departamento> departamento=departamentoDao.getDepartamentos();
		List<Programa> programa=programaDao.getProgramas();
		
	   datosEspecificos.put("categoria",categoria);
	   datosEspecificos.put("facultad",facultad);
	   datosEspecificos.put("departamento",departamento);
	   datosEspecificos.put("programa",programa);
	  
		return datosEspecificos;
	}
	
	public Object getSemilleroDirector()throws Exception {
		return this.procesoEspecificoDao.getSemilleroDirector();
	}
	
	public Object getLineaGrupoDocenteGrupo(int idGrupo)throws Exception{
		 return this.procesoEspecificoDao.getGrupoLineaSemilleroDocenteGrupo(idGrupo);
	}
	
	public Object getLineasGrupoTipoProyectoGrupo(int tipoSession,int idGruSemillero)throws Exception {
		     return this.procesoEspecificoDao.getLineasGrupoTipoProyectoGrupo(tipoSession, idGruSemillero);
	}
	
	public Object getProyectoResponsable(int idGrupoSemillero,int tipoSession)throws Exception {
		     return this.procesoEspecificoDao.getProyectoResponsable(idGrupoSemillero,tipoSession);
	}
	
}
