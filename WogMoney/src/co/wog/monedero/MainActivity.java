package co.wog.monedero;

import java.util.ArrayList;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.Toast;
import co.wog.adapter.MyExpandableAdapter;
import co.wog.monedero.model.DrawerItem;
import co.wog.monedero.utils.MenuCall;
import co.wog.wogmoney.R;

public class MainActivity extends FragmentActivity {

	public static final String C_MODO  = "modo" ;
	public static final String C_ACTIVADO = "activ";
	public static final String C_COD_MONEDERO = "codMonedero";
	public static final String C_MENSAJE_ERROR = "msjError";
	public static final String C_MENSAJE_TEXTO = "msjTexto";
	
	public static final int C_NUEVO = 551 ;
	public static final int C_ACTIVAR = 552 ;
	public static final int C_EXITO_ACTIVACION = 553 ;
	public static final int C_ERROR_ACTIVACION = 554 ;
	public static final int C_CONFIRMAR = 555 ;
	
	public static FragmentActivity frgActivity;
	
	private ListView lista;
//    private ListView navList;
	private ExpandableListView epView;
    private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle toggle;
	String currentTitle = "";
	int optionChecked = -1;
	
	private ArrayList<DrawerItem> parentsItems = new ArrayList<DrawerItem>();
	private ArrayList<Object> childItems = new ArrayList<Object>();
	
	public MainActivity(){
		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setTitle( R.string.label_titulo );
        
        if (savedInstanceState == null) {
        	
//        	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//			transaction.addToBackStack(null);
//			transaction.replace(R.id.container, new MainFragment());
//			transaction.commit();
        	
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment()).commit();
        }
        frgActivity = this;
        epView = (ExpandableListView)findViewById(R.id.left_drawer);
        
//        epView.setDividerHeight(2);
//        epView.setGroupIndicator(null);
//        epView.setClickable(true);
//        
        epView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View view,
					int groupPosition, int childPosition, long id) {
				
				callFragmentByOption( groupPosition, childPosition, getIntent() );
				
//				Toast.makeText(getApplicationContext(), "Sec clikeo el grupo: " + groupPosition, Toast.LENGTH_SHORT).show();
				
				epView.setItemChecked(childPosition, true);
				drawerLayout.closeDrawers();
				
				return true;
			}
    		
    	});
        
        epView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				
				callFragmentByOption( groupPosition, -1,  getIntent() );
				
				epView.setItemChecked(groupPosition, true);
				drawerLayout.closeDrawers();
				
				return false;
			}
    		
    	});
    	
        getInfoMenu();
        
    	MyExpandableAdapter adapter = new MyExpandableAdapter(parentsItems);
    	adapter.setInflater((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
    	epView.setAdapter(adapter);
    	
    	
    	
      //Montamos el Navigation Drawer
        
      		
        // Declarar e inicializar componentes para el Navigation Drawer
//        this.navList = (ListView) findViewById(R.id.left_drawer);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
 	    //Nueva lista de drawer items
        
        
        // Set previous array as adapter of the list
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, opciones);
        
        // Declarar adapter y eventos al hacer click
//        navList.setAdapter( new DrawerListAdapter(this, items) );
//        
//        navList.setOnItemClickListener( new OnItemClickListener() {
//        	 
//        	public void onItemClick(AdapterView parent, View view, int position, long id) {
//        		
//        		callFragmentByOption( position, getIntent() );
//        		
//        		navList.setItemChecked(position, true);
//        		drawerLayout.closeDrawers();
//        	}
//        } );
	    
	    //Integrar icono oficial de menu de google
        toggle = new ActionBarDrawerToggle(this, drawerLayout,  R.drawable.ic_drawer, R.string.app_name, R.string.app_name ){
   		 
        	public void onDrawerClosed(View view) {
        		super.onDrawerClosed(view);
        		String title  = getActionBar().getTitle().toString();
        		if ( title.equals(getResources().getString(R.string.menu)) ) {
        			getActionBar().setTitle(currentTitle);
        		} else {
        			getActionBar().setTitle(title);
        		}
        		
        	}
   		 
        	public void onDrawerOpened(View drawerView) {
        		super.onDrawerOpened(drawerView);
        		currentTitle = getActionBar().getTitle().toString();
        		getActionBar().setTitle(getResources().getString(R.string.menu));
        	}
        };
        
        //Añadimos este Toggle a nuestro Drawer Layout
        drawerLayout.setDrawerListener(toggle);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
//        handleIntent(getIntent());
    }

    //activamos el toggle
  	@Override
  	protected void onPostCreate(Bundle savedInstanceState) {
  		super.onPostCreate(savedInstanceState);
  		toggle.syncState();
  	}
  	
  	 @Override
  	 public void onConfigurationChanged(Configuration newConfig) {
  		 super.onConfigurationChanged(newConfig);
  		 toggle.onConfigurationChanged(newConfig);
  	 }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       
    	// Pass the event to ActionBarDrawerToggle, if it returns
    	// true, then it has handled the app icon touch event
    	if (toggle.onOptionsItemSelected(item)) {
    		return true;
    	}
    	
    	// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
