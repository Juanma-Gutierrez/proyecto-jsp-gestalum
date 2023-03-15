package grupos;

public class Grupo {
	private int id;
	private String nombre;
	private String curso;
	private int anyo;

	public Grupo(int id, String nombre, String curso, int anyo) {
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
		this.anyo = anyo;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCurso() {
		return curso;
	}

	public int getAnyo() {
		return anyo;
	}

	@Override
	public String toString() {
		return String.format("ID: %d, Nombre: %s, Curso: %s, Año: %d", this.id, this.nombre, this.curso, this.anyo);
	}
}
