package ch.zli.m223.view.tab.delete;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.AddressDAOImpl;
import ch.zli.m223.rest.data.Address;
import javafx.event.ActionEvent;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public class AddressDeleteTab extends AbstractDeleteTab<Address> {
    public AddressDeleteTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        entries.setItems(Model.getInstance().addresses);
    }

    @Override
    protected void actionDelete(ActionEvent actionEvent) {
        new AddressDAOImpl().delete(entries.getValue().getId());
    }
}
