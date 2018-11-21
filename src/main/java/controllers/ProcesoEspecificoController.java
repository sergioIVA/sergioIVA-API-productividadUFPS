package controllers;

import services.ProcesoEspecificoService;
import spark.Request;
import spark.Response;

public class ProcesoEspecificoController {

	final ProcesoEspecificoService procesoEspecificoService=new ProcesoEspecificoService();
	
	public ProcesoEspecificoController() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Object getPaginaPrincipal(Request req, Response res) {
		
		res.type("application/json");
		String cad = req.params(":id");

		if (cad == null) {
			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";

		}

		int id = Integer.parseInt(cad);

		try {

			Object obj = this.procesoEspecificoService.getPanelPrincipal(id);
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
	
	public Object getGrupoCategoriaDirector(Request req, Response res) {
		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return this.procesoEspecificoService.getGrupoCategoriaDirector();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}

	public Object getDatosCrearGrupo(Request req, Response res) {
		res.type("application/json");
		try {
			res.status(200);// 200 OK
			return this.procesoEspecificoService.getdatosCrearGrupo();
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}
	
	public Object getSemilleroDirector(Request req, Response res) {
		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return this.procesoEspecificoService.getSemilleroDirector();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}

	public Object getLineaGrupoDocenteGrupo(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":idGrupo");

		if (cad == null) {
			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";
		}
		int id = Integer.parseInt(cad);
		try {
			Object obj = this.procesoEspecificoService.getLineaGrupoDocenteGrupo(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "No encontrado";
			}
			res.status(200);// 200 OK
			return obj;
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}

	}
	
	public Object getProyectoResponsable(Request req, Response res) {
		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return this.procesoEspecificoService.getProyectoResponsable();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}
}