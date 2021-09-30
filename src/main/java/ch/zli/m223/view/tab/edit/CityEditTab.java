package ch.zli.m223.view.tab.edit;

import ch.zli.m223.rest.data.City;
import ch.zli.m223.view.node.NumberTextField;
import javafx.scene.control.TextField;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public class CityEditTab extends AbstractEditTab<City> {

    private TextField inputName;
    private NumberTextField inputZip;

    public CityEditTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        inputName = new TextField();
        inputZip = new NumberTextField();
    }

    @Override
    protected void updateForm() {

    }

    @Override
    protected void actionSave() {

    }
}
