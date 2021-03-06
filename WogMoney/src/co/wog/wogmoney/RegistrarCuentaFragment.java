package co.wog.wogmoney;

import co.wog.monedero.MainActivity;
import co.wog.monedero.model.ComboItem;
import co.wog.monedero.utils.CombosData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrarCuentaFragment extends Fragment {

	private static final String DEBUG_TAG = "RegistrarCuenta";
	
	private CombosData combosData;
	
	private EditText nombretitular;
	private Spinner tipoIdentificacion;
	private EditText numeroDocumento;
	private Spinner banco;
	private Spinner tipoCuenta;
	private EditText numeroCuenta;
	private EditText alias;
	private Button btnContinuar;
	
	public static RegistrarCuentaFragment newInstance(int modo, boolean showTwoFragments ) {
		RegistrarCuentaFragment f = new RegistrarCuentaFragment();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		
		args.putInt(TransferenciasActivity.C_MODO, modo);
		args.putBoolean(TransferenciasActivity.C_SHOW_TWO_FRAGMENTS, showTwoFragments);
		f.setArguments(args);

		return f;
	}
	
	public int getModo() {

		int modo = -1;
		Bundle args = getArguments();

		if (args != null) {
			modo = getArguments().getInt(TransferenciasActivity.C_MODO, 0);
		} else {
			Log.e(DEBUG_TAG, "No se encontro arguments.");
		}

		return modo;

	}
	
	public boolean getShowTwoFragments() {

		boolean dosFragmentos = false;
		Bundle args = getArguments();

		if (args != null) {
			dosFragmentos = getArguments().getBoolean(TransferenciasActivity.C_SHOW_TWO_FRAGMENTS, false);
		} else {
			Log.e(DEBUG_TAG, "No se encontro arguments.");
		}

		return dosFragmentos;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		setHasOptionsMenu(true);

		super.onCreate(savedInstanceState);

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_registrar_cuentas, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		TransferenciasActivity activity = (TransferenciasActivity)getActivity();
		activity.textViewTitulo.setText( R.string.title_registrarCuentas );
		
		// Obtenemos los elementos de la vista
		registerViews();
	
		addListenerOnButton();
		
		// Establecemos el modo del formulario
		establecerModo(getModo());
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                
            	TransferenciasFragment newFragment = new TransferenciasFragment();
    			FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.transferencia_container, newFragment);
                transaction.commit();
            	
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	private  void registerViews () {

		nombretitular = (EditText) getView().findViewById(R.id.nombre_titular);
		tipoIdentificacion = (Spinner) getView().findViewById(R.id.tipo_identificacion);
		numeroDocumento = (EditText) getView().findViewById(R.id.numero_documento);
		banco = (Spinner) getView().findViewById(R.id.banco);
		tipoCuenta = (Spinner) getView().findViewById(R.id.tipo_cuenta);
		numeroCuenta = (EditText) getView().findViewById(R.id.numero_cuenta);
		alias = (EditText) getView().findViewById(R.id.alias);
		
		combosData = new CombosData();
		
		ArrayAdapter<ComboItem> adaptadorTipoIdent = new ArrayAdapter<ComboItem>(getActivity(), android.R.layout.simple_spinner_item, combosData.getTiposdeIdentificacion());
		tipoIdentificacion.setAdapter(adaptadorTipoIdent);
		
		ArrayAdapter<ComboItem> adaptadorBanco = new ArrayAdapter<ComboItem>(getActivity(), android.R.layout.simple_spinner_item, combosData.getBancos());
		banco.setAdapter(adaptadorBanco);
		
		ArrayAdapter<ComboItem> adaptadorTipoCuenta = new ArrayAdapter<ComboItem>(getActivity(), android.R.layout.simple_spinner_item, combosData.getTiposDeCuenta());
		tipoCuenta.setAdapter(adaptadorTipoCuenta);
	
	}
	
	private void establecerModo(int modo) {

		this.getArguments().putInt(TransferenciasActivity.C_MODO, modo);

		if (modo == TransferenciasActivity.C_VISUALIZAR) {
			this.setEdicion(false);

		} else if (modo == TransferenciasActivity.C_CREAR) {
			resetFields();
			this.setEdicion(true);
			
		}
	}
	
	private void setEdicion(boolean habilitado) {
		nombretitular.setEnabled(habilitado);
		tipoIdentificacion.setEnabled(habilitado);
		numeroDocumento.setEnabled(habilitado);
		banco.setEnabled(habilitado);
		tipoCuenta.setEnabled(habilitado);
		numeroCuenta.setEnabled(habilitado);
		alias.setEnabled(habilitado);
	}
	
	private void resetFields() {
		nombretitular.setText("");
		tipoIdentificacion.setSelection(0);
		numeroDocumento.setText("");
		banco.setSelection(0);
		tipoCuenta.setSelection(0);
		numeroCuenta.setText("");
		alias.setText("");
	}
	
	public void addListenerOnButton () {
    	btnContinuar = (Button) getView().findViewById(R.id.btnContinuar);
    	
    	btnContinuar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if ( getModo() == TransferenciasActivity.C_CREAR ) {
				
					boolean cancelar = false;
					
					String sNombretitular = nombretitular.getText().toString();
					ComboItem itemTipo = (ComboItem)tipoIdentificacion.getSelectedItem();
					String sNumeroDocumento = numeroDocumento.getText().toString();
					ComboItem itemBanco = (ComboItem)banco.getSelectedItem();
					ComboItem itemTipoCta = (ComboItem)tipoCuenta.getSelectedItem();
					String sNumeroCuenta = numeroCuenta.getText().toString();
					String sAlias = alias.getText().toString();
					
					if (TextUtils.isEmpty( sNombretitular )) {
						nombretitular.setError(getString(R.string.error_field_required));
						nombretitular.requestFocus();
						cancelar = true;
					}
					if (TextUtils.isEmpty( sNumeroDocumento )) {
						numeroDocumento.setError(getString(R.string.error_field_required));
						numeroDocumento.requestFocus();
						cancelar = true;
					}
					
					if ( !cancelar ) {
					
						btnContinuar.setText( getString(R.string.boton_confirmar) );

						establecerModo( TransferenciasActivity.C_VISUALIZAR );
					}
					
				} else if ( getModo() == TransferenciasActivity.C_VISUALIZAR ) {
					
					
					//					ActivacionMonederoActivity activity = (ActivacionMonederoActivity)getActivity();
										TransferenciasActivity activity = (TransferenciasActivity)getActivity();
										
					//					String numeroTel = activity.getPhoneNumber();
										
					//					Toast.makeText(getActivity(), numeroTel, Toast.LENGTH_SHORT).show();
										
										//llama a servicio
										
										boolean exito = true;
										
										if ( exito ) {
					//						SmsManager sms = SmsManager.getDefault();
					//						sms.sendTextMessage(numeroTel, "jorge", "Clave Confirmación Monedero: " + clave, null, null);
					
											activity.cambiarFragmentoMensaje( getResources().getString(R.string.label_cuentaRegistradaExito), null );
											
										}
					
				}
			}
		});
    }
}
