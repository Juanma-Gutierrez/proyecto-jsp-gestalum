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

	<body>
		<%
		Navbar navbar = new Navbar("grupos"); out.print(navbar);
		String op = request.getParameter("op");
		int id;
		String nombre;
		String curso;
		int anyo;
		Grupo grupo;
		Aplicacion app = new Aplicacion();
		switch (op){
			case "crearGrupo":
				out.print(app.muestraUnGrupo(0, "crear"));
				break;
			case "creadoGrupo":
				nombre = request.getParameter("nombre");
				curso = request.getParameter("curso");
				anyo = Integer.parseInt(request.getParameter("anyo"));
				grupo = new Grupo(nombre, curso, anyo);
				out.print(app.muestraUnGrupo(app.crearGrupo(grupo), "creado"));
				break;
			case "consultarGrupo":
				id = Integer.parseInt(request.getParameter("id"));
				out.print(app.muestraUnGrupo(id, "consultar"));
				break;
			case "eliminarGrupo":
				id = Integer.parseInt(request.getParameter("id"));
				out.print(app.eliminarGrupo(id));
				out.print(app.muestraTodosLosGrupos("nombre"));
				break;
			case "modificarGrupo":
				id = Integer.parseInt(request.getParameter("id"));
				nombre = request.getParameter("nombre");
				curso = request.getParameter("curso");
				anyo = Integer.parseInt(request.getParameter("anyo"));
				grupo = new Grupo(id, nombre, curso, anyo);
				out.print(app.muestraUnGrupo(app.modificarGrupo(grupo), "modificado"));
				break;
			}
			Footer footer = new Footer(); out.print(footer);
			%>
	</body>
</body>

</html>