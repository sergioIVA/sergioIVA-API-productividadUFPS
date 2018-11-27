package controllers;

import services.ProcesoEspecificoService;
import spark.Request;
import spark.Response;

public class ProcesoEspecificoController {

	final ProcesoEspecificoService procesoEspecificoService = new ProcesoEspecificoService();

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

		
	   int	idDirector = Integer.parseInt(req.params(":idDirector"));
		
		try {
			res.status(200);// 200 OK
			return this.procesoEspecificoService.getGrupoCategoriaDirector(idDirector);

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

			int idGrupoSemillero = Integer.parseInt(req.params(":idGrupoSemillero"));
			int tipoSession = Integer.parseInt(req.params(":tipoSession"));

			res.status(200);// 200 OK

			return this.procesoEspecificoService.getProyectoResponsable(idGrupoSemillero, tipoSession);

		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}

	public Object getLineasGrupoTipoProyectoGrupo(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":idGrupoSemillero");

		if (cad == null) {
			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";
		}
		int id = Integer.parseInt(cad);
		int tipoSession = Integer.parseInt(req.params(":tipoSession"));
		try {
			Object obj = this.procesoEspecificoService.getLineasGrupoTipoProyectoGrupo(tipoSession, id);
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

	public Object getProyectoNuevosIntegrantes(Request req, Response res) {

		res.type("application/json");
		String cad = req.params(":idGrupoSemillero");

		if (cad == null) {
			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";
		}
		int idGrupoSemillero = Integer.parseInt(cad);
		int tipoSession = Integer.parseInt(req.params(":tipoSession"));
		try {
			Object obj = this.procesoEspecificoService.getproyectosNuevosIntegrantes(idGrupoSemillero, tipoSession);
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

	public Object getcreatePlanGrupoSemillero(Request req, Response res) {

		res.type("application/json");
		String cad = req.queryParams("idGrupoSemillero");
		String year = req.queryParams("year");
		String semestre = req.queryParams("semestre");

		int idGrupoSemillero = Integer.parseInt(cad);
		int tipoSession = Integer.parseInt(req.queryParams("tipoSession"));
		try {
			Object obj = this.procesoEspecificoService.getcreatePlanGrupoSemillero(idGrupoSemillero, tipoSession, year,
					semestre);
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

	public Object getProyectosActividadesNoterminadoPlanAccionGrupoSemillero(Request req, Response res) {

		res.type("application/json");
		int idGrupoSemillero = Integer.parseInt(req.params(":idGrupoSemillero"));
		int tipoSession = Integer.parseInt(req.params(":tipoSession"));
		try {
			Object obj = this.procesoEspecificoService
					.getProyectosActividadesNoterminadoPlanAccionGrupoSemillero(idGrupoSemillero, tipoSession);
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

	public Object eventoNoTerminadoPlanAccionGrupo(Request req, Response res) {

		res.type("application/json");
		int idGrupo = Integer.parseInt(req.params(":idGrupo"));

		try {
			Object obj = this.procesoEspecificoService.eventoNoTerminadoPlanAccionGrupo(idGrupo);
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

	public Object getCapacitacionNoTerminadoPlanAccionSemillero(Request req, Response res) {

		res.type("application/json");
		int idSemillero = Integer.parseInt(req.params(":idSemillero"));

		try {
			Object obj = this.procesoEspecificoService.getCapacitacionNoTerminadoPlanAccionSemillero(idSemillero);
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

	public Object CreateEventoGrupoAsignarPlanAccion(Request req, Response res) {

		res.type("application/json");

		/// plan de accion
		String year = req.queryParams("year");
		String semestre = req.queryParams("semestre");
		int idGrupo = Integer.parseInt(req.queryParams("idGrupo"));

		/// evento
		String nombre = req.queryParams("nombre");
		String caracterEvento = req.queryParams("caracterEvento");
		String responsables = req.queryParams("responsables");
		String instituciones_promo = req.queryParams("instituciones_promo");
		String entidades = req.queryParams("entidades");
		String fecha_inicio = req.queryParams("fecha_inicio");
		String fecha_final = req.queryParams("fecha_final");

		try {
			Object obj = this.procesoEspecificoService.CreateEventoGrupoAsignarPlanAccion(year, semestre, idGrupo,
					nombre, caracterEvento, responsables, instituciones_promo, entidades, fecha_inicio, fecha_final);
			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object createActividadGrupoSemilleroAsignarPlanAccion(Request req, Response res) {

		res.type("application/json");

		/// plan de accion
		String year = req.queryParams("year");
		String semestre = req.queryParams("semestre");
		int idGrupoSemillero = Integer.parseInt(req.queryParams("idGrupoSemillero"));
		int tipoSession = Integer.parseInt(req.queryParams("tipoSession"));

		///
		String nombre = req.queryParams("nombre");
		String responsables = req.queryParams("responsables");
		String producto = req.queryParams("producto");
		String fecha_inicio = req.queryParams("fecha_inicio");
		String fecha_final = req.queryParams("fecha_final");

		try {
			Object obj = this.procesoEspecificoService.createActividadGrupoSemilleroAsignarPlanAccion(year, semestre,
					idGrupoSemillero, tipoSession, nombre, responsables, producto, fecha_inicio, fecha_final);
			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object capacitacionCrearSemilleroAsignarPlanAccion(Request req, Response res) {

		res.type("application/json");

		/// plan de accion
		String year = req.queryParams("year");
		String semestre = req.queryParams("semestre");
		int idSemillero = Integer.parseInt(req.queryParams("idSemillero"));

		/// capacitacion
		String nombre = req.queryParams("nombre");
		String responsables = req.queryParams("responsables");
		String objetivo = req.queryParams("objetivo");
		int n_asistentes = Integer.parseInt(req.queryParams("n_asistentes"));
		String fecha_ini = req.queryParams("fecha_ini");
		String fecha_fin = req.queryParams("fecha_fin");

		try {
			Object obj = this.procesoEspecificoService.getCapacitacionCrearSemilleroAsignarPlanAccion(year, semestre,
					idSemillero, nombre, objetivo, responsables, n_asistentes, fecha_ini, fecha_fin);
			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object getAsignarProyectoPlanAccionGrupoSemillero(Request req, Response res) {

		res.type("application/json");

		/// plan de accion
		String year = req.queryParams("year");
		String semestre = req.queryParams("semestre");
		int idGrupoSemillero = Integer.parseInt(req.queryParams("idGrupoSemillero"));
		int tipoSession = Integer.parseInt(req.queryParams("tipoSession"));
		int id_proyecto = Integer.parseInt(req.queryParams("id_proyecto"));

		try {
			Object obj = this.procesoEspecificoService.asignarProyectoPlanAccion(year, semestre, idGrupoSemillero,
					tipoSession, id_proyecto);
			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}

	}

	public Object getAsignarActividadesPlanAccionGrupoSemillero(Request req, Response res) {

		res.type("application/json");

		/// plan de accion
		String year = req.queryParams("year");
		String semestre = req.queryParams("semestre");
		int idGrupoSemillero = Integer.parseInt(req.queryParams("idGrupoSemillero"));
		int tipoSession = Integer.parseInt(req.queryParams("tipoSession"));
		int id_actividad = Integer.parseInt(req.queryParams("id_actividad"));

		try {
			Object obj = this.procesoEspecificoService.getAsignarActividadesPlanAccionGrupoSemillero(year, semestre,
					idGrupoSemillero, tipoSession, id_actividad);
			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object asignarCapacitacionPlanAccionSemillero(Request req, Response res) {
		res.type("application/json");

		/// plan de accion
		String year = req.queryParams("year");
		String semestre = req.queryParams("semestre");
		int idSemillero = Integer.parseInt(req.queryParams("idSemillero"));
		int id_capacitacion = Integer.parseInt(req.queryParams("id_capacitacion"));

		try {
			Object obj = this.procesoEspecificoService.asignarCapacitacionPlanAccionSemillero(year, semestre,
					idSemillero, id_capacitacion);
			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object asignarEventoPlanAccionGrupo(Request req, Response res) {
		res.type("application/json");

		/// plan de accion
		String year = req.queryParams("year");
		String semestre = req.queryParams("semestre");
		int idGrupo = Integer.parseInt(req.queryParams("idGrupo"));
		int id_evento = Integer.parseInt(req.queryParams("id_evento"));

		try {
			Object obj = this.procesoEspecificoService.asignarEventoPlanAccionGrupo(year, semestre, idGrupo, id_evento);

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object getTipologiaProductos1(Request req, Response res) {
		res.type("application/json");

		try {
			Object obj = this.procesoEspecificoService.getTipologiaProductos1();

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object getTipologiaProductos2(Request req, Response res) {
		res.type("application/json");

		int id_tipologia1 = Integer.parseInt(req.params(":idTipologia1"));

		try {
			Object obj = this.procesoEspecificoService.getTipologiaProductos2(id_tipologia1);

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object getTipologiaProductos3(Request req, Response res) {
		res.type("application/json");

		int id_tipologia2 = Integer.parseInt(req.params(":idTipologia2"));

		try {
			Object obj = this.procesoEspecificoService.getTipologiaProductos3(id_tipologia2);

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object getcategoriaProducto3(Request req, Response res) {
		res.type("application/json");

		int id_tipologia3 = Integer.parseInt(req.params(":idTipologia3"));

		try {
			Object obj = this.procesoEspecificoService.getCategoria(id_tipologia3);

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object planesAccionGrupoSemillero(Request req, Response res) {
		res.type("application/json");

		int idGrupoSemillero = Integer.parseInt(req.params(":idGrupoSemillero"));
		int tipoSession = Integer.parseInt(req.params(":tipoSession"));

		try {
			Object obj = this.procesoEspecificoService.planesAccionGrupoSemillero(idGrupoSemillero, tipoSession);

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object tipoReferencia(Request req, Response res) {
		res.type("application/json");

		try {
			Object obj = this.procesoEspecificoService.tipoReferencia();

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

	public Object Createlibro(Request req, Response res) {

		res.type("application/json");

		String nombre = req.queryParams("nombre");
		String descripcion = req.queryParams("descripcion");
		int id_proyecto = Integer.parseInt(req.queryParams("id_proyecto"));
		int id_tipo_producto = Integer.parseInt(req.queryParams("id_tipo_producto"));

		String titulo = req.queryParams("titulo");
		String ISBN = req.queryParams("ISBN");
		String fecha_publica = req.queryParams("fecha_publica");
		String autores = req.queryParams("autores");
		String editorial = req.queryParams("editorial");
		String lugar_publica = req.queryParams("lugar_publica");
		String certificacion_entidad = req.queryParams("certificacion_entidad");
		String curriculo = req.queryParams("curriculo");
		int tipo_desarrollo = Integer.parseInt(req.queryParams("tipo_desarrollo"));

		try {
			Object obj = this.procesoEspecificoService.createLibro(nombre, descripcion, id_proyecto, id_tipo_producto,
					titulo, ISBN, fecha_publica, autores, editorial, lugar_publica, certificacion_entidad, curriculo,
					tipo_desarrollo);

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}

	}

	public Object tesis(Request req, Response res) {

		res.type("application/json");

		String nombre = req.queryParams("nombre");
		String descripcion = req.queryParams("descripcion");
		int id_proyecto = Integer.parseInt(req.queryParams("id_proyecto"));
		int id_tipo_producto = Integer.parseInt(req.queryParams("id_tipo_producto"));

		String titulo=req.queryParams("titulo");
		String institucion=req.queryParams("institucion");
		String anio=req.queryParams("anio");
		String reconocimiento=req.queryParams("reconocimiento");

		try {
			Object obj = this.procesoEspecificoService.tesis(nombre, descripcion,
					id_proyecto, id_tipo_producto, titulo, institucion, anio, reconocimiento);

			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}

	}
	
	public Object login(Request req, Response res) {
		res.type("application/json");

		String usuario = req.queryParams("usuario");
		String clave = req.queryParams("clave");

		try {
			
			Object obj = this.procesoEspecificoService.login(usuario, clave);
           
			if (obj == null) {
				res.status(400);
				return "no se ha encontrado";
			}

			res.status(200);
			return obj;
		} catch (Exception e) {
			res.status(500);
			return e.toString();
		}
	}

}
