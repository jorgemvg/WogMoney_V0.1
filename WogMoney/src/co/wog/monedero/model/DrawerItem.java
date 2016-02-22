package co.wog.monedero.model;

import java.util.ArrayList;

public class DrawerItem {
    private String name;
    private int iconId;
    private ArrayList<String> subMenus;
    
    public DrawerItem(String name, int iconId, ArrayList<String> subMenus) {
        this.name = name;
        this.iconId = iconId;
        this.subMenus = subMenus != null ? subMenus : new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

	public ArrayList<String> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(ArrayList<String> subMenus) {
		this.subMenus = subMenus;
	}
}
