package controllers;

import services.ProductoService;
import spark.Request;
import spark.Response;

public class ProductoController {
	
	final ProductoService service = new ProductoService();

	public ProductoController() {}
	
	public Object createProductoEmpresarial(Request req, Response res) {
		res.type("application/json");
		
		String nombre = req.queryParams("nombre");
		String descripcion = req.queryParams("descripcion");
		int id_proyecto = Integer.parseInt(req.queryParams("id_proyecto"));
		int id_tipo_producto = Integer.parseInt(req.queryParams("id_tipo_producto"));
		String valor_contrato = req.queryParams("valor_contrato");
		String numero_contrato = req.queryParams("numero_contrato");
		String nit = req.queryParams("nit");
		String certificado_camara = req.queryParams("certificado_camara");
		String certificado_institucional = req.queryParams("certificado_institucional");
		String autores = req.queryParams("autores");
		String certificacion_implementacion = req.queryParams("certificacion_implementacion");
		String documento_ley = req.queryParams("documento_ley");
		String certificacion_producto = req.queryParams("certificacion_producto");
		String nombre_empresa = req.queryParams("nombre_empresa");
		String fecha = req.queryParams("fecha");
		String titulo = req.queryParams("titulo");
		String nombre_innovacion = req.queryParams("nombre_innovacion");
		
		Object obj = null;
		try {
			obj = service.createProductoEmpresarial(nombre, descripcion, id_proyecto, id_tipo_producto, valor_contrato, numero_contrato, nit, certificado_camara, certificado_institucional, autores, certificacion_implementacion, documento_ley, certificacion_producto, nombre_empresa, fecha, titulo, nombre_innovacion);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object createProductoTecnologico(Request req, Response res) {
		res.type("application/json");
		
		String nombre = req.queryParams("nombre");
		String descripcion = req.queryParams("descripcion");
		int id_proyecto = Integer.parseInt(req.queryParams("id_proyecto"));
		int id_tipo_producto = Integer.parseInt(req.queryParams("id_tipo_producto"));
		String numero = req.queryParams("numero");
		String certificado = req.queryParams("certificado");
		String titular = req.queryParams("titular");
		String anio_obtencion = req.queryParams("anio_obtencion");
		String paises_obtencion = req.queryParams("paises_obtencion");
		String gaceta_publica = req.queryParams("gaceta_publica");
		int contrato_explotacion_licen_id = Integer.parseInt(req.queryParams("contrato_explotacion_licen_id"));
		int id_solicitud = Integer.parseInt(req.queryParams("id_solicitud"));
		int estado_patente = Integer.parseInt(req.queryParams("estado_patente"));
		
		Object obj = null;
		try {
			obj = service.createProductoTecnologico(nombre, descripcion, id_proyecto, id_tipo_producto, numero, certificado, titular, anio_obtencion, paises_obtencion, gaceta_publica, contrato_explotacion_licen_id, id_solicitud, estado_patente);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object getProductosEmp(Request req, Response res) {
		res.type("application/json");
		
		try {
			res.status(200);
			return this.service.getProductosEmpresariales();
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
	
	public Object getProductoEmp(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");
		
		if(cad == null) {
			res.status(400);
			return "parametro faltante";
		}
		
		int id = Integer.parseInt(cad);
		
		try {
			Object obj = this.service.getProductoEmpresarial(id);
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
	
	public Object getProductosTec(Request req, Response res) {
		res.type("application/json");
		
		try {
			res.status(200);
			return this.service.getProductosTecnologicos();
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
	}
	
	public Object getProductoTec(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");
		
		if(cad == null) {
			res.status(400);
			return "parametro faltante";
		}
		
		int id = Integer.parseInt(cad);
		
		try {
			Object obj = this.service.getProductoTecnologico(id);
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
