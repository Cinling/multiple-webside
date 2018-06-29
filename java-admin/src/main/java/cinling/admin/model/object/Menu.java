package cinling.admin.model.object;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;
    private String url;
    private List<Menu> itemList = null;

    public Menu(String name) {
        this.name = name;
    }

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void AddChild(Menu childMenu) {
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }

        this.itemList.add(childMenu);
    }

    public List<Menu> GetChildList() {
        return this.itemList;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public List<Menu> getItemList() {
        return itemList;
    }
}
