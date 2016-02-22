package co.wog.adapter;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import co.wog.wogmoney.R;
import co.wog.monedero.model.CompraModel;
import co.wog.monedero.model.CompraModel.CompraItem;
import co.wog.monedero.model.ProductoModel;
import co.wog.monedero.model.ProductoModel.ProductoItem;
import co.wog.monedero.utils.ResolverImagenProducto;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListaProductosAdapter extends ArrayAdapter<ProductoModel> {

	public static final String TIPO_PRODUCTO = "lista_productos";
	public static final String TIPO_MOVIMIENTO = "lista_movimientos";
	
	Activity context;
	List lista;
	String tipoLista;
	
	public ListaProductosAdapter(Activity context, List lista, String tipoLista) {
		super(context, R.layout.list_productos_adapter, lista );
		
		this.lista = lista;
		this.context = context;
		this.tipoLista = tipoLista;
		
	}
	
	
	public ListaProductosAdapter(Context context, int resource, List<ProductoModel> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = context.getLayoutInflater();
		
		View item = inflater.inflate(R.layout.list_productos_adapter, null);
		
		//this.getItem(position);
		
		ProductoItem producto = (ProductoItem)lista.get(position);

		TextView columna1 = (TextView)item.findViewById(R.id.col_descProducto);
		columna1.setText(String.valueOf(producto.getDescripcion()));

		TextView columna2 = (TextView)item.findViewById(R.id.col_valorProducto);
		
		double dMontoVenta = Double.parseDouble( producto.getValor() );
		String sMontoVenta = NumberFormat.getCurrencyInstance(new Locale("es","CO")).format( dMontoVenta ); 
		
		columna2.setText(  sMontoVenta );
		
		ImageView columna3 = (ImageView)item.findViewById(R.id.icon_producto);
		
		if ( tipoLista.equals(TIPO_PRODUCTO) ) {
			ResolverImagenProducto.setImagenByTipoProd(producto.getTipoProducto(), columna3);
			ImageView columna4 = (ImageView)item.findViewById(R.id.img_next);
			columna4.setVisibility(ImageView.VISIBLE);
		} else if ( tipoLista.equals(TIPO_MOVIMIENTO) ) {
			ResolverImagenProducto.setImagenByTipoMovimiento(producto.getTipoMovimiento(), columna3);
			ImageView columna4 = (ImageView)item.findViewById(R.id.img_next);
			columna4.setVisibility(ImageView.INVISIBLE);
		}
		
		ListView lvDocument = (ListView) parent;
        if (lvDocument.isItemChecked(position)) {
        	item.setBackgroundDrawable( context.getResources().getDrawable(R.color.white));               
        } else {
        	item.setBackgroundDrawable(context.getResources().getDrawable(android.R.color.transparent));               
        }
		
		return item;
	}
	
	
	
}