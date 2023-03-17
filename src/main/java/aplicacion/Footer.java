package aplicacion;

public class Footer {
	public String navbar;

	public Footer() {
		String res = "";
		res += "<footer>";
		res += "  <div class='fixed-bottom text-center bg-secondary text-light p-2 border-top'>";
		res += "    Diseño: Juan Manuel Gutiérrez";
		res += "  </div>";
		res += "</footer>";
		this.navbar = res;
	}

	public String toString() {
		return this.navbar;
	}
}
