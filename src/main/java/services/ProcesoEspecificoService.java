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
import spark.Request;
import spark.Response;

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
	
	
	
	public Object getproyectosNuevosIntegrantes(int idGrupoSemillero,int tipoSession)throws Exception {
		    return this.procesoEspecificoDao.getproyectosNuevosIntegrantes(idGrupoSemillero,tipoSession);
	}
	
	public Object getcreatePlanGrupoSemillero(int idGrupoSemillero,int tipoSession,String year,String  semestre)throws Exception {
		
		    return this.procesoEspecificoDao.getcreatePlanGrupoSemillero(idGrupoSemillero, tipoSession, year, semestre);
		     
	}
	
	public Object getProyectosActividadesNoterminadoPlanAccionGrupoSemillero(int idGrupoSemillero,int tipoSession)throws Exception {
		 return this.procesoEspecificoDao.getProyectosActividadesNoterminadoPlanAccionGrupoSemillero(idGrupoSemillero, tipoSession);
	}
	
	public Object eventoNoTerminadoPlanAccionGrupo(int idGrupo)throws Exception {
		return  this.procesoEspecificoDao.eventoNoTerminadoPlanAccionGrupo(idGrupo);
	}
	
	public Object getCapacitacionNoTerminadoPlanAccionSemillero(int idSemillero)throws Exception {
		return this.procesoEspecificoDao.getCapacitacionNoTerminadoPlanAccionSemillero(idSemillero);
	}
	
	public Object CreateEventoGrupoAsignarPlanAccion(String year,String semestre,int idGrupo,String nombre,
			String caracterEvento,String responsables,String instituciones_promo,	String entidades,String
			fecha_inicio,String fecha_final)throws Exception {
		return this.procesoEspecificoDao.CreateEventoGrupoAsignarPlanAccion(year, semestre, idGrupo, nombre, 
				caracterEvento, responsables, instituciones_promo, entidades, fecha_inicio, fecha_final);
	}
	
	public Object createActividadGrupoSemilleroAsignarPlanAccion(String year, String semestre, int idGrupoSemillero,
			int tipoSession, String nombre, String responsables, String producto, String fecha_inicio,
			String fecha_final)throws Exception{
		return this.procesoEspecificoDao.createActividadGrupoSemilleroAsignarPlanAccion(year,
				semestre, idGrupoSemillero, tipoSession, nombre, responsables, producto, fecha_inicio, fecha_final);
	}
	
	public Object getCapacitacionCrearSemilleroAsignarPlanAccion(String year,String semestre,int idSemillero,
			String nombre,String objetivo,String responsables,int n_asistentes,String fecha_ini,String fecha_fin)
					throws Exception{
		return this.procesoEspecificoDao.capacitacionCrearSemilleroAsignarPlanAccion(year, semestre,
				idSemillero, nombre, objetivo, responsables, n_asistentes, fecha_ini, fecha_fin);
	}
	
	public Object asignarProyectoPlanAccion(String year,String semestre,int idGrupoSemillero, int tipoSession,
			int id_proyecto)throws Exception {
		return this.procesoEspecificoDao.asignarProyectoPlanAccion(year, semestre,
				idGrupoSemillero, tipoSession, id_proyecto);
	}
	
	
	public Object getAsignarActividadesPlanAccionGrupoSemillero(String year,String
			semestre,int idGrupoSemillero,int tipoSession,int id_actividad)throws Exception {
		
		return this.procesoEspecificoDao.getAsignarActividadesPlanAccionGrupoSemillero(year,
				semestre, idGrupoSemillero, tipoSession, id_actividad);
	}
	
	public Object asignarCapacitacionPlanAccionSemillero(String year,String semestre,int idSemillero,int id_capacitacion
			)throws Exception {
		return this.procesoEspecificoDao.asignarCapacitacionPlanAccionSemillero(year,
				semestre, idSemillero, id_capacitacion);
	}
	
	public Object asignarEventoPlanAccionGrupo(String year,String semestre,int idGrupo,int id_evento
			)throws Exception {
		return this.procesoEspecificoDao.asignarEventoPlanAccionGrupo(year, semestre, idGrupo, id_evento);
	}
	
	public Object getTipologiaProductos1()throws Exception{
		return this.procesoEspecificoDao.getTipologiaProductos1();
	}
	public Object getTipologiaProductos2(int id_tipologia1)throws Exception{
		return this.procesoEspecificoDao.getTipologiaProductos2(id_tipologia1);
	}
	
	public Object getTipologiaProductos3(int id_tipologia2)throws Exception{
		return this.procesoEspecificoDao.getTipologiaProductos3(id_tipologia2);
	}
	
	public Object getCategoria(int id_tipologia3)throws Exception{
		return this.procesoEspecificoDao.getcategoriaProductos(id_tipologia3);
	}
	
	public Object planesAccionGrupoSemillero(int idGrupoSemillero, int tipoSession)throws Exception{
		return this.procesoEspecificoDao.planesAccionGrupoSemillero(idGrupoSemillero, tipoSession);
	}
	public Object tipoReferencia()throws Exception {
		return this.procesoEspecificoDao.tipoReferencia();
	}
	
	public Object createLibro(String nombre,String descripcion,int id_proyecto,int id_tipo_producto,
			String titulo,String ISBN,String fecha_publica,String autores,String editorial,String lugar_publica,
			String certificacion_entidad,String curriculo,int tipo_desarrollo ) throws Exception {
		
		return this.procesoEspecificoDao.createLibro(nombre, descripcion,
				id_proyecto, id_tipo_producto, titulo, ISBN, fecha_publica, autores,
				editorial, lugar_publica, certificacion_entidad, curriculo, tipo_desarrollo);
	}
	
}


