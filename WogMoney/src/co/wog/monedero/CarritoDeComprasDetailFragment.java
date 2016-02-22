package co.wog.monedero;

import co.wog.wogmoney.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import co.wog.adapter.FormasPagoAdapter;
import co.wog.monedero.model.CompraModel;
import co.wog.monedero.model.FormasPagoVO;

/**
 * A fragment representing a single CarritoDeCompras detail screen. This
 * fragment is either contained in a {@link CarritoDeComprasListActivity} in
 * two-pane mode (on tablets) or a {@link CarritoDeComprasDetailActivity} on
 * handsets.
 */
public class CarritoDeComprasDetailFragment extends Fragment {
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
	
	private Spinner formaPago;
	private EditText nombreComercio;
	private EditText montoCompra;
	private EditText referencia;
    private Button btnPagar;
    private Button btnConfirmar;
    private int modo;
	
	public CarritoDeComprasDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = CompraModel.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
			modo = getArguments().getInt(CarritoDeComprasDetailActivity.C_MODO);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_carritodecompras_detail, container, false);

		nombreComercio = (EditText) rootView.findViewById(R.id.CarritoDeCompras_NombreComercio);
		montoCompra = (EditText) rootView.findViewById(R.id.CarritoDeCompras_MontoTotal);
		
		formaPago = (Spinner) rootView.findViewById(R.id.CarritoDeCompras_FormaPago);
		ArrayList<FormasPagoVO> lista = llenadoComboFormaPago();
    	FormasPagoAdapter adaptador = new FormasPagoAdapter(getActivity(), android.R.layout.simple_spinner_item, lista);
    	formaPago.setAdapter(adaptador);
    	
    	referencia = (EditText) rootView.findViewById(R.id.CarritoDeCompras_Referencia);
    	
    	addListenerOnButton ( rootView );
    	
    	establecerModo( modo );
    	
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			nombreComercio.setText(mItem.getNombreComercio());
			
			double dMontoVenta = Double.parseDouble( mItem.getValor() );
			String sMontoVenta = NumberFormat.getCurrencyInstance(new Locale("es","CO")).format( dMontoVenta ); 
			montoCompra.setText( sMontoVenta );
		}

		return rootView;
	}
	
	public void addListenerOnButton ( View rootView ) {
    	btnPagar = (Button) rootView.findViewById(R.id.btnPagar);
    	
    	btnPagar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				boolean cancelar = false;
				
				String sNombreComercio = nombreComercio.getText().toString();
				String sMontoTotal = montoCompra.getText().toString();
				
				FormasPagoVO formaPagoVO = (FormasPagoVO)formaPago.getSelectedItem();
				String sFormaPago = formaPagoVO.getFormaPagoId();
				
				if (TextUtils.isEmpty( sNombreComercio )) {
					nombreComercio.setError(getString(R.string.error_field_required));
					nombreComercio.requestFocus();
					cancelar = true;
				}
				if (TextUtils.isEmpty( sMontoTotal )) {
					montoCompra.setError(getString(R.string.error_field_required));
					montoCompra.requestFocus();
					cancelar = true;
				}
				
				if ( !cancelar ) {
					
					Toast.makeText(getActivity(), "Revisar información y confirmar.", Toast.LENGTH_SHORT).show();
					
					establecerModo(CarritoDeComprasDetailActivity.C_CONFIRMAR);
				}

			}
		});
    	
    	btnConfirmar = (Button) rootView.findViewById(R.id.btnConfirmar);
    	
    	btnConfirmar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				String sNombreComercio = nombreComercio.getText().toString();
				String sMontoTotal = montoCompra.getText().toString();
				String sReferencia = referencia.getText().toString();
				
				FormasPagoVO formaPagoVO = (FormasPagoVO)formaPago.getSelectedItem();
				String sFormaPago = formaPagoVO.getFormaPagoId();
			
				CarritoDeComprasDetailActivity activity = (CarritoDeComprasDetailActivity)getActivity();
					
				//llama a servicio de confirmacion del pago
				
				boolean exito = true;
				String codConfirmacion = "08432-33028493";
				String msjRta = "Fondos Insuficientes";
				
				activity.cambiarFragmentoMensaje( exito, codConfirmacion, msjRta );
				
			}
		});
    }
	
	private void establecerModo(int modo) {

		this.getArguments().putInt(CarritoDeComprasDetailActivity.C_MODO, modo);
	
		if (modo == CarritoDeComprasDetailActivity.C_NUEVO) {
//			getActivity().setTitle( R.string.activar );
			this.setEdicion(true, modo);
			
		} else if (modo == CarritoDeComprasDetailActivity.C_CONFIRMAR) {
			this.setEdicion(false, modo);
		}
    }
	
	private void setEdicion(boolean habilitado, int modo) {

    	formaPago.setEnabled(habilitado);
		referencia.setEnabled(habilitado);
		
		if ( habilitado ) {
			btnPagar.setVisibility( View.VISIBLE );
			btnConfirmar.setVisibility( View.INVISIBLE );
		} else {
			btnPagar.setVisibility( View.INVISIBLE );
			btnConfirmar.setVisibility( View.VISIBLE );
		}

    }
	
//	private int getModo() {
//
//		int modo = -1;
//		Bundle args = getArguments();
//
//		if (args != null) {
//			modo = getArguments().getInt(MainActivity.C_MODO, 0);
//		} else {
//			Log.e("CarritoComprasDetalleFragment", "No se encontro arguments.");
//		}
//
//		return modo;
//
//	}
	
	private ArrayList<FormasPagoVO> llenadoComboFormaPago() {
    	
    	ArrayList<FormasPagoVO> lista = new ArrayList<FormasPagoVO>();
    	
    	lista.add( new FormasPagoVO("1", "Cuenta Ahorro 1234") );
    	lista.add( new FormasPagoVO("2", "Cupo Rotativo") );
    	lista.add( new FormasPagoVO("3", "Tarjeta Crédito") );
    	lista.add( new FormasPagoVO("4", "Efectivo") );
    	
    	return lista;
    	
    }
}
