package controllers;

import model.Facultad;
import model.Programa;
import services.FacultadService;
import services.ProgramaService;
import spark.Request;
import spark.Response;

public class ProgramaController {

	static final ProgramaService programaService = new ProgramaService();
	
	public ProgramaController() {
		// TODO Auto-generated constructor stub
	}
	
	public Object createPrograma(Request req, Response res) {
		res.type("application/json");

		String nombre = req.queryParams("nombre");
		String codigo = req.queryParams("codigo");
		String idFacultad=req.queryParams("id_facultad");
		
		 System.out.println(req.body());
		
		//validar los parametros de entrada
		if (nombre == null || codigo == null) {
			res.status(400);// 400 BAD REQUEST
			return "parametros de peticion incorrectos";
		}
		
		int cod=0;
		int id=0;
		  try {
		   cod=Integer.parseInt(codigo);
		   id=Integer.parseInt(idFacultad); 
		  }catch(Exception e1){
			  res.status(400);// 400 BAD REQUEST
			return "codigo o id alfanumerico no permitido";  
		  }

		Object obj = null;
		try {
			obj = programaService.createPrograma(cod,id, nombre);
			res.status(201);// 201 CREATED
		} 
		
		catch(java.sql.SQLIntegrityConstraintViolationException e) {
			  res.status(400);
			  return "codigo "+cod+" ya se encuentra asociado";
		}
		catch (Exception e) {

			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
		return obj;
	}
	

	public Object getProgramas(Request req, Response res) {

		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return this.programaService.getProgramas();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}

	
	
	public Object getPrograma(Request req, Response res) {

		res.type("application/json");
		String cad = req.params(":id");

		if (cad == null) {

			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";

		}

		int id = Integer.parseInt(cad);

		try {

			Object obj = this.programaService.getPrograma(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado el programa con ese id";

			}

			res.status(200);// 200 OK
			return obj;
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}

	}

	
	public Object updatePrograma(Request req, Response res) {

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
		String codigo=req.queryParams("codigo");

		if (nombre == null || codigo==null) {
			res.status(400);// 400 BAD REQUEST
			return "parametros no encontrados";
		}
		
		int cod=0;
		try {
			cod=Integer.parseInt(codigo);
			
		}catch(Exception e1) {
			res.status(400);// 400 BAD REQUEST
			return "codigo alfanumerico no valido";
		}
		
		
		try {

			Programa obj = this.programaService.getPrograma(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado el programa con ese id";
			}

			obj = this.programaService.updatePrograma(obj, nombre, cod);

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
	
	
	public Object deletePrograma(Request req, Response res) {
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

			Programa obj = this.programaService.getPrograma(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado el programa con ese id";
			}

			boolean hecho=this.programaService.deletePrograma(id);

			if (!hecho) {
				res.status(400);// 400 BAD REQUEST
				return "no se pudo actualizar";

			}
			res.status(200);// 200 OK
			return "se elimino el programa con id "+id;

		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}

		
	}

}
