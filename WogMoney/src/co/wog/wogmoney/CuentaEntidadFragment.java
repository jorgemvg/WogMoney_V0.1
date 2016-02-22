package co.wog.wogmoney;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import co.wog.monedero.model.CuentaBancariaModel;
import co.wog.monedero.utils.CurrencyFormat;

public class CuentaEntidadFragment extends Fragment {

	private static final String DEBUG_TAG = "CuentaEntidadFragment";
	
	private final static String SAVEINST = "saveinstate";
	
	private final static String CAMPO_ORIGEN = "campo_origen";
	private final static String CAMPO_DEST = "campo_dest";
	private final static String CAMPO_MONTO = "campo_monto";
	
	private CuentaBancariaModel.CuentaItem mItem;
	
	private TextView cuentaOrigenDesc;
	private TextView cuentaDestinoDesc;
	private EditText monto;
	private Button btnContinuar;
	
	private String cuentaOrigenId;
	private String cuentaDestinoId;
	
	String campo ;
	
	private Bundle savedState = null;
	
	public CuentaEntidadFragment() {
        // Required empty public constructor
    }
	
	public static CuentaEntidadFragment newInstance(int modo, boolean showTwoFragments ) {
		CuentaEntidadFragment f = new CuentaEntidadFragment();

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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//seteamos en true para que pueda entrar el metodo onOptionsItemSelected 
		setHasOptionsMenu(true);
		
		if (getArguments() != null && getArguments().containsKey(TransferenciasActivity.CUENTA_BANCARIA_INDEX)) {
			mItem = CuentaBancariaModel.LIST_CUENTAS.get( getArguments().getInt(TransferenciasActivity.CUENTA_BANCARIA_INDEX) );
			campo = getArguments().getString(CuentaBancariaFragment.CAMPO);
		}
		
		if ( savedInstanceState == null ) {
			
		}
	}
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View view = inflater.inflate(R.layout.frg_cuentas_entidad, container, false);

    	return view;
    }
    
    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		TransferenciasActivity activity = (TransferenciasActivity)getActivity();
		activity.textViewTitulo.setText( R.string.title_cuentas_entidad );
		
		registerViews();
    	
    	addListenerOnButton();	
    	
    	if (mItem != null) {
    		if ( campo != null && campo.equals(CAMPO_ORIGEN) ) {
    			cuentaOrigenDesc.setText(mItem.getNroCuenta());
        		cuentaOrigenId = mItem.getCuentaId();
    		} else if ( campo != null && campo.equals(CAMPO_DEST) ) {
    			cuentaDestinoDesc.setText(mItem.getNroCuenta());
        		cuentaDestinoId = mItem.getCuentaId();
    		}
    		
		}
		
	}
    
    @Override
    public void onPause() {
        super.onPause();
        String persistentVariable = cuentaOrigenDesc.getText().toString();

        getArguments().putString(CAMPO_ORIGEN, persistentVariable);
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
    	cuentaOrigenDesc.setEnabled(habilitado);
    	cuentaDestinoDesc.setEnabled(habilitado);
    	monto.setEnabled(habilitado);
	}
	
	private void resetFields() {
		cuentaOrigenDesc.setText(getString(R.string.label_cuentaOrigen));
		cuentaDestinoDesc.setText(getString(R.string.label_cuentaDestino));
		monto.setText("");
	}
    
    private  void registerViews () {

    	cuentaOrigenDesc = (TextView)getView().findViewById(R.id.tv_cuentaOrigen);
    	cuentaOrigenDesc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				CuentaBancariaFragment newFragment = new CuentaBancariaFragment();
				Bundle args = new Bundle();
				args.putString(CuentaBancariaFragment.CAMPO, CAMPO_ORIGEN);
				args.putString(CAMPO_ORIGEN, cuentaOrigenDesc.getText().toString());
				args.putString(CAMPO_DEST, cuentaDestinoDesc.getText().toString());
				args.putString(CAMPO_MONTO, monto.getText().toString());
				args.putInt(TransferenciasActivity.C_MODO, getModo() );
				newFragment.setArguments(args);
				FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.transferencia_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
			}
		});
    	
    	cuentaDestinoDesc = (TextView)getView().findViewById(R.id.tv_cuentaDestino);
    	cuentaDestinoDesc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				CuentaBancariaFragment newFragment = new CuentaBancariaFragment();
				Bundle args = new Bundle();
				args.putString(CuentaBancariaFragment.CAMPO, CAMPO_DEST);
				args.putString(CAMPO_ORIGEN, cuentaOrigenDesc.getText().toString());
				args.putString(CAMPO_DEST, cuentaDestinoDesc.getText().toString());
				args.putString(CAMPO_MONTO, monto.getText().toString());
				args.putInt(TransferenciasActivity.C_MODO, getModo() );
				newFragment.setArguments(args);
				FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.transferencia_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
			}
		});
		
    	monto = (EditText)getView().findViewById(R.id.monto);
    	monto.addTextChangedListener( CurrencyFormat.getCurrencyInputTextWatcher( monto ) );
    	
    	Bundle mySavedInstanceState = getArguments();
    	if ( mySavedInstanceState != null && !mySavedInstanceState.isEmpty() ) {
    		if ( mySavedInstanceState.getString(CAMPO_ORIGEN) != null ) 
    			cuentaOrigenDesc.setText(mySavedInstanceState.getString(CAMPO_ORIGEN));
    		if ( mySavedInstanceState.getString(CAMPO_DEST) != null )
    			cuentaDestinoDesc.setText( mySavedInstanceState.getString(CAMPO_DEST) );
    		if ( mySavedInstanceState.getString(CAMPO_MONTO) != null )
    			monto.setText( mySavedInstanceState.getString(CAMPO_MONTO) );
    	}
        

    }
    
