package controllers;

import services.PlanAccionGrupoService;
import spark.Request;
import spark.Response;

public class PlanAccionGrupoController {

	static final PlanAccionGrupoService service = new PlanAccionGrupoService();
	
	public PlanAccionGrupoController() {}
	
	public Object createPlan(Request req, Response res) {
		res.type("application/json");
		int year = Integer.parseInt(req.queryParams("year"));
		int semestre = Integer.parseInt(req.queryParams("semestre"));
		int id_grupo = Integer.parseInt(req.queryParams("id_grupo"));
		
			System.out.println(req.body());
			
		Object obj = null;
		try {
			obj = service.createPlan(year, semestre, id_grupo);
			res.status(201); //CREATED
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		
		return obj;
	}
	
	public Object createActividadPlan(Request req, Response res) {
		res.type("application/json");
		int id_actividad = Integer.parseInt(req.queryParams("id_actividad"));
		int year = Integer.parseInt(req.queryParams("year"));
		int semestre = Integer.parseInt(req.queryParams("semestre"));
		int id_grupo = Integer.parseInt(req.queryParams("id_grupo"));
		
		Object obj = null;
		try {
			obj = service.createActividadPlan(id_actividad, year, semestre, id_grupo);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object createProyectoPlan(Request req, Response res) {
		res.type("application/json");
		int year = Integer.parseInt(req.queryParams("year"));
		int semestre = Integer.parseInt(req.queryParams("semestre"));
		int id_grupo = Integer.parseInt(req.queryParams("id_grupo"));
		int id_proyecto = Integer.parseInt(req.queryParams("id_proyecto"));
		
		Object obj = null;
		try {
			obj = service.createProyectoPlan(year, semestre, id_grupo, id_proyecto);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object getPlanes(Request req, Response res) {
		res.type("application/json");
		try {
			res.status(200);
			return this.service.getPlanes();
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
	
	public Object getPlan(Request req, Response res) {
		res.type("appliaction/json");
		String cad = req.params(":id");
		
		if(cad == null) {
			res.status(400); //BAD
			return "parametro faltante";
		}
		
		int id = Integer.parseInt(cad);
		
		try {
			Object obj = this.service.getPlan(id);
			if(obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no hay plan con ese id";
			}
			
			res.status(200);
			return obj;
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
}
