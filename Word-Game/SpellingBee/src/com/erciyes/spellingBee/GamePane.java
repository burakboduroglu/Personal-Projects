package com.erciyes.spellingBee;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GamePane extends Pane {
    int score = 0;
    ListView listView = new ListView<>();
    List<String> shuffleList = new ArrayList<>();
    List<String> getBtnLbls = new ArrayList<>();
    ListView listView1 = createList(0, 0);
    Puzzle puzzle = new Puzzle(listView1);
    List<String> pangram = puzzle.getPangrams();
    ProgressBar progressBar;
    TextField textField, textField2;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btnEnter, btnYellow, btnDelete, btnChange, btnDeleteAll, btnPuzzle, btnOK;
    Label scoreLabel, scorelbl, situation, checkLabel, label, footer;



    public void createPane() throws IOException {
        // create puzzle
        puzzle.createPuzzle();

        // Create Text Field
        textField = createTextField("Click a button...", 35, 25);
        textField2 = createPersontTxt("Create self puzzle...", 30, 480);

        // Create List View
        listView = createList(300, 60);

        //Progress Bar
        progressBar = createBar(350, 10);

        // Create Labels
        scoreLabel = createLabel("Score", 305, 15);
        scoreLabel.getStyleClass().add("score-label");

        scorelbl = createLabel("0", 320, 0);

        situation = createLabel(situationText, 520, 10);
        situation.getStyleClass().add("situation-label");

        checkLabel = createLabel("Match Control Screen", 350, 470);
        checkLabel.getStyleClass().add("check-label");
        createTransition(checkLabel);

        label = createLabel("FINDING WORDS", 360, 35);
        label.getStyleClass().add("heading");

        footer = createLabel("!!! If you would like to create self puzzle please enter 7 Letter.", 25, 520);
        footer.getStyleClass().add("footer");

        //Create All Buttons
        createButtons();

        // Enter Button
        btnEnter = createRectButton("Enter", 160, 340);
        createEffect(btnEnter);
        enterButtonProcess();
        btnEnter.getStyleClass().add("button-enter");

        //Change Button
        Image image = new Image("https://cdn-icons-png.flaticon.com/512/339/339853.png");
        ImageView imgView = new ImageView(image);
        imgView.setFitWidth(25);
        imgView.setFitHeight(20);

        btnChange = createRectButton(null, 108, 340);
        btnChange.setGraphic(imgView);
        btnChange.getStyleClass().add("button-change");
        createEffect(btnChange);
        changeProcess(btnChange);

        // Delete Button
        btnDelete = createRectButton("Delete", 35, 340);
        createEffect(btnDelete);
        deleteButtonProcess(btnDelete, textField);
        btnDelete.getStyleClass().add("button-delete");

        // Delete All Button
        btnDeleteAll = createRectButton("Delete all", 85, 380);
        createEffect(btnDeleteAll);
        deleteAll(btnDeleteAll, textField);
        btnDeleteAll.getStyleClass().add("button-deleteAll");

        //Change Puzzle
        btnPuzzle = createRectButton("Change Puzzle", 67, 415);
        createEffect(btnPuzzle);
        changePuzzle(btnPuzzle);
        btnPuzzle.getStyleClass().add("button-puzzle");

        // OK Button Process
        btnOK = createRectButton("OK", 260, 485);
        createEffect(btnOK);
        btnOK.getStyleClass().add("btnOK");
        okButton();

        // Add Root
        getChildren().addAll(btnOK, textField2, btnPuzzle, situation, scoreLabel, scorelbl, progressBar, listView, btn1, btn2, btn3, btn4, btn5, btn6, btnDeleteAll, btnEnter, btnDelete, btnChange, textField, footer, btnYellow, label, checkLabel);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Create Progress Bar
    public ProgressBar createBar(int x, int y) {
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setLayoutX(x);
        progressBar.setLayoutY(y);
        progressBar.setPrefWidth(150);
        return progressBar;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Create List View
    public ListView createList(int x, int y) {
        ListView listView = new ListView();
        listView.getStyleClass().add("listView");
        listView.setLayoutX(x);
        listView.setLayoutY(y);
        listView.setMouseTransparent(true);
        return listView;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Create Label
    public Label createLabel(String labelText, int Xlayout, int Ylayout) {
        Label lbl = new Label(labelText);
        lbl.getStyleClass().add("lbl");
        lbl.setLayoutX(Xlayout);
        lbl.setLayoutY(Ylayout);
        return lbl;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Create Effects
    public void createEffect(Button btn) {
        /* Effect Shadow */
        DropShadow shadow = new DropShadow();
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setEffect(shadow);
            }
        });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setEffect(null);
            }
        });
    }

    public void createTransition(Label checkLabel) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5.0), checkLabel);
        fadeTransition.setFromValue(2.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Create Buttons
    public Button createRectButton(String btnLabel, int Xlayout, int Ylayout) {
        Button btn = new Button(btnLabel);
        /* Create a Rectangle */
        Rectangle rectangle = new Rectangle(20, 5, 55, 25);
        rectangle.setArcWidth(15);
        rectangle.setArcHeight(25);
        btn.setShape(rectangle);
        btn.setLayoutX(Xlayout);
        btn.setLayoutY(Ylayout);
        return btn;
    }

    public Button createHexagonButton(String buttonLabel, int Xlayout, int Ylayout) {
        Polyline hexagon = new Polyline();
        hexagon.getPoints().addAll(new Double[]{
                250.0, 100.0,
                450.0, 100.0,
                500.0, 200.0,
                450.0, 300.0,
                250.0, 300.0,
                200.0, 200.0,
        });
        hexagon.setStrokeWidth(100);
        Button btn = new Button(buttonLabel);
        btn.setContentDisplay(ContentDisplay.CENTER);
        btn.getStyleClass().add("button-letter");
        btn.setShape(hexagon);
        btn.setLayoutX(Xlayout);
        btn.setLayoutY(Ylayout);
        return btn;
    }

    List<String> shuffleList1 = new ArrayList<>();
    HashSet<Character> charr = new HashSet<>();
    ArrayList<Character> chars = new ArrayList<>(charr);

    public void createButtons() {
        shuffleList.clear();
        shuffle(pangram);
        String pngrm = pangram.get(0);

        for (int i = 0; i < pngrm.length(); i++) {
            charr.add(pngrm.charAt(i));
        }
        ArrayList<Character> chars
                = new ArrayList<>(charr);

        getBtnLbls.clear();

        // Create Buttons
        btn1 = createHexagonButton(String.valueOf(chars.get(0)), 170, 220);
        createEffect(btn1);
        getBtnLbls.add(btn1.getText());
        buttonPrintEvent(btn1, textField);

        btn2 = createHexagonButton(String.valueOf(chars.get(1)), 30, 220);
        createEffect(btn2);
        getBtnLbls.add(btn2.getText());
        buttonPrintEvent(btn2, textField);

        btn3 = createHexagonButton(String.valueOf(chars.get(2)), 30, 135);
        createEffect(btn3);
        getBtnLbls.add(btn3.getText());
        buttonPrintEvent(btn3, textField);

        btn4 = createHexagonButton(String.valueOf(chars.get(3)), 100, 95);
        createEffect(btn4);
        getBtnLbls.add(btn4.getText());
        buttonPrintEvent(btn4, textField);

        btn5 = createHexagonButton(String.valueOf(chars.get(4)), 100, 265);
        createEffect(btn5);
        getBtnLbls.add(btn5.getText());
        buttonPrintEvent(btn5, textField);

        btn6 = createHexagonButton(String.valueOf(chars.get(5)), 170, 135);
        createEffect(btn6);
        getBtnLbls.add(btn6.getText());
        buttonPrintEvent(btn6, textField);


        btnYellow = createHexagonButton(String.valueOf(chars.get(6)), 100, 180);
        btnYellow.getStyleClass().add("button-yellow");
        createEffect(btnYellow);
        buttonPrintEvent(btnYellow, textField);

    }


    //------------------------------------------------------------------------------------------------------------------
    // Create Text Field
    public TextField createPersontTxt(String str, int x, int y) {
        TextField textField2 = new TextField(str) {
            @Override
            public void replaceText(int start, int end, String text) {
                super.replaceText(start, end, text.toUpperCase());
            }
        };
        textField2.setLayoutX(x);
        textField2.setLayoutY(y);
        textField2.getStyleClass().add("text-field");
        textField2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (textField2.getText().equals("Create self puzzle...")) {
                    textField2.setText("");

                }
            }
        });

        textField2.setOnKeyTyped(event -> {
            String string = textField2.getText();
            if (string.length() > 7) {
                textField2.setText(string.substring(0, 7));
                textField2.positionCaret(string.length());
            }
        });

        textField2.textProperty().addListener((observablee, oldValuee, newValuee) -> {
            if (!newValuee.matches("[A-ZÜĞİŞÖÇ]")) {
                textField2.setText(newValuee.replaceAll("[[^A-ZÜĞİŞÖÇ]]", ""));
            }
        });

        textField2.setAlignment(Pos.CENTER);
        return textField2;
    }

    public TextField createTextField(String textLabel, int XLayout, int Ylayout) {
        // Upper Case Text
        TextField textField = new TextField(textLabel) {
            @Override
            public void replaceText(int start, int end, String text) {
                super.replaceText(start, end, text.toUpperCase());
            }
        };
        textField.setLayoutX(XLayout);
        textField.setLayoutY(Ylayout);
        textField.getStyleClass().add("text-field");

        // Mouse event for delete first text

        // text length check
        textField.setOnKeyTyped(event -> {
            String string = textField.getText();
            if (string.length() > 25) {
                textField.setText(string.substring(0, 25));
                textField.positionCaret(string.length());
            }
        });

        // check invalid character
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[A-ZÜĞİŞÖÇ]")) {
                textField.setText(newValue.replaceAll("[[^A-ZÜĞİŞÖÇ]]", ""));
            }
        });

        textField.setAlignment(Pos.CENTER);
        textField.setDisable(true);
        return textField;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Delete-Enter Button Process - EVENTS
    public void okButton(){
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                listView.getItems().clear();
                score = 0;
                scorelbl.setText("0");
                personalPuzzle();
            }
        });
    }
    public void deleteAll(Button btn, TextField textField) {
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                textField.setText("");
            }
        });
    }

    public void deleteButtonProcess(Button btnDelete, TextField textField) {
        btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (textField.getText().length() > 0) {
                    String text = textField.getText();
                    text = text.substring(0, text.length() - 1);
                    textField.setText(text);
                }
            }
        });
    }

    // Print Letter
    public void buttonPrintEvent(Button button, TextField textField) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (textField.getText().equals("Click a button...")) {
                    textField.setText(" ");
                }
                if (textField.getText().length() < 20) {
                    String text = textField.getText();
                    text += button.getText();
                    textField.setText(text);
                }
            }
        });
    }

    // Check Word Button
    public void enterButtonProcess() {
        btnEnter.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    checkWord(textField, listView, checkLabel, scorelbl, situation);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Change Puzzle
    public void changePuzzle(Button btn) {
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    getChildren().removeAll(listView, btnPuzzle, situation, scoreLabel, scorelbl, progressBar, btn1, btn2, btn3, btn4, btn5, btn6, btnDeleteAll, btnEnter, btnDelete, btnChange, textField, footer, btnYellow, label, checkLabel);
                    textField.setText("");
                    createPane();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Spelling Bee");
                alert.setHeaderText("Puzzle has changed.");
                alert.setContentText("Good Luck!");
                alert.showAndWait();
            }
        });
    }

    //------------------------------------------------------------------------------------------------------------------
    // Replace buttons
    public void changeProcess(Button btn) {
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                shuffle(getBtnLbls);
                btn1.setText(getBtnLbls.get(0));
                btn2.setText(getBtnLbls.get(4));
                btn3.setText(getBtnLbls.get(2));
                btn4.setText(getBtnLbls.get(3));
                btn5.setText(getBtnLbls.get(1));
                btn6.setText(getBtnLbls.get(5));

            }
        });
    }

    //------------------------------------------------------------------------------------------------------------------
    //Check Word Process
    public void checkWord(TextField textField, ListView listView, Label lbl, Label scoreLbl, Label situation) throws IOException {
        // Create list view for compare words
        puzzle.createPuzzle();
        boolean control = puzzle.isWordExist(listView1, textField);
        String text = textField.getText();
        int returnValue = isWordExist(listView, textField);

        String btnText = btnYellow.getText();
        char[] textArr = text.toCharArray();
        boolean bool = puzzle.checkText(textField, btnYellow);


        if (text.length() <= 3) {
            lbl.setText("***Too Short***");

        } else if (bool && control && textField.getText().length() > 3 && (returnValue == 1 || returnValue == 2)) {
            if (returnValue == 1) {
                listView.getItems().add(text);
                lbl.setText("***Matched***");
                textField.setText("");
                scoreProcess(text, scoreLbl, situation);
            } else if (returnValue == 2) {
                lbl.setText("***You already found it***");
            }
        } else {
            lbl.setText("***Did Not Match***");
        }
    }

    // is word already find
    public int isWordExist(ListView listView, TextField textField) {
        String item = textField.getText();
        int situation = 0;
        ObservableList<String> words = listView.getItems(); // Only here to clarify the code
        if (!words.contains(item)) {
            situation = 1;
        } else if (words.contains(item)) {
            situation = 2;
        }
        return situation;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Score Process
    String situationText;

    public void scoreProcess(String text, Label label, Label situation) {
        char[] charArr = text.toCharArray();
        String[] letters = text.split("");

        int count = 0;
        int len = text.length();
        int j = 0;

        getBtnLbls.add(btnYellow.getText());
        for (String lbl : getBtnLbls) {
            for (String letter : letters) {
                if (letter.equals(lbl)) {
                    j += 1;
                }
            }
            if (j == len && len >= 7) {
                score += 7;
            }
        }
        for (char ch : charArr) {
            count += 1;
            if (count > 3) {
                score += 1;
            }
        }
        if (score < 10) {
            situationText = "Bad!";
            situation.setText(situationText);
        }
        if (score >= 20) {
            situationText = "Good!";
            situation.setText(situationText);
        }
        if (score >= 30) {
            situationText = "Perfect!";
            situation.setText(situationText);
        }
        if (score >= 40) {
            situationText = "Awsome!";
            situation.setText(situationText);
        }
        if (score >= 70) {
            situationText = "Fantastic!";
            situation.setText(situationText);
        }
        if (score >= 100) {
            situationText = "Enormous!";
            situation.setText(situationText);
        }

        getBtnLbls.remove(btnYellow.getText());
        String scoreStr = String.valueOf(score);
        label.setText(scoreStr);
    }


    //------------------------------------------------------------------------------------------------------------------

    public void shuffle(List list) {
        Collections.shuffle(list);
    }

    public void personalPuzzle() {
        String txt = textField2.getText();
        HashSet<Character> charrs = new HashSet<>();
        ArrayList<Character> lst = new ArrayList<>(charrs);
        List<String> btn = new ArrayList<>();

        for (int i = 0; i < txt.length(); i++) {
            lst.add(txt.charAt(i));
        }

        if (lst.size() != 7) {
            checkLabel.setText("*** Please enter 7 Letter ***");
            btn1.setText("!");
            btn2.setText("!");
            btn3.setText("!");
            btn4.setText("!");
            btn5.setText("!");
            btn6.setText("!");
            btnYellow.setText("!");

        } else {
            checkLabel.setText("*** Puzzle has created ***");
            shuffle(lst);
            getBtnLbls.clear();

            btn1.setText(String.valueOf(lst.get(0)));
            btn2.setText(String.valueOf(lst.get(1)));
            btn3.setText(String.valueOf(lst.get(2)));
            btn4.setText(String.valueOf(lst.get(3)));
            btn5.setText(String.valueOf(lst.get(4)));
            btn6.setText(String.valueOf(lst.get(5)));
            btnYellow.setText(String.valueOf(lst.get(6)));

            btn.add(String.valueOf(lst.get(0)));
            btn.add(String.valueOf(lst.get(1)));
            btn.add(String.valueOf(lst.get(2)));
            btn.add(String.valueOf(lst.get(3)));
            btn.add(String.valueOf(lst.get(4)));
            btn.add(String.valueOf(lst.get(5)));
            btn.add(String.valueOf(lst.get(6)));
            getBtnLbls = btn;
            chars.clear();
        }

    }
}
