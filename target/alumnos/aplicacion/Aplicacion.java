package aplicacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import connection.ConnectionPool;
import alumnos.*;
import grupos.*;

public class Aplicacion {
	// Configuración de la conexión a la base de datos
	private static String url = "jdbc:mysql://127.0.0.1:3306/alumnos";
	private static String usuario = "alumnosjmgm";
	private static String clave = "jmgmalumnos";
	public static ConnectionPool pool = new ConnectionPool(url, usuario, clave);
	public static Scanner sc = new Scanner(System.in);
	private AlumnosService alumnoService;
	private GruposService grupoService;

	// Declaración de variables
	Connection conexion = null;
	Statement sentencia = null;
	ResultSet resultado = null;

	public Aplicacion() throws SQLException {
	}

	public void iniciaAplicacion() throws SQLException {
		int opcion;
		do {
			muestraMenuPrincipal();
			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
				case 1:
					opcionAlumnos();
					break;
				case 2:
					opcionGrupos();
					break;
			}
		} while (opcion != 9);
		sc.close();
	}

	public void opcionAlumnos() throws SQLException {
		int opcion;
		do {
			muestraMenuAlumnos();
			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
				case 1:
					crearAlumno();
					break;
				case 2:
					consultarAlumno();
					break;
				case 3:
					modificarAlumno();
					break;
				case 4:
					borrarAlumno();
					break;
			}
		} while (opcion != 9);
	}

	public void opcionGrupos() throws SQLException {
		int opcion;
		do {
			muestraMenuGrupos();
			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
				case 1:
					crearGrupo();
					break;
				case 2:
					consultarGrupo();
					break;
				case 3:
					modificarGrupo();
					break;
				case 4:
					borrarGrupo();
					break;
			}
		} while (opcion != 9);
	}

	// ***************** ALUMNOS ********************************************
	public Alumno crearAlumno() throws SQLException {
		System.out.print("Introduce el nombre del alumno: ");
		String nombre = sc.nextLine();
		System.out.print("Introduce los apellidos del alumno: ");
		String apellidos = sc.nextLine();
		alumnosAbreConexion();
		long id = this.alumnoService.create(nombre, apellidos);
		Alumno alumno = this.alumnoService.requestById((int) id);
		cierraConexion();
		System.out.println("Nuevo alumno creado: " + alumno);
		pause();
		return alumno;
	}

	public void consultarAlumno() throws SQLException {
		int opcion;
		do {
			muestraMenuConsultaAlumnos();
			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
				case 1:
					consultarUnAlumno();
					break;
				case 2:
					consultarTodosAlumnos();
					break;
			}
		} while (opcion != 9);
	}

	public int consultarUnAlumno() throws SQLException {
		System.out.println("Introduce el id del alumno:");
		int id = Integer.parseInt(sc.nextLine());
		alumnosAbreConexion();
		Alumno alumno = this.alumnoService.requestById(id);
		cierraConexion();
		if (alumno != null)
			System.out.println(alumno);
		else
			System.out.println("Alumno no encontrado");
		pause();
		return id;
	}

	public ArrayList<Alumno> consultarTodosAlumnos() throws SQLException {
		alumnosAbreConexion();
		ArrayList<Alumno> alumnos = this.alumnoService.requestAll();
		for (Alumno alumno : alumnos) {
			System.out.println(alumno);
		}
		cierraConexion();
		pause();
		return alumnos;
	}

	public Alumno modificarAlumno() throws SQLException {
		System.out.print("Introduce el id del alumno: ");
		int _id = Integer.parseInt(sc.nextLine());
		System.out.print("Introduce el nombre del alumno: ");
		String nombre = sc.nextLine();
		System.out.print("Introduce los apellidos del alumno: ");
		String apellidos = sc.nextLine();
		alumnosAbreConexion();
		this.alumnoService.update(_id, nombre, apellidos);
		Alumno alumno = this.alumnoService.requestById((int) _id);
		cierraConexion();
		System.out.println("Alumno modificado: " + alumno);
		pause();
		return alumno;
	}

	public int borrarAlumno() throws SQLException {
		System.out.println("Introduce el id del alumno:");
		long id = Integer.parseInt(sc.nextLine());
		alumnosAbreConexion();
		Boolean borrado = this.alumnoService.delete(id);
		cierraConexion();
		if (borrado == true)
			System.out.println("Alumno eliminado");
		else
			System.out.println("Alumno no encontrado");
		pause();
		return (int) id;
	}

	// ***************** GRUPOS ********************************************
	public Grupo crearGrupo() throws SQLException {
		System.out.print("Introduce el nombre del grupo: ");
		String nombre = sc.nextLine();
		System.out.print("Introduce el curso: ");
		String curso = sc.nextLine();
		System.out.print("Introduce el año: ");
		int anyo = Integer.parseInt(sc.nextLine());
		gruposAbreConexion();
		long id = this.grupoService.create(nombre, curso, anyo);
		Grupo grupo = this.grupoService.requestById((int) id);
		cierraConexion();
		System.out.println("Nuevo grupo creado: " + grupo);
		pause();
		return grupo;
	}

	public void consultarGrupo() throws SQLException {
		int opcion;
		do {
			muestraMenuConsultaGrupos();
			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
				case 1:
					consultarUnGrupo();
					break;
				case 2:
					consultarTodosGrupos();
					break;
			}
		} while (opcion != 9);
	}

	public ArrayList<Grupo> consultarTodosGrupos() throws SQLException {
		gruposAbreConexion();
		ArrayList<Grupo> grupos = this.grupoService.requestAll();
		for (Grupo grupo : grupos) {
			System.out.println(grupo);
		}
		cierraConexion();
		pause();
		return grupos;
	}

	public int consultarUnGrupo() throws SQLException {
		System.out.println("Introduce el id del grupo:");
		int id = Integer.parseInt(sc.nextLine());
		gruposAbreConexion();
		ArrayList<Alumno> alumnosEnGrupo = this.grupoService.requestAlumnosById(id);
		Grupo grupo = this.grupoService.requestById(id);
		cierraConexion();
		if (grupo != null) {
			System.out.printf("Alumnos en el grupo %s %s\n", grupo.getCurso(), grupo.getNombre());
			System.out.println("=================================");
			for (Alumno alumno : alumnosEnGrupo) {
				System.out.println(alumno);
			}
		} else
			System.out.println("Grupo no encontrado");
		pause();
		return id;
	}

	public Grupo modificarGrupo() throws SQLException {
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
		pause();
		return grupo;
	}

	public int borrarGrupo() throws SQLException {
		System.out.println("Introduce el id del grupo:");
		long id = Integer.parseInt(sc.nextLine());
		gruposAbreConexion();
		Boolean borrado = this.grupoService.delete(id);
		cierraConexion();
		if (borrado == true)
			System.out.println("Grupo eliminado");
		else
			System.out.println("Grupo no encontrado");
		pause();
		return (int) id;
	}

	// ***************** CONEXION ********************************************
	public ConnectionPool alumnosAbreConexion() {
		try {
			// Conexión a la base de datos
			this.alumnoService = new AlumnosService(pool.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pool;
	}

	public ConnectionPool gruposAbreConexion() {
		try {
			// Conexión a la base de datos
			this.grupoService = new GruposService(pool.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pool;
	}

	public void cierraConexion() throws SQLException {
		pool.closeAll();
	}

	// ****************** MENUS *******************************************
	public void muestraMenuPrincipal() {
		borraPantalla();
		System.out.println("*******************************");
		System.out.println("* M E N Ú   P R I N C I P A L *");
		System.out.println("*******************************");
		System.out.println("* 1. Gestión de alumnos       *");
		System.out.println("* 2. Gestión de grupos        *");
		System.out.println("* 9. Salir                    *");
		System.out.println("*******************************");
	}

	public void muestraMenuAlumnos() {
		borraPantalla();
		System.out.println("*******************************");
		System.out.println("*   M E N Ú   A L U M N O S   *");
		System.out.println("*******************************");
		System.out.println("* 1. Crear alumno             *");
		System.out.println("* 2. Consultar alumno         *");
		System.out.println("* 3. Modificar alumno         *");
		System.out.println("* 4. Borrar alumno            *");
		System.out.println("* 9. Salir                    *");
		System.out.println("*******************************");
	}

	public void muestraMenuConsultaAlumnos() {
		borraPantalla();
		System.out.println("*******************************");
		System.out.println("*   M E N Ú   A L U M N O S   *");
		System.out.println("*******************************");
		System.out.println("* 1. Consultar un alumno      *");
		System.out.println("* 2. Mostrar todos alumnos    *");
		System.out.println("* 9. Salir                    *");
		System.out.println("*******************************");
	}

	public void muestraMenuGrupos() {
		borraPantalla();
		System.out.println("*******************************");
		System.out.println("*    M E N Ú   G R U P O S    *");
		System.out.println("*******************************");
		System.out.println("* 1. Crear grupo              *");
		System.out.println("* 2. Consultar grupo          *");
		System.out.println("* 3. Modificar grupo          *");
		System.out.println("* 4. Borrar grupo             *");
		System.out.println("* 9. Salir                    *");
		System.out.println("*******************************");
	}

	public void muestraMenuConsultaGrupos() {
		borraPantalla();
		System.out.println("*******************************");
		System.out.println("*    M E N Ú   G R U P O S    *");
		System.out.println("*******************************");
		System.out.println("* 1. Consultar un grupo       *");
		System.out.println("* 2. Mostrar todos los grupos *");
		System.out.println("* 9. Salir                    *");
		System.out.println("*******************************");
	}

	public void borraPantalla() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void pause() {
		String y = "\033[0;33m";
		String r = "\033[0m";
		System.out.println(y + "\nPulse una tecla para continuar" + r);
		sc.nextLine();
	}
}