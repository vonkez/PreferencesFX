package com.dlsc.preferencesfx.history.view;

import com.dlsc.preferencesfx.history.Change;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * TODO: Add javadoc.
 * @author François Martin
 * @author Marco Sanfratello
 */
public class HistoryTable extends TableView<Change> {

  ObservableList<Change> changes;

  /**
   * TODO: Add javadoc.
   * @param changes TODO: Add javadoc.
   */
  public HistoryTable(ObservableList<Change> changes) {
    this.changes = changes;

    TableColumn<Change, String> timestamp = new TableColumn<>("Time");
    timestamp.setCellValueFactory(
        change -> new ReadOnlyStringWrapper(change.getValue().getTimestamp())
    );

    TableColumn<Change, String> breadcrumb = new TableColumn<>("Setting");
    breadcrumb.setCellValueFactory(
        change -> new ReadOnlyStringWrapper(change.getValue().getSetting().toString())
    );

    TableColumn<Change, Object> oldValue = new TableColumn<>("Old Value");
    oldValue.setCellValueFactory(change -> change.getValue().oldListProperty());

    TableColumn<Change, Object> newValue = new TableColumn<>("New Value");
    newValue.setCellValueFactory(change -> change.getValue().newListProperty());

    setItems(this.changes);
    getColumns().addAll(timestamp, breadcrumb, oldValue, newValue);
  }

  /**
   * TODO: Add javadoc.
   * @param currentChange TODO: Add javadoc.
   */
  public void addSelectionBinding(ReadOnlyObjectProperty<Change> currentChange) {
    currentChange.addListener(
        (observable, oldValue, newValue) -> getSelectionModel().select(newValue)
    );
  }

}
