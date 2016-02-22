package co.wog.monedero;

import co.wog.wogmoney.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ActivacionMonederoFragment extends Fragment {

	private EditText cuentaCoop;
	private EditText cedulaUsuario;
	private EditText nombreUsuario;
	private EditText claveActivacion;
	private Button btnContinuar;
	
	public static ActivacionMonederoFragment newInstance(int modo ) {
		ActivacionMonederoFragment f = new ActivacionMonederoFragment();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt(MainActivity.C_MODO, modo);
		f.setArguments(args);

		return f;
	}
	
	public ActivacionMonederoFragment() {
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activacion_monedero, container, false);
        return rootView;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	
    	registerViews();
    	
    	addListenerOnButton ();
    	
    }
    
    private  void registerViews () {

    	cuentaCoop = (EditText) getView().findViewById(R.id.CuentaCoop);
    	cedulaUsuario = (EditText) getView().findViewById(R.id.CedulaUsuario);
    	nombreUsuario = (EditText) getView().findViewById(R.id.NombreUsuario);
    	claveActivacion = (EditText) getView().findViewById(R.id.ClaveActivacion);
    	
    }
    
    private void resetFields() {
    	cuentaCoop.setText("");
    	cedulaUsuario.setText("");
    	nombreUsuario.setText("");
    	claveActivacion.setText("");
	}
    
    public void addListenerOnButton () {
    	btnContinuar = (Button) getView().findViewById(R.id.btnContinuar);
    	
    	btnContinuar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				boolean cancelar = false;
				
				String sCuentaCoop = cuentaCoop.getText().toString();
				String sCedulaUsuario = cedulaUsuario.getText().toString();
				String sNombreUsuario = nombreUsuario.getText().toString();
				String sClaveActivacion = claveActivacion.getText().toString();
				
				if (TextUtils.isEmpty( sCuentaCoop )) {
					cuentaCoop.setError(getString(R.string.error_field_required));
					cuentaCoop.requestFocus();
					cancelar = true;
				}
				if (TextUtils.isEmpty( sCedulaUsuario )) {
					cedulaUsuario.setError(getString(R.string.error_field_required));
					cedulaUsuario.requestFocus();
					cancelar = true;
				}
				if (TextUtils.isEmpty( sNombreUsuario )) {
					nombreUsuario.setError(getString(R.string.error_field_required));
					nombreUsuario.requestFocus();
					cancelar = true;
				}
				if (TextUtils.isEmpty( sClaveActivacion )) {
					claveActivacion.setError(getString(R.string.error_field_required));
					claveActivacion.requestFocus();
					cancelar = true;
				}
				
				if ( !cancelar ) {
					
//					ActivacionMonederoActivity activity = (ActivacionMonederoActivity)getActivity();
					MainActivity activity = (MainActivity)getActivity();
					
//					String numeroTel = activity.getPhoneNumber();
					
//					Toast.makeText(getActivity(), numeroTel, Toast.LENGTH_SHORT).show();
					
					//llama a servicio para la activacion del monedero en el celular
					
					boolean exito = true;
					
					if ( exito ) {
						// Si fue exitoso, me debe de mandar una clave para la activacion, que se la enviare al cel del usuario
						
						String clave = "XU349";
						String numeroTel = "3168271997";
						
						
//						SmsManager sms = SmsManager.getDefault();
//						sms.sendTextMessage(numeroTel, "jorge", "Clave Confirmación Monedero: " + clave, null, null);
						
						
					}
					
					String codMonedero = "88373-99283447";
					
					activity.cambiarFragmentoMensaje( exito, codMonedero );
				}

			}
		});
    }
    
   
	
}
