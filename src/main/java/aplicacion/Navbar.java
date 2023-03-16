package aplicacion;

public class Navbar {
	public String navbar;

	public Navbar(String opcion) {
		String activoAlumnos = "<li class='nav-item'>";
		String activoGrupos = "<li class='nav-item'>";
		String activaMatriculas = "<li class='nav-item'>";
		switch (opcion) {
			case "alumnos":
				activoAlumnos = "<li class='nav-item active'>";
				break;
			case "grupos":
				activoGrupos = "<li class='nav-item active'>";
				break;
			case "matriculas":
				activaMatriculas = "<li class='nav-item active'>";
				break;
		}
		String res = "";
		res += "<nav class='navbar navbar-expand-lg navbar-dark bg-primary sticky-top'>";
		res += "  <a class='navbar-brand'>";
		res += "    <a class='navbar-brand font-weight-bold' href='index.jsp'>";
		res += "      <img src='https://cdn-icons-png.flaticon.com/512/3665/3665939.png' class='d-inline-block mx-2' width='30' height='30' alt=''>Gestalum</a>";
		res += "        <button class='navbar-toggler' type='button' data-toggle='collapse'";
		res += "          data-target='#navbarSupportedContent' aria-controls='navbarSupportedContent'";
		res += "          aria-expanded='false' aria-label='Toggle navigation'>";
		res += "          <span class='navbar-toggler-icon'></span>";
		res += "        </button>";
		res += "      <div class='collapse navbar-collapse' id='navbarSupportedContent'>";
		res += "    <ul class='navbar-nav mr-auto'>";
		res += activoAlumnos;
		res += "        <a class='nav-link' href='alumnos.jsp?orden=apellidos'>Alumnos</a>";
		res += "      </li>";
		res += activoGrupos;
		res += "        <a class='nav-link' href='grupos.jsp?orden=g.nombre'>Grupos</a>";
		res += "      </li>";
		res += activaMatriculas;
		res += "        <a class='nav-link' href='matriculas.jsp?orden=a.apellidos'>Matrículas</a>";
		res += "      </li>";
		res += "    </ul>";
		res += "  </div>";
		res += "</nav>";
		this.navbar = res;
	}

	public String toString() {
		return this.navbar;
	}
}
