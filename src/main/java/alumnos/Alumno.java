package alumnos;

public class Alumno {
	long id;
	String nombre;
	String apellidos;
	int grupoId;

	String nombreGrupo;
	String curso;

	public Alumno() {
		this(0, "", "", 0);
	}

	public Alumno(int id, String nombre, String apellidos) {
		this(id, nombre, apellidos, 0);
	}

	public Alumno(int id, String nombre, String apellidos, int grupoId) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.grupoId = grupoId;
	}

	public Alumno(String nombre, String apellidos, int grupoId) {
		this(0, nombre, apellidos, grupoId);
	}

	public Alumno(int id, String nombre, String apellidos, String nombreGrupo, String curso, int grupoId) {
		this(id, nombre, apellidos, grupoId);
		this.curso = curso;
		this.nombreGrupo = nombreGrupo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public int getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(int grupoId) {
		this.grupoId = grupoId;
	}

	public String imprimeCabeceraFilas() {
		String res = "";
		res += "<div class='container alert alert-secondary'>";
		res += "  <div class='row border rounded text-dark font-weight-bold align-items-center'>";
		res += "    <div class='col-1 text-center'>ID</div>";
		res += "    <div class='col-2'>Nombre</div>";
		res += "    <div class='col-2'>Apellidos</div>";
		res += "    <div class='col-1 text-center'>Curso</div>";
		res += "    <div class='col-4'>Grupo</div>";
		res += "    <div class='col-2 text-center'>";
		res += "    </div>";
		res += "  </div>";
		res += "</div>";
		return res;
	}

	@Override
	public String toString() {
		String res = "";
		res += "<div class='container'>";
		res += "  <div class='row border border-light rounded text-dark align-items-center fila'>";
		res += "    <div class='col-1 text-center'>" + this.id + "</div>";
		res += "    <div class='col-2'>" + this.nombre + "</div>";
		res += "    <div class='col-2'>" + this.apellidos + "</div>";
		res += "    <div class='col-1 text-center'>" + this.curso + "</div>";
		res += "    <div class='col-4'>" + this.nombreGrupo + "</div>";
		res += "    <div class='col-2 text-center'>";
		res += "      <form action='alumno.jsp' method='POST'>";
		res += "        <input type='hidden' name='op' value='consultarAlumno'>";
		res += "        <input type='hidden' name='id' value='" + this.getId() + "'>";
		res += "        <button class='btn btn-info btn-sm m-1' type='submit'>";
		res += "          <i class='fa fa-id-card' aria-hidden='true'></i> Ver";
		res += "        </button>";
		res += "      </form>";
		res += "    </div>";
		res += "  </div>";
		res += "</div>";
		return res;
	}

}
