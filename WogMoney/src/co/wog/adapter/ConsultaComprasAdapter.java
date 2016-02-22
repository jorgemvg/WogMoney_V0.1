package co.wog.adapter;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import co.wog.wogmoney.R;
import co.wog.monedero.model.CompraModel;
import co.wog.monedero.model.CompraModel.CompraItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ConsultaComprasAdapter extends ArrayAdapter<CompraModel> {

	Activity context;
	List lista;
	
	public ConsultaComprasAdapter(Activity context, List lista) {
		super(context, R.layout.list_consulta_compras_adapter, lista );
		
		this.lista = lista;
		this.context = context;
		
	}
	
	
	public ConsultaComprasAdapter(Context context, int resource, List<CompraModel> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = context.getLayoutInflater();
		
		View item = inflater.inflate(R.layout.list_consulta_compras_adapter, null);
		
		//this.getItem(position);
		
		CompraItem compra = (CompraItem)lista.get(position);

		TextView columna1 = (TextView)item.findViewById(R.id.col_descripcionCompra);
		columna1.setText(String.valueOf(compra.getDescripcion()));

		TextView columna2 = (TextView)item.findViewById(R.id.col_valorCompra);
		
		double dMontoVenta = Double.parseDouble( compra.getValor() );
		String sMontoVenta = NumberFormat.getCurrencyInstance(new Locale("es","CO")).format( dMontoVenta ); 
		
		columna2.setText(  sMontoVenta );
		
		TextView columna3 = (TextView)item.findViewById(R.id.col_fecha);
		columna3.setText(String.valueOf(compra.getFechaCompra()));


		
		ListView lvDocument = (ListView) parent;
        if (lvDocument.isItemChecked(position)) {
        	item.setBackgroundDrawable( context.getResources().getDrawable(R.color.white));               
        } else {
        	item.setBackgroundDrawable(context.getResources().getDrawable(android.R.color.transparent));               
        }
		
		return item;
	}
	
}