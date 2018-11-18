package controllers;

import services.DocenteService;
import services.ProgramaService;
import spark.Request;
import spark.Response;

public class DocenteController {
	
	static final DocenteService docenteService = new DocenteService();

	public DocenteController() {}
	
	public Object createDocente(Request req, Response res) {
		res.type("application/json");
		
		String nombre = req.queryParams("nombre");
		String fecha_nacimiento = req.queryParams("fecha_nacimiento");
		String direccion = req.queryParams("direccion");
		String telefono = req.queryParams("telefono");
		String celular = req.queryParams("celular");
		String sexo = req.queryParams("sexo");
		String correo = req.queryParams("correo");
		String foto = req.queryParams("foto");
		String nacionalidad = req.queryParams("nacionalidad");
		String identificacion = req.queryParams("identificaicon");
		int tipo_identificacion = Integer.parseInt(req.queryParams("tipo_identificacion"));
		int id_investigador = Integer.parseInt(req.queryParams("id_investigador"));
		String codigo = req.queryParams("codigo");
		int id_departamento = Integer.parseInt(req.queryParams("id_departamento"));
		int id_modalidad = Integer.parseInt(req.queryParams("id_modalidad"));
		int id_semillero_director  = Integer.parseInt(req.queryParams("id_semillero_director"));
		int tipo_participante = Integer.parseInt(req.queryParams("tipo_participante"));
		int estudio = Integer.parseInt(req.queryParams("estudio"));
		
		Object obj = null;
		try {
			obj = docenteService.createDocente(tipo_identificacion, nombre, fecha_nacimiento, direccion,
					telefono, celular, sexo, correo, foto, nacionalidad,
					identificacion, codigo, id_departamento,id_modalidad,  id_semillero_director, 
					tipo_participante,  id_investigador, estudio);
			res.status(201);// 201 CREATED
		} 
		catch (Exception e) {
			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
		return obj;
	}
	
	public Object getDocentes(Request req, Response res) {

		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return this.docenteService.getDocentes();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}
	
	public Object getDocente(Request req, Response res) {

		res.type("application/json");
		String cad = req.params(":id");

		if (cad == null) {

			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";

		}

		int id = Integer.parseInt(cad);

		try {

			Object obj = this.docenteService.getDocente(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado el docente con ese id";

			}

			res.status(200);// 200 OK
			return obj;
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}

	}
}
