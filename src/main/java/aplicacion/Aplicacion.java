package aplicacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import alumnos.*;
import grupos.*;

import connection.ConnectionPool;

public class Aplicacion {
	// Configuración de la conexión a la base de datos
	private static String url = "jdbc:mysql://127.0.0.1:3306/alumnos";
	private static String usuario = "root";
	private static String clave = "";
	public static ConnectionPool pool = new ConnectionPool(url, usuario, clave);
	public static Scanner sc = new Scanner(System.in);
	private AlumnosService alumnoService;
	private GruposService grupoService;
	public final String centrado = "d-flex justify-content-center";
	public String orden = "apellidos";

	// Declaración de variables
	Connection conexion = null;
	Statement sentencia = null;
	ResultSet resultado = null;

	public Aplicacion() throws SQLException {
	}

	// ***************** ALUMNOS ********************************************
	public int crearAlumno(Alumno alumnoNuevo) throws SQLException, ClassNotFoundException {
		alumnosAbreConexion();
		long id = this.alumnoService.create(alumnoNuevo.getNombre(), alumnoNuevo.getApellidos(),
				alumnoNuevo.getGrupoId());
		Alumno alumno = this.alumnoService.requestById((int) id);
		cierraConexion();
		return (int) alumno.getId();
	}

	public Alumno consultarUnAlumno(int id) throws SQLException, ClassNotFoundException {
		alumnosAbreConexion();
		Alumno alumno = this.alumnoService.requestById(id);
		cierraConexion();
		return alumno;
	}

	public ArrayList<Alumno> consultarTodosAlumnos(String orden) throws SQLException, ClassNotFoundException {
		alumnosAbreConexion();
		ArrayList<Alumno> alumnos = this.alumnoService.requestAll(orden);
		cierraConexion();
		return alumnos;
	}

	public int modificarAlumno(Alumno alumno) throws SQLException, ClassNotFoundException {
		alumnosAbreConexion();
		this.alumnoService.update((int) alumno.getId(), alumno.getNombre(), alumno.getApellidos(), alumno.getGrupoId());
		cierraConexion();
		return (int) alumno.getId();
	}

	public String eliminarAlumno(int id) throws SQLException, ClassNotFoundException {
		alumnosAbreConexion();
		Boolean borrado = this.alumnoService.delete(id);
		cierraConexion();
		String res = "";
		if (borrado == true)
			res = alerta("Alumno eliminado correctamente", "success");
		else
			res = alerta("Alumno no encontrado", "danger");
		return res;
	}

	// ***************** GRUPOS ********************************************
	public int crearGrupo(Grupo grupoNuevo) throws SQLException, ClassNotFoundException {
		gruposAbreConexion();
		long id = this.grupoService.create(grupoNuevo.getNombre(), grupoNuevo.getCurso(), grupoNuevo.getAnyo());
		Grupo grupo = this.grupoService.requestById((int) id);
		cierraConexion();
		return (int) grupo.getId();
	}

	public ArrayList<Grupo> consultarTodosGrupos(String orden) throws SQLException, ClassNotFoundException {
		gruposAbreConexion();
		ArrayList<Grupo> grupos = this.grupoService.requestAll(orden);
		cierraConexion();
		return grupos;
	}

	public Grupo consultarUnGrupo(int id) throws SQLException, ClassNotFoundException {
		gruposAbreConexion();
		Grupo grupo = this.grupoService.requestById(id);
		cierraConexion();
		return grupo;
	}

	public Grupo modificarGrupo() throws SQLException, ClassNotFoundException {
		System.out.print("Introduce el id del grupo: ");
		int _id = Integer.parseInt(sc.nextLine());
		System.out.print("Introduce el nombre del grupo: ");
		String nombre = sc.nextLine();
		System.out.print("Introduce el curso: ");
		String curso = sc.nextLine();
		System.out.print("Introduce el año: ");
		int anyo = Integer.parseInt(sc.nextLine());
		gruposAbreConexion();
		this.grupoService.update(_id, nombre, curso, anyo);
		Grupo grupo = this.grupoService.requestById((int) _id);
		cierraConexion();
		System.out.println("Grupo modificado: " + grupo);
		return grupo;
	}

