package co.wog.monedero.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoModel {

	public static List<ProductoItem> LIST_PRODUCTOS = new ArrayList<ProductoItem>();
	
	public static List<ProductoItem> LIST_MOVIMIENTOS_PROD = new ArrayList<ProductoItem>();

	public static Map<String, ProductoItem> ITEM_MAP = new HashMap<String, ProductoItem>();
	
	public static void consultarListaProductos() {
		
		LIST_PRODUCTOS = new ArrayList<ProductoModel.ProductoItem>();
		ITEM_MAP = new HashMap<String, ProductoModel.ProductoItem>();
		
		//llamamos servicio para traer los productos
		addItem( new ProductoItem("1", "Aportes", "6580000", "APO") );
		addItem( new ProductoItem("2", "Cuenta de Ahorros - 1245", "970000", "CTA") );
		addItem( new ProductoItem("3", "CDATs", "520000", "CDT") );
		addItem( new ProductoItem("4", "Préstamos 4578", "355400", "PRE") );
		addItem( new ProductoItem("5", "Cupo Rotativo - 7890", "7500400", "CUP") );

	}
	
	public static void consultarMovimientosByProducto( String productoId ) {
		
		LIST_MOVIMIENTOS_PROD = new ArrayList<ProductoModel.ProductoItem>();
		
		//llamamos servicio para traer movimiento de productos
		addItemMov( new ProductoItem(productoId, "1", "Noviembre 12 de 2015", "70000", "MAS") );
		addItemMov( new ProductoItem(productoId, "2", "Octubre 15 de 2015", "100000", "MAS") );
		addItemMov( new ProductoItem(productoId, "3", "Octubre 2 de 2015", "64500", "MAS") );
		addItemMov( new ProductoItem(productoId, "4", "Septiembre 22 de 2015", "500000", "MENOS") );
		addItemMov( new ProductoItem(productoId, "5", "Septiembre 1 de 2015", "125000", "MENOS") );

	}
	
	private static void addItem(ProductoItem item) {
		LIST_PRODUCTOS.add(item);
		ITEM_MAP.put(item.getProductoId(), item);
	}
	
	private static void addItemMov(ProductoItem item) {
		LIST_MOVIMIENTOS_PROD.add(item);
	}
	
	public static class ProductoItem {

		private String productoId;
		
		private String movimientoId;

		private String descripcion;

		private String valor;

		private String tipoProducto;
		
		private String tipoMovimiento;

		public ProductoItem(String productoId, String descripcion, String valor, String tipoProducto) {
			this.productoId = productoId;
			this.descripcion = descripcion;
			this.valor = valor;
			this.tipoProducto = tipoProducto;
		}
		
		public ProductoItem(String productoId, String movimientoId, String descripcion, String valor, String tipoMovimiento) {
			this.productoId = productoId;
			this.movimientoId = movimientoId;
			this.descripcion = descripcion;
			this.valor = valor;
			this.tipoMovimiento = tipoMovimiento;
		}

		public String getProductoId() {
			return productoId;
		}

		public void setProductoId(String productoId) {
			this.productoId = productoId;
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

		public String getTipoProducto() {
			return tipoProducto;
		}

		public void setTipoProducto(String tipoProducto) {
			this.tipoProducto = tipoProducto;
		}

		public String getMovimientoId() {
			return movimientoId;
		}

		public void setMovimientoId(String movimientoId) {
			this.movimientoId = movimientoId;
		}

		public String getTipoMovimiento() {
			return tipoMovimiento;
		}

		public void setTipoMovimiento(String tipoMovimiento) {
			this.tipoMovimiento = tipoMovimiento;
		}


	
	}
}
