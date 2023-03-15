package grupos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import alumnos.Alumno;

public class GruposService {
	Connection conn;

	public GruposService(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<Grupo> requestAll(String orden) throws SQLException {
		ArrayList<Grupo> result = new ArrayList<Grupo>();
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = "SELECT id, nombre, curso, anyo FROM grupos g ORDER BY " + orden;
		// Ejecución de la consulta
		ResultSet querySet = statement.executeQuery(sql);
		// Recorrido del resultado de la consulta
		while (querySet.next()) {
			int id = querySet.getInt("id");
			String nombre = querySet.getString("nombre");
			String curso = querySet.getString("curso");
			int anyo = querySet.getInt("anyo");
			result.add(new Grupo(id, nombre, curso, anyo));
		}
		statement.close();
		return result;
	}

	public ArrayList<Alumno> requestAlumnosById(int id) throws SQLException {
		ArrayList<Alumno> result = new ArrayList<Alumno>();
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = String.format(
				"SELECT a.apellidos, a.nombre, a.id, g.nombre FROM alumnos a JOIN grupos g on g.id = a.id_grupos WHERE g.id = %d",
				id);
		// Ejecución de la consulta
		ResultSet querySet = statement.executeQuery(sql);
		// Recorrido del resultado de la consulta
		while (querySet.next()) {
			int _id = querySet.getInt("id");
			String nombre = querySet.getString("nombre");
			String apellidos = querySet.getString("apellidos");
			result.add(new Alumno(_id, nombre, apellidos));
		}
		statement.close();
		return result;
	}

	public Grupo requestById(int id) throws SQLException {
		Grupo result = null;
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = String.format("SELECT id, nombre, curso, anyo FROM grupos WHERE id=%d", id);
		// Ejecución de la consulta
		ResultSet querySet = statement.executeQuery(sql);
		// Recorrido del resultado de la consulta
		if (querySet.next()) {
			String nombre = querySet.getString("nombre");
			String curso = querySet.getString("curso");
			int anyo = querySet.getInt("anyo");
			result = new Grupo(id, nombre, curso, anyo);
		}
		statement.close();
		return result;
	}

	public long create(String nombre, String curso, int anyo) throws SQLException {
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = String.format("INSERT INTO grupos (nombre, curso, anyo) VALUES ('%s', '%s', '%d')",
				nombre, curso, anyo);
		// Ejecución de la consulta
		int affectedRows = statement.executeUpdate(sql,
				Statement.RETURN_GENERATED_KEYS);
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

	public int update(int id, String nombre, String curso, int anyo) throws SQLException {
		Statement statement = null;
		statement = this.conn.createStatement();
		String sql = String.format("UPDATE grupos SET nombre = '%s', curso = '%s', anyo = '%d' WHERE id=%d",
				nombre, curso, anyo, id);
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
		String sql = String.format("DELETE FROM grupos WHERE id=%d", id);
		// Ejecución de la consulta
		int result = statement.executeUpdate(sql);
		statement.close();
		return result == 1;
	}

}
