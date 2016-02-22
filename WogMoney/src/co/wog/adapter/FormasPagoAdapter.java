package co.wog.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import co.wog.wogmoney.R;
import co.wog.monedero.model.FormasPagoVO;

public class FormasPagoAdapter extends ArrayAdapter<FormasPagoVO> {

	// Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<FormasPagoVO> values;

    public FormasPagoAdapter(Context context, int textViewResourceId, List<FormasPagoVO> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount(){
       return values.size();
    }

    public FormasPagoVO getItem(int position){
       return values.get(position);
    }

    public long getItemId(int position){
       return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Puedo crear el layout dinamicamente, que en este caso seria in textView:
//        TextView label = new TextView(context);
//        label.setTextColor(Color.BLACK);
//        label.setTextAppearance(context, android.R.attr.textAppearanceMedium);
//        label.setText(values.get(position).getNombre() );

    	// o puedo usar un layout fijo:
        View spinView;
        if( convertView == null ){
        	LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            spinView = inflater.inflate(R.layout.spinner_selected_item, null);
        } else {
             spinView = convertView;
        }
        
        TextView text = (TextView)spinView.findViewById( R.id.texto );
        text.setText(values.get(position).getNombre() );
        
        return spinView;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        TextView label = new TextView(context);
//        label.setTextColor(Color.RED);
//        label.setText(values.get(position).getNombre());
//        label.setTextAppearance(context, android.R.attr.textAppearanceMedium);

    	
    	View spinView;
        if( convertView == null ){
        	LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            spinView = inflater.inflate(R.layout.spinner_selected_item, null);
        } else {
             spinView = convertView;
        }
        
        TextView text = (TextView)spinView.findViewById( R.id.texto );
        text.setText(values.get(position).getNombre() );
        
        return text;
    }
	
}