//    private void handleIntent (Intent intent) {
//		
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//        	
//        	if ( optionChecked != -1 ) {
//        		
//        		callFragmentByOption( optionChecked, null );
//        		
//        	}
//     
//        }
//		
//	}
    
    private void callFragmentByOption ( int position, int childPosition, Intent intent ) {
		
		FragmentManager fragmentManager = null;
		Bundle args = null;
		
		if ( intent != null ) {
			intent.removeExtra( SearchManager.QUERY );
		}
		
		optionChecked = position;
		
//		Fragment fragment = MenuCall.callFragmentByOption(position, childPosition);
//		
//		if ( fragment != null ) {
//			// Insert the fragment by replacing any existing fragment
////		    fragmentManager = getSupportFragmentManager();
////		    fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
//
//			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//			transaction.addToBackStack(null);
//			transaction.replace(R.id.container, fragment);
//			transaction.commit();
//			
//			
//		}
		
		Class activityClass = MenuCall.callActivityByOption( this, position, childPosition );
		if ( activityClass != null )  {
			Intent i = new Intent(getApplicationContext(), activityClass);
			i.putExtra(MainActivity.C_MODO, MainActivity.C_NUEVO);
			startActivityForResult(i, 0);
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
		transaction.replace(R.id.container, nuevoFragmento);
		transaction.commit();
		
	}
    
    private void getInfoMenu() {
    
    	final String[] opciones = getResources().getStringArray( R.array.nav_options );
    	
    	ArrayList<String> child = new ArrayList<String>();
    	
    	//Productos
    	parentsItems.add(new DrawerItem(opciones[0],R.drawable.ic_sales_report, null));
    	
    	//Transferencias
    	parentsItems.add(new DrawerItem(opciones[1],R.drawable.ic_money_transfer, null));
    	
    	//Pagos
        parentsItems.add(new DrawerItem(opciones[2],R.drawable.ic_payment, null));
        
        //Tarjetas WOG
        parentsItems.add(new DrawerItem(opciones[3],R.drawable.ic_credit_card, null));
        
        //Carrito de Compras
        parentsItems.add(new DrawerItem(opciones[4],R.drawable.ic_shopping_cart, null));
        
        //Sitios de interes
        parentsItems.add(new DrawerItem(opciones[5],R.drawable.ic_map, null));
        
        //Contactenos
        parentsItems.add(new DrawerItem(opciones[6],R.drawable.ic_megaphone, null));
        
        //Mensajes
        parentsItems.add(new DrawerItem(opciones[7],R.drawable.ic_solicitud_credito, null));
        
        //Tienda
        parentsItems.add(new DrawerItem(opciones[8],R.drawable.ic_shopping, child));
        
        //Configuracion
        parentsItems.add(new DrawerItem(opciones[9],R.drawable.ic_solicitud_credito, null));
        
        //Salir
        parentsItems.add(new DrawerItem(opciones[10],R.drawable.ic_sign_up, null));
     	
    }

}
