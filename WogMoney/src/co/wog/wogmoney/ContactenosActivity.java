package co.wog.wogmoney;

import co.wog.monedero.MainActivity;
import co.wog.monedero.MensajeExitoErrorFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ContactenosActivity extends ActionBarActivity {

	public static final int C_VISUALIZAR = 551 ;
	public static final int C_CREAR = 552 ;
	public static final int C_EDITAR = 553 ;
	
	public TextView textViewTitulo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contactenos);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		textViewTitulo = (TextView)findViewById(R.id.tv_titulo_contactos);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, ContactenosFragment.newInstance(C_CREAR, false)).commit();
		}
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contactenos, menu);
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
	
	public void cambiarFragmentoMensaje ( String mensajeExito, String mensajeError ) {
		
		MensajeExitoErrorFragment nuevoFragmento = new MensajeExitoErrorFragment();
		Bundle args = new Bundle();
		args.putString(MainActivity.C_MENSAJE_TEXTO, mensajeExito);
		args.putString(MainActivity.C_MENSAJE_ERROR, mensajeError);
//		args.putBoolean(MainActivity.C_ACTIVADO, activado);
//		args.putString(MainActivity.C_COD_MONEDERO, codigoMonedero);
		nuevoFragmento.setArguments(args);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		//No quiero que vaya a el fragmento anterior. Por lo tanto se ira a la pantalla de inicio.
		transaction.addToBackStack(null);
		transaction.replace(R.id.container, nuevoFragmento);
		transaction.commit();
		
	}
}
