package co.wog.monedero.utils;

import android.widget.ImageView;
import co.wog.wogmoney.R;

public class ResolverImagenProducto {
	
public static void setImagenByTipoProd( String tipoProducto, ImageView imageView ) {
		
		if ( tipoProducto != null ) {
			if ( tipoProducto.equals("APO") ) {
				imageView.setImageResource( R.drawable.ic_solicitud_credito );
			} else if ( tipoProducto.equals("CTA") ) {
				imageView.setImageResource( R.drawable.ic_sales_report );
			} else if ( tipoProducto.equals("CDT") ) {
				imageView.setImageResource( R.drawable.ic_money_transfer );
			} else if ( tipoProducto.equals("PRE") ) {
				imageView.setImageResource( R.drawable.ic_cash_register );
			} else if ( tipoProducto.equals("CUP") ) {
				imageView.setImageResource( R.drawable.ic_credit_card );
			}
		}
		
	}

public static void setImagenByTipoMovimiento( String tipoMovimiento, ImageView imageView ) {
	
	if ( tipoMovimiento != null ) {
		if ( tipoMovimiento.equals("MAS") ) {
			imageView.setImageResource( R.drawable.ic_mas );
		} else if ( tipoMovimiento.equals("MENOS") ) {
			imageView.setImageResource( R.drawable.ic_menos );
		}
	}
	
}

}
