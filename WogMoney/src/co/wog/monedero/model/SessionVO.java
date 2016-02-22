package co.wog.monedero.model;

public class SessionVO {

	private static String nombreUsuario;
	private static String fechaUltimaConeccion;
	private static String horaUltimaConeccion;
	
	public static String getNombreUsuario() {
		return nombreUsuario;
	}
	public static void setNombreUsuario(String nombreUsuario) {
		SessionVO.nombreUsuario = nombreUsuario;
	}
	public static String getFechaUltimaConeccion() {
		return fechaUltimaConeccion;
	}
	public static void setFechaUltimaConeccion(String fechaUltimaConeccion) {
		SessionVO.fechaUltimaConeccion = fechaUltimaConeccion;
	}
	public static String getHoraUltimaConeccion() {
		return horaUltimaConeccion;
	}
	public static void setHoraUltimaConeccion(String horaUltimaConeccion) {
		SessionVO.horaUltimaConeccion = horaUltimaConeccion;
	}
	
		
}
