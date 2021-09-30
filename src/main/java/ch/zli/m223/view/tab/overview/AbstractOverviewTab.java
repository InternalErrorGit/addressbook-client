package ch.zli.m223.view.tab.overview;

import ch.zli.m223.view.tab.create.AbstractTab;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public abstract class AbstractOverviewTab<T> extends AbstractTab {

    protected TableView<T> tableView;
    protected VBox content;
    protected HBox header;
    protected CheckBox showPublic;

    public AbstractOverviewTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        System.out.println("init gui");
        content = new VBox();
        header = new HBox();
        content.setSpacing(5);
        content.setPadding(new Insets(10,10,10,10));
        showPublic = new CheckBox("Show public entries");
        showPublic.setOnAction(this::update);
        header.getChildren().add(showPublic);
        tableView = new TableView<>();
        content.getChildren().addAll(header, tableView);
        setContent(content);
    }

    abstract void update(ActionEvent actionEvent);


}
