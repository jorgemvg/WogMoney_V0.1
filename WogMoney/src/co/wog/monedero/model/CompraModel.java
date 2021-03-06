package co.wog.monedero.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompraModel {

	public static List<CompraItem> LIST_COMPRAS = new ArrayList<CompraItem>();

	public static Map<String, CompraItem> ITEM_MAP = new HashMap<String, CompraItem>();
	
	public static void consultarListaCarritoCompras() {
		
		LIST_COMPRAS = new ArrayList<CompraModel.CompraItem>();
		ITEM_MAP = new HashMap<String, CompraModel.CompraItem>();
		
		//llamamos servicio para traer las compras
		addItem( new CompraItem("1", "Compra1", "20400", "Comercio 1", "1/9/2015", "Efectivo", "referencia 1") );
		addItem( new CompraItem("2", "Compra2", "35600", "Comercio 2", "3/10/2015", "Tarjeta Credito American", "referencia 2") );
		addItem( new CompraItem("3", "Compra3", "180000", "Comercio 3", "5/10/2015", "Efectivo", "referencia 3") );
		addItem( new CompraItem("4", "Compra4", "355400", "Comercio 4", "8/10/2015", "Tarjeta Debito Bancolombia", "referencia 4") );
		addItem( new CompraItem("5", "Compra5", "76500", "Comercio 5", "11/10/2015", "Efectivo", "referencia 5") );

	}
	
	public static void consultarListaCompras() {
		
		LIST_COMPRAS = new ArrayList<CompraModel.CompraItem>();
		ITEM_MAP = new HashMap<String, CompraModel.CompraItem>();
		
		//llamamos servicio para traer las compras
		addItem( new CompraItem("33", "Compra33", "20400", "Comercio 1", "1/9/2015", "Efectivo", "referencia 1") );
		addItem( new CompraItem("34", "Compra34", "35600", "Comercio 2", "3/10/2015", "Tarjeta Credito American", "referencia 2") );
		addItem( new CompraItem("35", "Compra35", "180000", "Comercio 3", "5/10/2015", "Efectivo", "referencia 3") );
		addItem( new CompraItem("36", "Compra36", "355400", "Comercio 4", "8/10/2015", "Tarjeta Debito Bancolombia", "referencia 4") );
		addItem( new CompraItem("37", "Compra37", "76500", "Comercio 5", "11/10/2015", "Efectivo", "referencia 5") );

	}
	
	private static void addItem(CompraItem item) {
		LIST_COMPRAS.add(item);
		ITEM_MAP.put(item.getCompraId(), item);
	}
	
	public static class CompraItem {

		private String compraId;

		private String descripcion;

		private String valor;

		private String nombreComercio;

		private String fechaCompra;
		
		private String formaPagoId;
		
		private String descFormaPago;
		
		private String referencia;
		

		public CompraItem(String compraId, String descripcion, String valor, String nombreComercio) {
			this.compraId = compraId;
			this.descripcion = descripcion;
			this.valor = valor;
			this.nombreComercio = nombreComercio;
		}

		public CompraItem(String compraId, String descripcion, String valor,
				String nombreComercio, String fechaCompra, String descFormaPago, String referencia) {
			this.compraId = compraId;
			this.descripcion = descripcion;
			this.valor = valor;
			this.nombreComercio = nombreComercio;
			this.fechaCompra = fechaCompra;
			this.descFormaPago = descFormaPago;
			this.referencia = referencia;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}

		public String getCompraId() {
			return compraId;
		}

		public void setCompraId(String compraId) {
			this.compraId = compraId;
		}

		public String getNombreComercio() {
			return nombreComercio;
		}

		public void setNombreComercio(String nombreComercio) {
			this.nombreComercio = nombreComercio;
		}

		public String getFechaCompra() {
			return fechaCompra;
		}

		public void setFechaCompra(String fechaCompra) {
			this.fechaCompra = fechaCompra;
		}

		public String getDescFormaPago() {
			return descFormaPago;
		}

		public void setDescFormaPago(String descFormaPago) {
			this.descFormaPago = descFormaPago;
		}

		public String getReferencia() {
			return referencia;
		}

		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}

		public String getFormaPagoId() {
			return formaPagoId;
		}

		public void setFormaPagoId(String formaPagoId) {
			this.formaPagoId = formaPagoId;
		}
	}
}
