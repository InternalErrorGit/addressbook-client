package ch.zli.m223.view.tab.delete;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.PersonDAOImpl;
import ch.zli.m223.rest.data.Person;
import javafx.event.ActionEvent;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public class PersonDeleteTab extends AbstractDeleteTab<Person> {
    public PersonDeleteTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        entries.setItems(Model.getInstance().people);
    }

    @Override
    protected void actionDelete(ActionEvent actionEvent) {
        new PersonDAOImpl().delete(entries.getValue().getId());
    }
}
