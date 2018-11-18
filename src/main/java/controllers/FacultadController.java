package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Facultad;
import services.FacultadService;
import spark.Request;
import spark.Response;

public class FacultadController {

	//static final Gson gson = new Gson();
	static final FacultadService facultadService = new FacultadService();

	public FacultadController() {
		
	}

	public Object createFacultades(Request req, Response res) {

		res.type("application/json");

		String nombre = req.queryParams("nombre");
		String codigo = req.queryParams("codigo");
		
		 System.out.println(req.body());
		
		//validar los parametros de entrada
		if (nombre == null || codigo == null) {
			res.status(400);// 400 BAD REQUEST
			return "parametros de peticion incorrectos";
		}
		
		int cod=0;
		  try {
		   cod=Integer.parseInt(codigo); 
		  }catch(Exception e1){
			  res.status(400);// 400 BAD REQUEST
			return "codigo alfanumerico no permitido";  
		  }

		Object obj = null;
		try {
			obj = facultadService.createFacultad(cod,nombre);
			res.status(201);// 201 CREATED
		} 
		
		catch (Exception e) {

			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
		return obj;
	}

	public Object getFacultades(Request req, Response res) {

		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return facultadService.getFacultades();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}

	public Object getFacultad(Request req, Response res) {

		res.type("application/json");
		String cad = req.params(":id");

		if (cad == null) {

			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";

		}

		int id = Integer.parseInt(cad);

		try {

			Object obj = this.facultadService.getFacultad(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado la facultad con ese id";

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

			Facultad obj = this.facultadService.getFacultad(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado la facultad con ese id";
			}

			obj = this.facultadService.updateFacultad(obj, nombre,cod);

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

			Facultad obj = this.facultadService.getFacultad(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado la facultad con ese id";
			}

			boolean hecho=this.facultadService.deleteFacultad(id);

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
