package controllers;

import services.LineaInvestigacionService;
import spark.Request;
import spark.Response;

public class LineaInvestigacionController {

	static final LineaInvestigacionService service = new LineaInvestigacionService();
	
	public LineaInvestigacionController() {}
	
	public Object createLinea(Request req, Response res) {
		res.type("application/json");
		String nombre = req.queryParams("nombre");
		String descripcion = req.queryParams("descripcion");
		int id_tipo_linea = Integer.parseInt(req.queryParams("id_tipo_linea"));
		String lider_linea= req.queryParams("lider_linea");
		
		Object obj = null;
		try {
			obj = this.service.createLineaInvestigacion(nombre, descripcion, id_tipo_linea, lider_linea);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object getLineasInvestigacion(Request req, Response res) {
		res.type("application/json");
		
		try {
			res.status(200);
			return this.service.getLineasInvestigacion();
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
	
	public Object getLineaInvestigacion(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");
		
		if(cad == null) {
			res.status(400);
			return "parametro faltante";
		}
		
		int id = Integer.parseInt(cad);
		
		try {
			Object obj = this.service.getLineaInvestigacion(id);
			if(obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}
			
			res.status(200);
			return obj;
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
	
	public Object asociarLineaGrupo(Request req, Response res) {
		res.type("application/json");
		int id_linea = Integer.parseInt(req.queryParams("id_linea"));
		int id_grupo = Integer.parseInt(req.queryParams("id_grupo"));
		
		Object obj = null;
		try {
			obj = this.service.asociarLineaGrupo(id_linea, id_grupo);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object asociarLineaSemillero(Request req, Response res) {
		res.type("application/json");
		int id_linea = Integer.parseInt(req.queryParams("id_linea"));
		int id_semillero = Integer.parseInt(req.queryParams("id_semillero"));
		
		Object obj = null;
		try {
			obj = this.service.asociarLineaSemillero(id_linea, id_semillero);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object getLineasGrupo(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");
		
		if(cad == null) {
			res.status(400);
			return "parametro faltante";
		}
		
		int id = Integer.parseInt(cad);
		
		try {
			Object obj = this.service.getLineasGrupo(id);
			if(obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}
			
			res.status(200);
			return obj;
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
	
	public Object getLineasSemillero(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");
		
		if(cad == null) {
			res.status(400);
			return "parametro faltante";
		}
		
		int id = Integer.parseInt(cad);
		
		try {
			Object obj = this.service.getLineasSemillero(id);
			if(obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}
			
			res.status(200);
			return obj;
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
	
}
