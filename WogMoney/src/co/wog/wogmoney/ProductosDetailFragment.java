package co.wog.wogmoney;

import java.text.NumberFormat;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import co.wog.adapter.ListaProductosAdapter;
import co.wog.monedero.model.ProductoModel;
import co.wog.monedero.utils.ResolverImagenProducto;

/**
 * A fragment representing a single Productos detail screen. This fragment is
 * either contained in a {@link ProductosListActivity} in two-pane mode (on
 * tablets) or a {@link ProductosDetailActivity} on handsets.
 */
public class ProductosDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private ProductoModel.ProductoItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ProductosDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = ProductoModel.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
			
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_productos_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			
			TextView columna1 = (TextView)rootView.findViewById(R.id.col_descProducto);
			columna1.setText(String.valueOf(mItem.getDescripcion()));

			TextView columna2 = (TextView)rootView.findViewById(R.id.col_valorProducto);
			double dMontoVenta = Double.parseDouble( mItem.getValor() );
			String sMontoVenta = NumberFormat.getCurrencyInstance(new Locale("es","CO")).format( dMontoVenta ); 
			columna2.setText(  sMontoVenta );
			
			ImageView columna3 = (ImageView)rootView.findViewById(R.id.icon_producto);
			ResolverImagenProducto.setImagenByTipoProd(mItem.getTipoProducto(), columna3);
			
			
			ListView list = (ListView)rootView.findViewById(R.id.listMovimientosProd);
			ProductoModel.consultarMovimientosByProducto( mItem.getProductoId() );
			ListaProductosAdapter productosAdapter = new ListaProductosAdapter(getActivity(), ProductoModel.LIST_MOVIMIENTOS_PROD, ListaProductosAdapter.TIPO_MOVIMIENTO);
			list.setAdapter(productosAdapter);
			
		}

		return rootView;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	
		if ( getActivity() instanceof ProductosDetailActivity ) {
			ProductosDetailActivity activity = (ProductosDetailActivity)getActivity();
			if ( activity.layoutAnuncio != null )
				activity.layoutAnuncio.setBackground( getResources().getDrawable(R.drawable.img_anuncio2));
		} else {
			ProductosListActivity activity = (ProductosListActivity)getActivity();
			if ( activity.layoutAnuncio != null )
				activity.layoutAnuncio.setBackground( getResources().getDrawable(R.drawable.img_anuncio2));
		}

	}
}
