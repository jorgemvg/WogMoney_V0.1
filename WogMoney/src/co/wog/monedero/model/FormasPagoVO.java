package co.wog.monedero.model;

public class FormasPagoVO {

	private String formaPagoId;
	
	private String nombre;
	
	public FormasPagoVO(String formaPagoId, String nombre) 
    {
        super();
        this.formaPagoId = formaPagoId;
        this.nombre = nombre;
    }

	public String getFormaPagoId() {
		return formaPagoId;
	}

	public void setFormaPagoId(String formaPagoId) {
		this.formaPagoId = formaPagoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
