package co.wog.wogmoney;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import co.wog.monedero.MainActivity;
import co.wog.monedero.model.ComboItem;
import co.wog.monedero.utils.CombosData;
import co.wog.monedero.utils.Validation;

public class ContactenosFragment extends Fragment {

	private static final String DEBUG_TAG = "ContactenosFragment";
	
	public String ciudadId;
	public ArrayAdapter<ComboItem> adapterAutotext;
	
	private EditText nombre;
	private AutoCompleteTextView ciudad;
	private EditText telefono;
	private EditText correo;
	private EditText asunto;
	private EditText mensaje;
	private Button btnEnviar;
	
	public ContactenosFragment() {
	}
	
	public static ContactenosFragment newInstance(int modo, boolean showTwoFragments ) {
		ContactenosFragment f = new ContactenosFragment();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		
		args.putInt(MainActivity.C_MODO, modo);
		args.putBoolean(TransferenciasActivity.C_SHOW_TWO_FRAGMENTS, showTwoFragments);
		f.setArguments(args);

		return f;
	}
	
	public int getModo() {

		int modo = -1;
		Bundle args = getArguments();

		if (args != null) {
			modo = getArguments().getInt(MainActivity.C_MODO, 0);
		} else {
			Log.e(DEBUG_TAG, "No se encontro arguments.");
		}

		return modo;

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		setHasOptionsMenu(true);

		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_contactenos,
				container, false);

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		ContactenosActivity activity = (ContactenosActivity)getActivity();
		activity.textViewTitulo.setText( R.string.title_activity_contactenos );
		
		registerViews();
		
		addListenerOnButton();
		
		establecerModo(getModo(), true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				return true;
			case android.R.id.home:
				if ( getModo() == ContactenosActivity.C_CREAR ) {
					getActivity().finish();
				} else if ( getModo() == ContactenosActivity.C_VISUALIZAR ) {
					ContactenosActivity activity = (ContactenosActivity)getActivity();
					activity.textViewTitulo.setText( R.string.title_activity_contactenos );
					
					btnEnviar.setText( getString(R.string.boton_enviar) );
					establecerModo(ContactenosActivity.C_CREAR, false);
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}
	
	private  void registerViews () {
		nombre = (EditText)getView().findViewById(R.id.nombre);
		ciudad = (AutoCompleteTextView) getView().findViewById(R.id.ciudad);
		telefono = (EditText)getView().findViewById(R.id.telefono);
		correo = (EditText)getView().findViewById(R.id.correo);
		asunto = (EditText)getView().findViewById(R.id.asunto);
		mensaje = (EditText)getView().findViewById(R.id.mensaje);
		
		CombosData combosData = new CombosData();
		adapterAutotext = new ArrayAdapter<ComboItem>(getActivity(), android.R.layout.simple_dropdown_item_1line, combosData.getCiudades());
		ciudad.setThreshold(2);
		ciudad.setAdapter(adapterAutotext);
		
		ciudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	ciudadId = adapterAutotext.getItem(position).getId() ;
		    }
		});
		
		ciudad.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) { }
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				ciudadId = null;
			}
			@Override
			public void afterTextChanged(Editable s) { }
		});
		
	}
	
	public void addListenerOnButton () {
		btnEnviar = (Button) getView().findViewById(R.id.btnEnviar);
    	
		btnEnviar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if ( getModo() == ContactenosActivity.C_CREAR ) {
				
					boolean cancelar = false;
					
					String sNombre = nombre.getText().toString();
					String cCiudad = ciudad.getText().toString();
					String sTelefono = telefono.getText().toString();
					String sCorreo = correo.getText().toString();
					String sAsunto= asunto.getText().toString();
					String sMensaje = mensaje.getText().toString();
					
					if (TextUtils.isEmpty( sNombre )) {
						nombre.setError(getString(R.string.error_field_required));
						nombre.requestFocus();
						cancelar = true;
					}
					if (TextUtils.isEmpty( sTelefono )) {
						telefono.setError(getString(R.string.error_field_required));
						telefono.requestFocus();
						cancelar = true;
					}
					if( TextUtils.isEmpty( cCiudad ) )  {
						ciudad.setError(getString(R.string.error_field_required));
						ciudad.requestFocus();
						cancelar = true;
					} else if (ciudadId == null) {
						ciudad.setError("Ciudad No Valida.");
						ciudad.requestFocus();
						cancelar = true;
					}
					if (TextUtils.isEmpty( sCorreo )) {
						correo.setError(getString(R.string.error_field_required));
						correo.requestFocus();
						cancelar = true;
					} else {
						boolean isValid = Validation.isEmailAddress(correo, true);
						if (!isValid) {
							correo.setError(getString(R.string.validation_formato_correo_invalido));
							correo.requestFocus();
							cancelar = true;
						}
					}
					if (TextUtils.isEmpty( sAsunto )) {
						asunto.setError(getString(R.string.error_field_required));
						asunto.requestFocus();
						cancelar = true;
					}
					if (TextUtils.isEmpty( sMensaje )) {
						mensaje.setError(getString(R.string.error_field_required));
						mensaje.requestFocus();
						cancelar = true;
					}
					
					if ( !cancelar ) {

						ContactenosActivity activity = (ContactenosActivity)getActivity();
						activity.textViewTitulo.setText( R.string.label_confirmar_datos );
						
						btnEnviar.setText( getString(R.string.boton_confirmar) );

						establecerModo( TransferenciasActivity.C_VISUALIZAR, false );
					}
					
				} else if ( getModo() == TransferenciasActivity.C_VISUALIZAR ) {
					
					
					//					ActivacionMonederoActivity activity = (ActivacionMonederoActivity)getActivity();
										ContactenosActivity activity = (ContactenosActivity)getActivity();
										
					//					String numeroTel = activity.getPhoneNumber();
										
					//					Toast.makeText(getActivity(), numeroTel, Toast.LENGTH_SHORT).show();
										
										//llama a servicio
										
										boolean exito = true;
										
										if ( exito ) {
					//						SmsManager sms = SmsManager.getDefault();
					//						sms.sendTextMessage(numeroTel, "jorge", "Clave Confirmación Monedero: " + clave, null, null);
					
											activity.cambiarFragmentoMensaje( getResources().getString(R.string.label_mensajeEnviadoExito), null );
											
										}
					
				}
			}
		});
	}
	
	private void establecerModo(int modo, boolean reset) {

		this.getArguments().putInt(TransferenciasActivity.C_MODO, modo);

		if (modo == TransferenciasActivity.C_VISUALIZAR) {
			this.setEdicion(false);

		} else if (modo == TransferenciasActivity.C_CREAR) {
			if ( reset )  
				resetFields();
			this.setEdicion(true);
			
		}
	}
	
	private void setEdicion(boolean habilitado) {
		nombre.setEnabled(habilitado);
		telefono.setEnabled(habilitado);
		correo.setEnabled(habilitado);
		ciudad.setEnabled(habilitado);
		asunto.setEnabled(habilitado);
		mensaje.setEnabled(habilitado);
	}
	
	private void resetFields() {
		nombre.setText("");
		telefono.setText("");
		correo.setText("");
		ciudad.setText("");
		ciudadId = null;
		asunto.setText("");
		mensaje.setText("");
	}
}
