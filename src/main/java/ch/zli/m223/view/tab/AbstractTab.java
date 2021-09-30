package ch.zli.m223.view.tab;

import ch.zli.m223.Util;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public abstract class AbstractTab extends Tab {

    protected BorderPane root;

    public AbstractTab(String s) {
        super(s);
        root = new BorderPane();
        BorderPane.setMargin(root, new Insets(15));
        root.setPadding(new Insets(10));
        root.setStyle(Util.rootStyle);
        setContent(root);
        initGUI();
    }

    public abstract void initGUI();
}
