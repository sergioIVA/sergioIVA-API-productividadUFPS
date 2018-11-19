package controllers;

import model.Facultad;
import model.Grupo;
import services.GrupoService;
import spark.Request;
import spark.Response;

public class GrupoController {

	static final GrupoService grupoService = new GrupoService();

	public GrupoController() {
		
	}
	
	public Object CreateGrupo(Request req, Response res) {
		
		res.type("application/json");

		String nombre = req.queryParams("nombre");
		String sigla = req.queryParams("sigla");
		String ubicacion = req.queryParams("ubicacion");
		String fecha_creacion = req.queryParams("fecha_creacion");
		String codigo_colciencias = req.queryParams("codigo_colciencias");
		int clasificado = Integer.parseInt(req.queryParams("clasificado"));
		String correo = req.queryParams("correo");
		int id_categoria = Integer.parseInt(req.queryParams("id_categoria"));
		int id_unidad = Integer.parseInt(req.queryParams("id_unidad"));
		int director_grupo = Integer.parseInt(req.queryParams("director_grupo"));
	
		Object obj;
		try {
			obj = grupoService.createGrupo(nombre,sigla,ubicacion,fecha_creacion,codigo_colciencias,clasificado,
					correo,id_categoria,id_unidad,director_grupo);
			res.status(201);// 201 CREATED
		} 
		
		catch (Exception e) {

			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.getMessage();
		}
		return obj;
	}

	public Object getGrupos(Request req, Response res) {

		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return grupoService.getGrupos();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			//return e.toString();
			return e.getMessage();

		}
	}

	public Object getGrupo(Request req, Response res) {

		res.type("application/json");
		String cad = req.params(":id");

		if (cad == null) {
			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";

		}

		int id = Integer.parseInt(cad);

		try {

			Object obj = this.grupoService.getGrupo(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado un grupo con ese id";

			}

			res.status(200);// 200 OK
			return obj;
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}

	}

	public Object updateFacultad(Request req, Response res) {
     
		
		res.type("application/json");
		String cad = req.params(":id");

		int id = -1;
		try {
			id = Integer.parseInt(cad);
		} catch (Exception e) {
			res.status(400);// 400 BAD REQUEST
			return "parametro alfanumero";
		}

		String nombre = req.queryParams("nombre");
		String sigla = req.queryParams("sigla");
		String ubicacion = req.queryParams("ubicacion");
		String fecha_creacion = req.queryParams("fecha_creacion");
		String codigo_colciencias = req.queryParams("codigo_colciencias");
		int clasificado = Integer.parseInt(req.queryParams("clasificado"));
		String correo = req.queryParams("correo");
		int id_categoria = Integer.parseInt(req.queryParams("id_categoria"));
		int id_unidad = Integer.parseInt(req.queryParams("id_unidad"));
		int director_grupo = Integer.parseInt(req.queryParams("director_grupo"));
		
		try {

			Grupo obj = this.grupoService.getGrupo(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado la facultad con ese id";
			}

			obj = this.grupoService.updateGrupo(obj, nombre,sigla,ubicacion,fecha_creacion, codigo_colciencias,
					clasificado,correo,id_categoria,id_unidad,director_grupo);

			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no se pudo actualizar";

			}
			res.status(200);// 200 OK
			return obj;
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
       
	}
	
	public Object deleteFacultad(Request req, Response res) {
		
		
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

			Grupo obj = this.grupoService.getGrupo(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado la facultad con ese id";
			}

			boolean hecho=this.grupoService.deleteGrupo(id);

			if (!hecho) {
				res.status(400);// 400 BAD REQUEST
				return "no se pudo actualizar";

			}
			res.status(200);// 200 OK
			return "se elimino la facultad con id "+id;

		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
		
	}
}
