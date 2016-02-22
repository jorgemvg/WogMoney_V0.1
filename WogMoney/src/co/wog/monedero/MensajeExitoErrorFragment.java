package co.wog.monedero;

import co.wog.wogmoney.R;
import co.wog.wogmoney.TransferenciasFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MensajeExitoErrorFragment extends Fragment{

	private static final String DEBUG_TAG = "MensajeExitoErrorFragment";
	
	private ImageView imagen;
	private TextView labelMsjExitoError;
	private TextView labelTerminalId;
	private TextView labelCodTerminal;
	
	private boolean bActivado;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		setHasOptionsMenu(true);

		super.onCreate(savedInstanceState);

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.frg_mensaje_exito_error, container, false);
		
		return view;
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	
    	imagen = (ImageView)getView().findViewById(R.id.imagen_mensaje);
    	labelMsjExitoError = (TextView)getView().findViewById(R.id.label_msjExitoError);
    	labelTerminalId = (TextView)getView().findViewById(R.id.label_terminalId);
    	labelCodTerminal = (TextView)getView().findViewById(R.id.label_codTerminal);
    	
    	Bundle args = getArguments();

    	boolean activado = false;
    	String codMonedero = "";
    	String mensajeTxt = "";
    	String mensajeError = "";
		if (args != null) {
			
			mensajeError = getArguments().getString(MainActivity.C_MENSAJE_ERROR);
			mensajeTxt = getArguments().getString(MainActivity.C_MENSAJE_TEXTO);
			
			if ( mensajeError != null ) {
				imagen.setImageResource(R.drawable.ic_error);
				labelMsjExitoError.setText( mensajeError );
				labelTerminalId.setVisibility(View.INVISIBLE);
				labelCodTerminal.setVisibility(View.INVISIBLE);
		
			} else if ( mensajeTxt != null ) {
				imagen.setImageResource(R.drawable.ic_exito);
				labelMsjExitoError.setText( mensajeTxt );
//				labelTerminalId.setVisibility(View.INVISIBLE);
//				labelCodTerminal.setVisibility(View.INVISIBLE);
				labelTerminalId.setText("");
				labelCodTerminal.setText("");
			} else {
				mensajeTxt = getArguments().getString(MainActivity.C_MENSAJE_TEXTO);		
				activado = getArguments().getBoolean(MainActivity.C_ACTIVADO);
				codMonedero = getArguments().getString(MainActivity.C_COD_MONEDERO);
				
				if ( activado ) {
					imagen.setImageResource(R.drawable.ic_exito);
					labelMsjExitoError.setText(R.string.label_activacionExitosa);
					labelCodTerminal.setText( codMonedero );
				} else {
					imagen.setImageResource(R.drawable.ic_error);
					labelMsjExitoError.setText(R.string.label_activacionError);
					labelTerminalId.setVisibility( View.INVISIBLE );
					labelCodTerminal.setText( "" );
				}
				
			}
			
		} else {
			Log.e(DEBUG_TAG, "No se encontro argumentos.");
		}
		
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                
//            	TransferenciasFragment newFragment = new TransferenciasFragment();
//    			FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.transferencia_container, newFragment);
//                transaction.commit();
                
            	Intent i = new Intent(getActivity().getApplicationContext(), MainActivity.class);
    			startActivity(i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP ));
            	
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
//	@Override
//	public void onPause() {
//	
//		super.onPause();
//		
//		if ( !bActivado ) {
//			Intent i = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
//			startActivity(i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP ));
//		}
//		
//		
//		
//	}
	
//	@Override
//	public void onStop() {
//		
//		super.onStop();
//		
//		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//		transaction.remove(this).commit();
//		
//	}
	
}
