package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Facultad;
import model.User;
import services.AuthService;
import services.FacultadService;
import services.UserService;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Date;
import java.util.Properties;

import util.JsonTransformer;

public class MainController {

	final UserService userService = new UserService();
	//final FacultadService facultadService = new FacultadService();
	final AuthService aut = new AuthService();
	final FacultadController facultadController=new FacultadController();
	final ProgramaController programaController=new ProgramaController();
	final DepartamentoController departamentoController=new DepartamentoController();
	final GrupoController grupoController=new GrupoController();
	final CategoriaGrupoController categoriaGrupoController=new CategoriaGrupoController();
	final DocenteController docenteController=new DocenteController();
	final ProcesoEspecificoController procesoEspecificoController=new ProcesoEspecificoController();
	final PlanAccionGrupoController planAccionGrupoController = new PlanAccionGrupoController();
	final SemilleroController semilleroController = new SemilleroController();
	final ObjetivoController objetivoController = new ObjetivoController();
	final ProyectoController proyectoController = new ProyectoController();
	final LineaInvestigacionController lineaInvestigacionController = new LineaInvestigacionController();

	public MainController() {

		// Definicion de los CORS

		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Request-Method", "POST, GET,PUT, PATCH, DELETE, OPTIONS");
			response.header("Access-Control-Allow-Headers", "Origin, X-Request-With, Content-Type, Accept");
			response.type("application/json");
		});

		// ruta de acceso principal
		path("/api/v1", () -> {

			//1.login
			//o post("/login",(req,res)->{return "acceso a ruta /login";});
			
			// registro de actores
			post("/actores",(req,res)->{return "acceso a ruta /login";});
			
			// 2.Facultad
			get("/facultad",(req,res)->{return facultadController.getFacultades(req, res);},new JsonTransformer());
			post("/facultad",(req,res)->{return facultadController.createFacultades(req, res);},new JsonTransformer());
			delete("/facultad/:id", (req, res) -> {return facultadController.deleteFacultad(req, res);},new JsonTransformer());
			get("/facultad/:id",(req,res)->{return facultadController.getFacultad(req, res);},new JsonTransformer());
			put("/facultad/:id", (req, res) -> {return facultadController.updateFacultad(req, res);},new JsonTransformer());
			
			//3.Departamento
			get("/departamento",(req,res)->{return this.departamentoController.getDepartamentos(req, res);},new JsonTransformer());
			get("/departamento/:id",(req,res)->{return this.departamentoController.getDepartamento(req, res);},new JsonTransformer());
			post("/departamento",(req,res)->{return this.departamentoController.createDepartamento(req, res);},new JsonTransformer());
			put("/departamento/:id", (req, res) -> {return this.departamentoController.updateDepartamento(req, res);},new JsonTransformer());
			delete("/departamento/:id", (req, res) -> {return this.departamentoController.deleteDepartamento(req, res);},new JsonTransformer());
			
			
			//4.Programa
			get("/programa",(req,res)->{return this.programaController.getProgramas(req, res);},new JsonTransformer());
			get("/programa/:id",(req,res)->{return this.programaController.getPrograma(req, res);},new JsonTransformer());
			post("/programa",(req,res)->{return programaController.createPrograma(req, res);},new JsonTransformer());
			put("/programa/:id", (req, res) -> {return this.programaController.updatePrograma(req, res);},new JsonTransformer());
			delete("/programa/:id", (req, res) -> {return this.programaController.deletePrograma(req, res);},new JsonTransformer());
			
			//categoriaGrupo
			get("/categoriaGrupo",(req,res)->{return this.categoriaGrupoController.getCategoriaGrupo(req, res);},new JsonTransformer());
			
			
			//panelPrincipal
			get("/paginaPrincipal/:id",(req,res)->{return this.procesoEspecificoController.getPaginaPrincipal(req, res);},new JsonTransformer());
			get("/grupoCategoriaDirector",(req,res)->{return this.procesoEspecificoController.getGrupoCategoriaDirector(req, res);},new JsonTransformer());
			get("/datosCrearGrupo",(req,res)->{return this.procesoEspecificoController.getDatosCrearGrupo(req, res);},new JsonTransformer());
			get("/datosSemilleroDirector",(req,res)->{return this.procesoEspecificoController.getSemilleroDirector(req, res);},new JsonTransformer());
			get("/lineaGrupoDocenteGrupo/:idGrupo",(req,res)->{return this.procesoEspecificoController.getLineaGrupoDocenteGrupo(req, res);},new JsonTransformer());
			get("/proyectoResponsable",(req,res)->{return this.procesoEspecificoController.getProyectoResponsable(req, res);},new JsonTransformer());
			get("/lineasGrupoTipoProyectoGrupo/:idGrupoSemillero/session/:tipoSession",(req,res)->{return this.procesoEspecificoController.getLineasGrupoTipoProyectoGrupo(req, res);},new JsonTransformer());
			
			//semillero
			post("/semillero",(req,res)->{return this.semilleroController.createSemillero(req, res);},new JsonTransformer());
			
			
			//5.Grupo
			get("/grupo",(req,res)->{return "acceso /grupo get";});
			get("/grupo/:id",(req,res)->{return this.grupoController.getGrupo(req, res);},new JsonTransformer());
			post("/grupo",(req,res)->{return grupoController.CreateGrupo(req, res);},new JsonTransformer());
			put("/grupo/:id", (req, res) -> {return "acceso /grupo put con id "+req.queryParams(":id");});
			delete("/grupo/:id", (req, res) -> {return "acceso /grupo delete con id "+req.queryParams(":id");});
			
			
			// 6.apoyo del programa
			get("/apoyo",(req,res)->{return "acceso /apoyo get";});
			get("/apoyo/:id",(req,res)->{return "acceso /apoyo get con id "+req.queryParams(":id");});
			post("/apoyo",(req,res)->{return "acceso /apoyo post ";});
			put("/apoyo/:id", (req, res) -> {return "acceso /apoyo put con id "+req.queryParams(":id");});
			delete("/apoyo/:id", (req, res) -> {return "acceso /apoyo delete con id "+req.queryParams(":id");});
			
			
			//7.Docente
			get("/docente",(req,res)->{return this.docenteController.getDocentes(req, res);},new JsonTransformer());
			get("/docente/:id",(req,res)->{return "acceso /docente get con id "+req.queryParams(":id");});
			post("/docente",(req,res)->{return "acceso /docente post ";});
			put("/doente/:id", (req, res) -> {return "acceso /docente put con id "+req.queryParams(":id");});
			delete("/docente/:id", (req, res) -> {return "acceso /docente delete con id "+req.queryParams(":id");});
			
			//8.Investigador externo
			get("/investigadorExterno",(req,res)->{return "acceso /investigador get";});
			get("/investigadorExterno/:id",(req,res)->{return "acceso /investigador get con id "+req.queryParams(":id");});
			post("/investigadorExterno",(req,res)->{return "acceso /investigador post ";});
			put("/investigadorExterno/:id", (req, res) -> {return "acceso /investigador put con id "+req.queryParams(":id");});
			delete("/investigadorExterno/:id", (req, res) -> {return "acceso /investigador delete con id "+req.queryParams(":id");});
			
			//9.Estudiante 
			get("/estudiante",(req,res)->{return "acceso /estudiante get";});
			get("/estudiante/:id",(req,res)->{return "acceso /estudiante get con id "+req.queryParams(":id");});
			post("/estudiante",(req,res)->{return "acceso /estudiante post ";});
			put("/estudiante/:id", (req, res) -> {return "acceso /estudiante put con id "+req.queryParams(":id");});
			delete("/estudiante/:id", (req, res) -> {return "acceso /estudiante delete con id "+req.queryParams(":id");});
			
			//10.Joven investigador
			get("/joven",(req,res)->{return "acceso /joven get";});
			get("/joven/:id",(req,res)->{return "acceso /joven get con id "+req.queryParams(":id");});
			post("/joven",(req,res)->{return "acceso /joven post ";});
			put("/joven/:id", (req, res) -> {return "acceso /joven put con id "+req.queryParams(":id");});
			delete("/joven/:id", (req, res) -> {return "acceso /joven delete con id "+req.queryParams(":id");});
			
			//11.Director grupo
			get("/directorGrupo",(req,res)->{return "acceso /directorGrupo get";});
			get("/directorGrupo/:id",(req,res)->{return "acceso /directorGrupo get con id "+req.queryParams(":id");});
			post("/directorGrupo",(req,res)->{return "acceso /directorGrupo post ";});
			put("/directorGrupo/:id", (req, res) -> {return "acceso /directorGrupo put con id "+req.queryParams(":id");});
			delete("/directorGrupo/:id", (req, res) -> {return "acceso /directorGrupo delete con id "+req.queryParams(":id");});
			
			//12.Director semillero
			get("/directorSemillero",(req,res)->{return "acceso /directorSemillero get";});
			get("/directorSemillero/:id",(req,res)->{return "acceso /directorSemillero get con id "+req.queryParams(":id");});
			post("/directorSemillero",(req,res)->{return "acceso /directorSemillero post ";});
			put("/directorSemillero/:id", (req, res) -> {return "acceso /directorSemillero put con id "+req.queryParams(":id");});
			delete("/directorSemillero/:id", (req, res) -> {return "acceso /directorSemillero delete con id "+req.queryParams(":id");});
			
			//categoria
			
			// 1.Resgitar
			// 2.Eliminar
			
			
			//14. Administrador
			get("/administrador",(req,res)->{return "acceso /administrador get";});
			get("/administrador/:id",(req,res)->{return "acceso /administrador get con id "+req.queryParams(":id");});
			put("/administrador/:id", (req, res) -> {return "acceso /administrador put con id "+req.queryParams(":id");});
			post("/administrador",(req,res)->{return "acceso /administrador post ";});
			delete("/administrador/:id", (req, res) -> {return "acceso /administrador delete";});
			
			
            //15.Proyecto 
			
			get("/proyecto",(req,res)->{return this.proyectoController.getProyectos(req, res);}, new JsonTransformer());
			get("/proyecto/:id",(req,res)->{return this.proyectoController.getProyecto(req, res);}, new JsonTransformer());
			get("/proyecto/:id_grupo", (req, res) -> {return this.proyectoController.getProyectosGrupo(req, res);}, new JsonTransformer());
			post("/proyecto",(req,res)->{return this.proyectoController.createProyecto(req, res);}, new JsonTransformer());
			delete("/proyecto/:id", (req, res) -> {return this.proyectoController.deleteProyecto(req, res);}, new JsonTransformer());
			
			
			//16.Registrar avance del proyecto
			put("/avanceActividad/:id", (req, res) -> {return "acceso /avanceProyecto put con id "+req.queryParams(":id");});
			  
			//entrada
			/*
			 * idGrupo
			 * año plan
			 * semestre
			 * id_actividad
			 * 
			 * */
			
			
			//17.Productos
			get("/producto",(req,res)->{return "acceso /producto get";});
			get("/producto/:id",(req,res)->{return "acceso /producto get con id "+req.queryParams(":id");});
			put("/producto/:id", (req, res) -> {return "acceso /producto put con id "+req.queryParams(":id");});
			post("/producto",(req,res)->{return "acceso /producto post ";});
			delete("/producto/:id", (req, res) -> {return "acceso /producto delete";});
			
			
			//18.Planes de accion grupo
			get("/planGrupo",(req,res)->{return this.planAccionGrupoController.getPlanes(req, res);});
			get("/planGrupo/:id",(req,res)->{return this.planAccionGrupoController.getPlan(req, res);}, new JsonTransformer());
			put("/planGrupo/:id", (req, res) -> {return "acceso /planGrupo put con id "+req.queryParams(":id");});
			post("/planGrupo",(req,res)->{return this.planAccionGrupoController.createPlan(req, res);}, new JsonTransformer());
			delete("/planGrupo/:id", (req, res) -> {return "acceso /planGrupo delete";});
			
			
			//19.Planes de accion Semillero
			get("/planSemillero",(req,res)->{return "acceso /planSemillero get";});
			get("/planSemillero/:id",(req,res)->{return "acceso /planSemillero get con id "+req.queryParams(":id");});
			put("/planSemillero/:id", (req, res) -> {return "acceso /planSemillero put con id "+req.queryParams(":id");});
			post("/planSemillero",(req,res)->{return "acceso /planSemillero post ";});
			delete("/planSemillero/:id", (req, res) -> {return "acceso /planSemillero delete";});
		
			//20.Eventos
			get("/evento",(req,res)->{return "acceso /evento get";});
			get("/evento/:id",(req,res)->{return "acceso /evento get con id "+req.queryParams(":id");});
			put("/evento/:id", (req, res) -> {return "acceso /evento put con id "+req.queryParams(":id");});
			post("/evento",(req,res)->{return "acceso /evento post ";});
			delete("/evento/:id", (req, res) -> {return "acceso /evento delete";});
			
			
			//21.evidencias
			get("/evidencia",(req,res)->{return "acceso /evidencia get";});
			get("/evidencia/:id",(req,res)->{return "acceso /evidencia get con id "+req.queryParams(":id");});
			put("/evidencia/:id", (req, res) -> {return "acceso /evidencia put con id "+req.queryParams(":id");});
			post("/evidencia",(req,res)->{return "acceso /evidencia post ";});
			delete("/evidencia/:id", (req, res) -> {return "acceso /evidencia delete";});
			
			//22.capacitaciones 
			get("/capacitacion",(req,res)->{return "acceso /capacitacion get";});
			get("/capacitacion/:id",(req,res)->{return "acceso /capacitacion get con id "+req.queryParams(":id");});
			put("/capacitacion/:id", (req, res) -> {return "acceso /capacitacion put con id "+req.queryParams(":id");});
			post("/capacitacion",(req,res)->{return "acceso /capacitacion post ";});
			delete("/capacitacion/:id", (req, res) -> {return "acceso /capacitacion delete";});
			
			//23.Actividades grupo
			get("/actividadGrupo",(req,res)->{return "acceso /actividadGrupo get";});
			get("/actividadGrupo/:id",(req,res)->{return "acceso /actividadGrupo get con id "+req.queryParams(":id");});
			put("/actividadGrupo/:id", (req, res) -> {return "acceso /actividadGrupo put con id "+req.queryParams(":id");});
			post("/actividadGrupo",(req,res)->{return "acceso /actividadGrupo post ";});
			delete("/actividadGrupo/:id", (req, res) -> {return "acceso /actividadGrupo delete";});
			
			//24.actividad semillero
			get("/actividadSemillero",(req,res)->{return "acceso /actividadSemillero get";});
			get("/actividadSemillero/:id",(req,res)->{return "acceso /actividadSemillero get con id "+req.queryParams(":id");});
			put("/actividadSemillero/:id", (req, res) -> {return "acceso /actividadSemillero put con id "+req.queryParams(":id");});
			post("/actividadSemillero",(req,res)->{return "acceso /actividadSemillero post ";});
			delete("/actividadSemillero/:id", (req, res) -> {return "acceso /actividadSemillero delete";});
			
			
			//25.Productidad por grupo
			get("/productividadGrupo",(req,res)->{return "acceso /productividadGrupo get";});
			
			//26.Productividad por semillero
			get("/productividadSemillero",(req,res)->{return "acceso /productividadSemillero get";});
			
            //27.Productividad por investigador
			get("/productividadInvestigador",(req,res)->{return "acceso /productividadInvestigador get";});
			
	
			//28.Productivad por programa
			get("/productividadPrograma",(req,res)->{return "acceso /prductividadPrograma get";});
			
			
			//29.Productividad por departamento
			get("/productividadDepartamento",(req,res)->{return "acceso /prductividadDepartamento get";});
			
			
			//30. Objetivo
			post("/objetivoEspecifico",(req,res)->{return this.objetivoController.createObjetivoEspecifico(req, res);}, new JsonTransformer());
			
			//Lineas de Investigacion
			get("/lineas_investigacion",(req,res)->{return lineaInvestigacionController.getLineasInvestigacion(req, res);}, new JsonTransformer());
			get("/lineas_investigacion/:id",(req,res)->{return lineaInvestigacionController.getLineaInvestigacion(req, res);}, new JsonTransformer());
			get("/lineas_grupo/:id",(req,res)->{return lineaInvestigacionController.getLineasGrupo(req, res);}, new JsonTransformer());
			get("/lineas_semillero/:id", (req,res)->{return lineaInvestigacionController.getLineasSemillero(req, res);}, new JsonTransformer());
			post("/lineas_investigacion",(req,res)->{return lineaInvestigacionController.createLinea(req, res);}, new JsonTransformer());
			post("/lineas_grupo",(req,res)->{return lineaInvestigacionController.asociarLineaGrupo(req, res);}, new JsonTransformer());
			post("/lineas_semillero",(req,res)->{return lineaInvestigacionController.asociarLineaSemillero(req, res);}, new JsonTransformer());
			
			//lo de aca son ejemplos no mas!
			
			/**
			
			post("/facultad", (req, res) -> {

				String name = req.queryParams("nombre");
				String body = req.body();

				Facultad facultad = new Gson().fromJson(body, Facultad.class);

				if (facultad != null) {
					name = facultad.getNombre();

				}

				return facultadService.createFacultad(name);

			}, new JsonTransformer());
			
		  
			**/
			
			// ruta para el logeo
			post("/login", (req, res) -> {

				String nombre = req.queryParams("nombre");
				String clave = req.queryParams("password");

				// consultas de la base de datos.....

				if (!nombre.equals("sergio")) {

					JsonObject object = new JsonObject();
					object.addProperty("message", "Usuario no encontrado");
					object.addProperty("error", "Usuario no registrado");
					res.status(401);
					return object.toString();

				}

				if (!clave.equals("123456")) {
					JsonObject object = new JsonObject();
					object.addProperty("message", "Usuario no encontrado");
					object.addProperty("error", "Usuario no registrado");
					res.status(401);
					return object.toString();
				}

				String jwt = "";
				long tiempo=System.currentTimeMillis();

				try {
					Algorithm algorithm = Algorithm.HMAC256("miClave");
					jwt = JWT.create()
							.withIssuer("auth0")
							.withExpiresAt(new Date(tiempo+900000))
							.sign(algorithm);
				} catch (JWTCreationException exception) {
					// Invalid Signing configuration / Couldn't convert Claims.
				}

				JsonObject object = new JsonObject();
				object.addProperty("message", "inicio de session exitoso");
				object.addProperty("nombre", nombre);
				object.addProperty("token", jwt);
				String json = object.toString();

				res.status(200);
				return json;

			}

			);

			// Método para tratar los gets de /users
			get("/users", (request, response) -> {

				response.type("application/json");
				return userService.getAllUsers();
			}, new JsonTransformer());

			// Método para tratar los posts de /users (Creación de usuarios)
			post("/users", (req, res) -> {

				String token = req.queryParams("token");

				try {
					this.aut.validarToken(token);
				} catch (JWTVerificationException exception) {
					
					JsonObject object = new JsonObject();
					object.addProperty("message", "no tienes suficientes permisos");
					object.addProperty("error", "no te has logeado");
					res.status(401);
					return object.toString();
				}

				// Se cargan los parámetros de la query (URL)
				String name = req.queryParams("name");
				String email = req.queryParams("email");
				String body = req.body();

				// Convertimos de JSON a objeto de la clase User
				User user = new Gson().fromJson(body, User.class);

				if (user != null) {
					System.out.println("---- Usuario cargado correctamente.");
					name = user.getName();
					email = user.getEmail();
				}

				System.out.println("---- Datos del usuario.");
				System.out.println("---- name: " + name);
				System.out.println("---- email: " + email);

				return new Gson().toJson(userService.createUser(name, email));
			});

			// Método para tratar los put de /users/:idUser (Modificación de datos de
			// usuarios)
			put("/users/:idUser", (req, res) -> {
				res.type("application/json");
				String idUser = req.params(":idUser");

				// Se cargan los parámetros de la query (URL)
				String name = req.queryParams("name");
				String email = req.queryParams("email");

				User user = userService.getUser(idUser);
				if (user != null) {
					return userService.updateUser(idUser, name, email);
				}

				res.status(400);
				return "No user with id '" + idUser + "' found";
			}, new JsonTransformer());

			// Método para tratar los delete de /users/:idUser (Eliminar usuario)
			delete("/users/:idUser", (req, res) -> {
				res.type("application/json");
				String idUser = req.params(":idUser");

				User user = userService.getUser(idUser);
				if (user != null) {
					userService.deleteUser(idUser);
					res.status(200);
					return "User with id '" + idUser + "' deleted";
				}

				res.status(400);
				return "No user with id '" + idUser + "' found";
			}, new JsonTransformer());

		

		});

	}

}
