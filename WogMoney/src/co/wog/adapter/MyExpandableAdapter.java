package co.wog.adapter;

import java.util.ArrayList;

import co.wog.monedero.MainActivity;
import co.wog.wogmoney.R;
import co.wog.monedero.model.DrawerItem;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyExpandableAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	private ArrayList menuItems, child;
	
	public MyExpandableAdapter( ArrayList menuItems ) {
		this.menuItems = menuItems;
	}
	
	public void setInflater( LayoutInflater inflater, Activity activity ){
		this.inflater = inflater;
		this.activity = activity;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		if ( convertView == null ) {
			convertView = inflater.inflate(R.layout.drawer_list_item, null);
		}
		
		TextView textView = (TextView)convertView.findViewById(R.id.text1);
		ImageView imageView = (ImageView)convertView.findViewById(R.id.icon);
		
		ImageView imageViewRight = (ImageView)convertView.findViewById(R.id.iconRight);
		
		DrawerItem item = (DrawerItem) menuItems.get(groupPosition);
		
		if ( !item.getSubMenus().isEmpty() ) {
			if ( isExpanded ) {
				imageViewRight.setImageResource( R.drawable.ic_bullet_arrow_up);
			} else {
				imageViewRight.setImageResource( R.drawable.ic_bullet_arrow_down);
			}
		} else {
			imageViewRight.setImageResource( 0 );
		}
		
		textView.setText( item.getName() );
		convertView.setTag( item.getName() );
		imageView.setImageResource( item.getIconId() );
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
		DrawerItem item = (DrawerItem) menuItems.get(groupPosition);
		child = (ArrayList)item.getSubMenus();
				
		TextView textView = null;
		
		if ( convertView == null ) {
			convertView = inflater.inflate(R.layout.drawer_list_sub_item, null);
		}
		
		textView = (TextView) convertView.findViewById(R.id.textView1);
		textView.setText((String)child.get(childPosition));
		
		
		
		return convertView;
	}

	

	

	@Override
	public int getGroupCount() {
		return menuItems.size();
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		DrawerItem item = (DrawerItem) menuItems.get(groupPosition);
		return ((ArrayList)item.getSubMenus()).size();
	}

	@Override
	public Object getGroup(int groupPosition ) {
		return menuItems.get(groupPosition );
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return  ((DrawerItem) menuItems.get(groupPosition)).getSubMenus().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition ) {
		return groupPosition ;
	}
	
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void onGroupCollapsed(int groupPosition) {
		// TODO Auto-generated method stub
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		// TODO Auto-generated method stub
		super.onGroupExpanded(groupPosition);
	}
}
