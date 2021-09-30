package ch.zli.m223.view.tab.delete;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.CityDAOImpl;
import ch.zli.m223.rest.data.City;
import javafx.event.ActionEvent;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public class CityDeleteTab extends AbstractDeleteTab<City> {

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        entries.setItems(Model.getInstance().cities);
    }

    public CityDeleteTab(String s) {
        super(s);
    }

    @Override
    protected void actionDelete(ActionEvent actionEvent) {
        new CityDAOImpl().delete(entries.getValue().getId());
    }
}
