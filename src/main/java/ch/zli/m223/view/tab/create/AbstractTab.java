package ch.zli.m223.view.tab.create;

import javafx.scene.control.Tab;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public abstract class AbstractTab extends Tab {

    public AbstractTab(String s) {
        super(s);
        initGUI();
    }

    abstract void initGUI();
}
