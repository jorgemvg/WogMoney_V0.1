package co.wog.monedero;

import co.wog.wogmoney.R;
import java.text.NumberFormat;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.wog.monedero.model.CompraModel;

/**
 * A fragment representing a single Compra detail screen. This fragment is
 * either contained in a {@link CompraListActivity} in two-pane mode (on
 * tablets) or a {@link CompraDetailActivity} on handsets.
 */
public class CompraDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private CompraModel.CompraItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public CompraDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = CompraModel.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_compra_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.NombreComercio)).setText(mItem.getNombreComercio());
			((TextView) rootView.findViewById(R.id.FechaCompra)).setText(mItem.getFechaCompra());
			
			double dMontoVenta = Double.parseDouble( mItem.getValor() );
			String sMontoVenta = NumberFormat.getCurrencyInstance(new Locale("es","CO")).format( dMontoVenta ); 
			((TextView) rootView.findViewById(R.id.MontoCompra)).setText( sMontoVenta );
			
			((TextView) rootView.findViewById(R.id.FormaPago)).setText(mItem.getDescFormaPago());
			((TextView) rootView.findViewById(R.id.Referencia)).setText(mItem.getReferencia());
		}

		return rootView;
	}
}
