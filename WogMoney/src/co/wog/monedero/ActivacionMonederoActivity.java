package co.wog.monedero;

import co.wog.wogmoney.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ActivacionMonederoActivity extends ActionBarActivity {

	private int modo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activacion_monedero);

		Bundle extras = getIntent().getExtras();
		
		this.modo = extras.getInt( MainActivity.C_MODO );
		
		if (savedInstanceState == null) {

			ActivacionMonederoFragment fragment = ActivacionMonederoFragment.newInstance(modo);
        	
        	FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        	ft.add(R.id.containerActivarMonedero, fragment);
        	ft.commit();
			
		}
	}
	
	public void cambiarFragmentoMensaje ( boolean activado, String codigoMonedero ) {
		
		MensajeExitoErrorFragment nuevoFragmento = new MensajeExitoErrorFragment();
		Bundle args = new Bundle();
		args.putBoolean(MainActivity.C_ACTIVADO, activado);
		args.putString(MainActivity.C_COD_MONEDERO, codigoMonedero);
		nuevoFragmento.setArguments(args);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		//No quiero que vaya a el fragmento anterior. Por lo tanto se ira a la pantalla de inicio.
//		transaction.addToBackStack(null);
		transaction.replace(R.id.containerActivarMonedero, nuevoFragmento);
		transaction.commit();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activacion_monedero, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_activacion_monedero, container, false);
			return rootView;
		}
	}
	
	public String getPhoneNumber(){
   	  
		String numeroTel = "";
		 
		TelephonyManager mTelephonyManager;
		mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); 
		numeroTel = mTelephonyManager.getLine1Number();
   	  
		// si esta vacio es pq el numero no esta configurado en la SIM... CASI NUNCA LO ESTA
		//Como casi todo el mundo tiene whatsApp y el propio ws tiene que pedir el numero, lo sacamos de alli
		//Actualizacion: Tampoco me funciono con wapp no trae el nro en el nombre. Lo mejor va ser pedir el numero.
//		if ( numeroTel == null || numeroTel.isEmpty() ) {
//			
//			mTelephonyManager.getSubscriberId();
//			
//			AccountManager am = AccountManager.get(this);
//	   	  	Account[] accounts = am.getAccounts();
//
//	   	  	for (Account ac : accounts) {
//	   	  		String acname = ac.name;
//	   	  		String actype = ac.type;
//
//	   	  		System.out.println("Accounts : " + acname + ", " + actype);
//	   	  		if(actype.equals("com.whatsapp")){
//	   	  			numeroTel = ac.name;
//	   	  		}
//	   	  	}
//		}
   	  
   	  	return numeroTel;
   	  
   }

}
