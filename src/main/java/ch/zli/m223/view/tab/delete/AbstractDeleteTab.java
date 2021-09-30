package ch.zli.m223.view.tab.delete;

import ch.zli.m223.rest.data.AbstractEntity;
import ch.zli.m223.view.tab.AbstractTab;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public abstract class AbstractDeleteTab<T extends AbstractEntity<T>> extends AbstractTab {

    protected ComboBox<T> entries;
    protected T selectedEntry;

    public AbstractDeleteTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(new Label("Select an entry to be deleted"));
        entries = new ComboBox<>();
        entries.setOnAction(actionEvent -> selectedEntry = entries.getValue());
        vBox.getChildren().add(entries);
        Button submit = new Button("Apply");
        submit.setOnAction(this::actionSubmit);
        Button cancel = new Button("Cancel");
        cancel.setOnAction(this::actionCancel);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(submit,cancel);
        vBox.getChildren().add(hBox);
        setContent(vBox);
    }

    private void actionSubmit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you really want to delete this entry?");
        alert.setContentText(selectedEntry.toString());
        Optional<ButtonType> result = alert.showAndWait();
        assert result.isPresent();
        if (result.get() == ButtonType.CANCEL)
            return;
        actionDelete(actionEvent);
        getTabPane().getTabs().remove(this);
    }

    protected abstract void actionDelete(ActionEvent actionEvent);

    private void actionCancel(ActionEvent actionEvent) {
        getTabPane().getTabs().remove(this);
    }
}
