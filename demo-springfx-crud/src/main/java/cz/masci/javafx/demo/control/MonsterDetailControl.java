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
package cz.masci.javafx.demo.control;

import cz.masci.javafx.demo.FxmlRoot;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel
 */
@Service
@Scope("prototype")
@FxmlView("monster-detail-control.fxml")
@FxmlRoot
public class MonsterDetailControl extends GridPane {

  public MonsterDetailControl() {
    System.out.println("MonsterDetailControl constructor");
  }

  public void initialize() {
    System.out.println("MonsterDetailControl initialize");
  }

  @FXML
  private TextField name;

  @FXML
  private TextArea description;

  public StringProperty nameProperty() {
    return name.textProperty();
  }

  public void setName(String name) {
    nameProperty().set(name);
  }

  public String getName() {
    return nameProperty().get();
  }

  public StringProperty descriptionProperty() {
    return description.textProperty();
  }

  public void setDescription(String description) {
    descriptionProperty().set(description);
  }

  public String getDescription() {
    return descriptionProperty().get();
  }
}
