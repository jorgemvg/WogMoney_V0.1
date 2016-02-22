package co.wog.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import co.wog.wogmoney.R;
import co.wog.wogmoney.TransferenciasActivity;

public class TransferenciaAdapter extends BaseAdapter {

	String[] result;
	TransferenciasActivity activity;
	int[] imageId;
	private static LayoutInflater inflater = null;

	public TransferenciaAdapter(Activity activity, String[] prgmNameList, int[] prgmImages) {
		// TODO Auto-generated constructor stub
		result = prgmNameList;
		this.activity = (TransferenciasActivity)activity;
		imageId = prgmImages;
		inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View rowView;

		rowView = inflater.inflate(R.layout.transferencias_adapter, null);
		TextView tv = (TextView) rowView.findViewById(R.id.textView_transferencia);
		ImageView img = (ImageView) rowView.findViewById(R.id.img_transferencia);

		tv.setText(result[position]);
		img.setImageResource(imageId[position]);

		
//		rowView.setOnClickListener(new OnClickListener() {
//
//			
//			
//			@Override
//			public void onClick(View v) {
//				
//				if ( position == 0 ) {
//					activity.cambiarFragmento( TransferenciasActivity.FRG_CUENTA_ENTIDAD );
//				} else if ( position == 1 ) {
//					
//				} if ( position == 2 ) {
//					activity.cambiarFragmento( TransferenciasActivity.FRG_REGISTRAR_CUENTAS);
//				}
//				
//		
//			}
//		});

		
		
		return rowView;
	}

}