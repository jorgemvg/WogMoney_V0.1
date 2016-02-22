package co.wog.wogmoney;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import co.wog.adapter.CuentaBancariaAdapter;
import co.wog.monedero.model.CuentaBancariaModel;

public class CuentaBancariaFragment extends Fragment {
	
	OnHeadlineSelectedListener mCallback;
	
	public final static String CAMPO = "campo";
	
	private String fragmento;
	private String campoValue;
	
	// Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onCuentaBancariaSelected(int position, String campoValue, Bundle args);
    }
	
	public CuentaBancariaFragment () {
		
	}
	
	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // Nos aseguramos de que la actividad contenedora haya implementado la
        // interfaz de retrollamada. Si no, lanzamos una excepción
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " debe implementar OnHeadlineSelectedListener");
        }
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//seteamos en true para que pueda entrar el metodo onOptionsItemSelected 
		setHasOptionsMenu(true);
		
		Bundle args = getArguments();
		if ( args != null ) {
			
			campoValue = args.getString(CAMPO);
			
		}
  
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.frg_cuentas_bancarias, container, false);
		
		ListView lv = (ListView)view.findViewById(R.id.listView1);
		
		//Cargamos la lista de cuentas bancarias
        CuentaBancariaModel.consultarCuentasBancarias();
		
		CuentaBancariaAdapter cuentasAdapter = new CuentaBancariaAdapter(getActivity(), CuentaBancariaModel.LIST_CUENTAS);
		
		lv.setAdapter(cuentasAdapter);
		
//		mCallback.onCuentaBancariaSelected( position );
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				mCallback.onCuentaBancariaSelected( position, campoValue, getArguments() );
            }
		});
		
		return view;
		
	}
	
	 @Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			
			TransferenciasActivity activity = (TransferenciasActivity)getActivity();
			activity.textViewTitulo.setText( R.string.title_cuentasBancarias );
		}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
	
	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	
	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                
            	if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            		getActivity().getSupportFragmentManager().popBackStackImmediate();
            	}
            	
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	public String getFragmento() {
		return fragmento;
	}

	public void setFragmento(String fragmento) {
		this.fragmento = fragmento;
	}

}
