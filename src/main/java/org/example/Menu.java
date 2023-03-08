package org.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
/**
 * Klasa  obslugujaca dzialanie menu
 */
public class Menu implements Initializable {

    @FXML
    private Button btnAccept;

    @FXML
    private Button btnDeleteSelect;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnadd;

    @FXML
    private Spinner<Integer> cQty;

    @FXML
    private ChoiceBox<String> cbSize;

    @FXML
    private Label lblError;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblCash;
    @FXML
    private Label lblSize;

    @FXML
    private Label lblType;

    @FXML
    private RadioButton rbHaw;

    @FXML
    private RadioButton rbOw;

    @FXML
    private RadioButton rbSal;

    @FXML
    private Label lblTotalSum;

    @FXML
    private TableView<Products> tablemodel;

    @FXML
    private TableColumn<Products, Integer> tabPrice;

    @FXML
    private TableColumn<Products, Integer> tabQuantity;

    @FXML
    private TableColumn<Products, String> tabSize;

    @FXML
    private TableColumn<Products, Integer> tabSumPrice;

    @FXML
    private TableColumn<Products, String> tabType;

    @FXML
    private TextField tfCash;
    @FXML
    private ToggleGroup tgSize;


    private final String[] sizeItems = {"Mala", "Srednia", "Duza"};
    private final ObservableList<String> sizeList = FXCollections.observableArrayList(sizeItems);
    int currentValue;
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);

    /**
     *Metoda inicjujaca
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSize.setItems(sizeList);
        valueFactory.setValue(1);
        cQty.setValueFactory(valueFactory);
        currentValue = cQty.getValue();

        tabType.setCellValueFactory(new PropertyValueFactory<Products, String>("type"));
        tabQuantity.setCellValueFactory(new PropertyValueFactory<Products, Integer>("quantity"));
        tabSize.setCellValueFactory(new PropertyValueFactory<Products, String>("size"));
        tabPrice.setCellValueFactory(new PropertyValueFactory<Products, Integer>("price"));
        tabSumPrice.setCellValueFactory(new PropertyValueFactory<Products, Integer>("sumprice"));
        Products prod = new Products();
        prod.setTotalsuma(0);
        tfCash.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue.matches("\\d*")) {
                    int value = Integer.parseInt(newValue);
                } else {
                    tfCash.setText(oldValue);
                }
            }
        });
    }

    /**
     * Metoda odpowiadajaca za akcje wykonywana po wcisnieciu przycisku "Dodaj"
     * @param event
     * @throws IOException
     */
    @FXML
    public void addbutton(ActionEvent event) throws IOException {
        Products prod = new Products();
        if (cbSize.getSelectionModel().isEmpty()) {
            lblError.setText("Niepoprawne wartości");
        } else {
            if (rbHaw.isSelected()) {
                prod.setType("Hawajska");
                prod.setSize(cbSize.getSelectionModel().getSelectedItem());
                if (cbSize.getSelectionModel().getSelectedItem() == "Mala") {
                    prod.setPrice(20);
                } else if (cbSize.getSelectionModel().getSelectedItem() == "Srednia") {
                    prod.setPrice(30);
                } else {
                    prod.setPrice(35);
                }
                prod.setQuantity(Integer.parseInt(cQty.getValue().toString()));
                prod.setSumprice(prod.getQuantity() * prod.getPrice());
                ObservableList<Products> products = (ObservableList<Products>) tablemodel.getItems();
                products.add(prod);
                tablemodel.setItems(products);
            } else if (rbSal.isSelected()) {
                prod.setType("Salami");
                prod.setSize(cbSize.getSelectionModel().getSelectedItem());
                if (cbSize.getSelectionModel().getSelectedItem() == "Mala") {
                    prod.setPrice(18);
                } else if (cbSize.getSelectionModel().getSelectedItem() == "Srednia") {
                    prod.setPrice(25);
                } else {
                    prod.setPrice(30);
                }
                prod.setQuantity(Integer.parseInt(cQty.getValue().toString()));
                prod.setSumprice(prod.getQuantity() * prod.getPrice());
                ObservableList<Products> products = tablemodel.getItems();
                products.add(prod);
                tablemodel.setItems(products);
            } else if (rbOw.isSelected()) {
                prod.setType("Owoce Morza");
                prod.setSize(cbSize.getSelectionModel().getSelectedItem());
                if (cbSize.getSelectionModel().getSelectedItem() == "Mala") {
                    prod.setPrice(25);
                } else if (cbSize.getSelectionModel().getSelectedItem() == "Srednia") {
                    prod.setPrice(35);
                } else {
                    prod.setPrice(40);
                }
                prod.setQuantity(Integer.parseInt(cQty.getValue().toString()));
                prod.setSumprice(prod.getQuantity() * prod.getPrice());
                ObservableList<Products> products = tablemodel.getItems();
                products.add(prod);
                tablemodel.setItems(products);
            } else {
                lblError.setText("Niepoprawne wartości");
            }
        }
        prod.setTotalsuma(0);
        for (Products item : tablemodel.getItems()) {
            prod.setTotalsuma(prod.getTotalsuma() + item.getSumprice());
            lblTotalSum.setText(prod.getTotalsuma()+"");
        }
    }

    /**
     * Metoda odpowiadajaca za akcje wykonywana po wcisnieciu przycisku "Resetuj"
     * @param event
     * @throws IOException
     */
    @FXML
    public void resetbutton(ActionEvent event) throws IOException {
        rbHaw.setSelected(false);
        rbSal.setSelected(false);
        rbOw.setSelected(false);
        cbSize.setValue(null);
        valueFactory.setValue(1);
        cQty.setValueFactory(valueFactory);
        currentValue = cQty.getValue();
        lblTotalSum.setText("0");
        tfCash.setText("0");
    }

    /**
     * Metoda odpowiadajaca za akcje wykonywana po wcisnieciu przycisku "Usun zaznaczona pozycje"
     * @param event
     * @throws IOException
     */
    @FXML
    public void deletebutton(ActionEvent event) throws IOException {
        Products prod = new Products();
        int selectedID = tablemodel.getSelectionModel().getSelectedIndex();
        tablemodel.getItems().remove(selectedID);
        for (Products item : tablemodel.getItems()) {
            prod.setTotalsuma(prod.getTotalsuma() + item.getSumprice());
            lblTotalSum.setText(prod.getTotalsuma() + "");
        }
    }

    /**
     * Metoda odpowiadajaca za akcje wykonywana po wcisnieciu przycisku "Wyjdz"
     * @param event
     */
    @FXML
    public void exitOnAction(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    /**
     * Metoda odpowiadajaca za akcje wykonywana po wcisnieciu przycisku "Zamow"
     * @param event
     * @throws IOException
     */
    @FXML
    public void orderButton(ActionEvent event) throws IOException {
        int cash = Integer.parseInt(tfCash.getText());
        int total = Integer.parseInt(lblTotalSum.getText());
        if (cash>=total) {
            int balance = cash - total;
            File file = new File("order.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            String headers = "Rodzaj, Rozmiar, Cena, Ilość, Cena łącznie \n";
            writer.write(headers);
            for (Products item : tablemodel.getItems()) {
                String text = item.getType() + ", " + item.getSize() + ",  " + item.getPrice() + ",   " + item.getQuantity() + ",    " + item.getSumprice() + "\n";
                writer.write(text);
            }
            String summary = "\n" + "Razem do zaplaty: " + lblTotalSum.getText() + "\n" + "Twoja gotowka to: " + tfCash.getText() + "\n" + "Twoja reszta to: " + balance;
            writer.write(summary);
            writer.flush();
            writer.close();
            lblError.setText("Zobacz swoje zamówienie w pliku order.txt");
        }else{
            lblError.setText("Zbyt zniska kwota zapłaty");
        }
    }
}








