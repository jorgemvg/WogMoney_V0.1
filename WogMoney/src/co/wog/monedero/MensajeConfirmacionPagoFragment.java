package co.wog.monedero;

import java.util.EnumMap;
import java.util.Map;

import co.wog.wogmoney.R;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MensajeConfirmacionPagoFragment extends Fragment {

	private static final String DEBUG_TAG = "MensajeConfirmacionVentaFragment";
	
	private TextView labelMsjResultTransaccion;
	private TextView labelConfirmNumero;
	private TextView labelCodConfirmacion;
	private ImageView imageImgCodigoBarras;
	
	private boolean bConfirmado;
	
	 private static final int WHITE = 0xFFFFFFFF;
	 private static final int BLACK = 0xFF000000;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.frg_mensaje_confirmacion_venta, container, false);
		
		return view;
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	
    	labelMsjResultTransaccion = (TextView)getView().findViewById(R.id.label_msjResultTransaccion);
    	labelConfirmNumero = (TextView)getView().findViewById(R.id.label_confirmNumero);
    	labelCodConfirmacion = (TextView)getView().findViewById(R.id.label_codConfirmacion);
    	imageImgCodigoBarras = (ImageView)getView().findViewById(R.id.image_imgCodigoBarras);
    	
    	Bundle args = getArguments();

    	boolean confirmado = false;
    	String codConfirm = "";
    	String mensajeError = "";
    	int mensajeTexto = 0;
    	
		if (args != null) {
			confirmado = getArguments().getBoolean(CarritoDeComprasDetailActivity.C_CONFIRMADO);
			codConfirm = getArguments().getString(CarritoDeComprasDetailActivity.C_COD_CONFIRMACION);
			mensajeError = getArguments().getString(MainActivity.C_MENSAJE_ERROR);
			mensajeTexto = getArguments().getInt(MainActivity.C_MENSAJE_TEXTO);
			bConfirmado = confirmado;
		} else {
			Log.e(DEBUG_TAG, "No se encontro argumentos.");
		}

		if ( confirmado ) {
			labelMsjResultTransaccion.setText(R.string.label_pagoExitoso);
			labelCodConfirmacion.setText( codConfirm );
			
			Bitmap bitmap = generarCodigoBarras( codConfirm, BarcodeFormat.CODE_128, 600, 300 );
			
			imageImgCodigoBarras.setImageBitmap( bitmap );
			
		} else {
			labelMsjResultTransaccion.setText(R.string.label_pagoError);
			labelConfirmNumero.setVisibility( View.INVISIBLE );
			labelCodConfirmacion.setText( mensajeError );
			imageImgCodigoBarras.setVisibility( View.INVISIBLE );
		}
		
    }
	
	@Override
	public void onPause() {
	
		super.onPause();
		
		if ( !bConfirmado ) {
			Intent i = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
			startActivity(i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP ));
		}
		
	}
	
	private Bitmap generarCodigoBarras( String codigo, BarcodeFormat format, int img_width, int img_height ) {
		
		Bitmap bitmap = null;
		
		try{
		
			String contentsToEncode = codigo;
		    if (contentsToEncode == null) {
		        return null;
		    }
		    Map<EncodeHintType, Object> hints = null;
		    String encoding = guessAppropriateEncoding(contentsToEncode);
		    if (encoding != null) {
		        hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
		        hints.put(EncodeHintType.CHARACTER_SET, encoding);
		    }
		    MultiFormatWriter writer = new MultiFormatWriter();
		    BitMatrix result;
		    try {
		        result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
		    } catch (IllegalArgumentException iae) {
		        // Unsupported format
		        return null;
		    }
		    int width = result.getWidth();
		    int height = result.getHeight();
		    int[] pixels = new int[width * height];
		    for (int y = 0; y < height; y++) {
		        int offset = y * width;
		        for (int x = 0; x < width; x++) {
		        pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
		        }
		    }
	
		    bitmap = Bitmap.createBitmap(width, height,
		        Bitmap.Config.ARGB_8888);
		    bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		    
		}catch(Exception e) {
			Log.e(DEBUG_TAG, "No se encontro argumentos.");
		}
		
	    return bitmap;
	}
	

	private static String guessAppropriateEncoding(CharSequence contents) {
	    // Very crude at the moment
	    for (int i = 0; i < contents.length(); i++) {
	        if (contents.charAt(i) > 0xFF) {
	        return "UTF-8";
	        }
	    }
	    return null;
	}
		
	
}