	public int borrarGrupo() throws SQLException, ClassNotFoundException {
		System.out.println("Introduce el id del grupo:");
		long id = Integer.parseInt(sc.nextLine());
		gruposAbreConexion();
		Boolean borrado = this.grupoService.delete(id);
		cierraConexion();
		if (borrado == true)
			System.out.println("Grupo eliminado");
		else
			System.out.println("Grupo no encontrado");
		return (int) id;
	}

	// ***************** CONEXION ********************************************
	public ConnectionPool alumnosAbreConexion() throws ClassNotFoundException {
		try {
			// Conexión a la base de datos
			this.alumnoService = new AlumnosService(pool.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pool;
	}

	public ConnectionPool gruposAbreConexion() throws ClassNotFoundException, SQLException {
		try {
			// Conexión a la base de datos
			this.grupoService = new GruposService(pool.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
			pool.closeAll();
		}
		return pool;
	}

	public void cierraConexion() throws SQLException {
		try {
			pool.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ****************** MENUS *******************************************
	public String inicioCabeceraFilas() {
		String res = "";
		res += "<div class='container alert alert-secondary mt-2 mb-0 font-weight-bold p-1'>";
		res += "<div class='row'>";
		return res;
	}

	public String cierreCabeceraFilas() {
		return "</div></div>";
	}

	public String muestraTodosLosAlumnos(String orden) throws ClassNotFoundException, SQLException {
		// System.out.println("muestraTodosLosAlumnos");
		String res = "";
		res += inicioCabeceraFilas();
		res += botonCabeceraAlumnos(1, "text-center", "a.id", "ID");
		res += botonCabeceraAlumnos(2, "", "a.nombre", "Nombre");
		res += botonCabeceraAlumnos(2, "", "a.apellidos", "Apellidos");
		res += botonCabeceraAlumnos(1, "text-center", "g.curso", "Curso");
		res += botonCabeceraAlumnos(4, "", "g.nombre", "Nombre del grupo");
		res += "<div class='col-2 text-center'>";
		res += "  <form action='alumno.jsp' method='GET'>";
		res += "    <input type='hidden' name='op' value='crearAlumno'>";
		res += "    <button class='btn btn-info' type='submit'>Nuevo alumno</button>";
		res += "  </form>";
		res += "</div>";
		res += cierreCabeceraFilas();
		ArrayList<Alumno> alumnos = consultarTodosAlumnos(orden);
		res += "<div class='container mb-5 p-0'>";
		for (Alumno alumno : alumnos) {
			res += alumno;
		}
		res += "</div>";
		return res;
	}

	public String botonCabecera(int col, String alineacion, String link, String orden, String textoBoton) {
		String res = "";
		res += "<div class='col-" + col + " " + alineacion + "'>";
		res += "<a href='" + link + ".jsp?orden=" + orden + "'>";
		res += "<button class='btn btn-link'>";
		res += textoBoton;
		res += "</button></a></div>";
		return res;
	}

	public String botonCabeceraAlumnos(int col, String alineacion, String orden, String textoBoton) {
		return (botonCabecera(col, alineacion, "alumnos", orden, textoBoton));
	}

	public String botonCabeceraGrupos(int col, String alineacion, String orden, String textoBoton) {
		return (botonCabecera(col, alineacion, "grupos", orden, textoBoton));
	}

	public String alerta(String mensaje, String color) {
		String res = "";
		res += "<div class='container p-0 mt-3 mb-3'>";
		res += "<div class='alert alert-" + color;
		res += " text-center' role='alert'>" + mensaje + "</div>";
		res += "</div>";
		return res;
	}

	public String muestraUnAlumno(int id, String modo) throws ClassNotFoundException, SQLException {
		System.out.println("Muestra un alumno");
		Alumno alumno = new Alumno();
		if (id != 0) {
			alumno = consultarUnAlumno(id);
		}
		String mensaje = "";
		switch (modo) {
			case "consultar":
				mensaje = alerta("Consulta de alumno", "primary");
				break;
			case "crear":
				mensaje = alerta("Alta de nuevo alumno", "warning");
				break;
			case "creado":
				mensaje = alerta("Nuevo alumno creado correctamente", "success");
				break;
			case "modificado":
				mensaje = alerta("Datos del alumno modificados correctamente", "success");
				break;
		}
		ArrayList<Grupo> grupos = consultarTodosGrupos("nombre");
		String res = "";
		res += "<div class='container px-5 w-50 my-5'>";
		res += "  <form action='alumno.jsp' method='GET'>";
		res += mensaje;
		res += "    <div class='form-floating mb-3'>";
		res += "      <input class='form-control' name='nombre' type='text' placeholder='Nombre' required value='"
				+ alumno.getNombre() + "'/>";
		res += "    </div>";
		res += "    <div class='form-floating mb-3'>";
		res += "      <input class='form-control' name='apellidos' type='text' placeholder='Apellidos' required value='"
				+ alumno.getApellidos() + "'>";
		res += "    </div>";
		res += "    <div class='form-floating mb-3'>";
		res += "      <select class='form-control' name='grupoId' aria-label='Nombre de la clase' required>";
		res += "        <option value=''>Seleccione una clase</option>";
		for (Grupo grupo : grupos) {
			String mode = grupo.getId() == alumno.getGrupoId() ? " selected " : "";
			res += "        <option value='" + grupo.getId() + "' " + mode + ">" + grupo.getCurso() + " "
					+ grupo.getNombre() + "</option>";
		}
		res += "      </select>";
		res += "    </div>";
		res += "    <div class='container px-5'>";
		res += "      <div class='row'>";
		res += "        <div class='col-2'>";
		res += "        </div>";
		res += "        <div class='col-8 row justify-content-center'>";
		// Botonera consulta
		// Botonera alta de alumno
		if (id == 0)
			res += creaBotonAlumno("creadoAlumno", "Dar de alta", alumno, "info", "crear");
		else
			res += creaBotonAlumno("modificarAlumno", "Actualizar", alumno, "info", "actualizar"); // actualizar
		if (id != 0) {
			res += creaBotonAlumno("eliminarAlumno", "Eliminar", alumno, "danger", "eliminar");
		}
		res += "        </div>";
		res += "        <div class='col-2 text-right'>";
		res += "          <a href='alumnos.jsp?orden=apellidos'>";
		res += "            <button class='btn btn-info text-right' type='button'>Cerrar</button>";
		res += "          </a>";
		res += "        </div>";
		res += "      </div>";
		res += "    </div>";
		res += "  </form>";
		res += "</div>";
		return res;
	}

	public String creaBotonAlumno(String operacion, String textoBoton, Alumno alumno, String color, String modo) {
		String res = "";
		res += "<form action='alumno.jsp?' method='GET'>";
		res += "<input type='hidden' name='op' value='" + operacion + "'>";
		res += "<input type='hidden' name='id' value='" + alumno.getId() + "'>";
		if (!modo.equals("actualizar")) {
			res += "<input type='hidden' name='nombre' value='" + alumno.getNombre() + "'>";
			res += "<input type='hidden' name='apellidos' value='" + alumno.getApellidos() + "'>";
			res += "<input type='hidden' name='grupoId' value='" + alumno.getGrupoId() + "'>";
		}
		res += "<button class='btn btn-" + color + " mx-1' type='submit'>" + textoBoton + "</button>";
		res += "</form>";
		return res;
	}

	public String creaBotonGrupo(String operacion, String textoBoton, Grupo grupo, String color, String modo) {
		String res = "";
		res += "<form action='grupo.jsp?' method='GET'>";
		res += "<input type='hidden' name='op' value='" + operacion + "'>";
		res += "<input type='hidden' name='id' value='" + grupo.getId() + "'>";
		if (!modo.equals("actualizar")) {
			res += "<input type='hidden' name='nombre' value='" + grupo.getNombre() + "'>";
			res += "<input type='hidden' name='curso' value='" + grupo.getCurso() + "'>";
			res += "<input type='hidden' name='anyo' value='" + grupo.getAnyo() + "'>";
		}
		res += "<button class='btn btn-" + color + " mx-1' type='submit'>" + textoBoton + "</button>";
		res += "</form>";
		return res;
	}

	public String muestraTodosLosGrupos(String orden) throws ClassNotFoundException, SQLException {
		String res = "";
		res += inicioCabeceraFilas();
		res += botonCabeceraGrupos(1, "text-center", "g.id", "ID");
		res += botonCabeceraGrupos(1, "text-center", "g.curso", "Curso");
		res += botonCabeceraGrupos(7, "", "g.nombre", "Nombre del grupo");
		res += botonCabeceraGrupos(1, "", "g.anyo", "Año");
		res += "<div class='col-2 text-center'>";
		res += "  <form action='grupo.jsp' method='GET'>";
		res += "    <input type='hidden' name='op' value='crearGrupo'>";
		res += "    <button class='btn btn-info' type='submit'>Nuevo grupo</button>";
		res += "  </form>";
		res += "</div>";
		res += cierreCabeceraFilas();

		ArrayList<Grupo> grupos = consultarTodosGrupos(orden);
		for (Grupo grupo : grupos) {
			res += grupo;
		}
		return res;
	}

	public String muestraUnGrupo(int id, String modo) throws ClassNotFoundException, SQLException {
		Grupo grupo = new Grupo();
		String mensaje = "";
		switch (modo) {
			case "consultar":
				mensaje = alerta("Consulta de grupo", "primary");
				break;
			case "crear":
				mensaje = alerta("Alta de nuevo grupo", "warning");
				break;
			case "creado":
				mensaje = alerta("Nuevo grupo creado correctamente", "success");
				break;
			case "modificado":
				mensaje = alerta("Datos del grupo modificados correctamente", "success");
				break;
		}
		if (id != 0) {
			grupo = consultarUnGrupo(id);
		}
		ArrayList<Alumno> alumnos = consultarTodosAlumnos("apellidos");
		String res = "";
		res += "<div class='container px-5 mb-5'>";
		res += mensaje;
		res += "  <form action='grupo.jsp' method='GET'>";
		res += "    <div class='row'>";
		res += "      <div class='col-8 form-floating mb-3'>";
		res += "        <input class='form-control'type='text' name='nombre' placeholder='Nombre' required value='"
				+ grupo.getNombre() + "'/>";
		res += "      </div>";
		res += "      <div class='col-2 form-floating mb-3'>";
		res += "        <input class='form-control text-center' name ='curso' type='text' placeholder='Curso' required value='"
				+ grupo.getCurso() + "'>";
		res += "      </div>";
		res += "      <div class='col-2 form-floating mb-3'>";
		res += "        <input class='form-control text-center' name='anyo' type='text' placeholder='Año' required value='"
				+ grupo.getAnyo() + "'>";
		res += "      </div>";
		res += "    </div>";
		res += "    <div class='row'>";
		res += "      <div class='col-2'>";
		res += "      </div>";
		res += "      <div class='row col-8 justify-content-center'>";
		// Botonera
		// Botonera alta de grupo
		if (id == 0)
			res += creaBotonGrupo("creadoGrupo", "Dar de alta", grupo, "info", "crear");
		else
			res += creaBotonGrupo("modificarGrupo", "Actualizar", grupo, "info", "actualizar"); // actualizar
		if (id != 0) {
			res += creaBotonGrupo("eliminarGrupo", "Eliminar", grupo, "danger", "eliminar");
		}
		res += "      </div>";
		res += "      <div class='col-2 text-right'>";
		res += "        <a href='grupos.jsp?orden=nombre'>";
		res += "          <button class='btn btn-info text-right'type='button'>Cerrar</button>";
		res += "        </a>";
		res += "      </div>";
		res += "    </div>";
		res += "  </form>";
		res += "</div>";

		res += "<div class='container overflow-auto mb-5'>";
		for (Alumno alumno : alumnos) {
			if (alumno.getGrupoId() == id)
				res += alumno;
		}
		res += "</div>";
		return res;

	}
	/*
	 * public void muestraMenuAlumnos() {
	 * }
	 * 
	 * public void muestraMenuConsultaAlumnos() {
	 * }
	 * 
	 * public void muestraMenuGrupos() {
	 * }
	 * 
	 * public void muestraMenuConsultaGrupos() {
	 * }
	 */

}