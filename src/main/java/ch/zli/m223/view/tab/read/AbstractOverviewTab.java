package ch.zli.m223.view.tab.read;

import ch.zli.m223.view.tab.AbstractTab;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public abstract class AbstractOverviewTab<T> extends AbstractTab {

    protected TableView<T> tableView;
    protected Label title;

    public AbstractOverviewTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        tableView = new TableView<>();
        title = new Label(getText());
        title.setFont(new Font("System Bold", 22));
        root.setCenter(tableView);
        root.setTop(title);
    }
}
