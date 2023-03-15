package grupos;

import java.time.LocalDate;

public class Grupo {
	private int id;
	private String nombre;
	private String curso;
	private int anyo;

	public Grupo() {
		this(0, "", "", LocalDate.now().getYear());
	}

	public Grupo(int id, String nombre, String curso, int anyo) {
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
		this.anyo = anyo;
	}

	public Grupo(String nombre, String curso, int anyo) {
		this(0, nombre, curso, anyo);
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
		String res = "";
		res += "<div class='container'>";
		res += "<div class='row border border-light rounded text-dark align-items-center fila'>";
		res += "<div class='col-1 text-center'>" + this.id + "</div>";
		res += "<div class='col-1 text-center'> " + this.curso + "</div>";
		res += "<div class='col-7'>" + this.nombre + "</div>";
		res += "<div class='col-1 text-center'>" + this.anyo + "</div>";
		res += "<div class='col-2 text-center'>";
		res += "<a href='grupo.jsp?op=consultarGrupo&id=" + this.getId()
				+ "' class='btn btn-info btn-sm m-1'><i class='fa fa-id-card' aria-hidden='true'></i> Ver</a>";
		res += "</div></div></div>";
		return res;
	}
}
