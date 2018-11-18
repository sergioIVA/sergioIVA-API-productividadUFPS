package controllers;


import services.CategoriaGrupoService;
import spark.Request;
import spark.Response;

public class CategoriaGrupoController {
	
	final CategoriaGrupoService categoriaGrupoService=new CategoriaGrupoService();
	
	public CategoriaGrupoController() {
		
	}

	public Object getCategoriaGrupo(Request req, Response res) {

		res.type("application/json");

		try {
			res.status(200);// 200 OK
			return this.categoriaGrupoService.getCategoriasGrupo();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}

	}
	
	
}
