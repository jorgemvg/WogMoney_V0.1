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

public class ComprasAdapter extends ArrayAdapter<CompraModel> {

	Activity context;
	List lista;
	
	public ComprasAdapter(Activity context, List lista) {
		super(context, R.layout.list_compras_adapter, lista );
		
		this.lista = lista;
		this.context = context;
		
	}
	
	
	public ComprasAdapter(Context context, int resource, List<CompraModel> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = context.getLayoutInflater();
		
		View item = inflater.inflate(R.layout.list_compras_adapter, null);
		
		//this.getItem(position);
		
		CompraItem compra = (CompraItem)lista.get(position);

		TextView columna1 = (TextView)item.findViewById(R.id.col_descripcionCompra);
		columna1.setText(String.valueOf(compra.getDescripcion()));

		TextView columna2 = (TextView)item.findViewById(R.id.col_valorCompra);
		
		double dMontoCompra = Double.parseDouble( compra.getValor() );
		String sMontoCompra = NumberFormat.getCurrencyInstance(new Locale("es","CO")).format( dMontoCompra ); 
		
		columna2.setText( sMontoCompra );
		
		ListView lvDocument = (ListView) parent;
        if (lvDocument.isItemChecked(position)) {
        	item.setBackgroundDrawable( context.getResources().getDrawable(R.color.white));               
        } else {
        	item.setBackgroundDrawable(context.getResources().getDrawable(android.R.color.transparent));               
        }
		
		return item;
	}
	
}