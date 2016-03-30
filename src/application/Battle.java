package application;

import application.Ships;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.Main;

public class Battle extends Ships {

  public boolean first = false;
  public boolean second = true;

  public int battleship1 = 1;
  public int cruiser1 = 2;
  public int destroyer1 = 3;
  public int boat1 = 4;
  public int battleship2 = 1;
  public int cruiser2 = 2;
  public int destroyer2 = 3;
  public int Boat2 = 4;
  public static int[][] newCheck = new int[SIZE][SIZE];
  Button[][] field = new Button[SIZE][SIZE];

  public Battle() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        field[i][j] = new Button();
      }
    }
  }

  public void CreateBattleField(Stage primaryStage) {
    Pane battle = new Pane();
    Scene scene4 = new Scene(battle, WEIGHT, HEIGHT);

    // Create Image
    Image imgage = new Image(getClass().getResourceAsStream("Fon.jpg"));
    ImageView img5 = new ImageView(imgage);
    img5.setFitHeight(HEIGHT);
    img5.setFitWidth(WEIGHT);
    battle.getChildren().add(img5);

    // Create button to return
    Rectangle bg1 = new Rectangle(130, 25, Color.DARKSALMON);
    bg1.setOpacity(0.5);
    bg1.setLayoutX(750);
    bg1.setLayoutY(550);
    FillTransition st = new FillTransition(Duration.seconds(0.5), bg1);
    bg1.setOnMouseEntered(event2 -> {
      st.setFromValue(Color.YELLOW);
      st.setToValue(Color.CORNSILK);
      st.setCycleCount(Animation.INDEFINITE);
      st.setAutoReverse(true);
      st.play();
    });
    bg1.setOnMouseExited(event3 -> {
      st.stop();
      bg1.setFill(Color.DARKSALMON);
    });
    bg1.setOnMouseClicked(event1 -> {
      Main m = new Main();
      m.start(primaryStage);
    });

    Text text = new Text("НАЗАД");
    text.setFill(Color.FIREBRICK);

    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(785);
    text.setLayoutY(568);
    battle.getChildren().addAll(text, bg1);

    fieldName(battle);
    BeginBattle(battle);

    primaryStage.setScene(scene4);
    primaryStage.show();
  }

  public void fieldName(Pane battle) {
    Button[] symbols = new Button[SIZE];
    Button[] numbers = new Button[SIZE + 1];
    Button[] symbols2 = new Button[SIZE];
    Button[] numbers2 = new Button[SIZE + 1];
    // Part for symbols
    for (int i = 0; i < SIZE; i++) {
      symbols[i] = new Button();
      symbols2[i] = new Button();
      double shift = 1.5 + i;
      char[] name = new char[SIZE];
      name[0] = 'A';
      name[1] = 'Б';
      name[2] = 'В';
      name[3] = 'Г';
      name[4] = 'Д';
      name[5] = 'Е';
      name[6] = 'Ж';
      name[7] = 'З';
      name[8] = 'И';
      name[9] = 'К';

      String valueOfchar = String.valueOf(name[i]);
      symbols[i].setText(valueOfchar);
      symbols[i].setMinSize(30, 30);
      symbols[i].setLayoutX(15);
      symbols[i].setStyle("-fx-base: lightblue");
      symbols[i].setLayoutY(30 * shift);
      symbols2[i].setText(valueOfchar);
      symbols2[i].setMinSize(30, 30);
      symbols2[i].setLayoutX(515);
      symbols2[i].setStyle("-fx-base: lightblue");
      symbols2[i].setLayoutY(30 * shift);
      battle.getChildren().addAll(symbols[i], symbols2[i]);
    }

    // Part for numbers
    for (int i = 0; i < 11; i++) {
      numbers[i] = new Button();
      numbers2[i] = new Button();
      double shift = i + 0.5;
      char[] name = new char[11];
      name[0] = ' ';
      name[1] = '1';
      name[2] = '2';
      name[3] = '3';
      name[4] = '4';
      name[5] = '5';
      name[6] = '6';
      name[7] = '7';
      name[8] = '8';
      name[9] = '9';
      name[10] = ' ';

      // Set Number
      if (i != 10) {
        String valueOfchar = String.valueOf(name[i]);
        numbers[i].setText(valueOfchar);
        numbers2[i].setText(valueOfchar);
      } else {
        numbers[10].setText("10");
        numbers2[10].setText("10");
      }
      numbers[i].setMinSize(30, 30);
      numbers[i].setStyle("-fx-base: lightblue");
      numbers[i].setLayoutY(15);
      numbers[i].setLayoutX(30 * shift);
      numbers2[i].setMinSize(30, 30);
      numbers2[i].setStyle("-fx-base: lightblue");
      numbers2[i].setLayoutY(15);
      numbers2[i].setLayoutX(30 * shift + 500);
      battle.getChildren().addAll(numbers[i], numbers2[i]);
    }
  }

  public void BeginBattle(Pane battle) {
    Button[][] firstField = new Button[SIZE][SIZE];
    Button[][] secondField = new Button[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        double k = i + 1.5;
        double l = j + 1.5;

        firstField[i][j] = new Button();
        secondField[i][j] = new Button();

        firstField[i][j].setStyle("-fx-base: lightgreen");
        secondField[i][j].setStyle("-fx-base: lightgreen");
        firstField[i][j].setMinSize(30, 30);
        secondField[i][j].setMinSize(30, 30);
        firstField[i][j].setLayoutX(30 * k);
        firstField[i][j].setLayoutY(30 * l);
        secondField[i][j].setLayoutX(30 * k + 500);
        secondField[i][j].setLayoutY(30 * l);
        battle.getChildren().addAll(firstField[i][j], secondField[i][j]);
      }
    }
    Text win = new Text();
    win.setLayoutX(330);
    win.setLayoutY(250);
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    win.setFill(Color.DARKSALMON);
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        int x = i;
        int y = j;

        // Battle
        firstField[i][j].setOnMouseClicked(eventSIZE -> {
          if (first == false) {
            newCheck = checkForShipFirst;
            if (newCheck[x][y] == -1) {
              second = false;
              first = false;
            } else {
              if (newCheck[x][y] != 0) {

                if (boat1 == 0 && battleship1 == 0 && cruiser1 == 0 && destroyer1 == 0) {
                  win.setText("Второй Игрок Победил!!!");
                  Image imgage = new Image(getClass().getResourceAsStream("Fon.jpg"));
                  ImageView img5 = new ImageView(imgage);
                  img5.setFitHeight(HEIGHT);
                  img5.setFitWidth(WEIGHT);
                  battle.getChildren().addAll(img5, win);
                  return;
                }

                second = false;
                first = false;
                field = firstField;
                boat1 = boatCheck(field, x, y, boat1); // Boat
                battleship1 = destroyerCheck(field, x, y, destroyer1); // Destroyer
                cruiser1 = cruiserCheck(field, x, y, cruiser1); // Cruiser
                destroyer1 = battleshipCheck(field, x, y, battleship1); // Battleship
              } else {
                firstField[x][y].setStyle("-fx-base: lightgrey");
                firstField[x][y].setOpacity(0);
                newCheck[x][y] = -1;
                second = true;
                first = true;
              }
            }
          }
        });

        secondField[i][j].setOnMouseClicked(event11 -> {
          if (second) {
            if (Boat2 == 0 && battleship2 == 0 && cruiser2 == 0 && destroyer2 == 0) {
              win.setText("Первый Игрок Победил!!!");
              Image imgage = new Image(getClass().getResourceAsStream("Fon.jpg"));
              ImageView img5 = new ImageView(imgage);
              img5.setFitHeight(HEIGHT);
              img5.setFitWidth(WEIGHT);
              battle.getChildren().addAll(img5, win);
              return;
            }
            newCheck = checkForShipSecond;
            if (newCheck[x][y] == -1) {
              second = true;
              first = true;
            } else {
              if (newCheck[x][y] != 0) {
                second = true;
                first = true;
                field = secondField;
                Boat2 = boatCheck(field, x, y, Boat2); // Boat
                battleship2 = destroyerCheck(field, x, y, destroyer2); // Destroyer
                cruiser2 = cruiserCheck(field, x, y, cruiser2); // Cruiser
                destroyer2 = battleshipCheck(field, x, y, battleship2); // Battleship
              } else {
                secondField[x][y].setStyle("-fx-base: lightgrey");
                secondField[x][y].setOpacity(0);
                newCheck[x][y] = -1;
                second = false;
                first = false;
              }
            }
          }
        });
      }
    }
  }

  public int battleshipCheck(Button[][] field, int x, int y, int battleship) {
    int count = 0;
    if (battleship == 0) {
      return 0;
    }
    if (newCheck[x][y] == 4) {
      for (int k = x; k < x + 4; k++) {
        if (k < SIZE && newCheck[k][y] == 8) {
          count++;
        }
      }
      if (count < 3) {
        for (int k = x - 4; k < x; k++) {
          if (k >= 0 && newCheck[k][y] == 8) {
            count++;
          }
        }
      }
      if (count < 3) {
        for (int k = y - 4; k < y; k++) {
          if (k >= 0 && newCheck[x][k] == 8) {
            count++;
          }
        }
      }
      if (count < 3) {
        for (int k = y; k < y + 4; k++) {
          if (k < SIZE && newCheck[x][k] == 8) {
            count++;
          }
        }
      }
      if (count == 3) {
        System.out.println("Четырехпалубный"); // Use for check in console
        battleship--;
        count = 0;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: lightblue");
    }
    return battleship;
  }

  public int cruiserCheck(Button[][] field, int x, int y, int cruiser) {
    int count = 0;
    if (cruiser == 0) {
      return 0;
    }
    if (newCheck[x][y] == 3) {
      for (int k = x; k < x + 3; k++) {
        if (k < SIZE && newCheck[k][y] == 6) {
          count++;
        }
      }
      if (count < 2) {
        for (int k = x - 3; k < x; k++) {
          if (k >= 0 && newCheck[k][y] == 6) {
            count++;
          }
        }
      }
      if (count < 2) {
        for (int k = y - 3; k < y; k++) {
          if (k >= 0 && newCheck[x][k] == 6) {
            count++;
          }
        }
      }
      if (count < 2) {
        for (int k = y; k < y + 3; k++) {
          if (k < SIZE && newCheck[x][k] == 6) {
            count++;
          }
        }
      }
      if (count == 2) {
        System.out.println("Трехпалубный"); ////
        cruiser--;
        count = 0;
      }
      newCheck[x][y] = 6;
      field[x][y].setStyle("-fx-base: green");

    }
    return cruiser;
  }

  public int destroyerCheck(Button[][] field, int x, int y, int destroyer) {
    int count = 0;
    if (destroyer == 0) {
      return 0;
    }
    if (newCheck[x][y] == 2) {
      for (int k = x; k < x + 2; k++) {
        if (k < SIZE && newCheck[k][y] == 5) {
          count++;
        }
      }
      if (count < 1) {
        for (int k = x - 2; k < x; k++) {
          if (k >= 0 && newCheck[k][y] == 5) {
            count++;
          }
        }
      }
      if (count < 1) {
        for (int k = y - 2; k < y; k++) {
          if (k >= 0 && newCheck[x][k] == 5) {
            count++;
          }
        }
      }
      if (count < 1) {
        for (int k = y; k < y + 2; k++) {
          if (k < SIZE && newCheck[x][k] == 5) {
            count++;
          }
        }
      }
      if (count == 1) {
        System.out.println("Двухпалубный"); ////
        destroyer--;
        count = 0;
      }
      newCheck[x][y] = 5;
      field[x][y].setStyle("-fx-base: lightgrey");
    }
    return destroyer;
  }

  public int boatCheck(Button[][] field, int x, int y, int boat) {
    if (boat == 0) {
      return 0;
    }
    if (newCheck[x][y] == 1) {
      newCheck[x][y] = 9;
      System.out.println("Однопалубный"); ////
      field[x][y].setStyle("-fx-base: yellow");
      boat--;
    }
    return boat;
  }
}
