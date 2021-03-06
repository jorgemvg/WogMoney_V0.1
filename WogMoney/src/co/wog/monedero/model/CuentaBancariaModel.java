package co.wog.monedero.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuentaBancariaModel {

	public static List<CuentaItem> LIST_CUENTAS = new ArrayList<CuentaItem>();

	public static Map<String, CuentaItem> ITEM_MAP = new HashMap<String, CuentaItem>();
	
	public static void consultarCuentasBancarias() {
		
		LIST_CUENTAS = new ArrayList<CuentaBancariaModel.CuentaItem>();
		ITEM_MAP = new HashMap<String, CuentaBancariaModel.CuentaItem>();
		
		//llamamos servicio para traer las compras
		addItem( new CuentaItem("1", "Cuenta Bancaria 1", "172632-1721") );
		addItem( new CuentaItem("2", "Cuenta Bancaria 2", "22738-364") );
		addItem( new CuentaItem("3", "Cuenta Bancaria 3", "429173-925") );

	}
	
	private static void addItem(CuentaItem item) {
		LIST_CUENTAS.add(item);
		ITEM_MAP.put(item.getCuentaId(), item);
	}
	
	public static class CuentaItem {

		private String cuentaId;

		private String descripcion;

		private String nroCuenta;


		public CuentaItem(String cuentaId, String descripcion, String nroCuenta) {
			this.cuentaId = cuentaId;
			this.descripcion = descripcion;
			this.nroCuenta = nroCuenta;
		}


		public String getCuentaId() {
			return cuentaId;
		}


		public void setCuentaId(String cuentaId) {
			this.cuentaId = cuentaId;
		}


		public String getDescripcion() {
			return descripcion;
		}


		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}


		public String getNroCuenta() {
			return nroCuenta;
		}


		public void setNroCuenta(String nroCuenta) {
			this.nroCuenta = nroCuenta;
		}

	}
}
