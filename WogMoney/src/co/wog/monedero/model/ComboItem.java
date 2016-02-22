package co.wog.monedero.model;

public class ComboItem {

	private String id;
	
	private String descripcion;

	public ComboItem (String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
		return descripcion;
	}
	
}