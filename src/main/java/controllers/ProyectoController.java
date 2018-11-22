package controllers;

import services.ProyectoService;
import spark.Request;
import spark.Response;

import java.util.LinkedHashMap;

import model.Proyecto;

public class ProyectoController {

	static final ProyectoService service = new ProyectoService();
	
	public ProyectoController() {}
	
	public Object createProyecto(Request req, Response res) {
		res.type("application/json");
		int costoTotal = Integer.parseInt(req.queryParams("costoTotal"));
		int id_tipo = Integer.parseInt(req.queryParams("id_tipo"));
		int id_linea = Integer.parseInt(req.queryParams("id_linea"));
		String tiempo_total_ejecucion = req.queryParams("tiempo_ejecucion");
		String titulo = req.queryParams("titulo");
		String fecha_inicio = req.queryParams("fecha_inicio");
		String fecha_final = req.queryParams("fecha_final");
		String resultados_esperados = req.queryParams("resultados_esperados");
		String n_contrato = req.queryParams("n_contrato");
		String resumen = req.queryParams("resumen");
		String objetivo_general = req.queryParams("objetivo_general");
		int tipo_participacion_id = Integer.parseInt(req.queryParams("tipo_participacion_id"));
		
		System.out.println(req.body());
		
		Object obj = null;
		try {
			obj = service.createProyecto(costoTotal, id_tipo, id_linea, tiempo_total_ejecucion, tipo_participacion_id, 
					titulo,fecha_inicio, fecha_final, resultados_esperados, n_contrato, resumen, objetivo_general);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object getProyectos(Request req, Response res) {
		res.type("application/json");
		
		try {
			res.status(200);
			return this.service.getProyectos();
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
	
	public Object getProyectosGrupo(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id_grupo");
		
		if(cad == null) {
			res.status(400);
			return "parametro faltante";
		}
		
		int id = Integer.parseInt(cad);
		
		try {
			Object obj = this.service.getProyectosGrupo(id);
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
	
	public Object getProyecto(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");
		
		if(cad == null) {
			res.status(400);
			return "parametro faltante";
		}
		
		int id = Integer.parseInt(cad);
		
		try {
			Object obj = this.service.getSpecificProyecto(id);
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
	
	public Object deleteProyecto(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");
		
		int id = -1;
		try {
			id = Integer.parseInt(cad);
		} catch (Exception e) {
			res.status(400);// 400 BAD REQUEST
			return "parametro alfanumero";
		}
		
		try {
			Object obj = this.service.getSpecificProyecto(id);
			if(obj == null) {
				res.status(400);
				return "no se encuentra";
			}
			
			boolean done = this.service.deleteProyecto(id);
			
			if(!done) {
				res.status(400);
				return "no se pudo borrar";
			}else {
				res.status(200);
				return "se elimino satisfactoriamente con id " + id;
			}
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
}
