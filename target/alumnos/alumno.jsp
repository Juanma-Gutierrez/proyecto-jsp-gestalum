<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@page
import="alumnos.*" %> <%@page import="grupos.*" %> <%@page import="connection.*"
%> <%@page import="aplicacion.*" %>

<!DOCTYPE html>
<html>
<!-- CSS Boostrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />

<!-- JS Bootstrap (Opcional) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
</script>
<script src="https://kit.fontawesome.com/ad22caf6eb.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="assets/css/style.css" />

<body>
	<!-- TODO: hacer request para que capture el id del alumno -->
	<!-- TODO: llamar a la función que muestre la ficha completa del alumno -->
	<%
	Navbar navbar = new Navbar("alumnos"); out.print(navbar);
  String op = request.getParameter("op");
  int id;
  String nombre;
  String apellidos;
  int grupoId;
  Alumno alumno;
  Aplicacion app = new Aplicacion();
  switch (op){
    case "consultarAlumno":
		id = Integer.parseInt(request.getParameter("id"));
		out.print(app.muestraUnAlumno(id, "consultar"));
		break;
	case "crearAlumno":
		out.print(app.muestraUnAlumno(0, "crear"));
		break;
	case "creadoAlumno":
		nombre = request.getParameter("nombre");
		apellidos = request.getParameter("apellidos");
		grupoId = Integer.parseInt(request.getParameter("grupoId"));
		alumno = new Alumno(nombre, apellidos, grupoId);
		out.print(app.muestraUnAlumno(app.crearAlumno(alumno), "creado"));
		break;
	case "eliminarAlumno":
		id = Integer.parseInt(request.getParameter("id"));
		out.print(app.eliminarAlumno(id));
		out.print(app.muestraTodosLosAlumnos("apellidos"));
		break;
	case "modificarAlumno":
		id = Integer.parseInt(request.getParameter("id"));
		nombre = request.getParameter("nombre");
		apellidos = request.getParameter("apellidos");
		grupoId = Integer.parseInt(request.getParameter("grupoId"));
		alumno = new Alumno(nombre, apellidos, grupoId);
		out.print(app.muestraUnAlumno(app.modificarAlumno(alumno), "creado"));
		break;
  }
	Footer footer = new Footer(); out.print(footer);
	%>
</body>

</html>