package co.wog.monedero;

import co.wog.wogmoney.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * An activity representing a single CarritoDeCompras detail screen. This
 * activity is only used on handset devices. On tablet-size devices, item
 * details are presented side-by-side with a list of items in a
 * {@link CarritoDeComprasListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link CarritoDeComprasDetailFragment}.
 */
public class CarritoDeComprasDetailActivity extends FragmentActivity {

	public static final String C_CONFIRMADO = "confirm";
	public static final String C_COD_CONFIRMACION = "codConfirm";
	public static final String C_MODO = "modo" ;
	public static final int C_NUEVO = 1 ;
	public static final int C_CONFIRMAR = 2 ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carritodecompras_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString( CarritoDeComprasDetailFragment.ARG_ITEM_ID, getIntent().getStringExtra( CarritoDeComprasDetailFragment.ARG_ITEM_ID));
			arguments.putInt( C_MODO, getIntent().getIntExtra( C_MODO, 0 ) );
			CarritoDeComprasDetailFragment fragment = new CarritoDeComprasDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.carritodecompras_detail_container, fragment)
					.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this,
					CarritoDeComprasListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void cambiarFragmentoMensaje ( boolean confirmado, String codigoConfirm, String msjRta ) {
		
		MensajeConfirmacionPagoFragment nuevoFragmento = new MensajeConfirmacionPagoFragment();
		Bundle args = new Bundle();
		args.putBoolean( C_CONFIRMADO, confirmado);
		args.putString( C_COD_CONFIRMACION, codigoConfirm);
		args.putString(MainActivity.C_MENSAJE_ERROR, msjRta);
		
		nuevoFragmento.setArguments(args);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		//No quiero que vaya a el fragmento anterior. Por lo tanto se ira a la pantalla de inicio.
//		transaction.addToBackStack(null);
		transaction.replace(R.id.carritodecompras_detail_container, nuevoFragmento);
		transaction.commit();
		
	}

}