//    private Bundle saveState () {
//    	Bundle state = new Bundle();
//    	state.putCharSequence(CAMPO_ORIGEN, cuentaOrigenDesc != null ? cuentaOrigenDesc.getText() : "");
//    	return state;
//    }
//
//	@Override
//	public void onSaveInstanceState(Bundle outState) {
//		// TODO Auto-generated method stub
//		super.onSaveInstanceState(outState);
//		
//		outState.putBundle( SAVEINST, savedState != null ? savedState : saveState() );
//	}
    
	public void addListenerOnButton () {
    	btnContinuar = (Button) getView().findViewById(R.id.btnContinuar);
    	
    	btnContinuar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if ( getModo() == TransferenciasActivity.C_CREAR ) {
				
					boolean cancelar = false;
					
					String sCuentaOrigenDesc = cuentaOrigenDesc.getText().toString();
					String sCuentaDestinoDesc = cuentaDestinoDesc.getText().toString();
					String sMonto = monto.getText().toString();
//					
//					if (TextUtils.isEmpty( sCuentaOrigenDesc )) {
//						cuentaOrigenDesc.setError(getString(R.string.error_field_required));
//						cuentaOrigenDesc.requestFocus();
//						cancelar = true;
//					}
//					if (TextUtils.isEmpty( sCuentaDestinoDesc )) {
//						cuentaDestinoDesc.setError(getString(R.string.error_field_required));
//						cuentaDestinoDesc.requestFocus();
//						cancelar = true;
//					}
					if (TextUtils.isEmpty( sMonto )) {
						monto.setError(getString(R.string.error_field_required));
						monto.requestFocus();
						cancelar = true;
					} else {
						Long lMonto = Long.valueOf( CurrencyFormat.cleanCurrencyString( sMonto ) );
						if ( lMonto <= 0 ) {
							monto.setError(getString(R.string.error_monto_mayor_cero));
							monto.requestFocus();
							cancelar = true;
						}
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
					
											activity.cambiarFragmentoMensaje( getResources().getString(R.string.label_transferenciaExitosa), null );
											
										}
					
				}
			}
		});
    }
    

}