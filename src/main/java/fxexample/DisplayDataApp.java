package fxexample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DisplayDataApp extends Application {
    private Person updatePerson;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //TextField t1 = new TextField("Enter");
        //Button b = new Button("f");
        final ComboBox<PersonAge> categories = new ComboBox<>();
        final ComboBox<Person> people = new ComboBox<>();

        people.setVisible(false);

        Map<PersonAge, List<Person>> peopleMap = Person.getPeopleMap();
        ObservableList<PersonAge> ages = FXCollections.observableArrayList(peopleMap.keySet());

        categories.getItems().addAll(ages);
        categories.setPromptText("--Select an age group--");
        categories.valueProperty().addListener(new ChangeListener<PersonAge>() {
            @Override
            public void changed(ObservableValue<? extends PersonAge> observable, PersonAge oldValue, PersonAge newValue) {
                people.getItems().clear();
                people.getItems().addAll(peopleMap.get(newValue));
                people.setVisible(true);
            }
        });

        BorderPane pane = new BorderPane();
        pane.setTop(categories);
        pane.setCenter(people);
        Button del, upd;
        Button save = new Button("Save");
        Button search = new Button ("Last name search");
        upd = new Button("Update");
        TextField uFName = new TextField("");
        TextField uLName = new TextField("");
        TextField uAge = new TextField("");
        TextField sLName = new TextField("");
        Label fName = new Label("First Name:");
        Label lName = new Label("Last Name:");
        Label age = new Label("Age:");
        Label id = new Label("ID: ???  ");
        HBox box1 = new HBox(5);
        HBox box = new HBox(5);
        box.getChildren().addAll(upd, id, fName, uFName, lName, uLName, age, uAge, save);
        pane.setBottom(box);

        /*search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sName = search.getText().toLowerCase(Locale.ROOT).strip();

                for (Map.Entry e : peopleMap.entrySet()) {
                    for (Person p : e.getValue()) {
                        if (sName.equalsIgnoreCase(p.getLName())) {
                            //tbd
                        }
                    }
                }
            }
        });*/

        upd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if ((updatePerson = people.getValue()) == null)
                    return;

                id.setText("ID: " + updatePerson.getId());
                uFName.setText(updatePerson.getFName());
                uLName.setText(updatePerson.getLName());
                uAge.setText(updatePerson.getAge() + "");
            }
        });

        pane.setRight(del = new Button("Delete"));

        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person p;

                if ((p = people.getValue()) == null)
                    return;
                peopleMap.get(PersonAge.getPersonAge(p.getAge())).remove(p);

            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (updatePerson == null)
                    return;

                updatePerson.setFName(uFName.getText().strip());
                updatePerson.setLName(uLName.getText().strip());

                int oAge = Integer.valueOf(updatePerson.getAge());
                int nAge = Integer.valueOf(uAge.getText());
                updatePerson.setAge(nAge);
                PersonAge opa, npa;

                if ((opa = PersonAge.getPersonAge(oAge)) != (npa = PersonAge.getPersonAge(nAge))) {
                    peopleMap.get(opa).remove(updatePerson);
                    peopleMap.get(npa).add(updatePerson);
                }
            }
        });
        Scene scene = new Scene(pane, 900, 300);
        //Button b = new Button("K");
        //Label label = new Label("This is label");
        //scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Contacts");
        stage.show();
    }
}
