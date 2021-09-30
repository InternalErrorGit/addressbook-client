package ch.zli.m223.view.tab.update;

import ch.zli.m223.view.tab.AbstractTab;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public abstract class AbstractEditTab<T> extends AbstractTab {
    protected ComboBox<T> entries;
    protected T selectedEntry;
    protected VBox vBox;

    public AbstractEditTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(new Label("Select an entry to be changed"));
        entries = new ComboBox<>();
        entries.setOnAction(actionEvent -> {
            selectedEntry = entries.getValue();
            if (selectedEntry != null)
                updateForm();
        });
        vBox.getChildren().add(entries);
        setContent(vBox);
    }

    protected abstract void updateForm();

    protected void actionCancel(ActionEvent actionEvent) {
        getTabPane().getTabs().remove(this);
    }

    protected void actionSubmit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Save changes?");
        Optional<ButtonType> result = alert.showAndWait();
        assert result.isPresent();
        if (result.get() == ButtonType.CANCEL)
            return;
        actionSave();
        getTabPane().getTabs().remove(this);
    }

    protected abstract void actionSave();

}
