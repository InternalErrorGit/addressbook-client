package ch.zli.m223.view.tab.create;

import ch.zli.m223.view.tab.AbstractTab;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public abstract class AbstractNewTab extends AbstractTab {

    protected VBox form;
    private Label title;

    public AbstractNewTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        form = new VBox();
        form.setSpacing(5);
        form.setMaxWidth(300);
        title = new Label(getText());
        title.setFont(new Font("System Bold",22));
        form.getChildren().add(title);
        root.setCenter(form);

    }

    protected void addButtons(){
        Button save = new Button("Submit");
        save.setOnAction(this::actionSave);
        Button cancel = new Button("Cancel");
        cancel.setOnAction(this::actionCancel);

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.getChildren().addAll(save, cancel);
        form.getChildren().add(hBox);
    }

    protected abstract void actionSave(ActionEvent actionEvent);

    protected void actionCancel(ActionEvent actionEvent) {
        getTabPane().getTabs().remove(this);
    }
}
