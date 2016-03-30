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

import java.security.SecureRandom;

import application.Main;

public class BattleWithComp extends Ships {

  public boolean first = false;
  public boolean second = true;

  public int battleship1 = 1;
  public int cruiser1 = 2;
  public int destroyer1 = 3;
  public int boat1 = 4;
  public int battleship2 = 1;
  public int cruiser2 = 2;
  public int destroyer2 = 3;
  public int boat2 = 4;
  public static int[][] newCheck = new int[SIZE][SIZE];
  Button[][] field = new Button[SIZE][SIZE];

  BattleWithComp() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        field[i][j] = new Button();
      }
    }
  }

  public void createBattle(Stage primaryStage) {
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

    // Create field for text
    Rectangle textFiel1 = new Rectangle(330, 150, Color.DARKSALMON);

    textFiel1.setOpacity(0.5);
    textFiel1.setLayoutX(45);
    textFiel1.setLayoutY(390);

    Battle b = new Battle();
    b.fieldName(battle);
    battle(battle);

    primaryStage.setScene(scene4);
    primaryStage.show();
  }

  public void battle(Pane battle) {
    // Create field for 2 players
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
    win.setLayoutX(350);
    win.setLayoutY(250);
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    win.setFill(Color.DARKSALMON);
    // Battle
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        int x = i;
        int y = j;
        secondField[i][j].setOnMouseClicked(eventSIZE -> {
          if (first == false) {
            newCheck = checkForShipSecond;
            if (newCheck[x][y] == -1) {
              second = false;
              first = false;
            } else {
              if (newCheck[x][y] != 0) {
                if (boat1 == 0 && battleship1 == 0 && cruiser1 == 0 && destroyer1 == 0) {
                  System.out.println("First");
                  win.setText("Компьютер победил!!!");
                  Image imgage = new Image(getClass().getResourceAsStream("Fon.jpg"));
                  ImageView img5 = new ImageView(imgage);
                  img5.setFitHeight(HEIGHT);
                  img5.setFitWidth(WEIGHT);
                  battle.getChildren().addAll(img5, win);
                  return;
                }
                second = false;
                first = false;
                field = secondField;
                boat1 = boatCheck(field, x, y, boat1); // Boat
                battleship1 = destroyerCheck(field, x, y, destroyer1); // Destroyer
                cruiser1 = cruiserCheck(field, x, y, cruiser1); // Cruiser
                destroyer1 = battleshipCheck(field, x, y, battleship1, 1); // Battleship
              }
              if (newCheck[x][y] == 0) {
                secondField[x][y].setStyle("-fx-base: lightgrey");
                secondField[x][y].setOpacity(0);
                newCheck[x][y] = -1;
                second = true;
                first = true;
              }
            }
          }

          if (second) {
            if (boat2 == 0 && battleship2 == 0 && cruiser2 == 0 && destroyer2 == 0) {
              System.out.println("Second");
              win.setText("Игрок Победил!!!");
              Image imgage = new Image(getClass().getResourceAsStream("Fon.jpg"));
              ImageView img5 = new ImageView(imgage);
              img5.setFitHeight(HEIGHT);
              img5.setFitWidth(WEIGHT);
              battle.getChildren().addAll(img5, win);
              return;
            }
            SecureRandom rand = new SecureRandom();
            int X = (rand.nextInt(9));
            int Y = (rand.nextInt(9));
            newCheck = checkForShipFirst;
            if (newCheck[X][Y] == -1) {
              second = true;
              first = true;
            } else {
              if (newCheck[X][Y] != 0) {
                second = true;
                first = true;
                field = firstField;
                boat2 = boatCheck(field, X, Y, boat1);
                battleship2 = destroyerCheck(field, X, Y, destroyer1);
                cruiser2 = cruiserCheck(field, X, Y, cruiser2);
                destroyer2 = battleshipCheck(field, X, Y, battleship2, 2);
              }
              if (newCheck[X][Y] == 0) {
                firstField[X][Y].setStyle("-fx-base: lightgrey");
                firstField[X][Y].setOpacity(0);
                newCheck[X][Y] = -1;
                second = false;
                first = false;
              }
            }
          }
        });
      }
    }
  }

  // System.out.println(...) use for check in console
  public int battleshipCheck(Button[][] field, int x, int y, int Battleship, int stroke) {
    int count = 0;
    if (Battleship == 0) {
      return 0;
    }
    if (newCheck[x][y] == 4 && stroke == 1) {

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
        System.out.println("Четырехпалубный");
        Battleship--;
        count = 0;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: blue");
    }

    if (newCheck[x][y] == 4 && stroke == 2) {
      for (int k = x + 1; k < x + 4; k++) {
        if (k < SIZE && newCheck[k][y] == 4) {
          count++;
          newCheck[k][y] = 8;
          field[k][y].setStyle("-fx-base: blue");
        }
      }
      if (count < 3) {
        for (int k = x - 4; k < x; k++) {
          if (k >= 0 && newCheck[k][y] == 4) {
            count++;
            newCheck[k][y] = 8;
            field[k][y].setStyle("-fx-base: blue");
          }
        }
      }
      if (count < 3) {
        for (int k = y - 4; k < y; k++) {
          if (k >= 0 && newCheck[x][k] == 4) {
            count++;
            newCheck[x][k] = 8;
            field[x][k].setStyle("-fx-base: blue");
          }
        }
      }
      if (count < 3) {
        for (int k = y + 1; k < y + 4; k++) {
          if (k < SIZE && newCheck[x][k] == 4) {
            count++;
            newCheck[x][k] = 8;
            field[x][k].setStyle("-fx-base: blue");
          }
        }
      }
      if (count == 3) {
        System.out.println("Четырехпалубный"); ////
        Battleship--;
        count = 0;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: blue");
      field[x][y].setOpacity(0.5);
      System.out.println(count);
    }
    return Battleship;
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


