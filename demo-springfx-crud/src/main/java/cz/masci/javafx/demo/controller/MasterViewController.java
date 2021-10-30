/*
 * Copyright (C) 2021 Daniel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.masci.javafx.demo.controller;

import cz.masci.javafx.demo.dto.MonsterDTO;
import cz.masci.javafx.demo.service.Modifiable;
import cz.masci.javafx.demo.service.ModifiableService;
import cz.masci.javafx.demo.utility.StyleChangingRowFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

/**
 * This is abstract controller for Master View editor with list of items.
 *
 * @author Daniel
 *
 * @param <T>
 */
@Slf4j
@RequiredArgsConstructor
@FxmlView("master-view.fxml")
public abstract class MasterViewController<T extends Modifiable> {

  private final FxWeaver fxWeaver;
  private final ModifiableService modifiableService;

  @FXML
  protected BorderPane borderPane;

  @FXML
  protected TableView<T> tableView;

  @FXML
  protected VBox items;

  @FXML
  protected Label tableTitle;

  @FXML
  protected Label viewTitle;

  public final void initialize() {
    init();
    tableView.setItems(getItems());
  }

  protected void setViewTitle(String title) {
    viewTitle.setText(title);
  }

  protected void setTableTitle(String title) {
    tableTitle.setText(title);
  }

  public void clearCollumns() {
    tableView.getColumns().clear();
  }

  public void addCollumns(TableColumn<T, ?>... collumns) {
    tableView.getColumns().addAll(collumns);
  }

  public <E extends DetailViewController<T>> void setDetailController(Class<E> detailController) {
    setDetailController(detailController, detailController.getSimpleName());
  }

  public <E extends DetailViewController<T>> void setDetailController(Class<E> detailController, String modifiableKey) {
    FxControllerAndView<E, Node> detailView = fxWeaver.load(detailController);

    borderPane.setCenter(detailView.getView().get());

    tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      detailView.getController().setItem(newValue);
    });

  }

  protected void setRowFactory(String styleClass, Class<T> clazz) {
    setRowFactory(styleClass, clazz.getSimpleName());
  }

  protected void setRowFactory(String styleClass, String modifiableKey) {
    tableView.setRowFactory(new StyleChangingRowFactory<>(styleClass, modifiableKey, modifiableService));
  }

  /**
   * Delegate titles and collumns initialization to subclass.
   */
  protected abstract void init();

  protected abstract ObservableList<T> getItems();

}
