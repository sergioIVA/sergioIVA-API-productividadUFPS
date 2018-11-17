package controllers;

import model.Departamento;
import model.Facultad;
import services.DepartamentoService;
import spark.Request;
import spark.Response;

public class DepartamentoController {

	
	static final DepartamentoService departamentoService=new DepartamentoService();
	
	public DepartamentoController() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Object createDepartamento(Request req, Response res) {

		res.type("application/json");

		String nombre = req.queryParams("nombre");
		String codigo = req.queryParams("codigo");
		String idfacultad=req.queryParams("id_facultad");
		
		
		
		//validar los parametros de entrada
		if (nombre == null || codigo == null || idfacultad==null) {
			res.status(400);// 400 BAD REQUEST
			return "parametros de peticion incorrectos";
		}
		
		int cod=0;
		int idFacul=0;
		  try {
		   cod=Integer.parseInt(codigo); 
		   idFacul=Integer.parseInt(idfacultad);
		  }catch(Exception e1){
			  res.status(400);// 400 BAD REQUEST
			return "codigo alfanumerico no permitido";  
		  }

		Object obj = null;
		try {
			obj = this.departamentoService.createDepartamento(cod, nombre, idFacul);
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
	
	public Object getDepartamento(Request req, Response res) {
		
		res.type("application/json");
		String cad = req.params(":id");

		if (cad == null) {

			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";

		}

		int id = Integer.parseInt(cad);

		try {

			Object obj = this.departamentoService.getDepartamento(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado el departamento con ese id";

			}

			res.status(200);// 200 OK
			return obj;
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}

		
	}
	
	public Object getDepartamentos(Request req, Response res) {

		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return this.departamentoService.getDepartamentos();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}

	}
	
	
	
	public Object updateDepartamento(Request req, Response res) {

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

			Departamento obj = this.departamentoService.getDepartamento(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado ese departamento con ese id";
			}

			obj = this.departamentoService.updateDepartamento(obj, nombre, cod);

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
	
	public Object deleteDepartamento(Request req, Response res) {
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

			Departamento obj = this.departamentoService.getDepartamento(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado el departamento con ese id";
			}

			boolean hecho=this.departamentoService.deleteDepartamento(id);

			if (!hecho) {
				res.status(400);// 400 BAD REQUEST
				return "no se pudo actualizar";

			}
			res.status(200);// 200 OK
			return "se elimino el departamento con id "+id;

		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}

		
	}



}
