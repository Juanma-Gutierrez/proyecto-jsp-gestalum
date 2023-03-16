package alumnos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumnosService {
	Connection conn;

	public AlumnosService(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<Alumno> requestAll(String order) throws SQLException {
		ArrayList<Alumno> result = new ArrayList<Alumno>();
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = "SELECT a.id, a.nombre, a.apellidos, IFNULL(g.curso,'-') AS 'g.curso', IFNULL(g.nombre, 'Sin asignar curso') AS 'g.nombre', g.id FROM alumnos a LEFT JOIN grupos g ON g.id = a.id_grupos ORDER BY "
				+ order + ";";
		// Ejecución de la consulta
		ResultSet querySet = statement.executeQuery(sql);
		// Recorrido del resultado de la consulta
		while (querySet.next()) {
			int id = querySet.getInt("a.id");
			String nombre = querySet.getString("a.nombre");
			String apellidos = querySet.getString("a.apellidos");
			String nombreGrupo = querySet.getString("g.nombre");
			String curso = querySet.getString("g.curso");
			int grupoId = querySet.getInt("g.id");
			result.add(new Alumno(id, nombre, apellidos, nombreGrupo, curso, grupoId));
		}
		statement.close();
		return result;
	}

	public ArrayList<Alumno> requestWithoutGroup(String order) throws SQLException {
		ArrayList<Alumno> result = new ArrayList<Alumno>();
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = "SELECT a.id, a.nombre, a.apellidos, IFNULL(g.curso,'-') AS 'g.curso', IFNULL(g.nombre, 'Sin asignar curso') AS 'g.nombre', g.id FROM alumnos a LEFT JOIN grupos g ON g.id = a.id_grupos  WHERE a.id_grupos IS NULL ORDER BY "
				+ order + ";";
		// Ejecución de la consulta
		ResultSet querySet = statement.executeQuery(sql);
		// Recorrido del resultado de la consulta
		while (querySet.next()) {
			int id = querySet.getInt("a.id");
			String nombre = querySet.getString("a.nombre");
			String apellidos = querySet.getString("a.apellidos");
			String nombreGrupo = querySet.getString("g.nombre");
			String curso = querySet.getString("g.curso");
			int grupoId = querySet.getInt("g.id");
			result.add(new Alumno(id, nombre, apellidos, nombreGrupo, curso, grupoId));
		}
		statement.close();
		return result;
	}

	public Alumno requestById(int id) throws SQLException {
		Alumno result = null;
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = String.format(
				"SELECT a.id, a.nombre, a.apellidos, IFNULL(g.nombre,'Sin grupo asignado') AS 'g.nombre', IFNULL(g.curso, '-') AS 'g.curso', g.id FROM alumnos a LEFT JOIN grupos g ON a.id_grupos = g.id WHERE a.id=%d",
				id);
		// Ejecución de la consulta
		ResultSet querySet = statement.executeQuery(sql);
		// Recorrido del resultado de la consulta
		if (querySet.next()) {
			String nombre = querySet.getString("a.nombre");
			String apellidos = querySet.getString("a.apellidos");
			String nombreGrupo = querySet.getString("g.nombre");
			String curso = querySet.getString("g.curso");
			int grupoId = querySet.getInt("g.id");
			result = new Alumno(id, nombre, apellidos, nombreGrupo, curso, grupoId);
		}
		statement.close();
		return result;
	}

	public long create(String nombre, String apellidos, int grupoId) throws SQLException {
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = String.format("INSERT INTO alumnos (nombre, apellidos, id_grupos) VALUES ('%s', '%s', '%d')",
				nombre, apellidos, grupoId);
		// Ejecución de la consulta
		int affectedRows = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}
		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				long id = generatedKeys.getLong(1);
				statement.close();
				return id;
			} else {
				statement.close();
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}
	}

	public int update(int id, String nombre, String apellidos, int grupoId) throws SQLException {
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = String.format("UPDATE alumnos SET nombre = '%s', apellidos = '%s', id_grupos = '%d' WHERE id=%d",
				nombre, apellidos, grupoId, id);
		// Ejecución de la consulta
		int affectedRows = statement.executeUpdate(sql);
		statement.close();
		if (affectedRows == 0)
			throw new SQLException("Creating user failed, no rows affected.");
		else
			return affectedRows;
	}

	public boolean delete(long id) throws SQLException {
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = String.format("DELETE FROM alumnos WHERE id=%d", id);
		// Ejecución de la consulta
		int result = statement.executeUpdate(sql);
		statement.close();
		return result == 1;
	}
}
