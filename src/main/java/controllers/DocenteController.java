package controllers;

import spark.Request;
import spark.Response;

public class DocenteController {

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
		String identificaicon = req.queryParams("identificaicon");
		int tipo_identificacion = Integer.parseInt(req.queryParams("tipo_identificacion"));
		int id_investigador = Integer.parseInt(req.queryParams("id_investigador"));
		String codigo = req.queryParams("codigo");
		int id_departamento = Integer.parseInt(req.queryParams("id_departamento"));
		int id_modalidad = Integer.parseInt(req.queryParams("id_modalidad"));
		int id_semillero_director  = Integer.parseInt(req.queryParams("id_semillero_director"));
		
		Object obj = null;
		try {
			//obj = DocenteService.createDOcente(datos...);
			res.status(201);// 201 CREATED
		} 
		catch (Exception e) {
			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
		return obj;
	}
}
