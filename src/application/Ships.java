package application;

import application.Battle;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.security.SecureRandom;

public class Ships {
  public final static int WEIGHT = 900;
  public final static int HEIGHT = 600;
  public final static int SIZE = 10;
  private static final int BATTLESHIP = 1;
  private static final int CRUISER = 2;
  private static final int DESTROYER = 3;
  private static final int BOAT = 4;
  public static int[][] checkForShip = new int[SIZE][SIZE];
  public static int[][] checkForShipFirst = new int[SIZE][SIZE];
  public static int[][] checkForShipSecond = new int[SIZE][SIZE];
  public int nextPlayer = 1;
  public int choice = 0;

  public Ships() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        checkForShip[i][j] = 0;
      }
    }
  }

  public void createField(Stage primaryStage, int buttle) {

    Pane shipsAndField = new Pane();
    Scene scene3 = new Scene(shipsAndField, WEIGHT, HEIGHT);
    // Create image for background
    Image imgage = new Image(getClass().getResourceAsStream("Fon.jpg"));
    ImageView img5 = new ImageView(imgage);
    img5.setFitHeight(HEIGHT);
    img5.setFitWidth(WEIGHT);
    shipsAndField.getChildren().add(img5);
    // Create field
    Button[][] ship = new Button[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        double k = i + 1.8;
        double l = j + 1.8;
        ship[i][j] = new Button();
        ship[i][j].setStyle("-fx-base: lightgreen");
        ship[i][j].setMinSize(40, 40);
        ship[i][j].setLayoutX(40 * k);
        ship[i][j].setLayoutY(40 * l);
        shipsAndField.getChildren().addAll(ship[i][j]);
      }
    }
    fieldName(shipsAndField);

    /// Create Menu Button
    MenuItem battleship = new MenuItem("ËÈÍÊÎÐ(õ1)");
    MenuItem cruiser = new MenuItem("ÊÐÅÉÑÅÐ(õ2)");
    MenuItem destroyer = new MenuItem("ÝÑÌÈÍÅÖ(õ3)");
    MenuItem boat = new MenuItem("ÊÀÒÅÐ(õ4)");
    MenuItem random = new MenuItem("CËÓ×ÀÉÍÀß ÐÀÑÑÒÀÍÎÂÊÀ");
    Menu mainMenu = new Menu(battleship, cruiser, destroyer, boat, random);
    MenuBox menuBox = new MenuBox(mainMenu);
    menuBox.setVisible(true);

    shipsAndField.getChildren().addAll(menuBox);

    Rectangle choice = new Rectangle(130, 25, Color.DARKSALMON);
    choice.setOpacity(0.5);
    choice.setLayoutX(750);
    choice.setLayoutY(550);
    FillTransition st = new FillTransition(Duration.seconds(0.5), choice);
    choice.setOnMouseEntered(event2 -> {
      st.setFromValue(Color.YELLOW);
      st.setToValue(Color.CORNSILK);
      st.setCycleCount(Animation.INDEFINITE);
      st.setAutoReverse(true);
      st.play();
    });
    choice.setOnMouseExited(event3 -> {
      st.stop();
      choice.setFill(Color.DARKSALMON);
    });

    choice.setOnMouseClicked(event1 -> {
      if (buttle == 0) {
        if (nextPlayer == 2) {
          rememberItem();
          Battle battle = new Battle();
          battle.CreateBattleField(primaryStage);
        }
        if (nextPlayer == 1) {
          rememberItem();
          createField(primaryStage, 0);
          nextPlayer = 2;
        }
      }
      if (buttle == 2) {
        if (nextPlayer == 2) {
          rememberItem();
          BattleWithComp battle = new BattleWithComp();
          battle.createBattle(primaryStage);
        }
        if (nextPlayer == 1) {

          rememberItem();
          nextPlayer = 2;
          fifthButton(random, ship, 1);
          Text text = new Text("Êîìïüþòåð \nðàññòàâèë\n êîðàáëè");
          text.setFill(Color.FIREBRICK);
          text.setOpacity(0.7);
          text.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
          text.setLayoutX(170);
          text.setLayoutY(210);
          shipsAndField.getChildren().addAll(text);
        }
      }
    });

    Text text = new Text("ÂÏÅÐÅÄ");
    text.setFill(Color.FIREBRICK);
    text.setOpacity(0.7);
    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(785);
    text.setLayoutY(568);
    shipsAndField.getChildren().addAll(choice, text);

    firstButton(battleship, ship, buttle);
    secondButton(cruiser, ship, buttle);
    thirdButton(destroyer, ship, buttle);
    forthButton(boat, ship, buttle);
    random.setOnMouseClicked(event6 -> {
      fifthButton(random, ship, buttle);
    });

    primaryStage.setScene(scene3);
    primaryStage.show();
  }

  // Occupancy ships
  public void firstButton(MenuItem battleship, Button[][] ship, int buttle) {
    battleship.setOnMouseClicked(event12 -> {
      // Delete ship
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (checkForShip[i][j] == 4) {
            checkForShip[i][j] = 0;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = 0; i < BATTLESHIP; i++) {
        i = setBattleship(ship, i, buttle);
      }
    });
  }

  public void secondButton(MenuItem cruiser, Button[][] ship, int buttle) {
    cruiser.setOnMouseClicked(event12 -> {
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (checkForShip[i][j] == 3) {
            checkForShip[i][j] = 0;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = 0; i < CRUISER; i++) {
        i = setCruiser(ship, i, buttle);
      }
    });
  }

  public void thirdButton(MenuItem destroyer, Button[][] ship, int buttle) {
    destroyer.setOnMouseClicked(event20 -> {
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (checkForShip[i][j] == 2) {
            checkForShip[i][j] = 0;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = 0; i < DESTROYER; i++) {
        i = setDestroyer(ship, i, buttle);
      }
    });
  }

  public void forthButton(MenuItem boat, Button[][] ship, int buttle) {
    boat.setOnMouseClicked(event21 -> {
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (checkForShip[i][j] == 1) {
            checkForShip[i][j] = 0;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = 1; i < BOAT + 1; i++) {
        i = setBoat(ship, i, buttle);
      }
    });
  }

  public void fifthButton(MenuItem random, Button[][] ship, int buttle) {

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        checkForShip[i][j] = 0;
        ship[i][j].setStyle("-fx-base: lightgreen");
      }
    }
    for (int i = 0; i < BATTLESHIP; i++) {
      i = setBattleship(ship, i, buttle);
    }
    for (int i = 0; i < CRUISER; i++) {
      i = setCruiser(ship, i, buttle);
    }
    for (int i = 0; i < DESTROYER; i++) {
      i = setDestroyer(ship, i, buttle);
    }
    for (int i = 1; i < BOAT + 1; i++) {
      i = setBoat(ship, i, buttle);
    }
  }

  public void fieldName(Pane shipsAndField) {
    // Fields name
    Button[] symbols = new Button[SIZE];
    Button[] numbers = new Button[11];
    // Part for symbols
    for (int i = 0; i < SIZE; i++) {
      symbols[i] = new Button();
      double shift = 1.8 + i;
      char[] name = new char[SIZE];
      name[0] = 'A';
      name[1] = 'Á';
      name[2] = 'Â';
      name[3] = 'Ã';
      name[4] = 'Ä';
      name[5] = 'Å';
      name[6] = 'Æ';
      name[7] = 'Ç';
      name[8] = 'È';
      name[9] = 'Ê';

      String valueOfchar = String.valueOf(name[i]);
      symbols[i].setText(valueOfchar);
      symbols[i].setMinSize(40, 40);
      symbols[i].setLayoutX(32);
      symbols[i].setStyle("-fx-base: lightblue");
      symbols[i].setLayoutY(40 * shift);
      shipsAndField.getChildren().addAll(symbols[i]);
    }

    // Part for numbers
    for (int i = 0; i < 11; i++) {
      numbers[i] = new Button();
      double shift = i + 0.8;
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
      name[SIZE] = ' ';

      // Set Number
      if (i != 10) {
        String valueOfchar = String.valueOf(name[i]);
        numbers[i].setText(valueOfchar);
      } else {
        numbers[10].setText("10");
      }
      numbers[i].setMinSize(40, 40);
      numbers[i].setStyle("-fx-base: lightblue");
      numbers[i].setLayoutY(32);
      numbers[i].setLayoutX(40 * shift);
      shipsAndField.getChildren().addAll(numbers[i]);
    }
  }

  // In next part you will meet large blocks of "if(){...}"
  // This is necessary, because I should check all position,
  // where boats may be arranged.Break it to function probably does
  // not make sense, because each test uses a different location.

  public int setBattleship(Button[][] ship, int i, int buttle) {
    SecureRandom rand = new SecureRandom();
    int number = 4;
    int choice = (rand.nextInt(2));
    int x = (rand.nextInt(SIZE - 1)); // Coordinates
    int y = (rand.nextInt(SIZE - 1)); // Coordinates
    // Vertical ship
    if (choice == 0) {

      if (y + number > SIZE) {
        i = i - 1;
      } else
        for (int k = y; k < y + number; k++) {
          setShipColor(ship, x, k, buttle);
          checkForShip[x][k] = number;
        }
      // Horizontally ship
    } else if (choice == 1) {
      if (x + number > SIZE) {
        i = i - 1;
      } else
        for (int k = x; k < x + number; k++) {
          setShipColor(ship, k, y, buttle);
          checkForShip[k][y] = number;
        }
    }
    return i;
  }

  public int setCruiser(Button[][] ship, int i, int buttle) {
    SecureRandom rand = new SecureRandom();
    int number = 3;
    int choice = (rand.nextInt(2));
    int x = (rand.nextInt(SIZE));
    int y = (1 + rand.nextInt(6));
    // vertical
    if (choice == 0) {
      if (y + number > SIZE) {
        i = i - 1;
      } else {
        int flag = 0;
        if (x == 0) {
          for (int k = y - 1; k < y + 4; k++) {
            int ch1 = x + 1;

            if (checkForShip[x][k] == 1 || checkForShip[ch1][k] == 1 || checkForShip[x][k] == 2
                || checkForShip[x][k] == 4 || checkForShip[ch1][k] == 2 || checkForShip[ch1][k] == 4
                || checkForShip[ch1][k] == 3 || checkForShip[x][k] == 3) {
              i = i - 1;
              flag = 1;
              k = y + 4;
            }
          }
          if (flag == 0) {
            for (int k = y; k < y + number; k++) {
              setShipColor(ship, x, k, buttle);
              checkForShip[x][k] = number;
            }
          }
        } else {
          if (x == 9) {
            for (int k = y - 1; k < y + 4; k++) {
              int ch1 = x - 1;

              if (checkForShip[x][k] == 1 || checkForShip[ch1][k] == 1 || checkForShip[x][k] == 2
                  || checkForShip[x][k] == 4 || checkForShip[ch1][k] == 2 || checkForShip[x][k] == 4
                  || checkForShip[ch1][k] == 4 || checkForShip[ch1][k] == 3) {
                i = i - 1;
                flag = 1;
                k = y + 4;
              }
            }
            if (flag == 0) {
              for (int k = y; k < y + number; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = number;
              }
            }
          } else {

            for (int k = y - 1; k < y + 4; k++) {
              int ch1 = x + 1;
              int ch2 = x - 1;

              if (checkForShip[x][k] == 1 || checkForShip[ch1][k] == 1 || checkForShip[ch2][k] == 1
                  || checkForShip[x][k] == 2 || checkForShip[ch1][k] == 2
                  || checkForShip[ch2][k] == 2 || checkForShip[x][k] == 4
                  || checkForShip[ch1][k] == 4 || checkForShip[ch2][k] == 4
                  || checkForShip[x][k] == number || checkForShip[ch1][k] == number
                  || checkForShip[ch2][k] == number) {
                i = i - 1;
                flag = 1;
                k = y + 4;
              }
            }
            if (flag == 0) {
              for (int k = y; k < y + number; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = number;
              }
            }
          }
        }
      }
    }
    // Horizontally ship
    if (choice == 1) {
      x = (1 + rand.nextInt(6));
      y = (rand.nextInt(SIZE));
      if (x + number > SIZE) {
        i = i - 1;
      } else {
        int flag = 0;
        if (y == 0) {
          for (int k = x - 1; k < x + 4; k++) {
            int ch1 = y + 1;

            if (checkForShip[k][y] == 1 || checkForShip[k][ch1] == 1 || checkForShip[k][y] == 2
                || checkForShip[k][ch1] == 2 || checkForShip[k][y] == 3 || checkForShip[k][ch1] == 3
                || checkForShip[k][y] == 4 || checkForShip[k][ch1] == 4) {
              i = i - 1;
              flag = 1;
              k = x + 4;
            }
          }
          if (flag == 0) {
            for (int k = x; k < x + number; k++) {
              setShipColor(ship, k, y, buttle);
              checkForShip[k][y] = number;
            }
          }
        } else {
          if (y == 9) {
            for (int k = x - 1; k < x + 4; k++) {
              int ch1 = y - 1;

              if (checkForShip[k][y] == 1 || checkForShip[k][ch1] == 1 || checkForShip[k][y] == 2
                  || checkForShip[k][ch1] == 2 || checkForShip[k][y] == number
                  || checkForShip[k][y] == 4 || checkForShip[k][ch1] == number
                  || checkForShip[k][ch1] == 4) {
                i = i - 1;
                flag = 1;
                k = x + 4;
              }
            }
            if (flag == 0) {
              for (int k = x; k < x + number; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = number;
              }
            }
          } else {
            for (int k = x - 1; k < x + 4; k++) {
              int ch1 = y + 1;
              int ch2 = y - 1;

              if (checkForShip[k][y] == 1 || checkForShip[k][ch1] == 1 || checkForShip[k][ch2] == 1
                  || checkForShip[k][y] == 2 || checkForShip[k][ch1] == 2
                  || checkForShip[k][ch2] == 2 || checkForShip[k][y] == 3
                  || checkForShip[k][ch1] == 3 || checkForShip[k][ch2] == 3
                  || checkForShip[k][y] == 4 || checkForShip[k][ch1] == 4
                  || checkForShip[k][ch2] == 4) {
                i = i - 1;
                flag = 1;
                k = x + 4;
              }
            }

            if (flag == 0) {
              for (int k = x; k < x + number; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = number;
              }
            }
          }
        }
      }
    }
    return i;
  }

  public int setDestroyer(Button[][] ship, int i, int buttle) {
    SecureRandom rand = new SecureRandom();
    int number = 2;
    int choice = (rand.nextInt(number));
    int x = (rand.nextInt(SIZE)); // x
    int y = (1 + rand.nextInt(7)); // y

    if (choice == 0) {
      if (y + number > SIZE) {
        i = i - 1;
      } else {
        int flag = 0;
        if (x == 0) {

          for (int k = y - 1; k < y + 3; k++) {
            int ch1 = x + 1;

            if (checkForShip[x][k] == 1 || checkForShip[ch1][k] == 1 || checkForShip[x][k] == 2
                || checkForShip[ch1][k] == 2 || checkForShip[x][k] == 3 || checkForShip[ch1][k] == 3
                || checkForShip[x][k] == 4 || checkForShip[ch1][k] == 4) {
              i = i - 1;
              flag = 1;
              k = y + 3;
            }
          }
          if (flag == 0) {
            for (int k = y; k < y + number; k++) {
              setShipColor(ship, x, k, buttle);
              checkForShip[x][k] = number;
            }
          }
        } else {
          if (x == 9) {
            for (int k = y - 1; k < y + 3; k++) {
              int ch1 = x - 1;

              if (checkForShip[x][k] == 1 || checkForShip[ch1][k] == 1
                  || checkForShip[x][k] == number || checkForShip[ch1][k] == 2
                  || checkForShip[x][k] == 3 || checkForShip[ch1][k] == 4
                  || checkForShip[ch1][k] == 3 || checkForShip[x][k] == 4) {
                i = i - 1;
                flag = 1;
                k = y + 3;
              }
            }
            if (flag == 0) {
              for (int k = y; k < y + number; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = number;
              }
            }
          } else {
            for (int k = y - 1; k < y + 3; k++) {
              int ch1 = x + 1;
              int chnumber = x - 1;

              if (checkForShip[x][k] == 1 || checkForShip[ch1][k] == 1
                  || checkForShip[chnumber][k] == 1 || checkForShip[x][k] == number
                  || checkForShip[ch1][k] == number || checkForShip[chnumber][k] == number
                  || checkForShip[x][k] == 3 || checkForShip[ch1][k] == 3
                  || checkForShip[chnumber][k] == 3 || checkForShip[x][k] == 4
                  || checkForShip[ch1][k] == 4 || checkForShip[chnumber][k] == 4) {
                i = i - 1;
                flag = 1;
                k = y + 3;
              }
            }
            if (flag == 0) {
              for (int k = y; k < y + number; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = number;
              }
            }
          }
        }
      }
    }

    if (choice == 1) {
      x = (1 + rand.nextInt(7));
      y = (rand.nextInt(SIZE));
      if (x + number > SIZE) {
        i = i - 1;
      } else {
        int flag = 0;
        if (y == 0) {
          for (int k = x - 1; k < x + 3; k++) {
            int ch1 = y + 1;

            if (checkForShip[k][y] == 1 || checkForShip[k][ch1] == 1 || checkForShip[k][y] == number
                || checkForShip[k][ch1] == number || checkForShip[k][y] == 3
                || checkForShip[k][ch1] == 3 || checkForShip[k][y] == 4
                || checkForShip[k][ch1] == 4) {
              i = i - 1;
              flag = 1;
              k = x + 3;
            }
          }
          if (flag == 0) {
            for (int k = x; k < x + number; k++) {
              setShipColor(ship, k, y, buttle);
              checkForShip[k][y] = number;
            }
          }
        } else {
          if (y == 9) {
            for (int k = x - 1; k < x + 3; k++) {
              int ch1 = y - 1;

              if (checkForShip[k][y] == 1 || checkForShip[k][ch1] == 1
                  || checkForShip[k][y] == number || checkForShip[k][ch1] == number
                  || checkForShip[k][y] == 3 || checkForShip[k][ch1] == 3 || checkForShip[k][y] == 4
                  || checkForShip[k][ch1] == 4) {
                i = i - 1;
                flag = 1;
                k = x + 3;
              }
            }
            if (flag == 0) {
              for (int k = x; k < x + number; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = number;
              }
            }
          } else {
            for (int k = x - 1; k < x + 3; k++) {
              int ch1 = y + 1;
              int chnumber = y - 1;

              if (checkForShip[k][y] == 1 || checkForShip[k][ch1] == 1
                  || checkForShip[k][chnumber] == 1 || checkForShip[k][y] == number
                  || checkForShip[k][ch1] == number || checkForShip[k][chnumber] == number
                  || checkForShip[k][y] == 3 || checkForShip[k][ch1] == 3
                  || checkForShip[k][chnumber] == 3 || checkForShip[k][y] == 4
                  || checkForShip[k][ch1] == 4 || checkForShip[k][chnumber] == 4) {
                i = i - 1;
                flag = 1;
                k = x + 3;
              }
            }
            if (flag == 0) {
              for (int k = x; k < x + number; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = number;
              }
            }
          }
        }
      }
    }
    return i;
  }

  public int setBoat(Button[][] ship, int i, int buttle) {
    SecureRandom rand = new SecureRandom();
    int x = (rand.nextInt(SIZE - 1));
    int y = (rand.nextInt(SIZE - 1));

    if (x == 0 && y == 0) {
      int ch1 = x + 1;
      int ch3 = y + 1;
      if (checkForShip[x][y] == 1 || checkForShip[ch1][y] == 1 || checkForShip[x][ch3] == 1
          || checkForShip[ch1][ch3] == 1 || checkForShip[x][y] == 2 || checkForShip[ch1][y] == 2
          || checkForShip[x][ch3] == 2 || checkForShip[ch1][ch3] == 2 || checkForShip[x][y] == 3
          || checkForShip[ch1][y] == 3 || checkForShip[x][ch3] == 3 || checkForShip[ch1][ch3] == 3
          || checkForShip[x][y] == 4 || checkForShip[ch1][y] == 4 || checkForShip[x][ch3] == 4
          || checkForShip[ch1][ch3] == 4) {
        i = i - 1;
      } else {
        setShipColor(ship, x, y, buttle);
        checkForShip[x][y] = 1;
      }
    } else {
      if (x == 0) {
        int ch1 = x + 1;
        int ch3 = y + 1;
        int ch4 = y - 1;
        if (checkForShip[x][y] == 1 || checkForShip[ch1][y] == 1 || checkForShip[x][ch3] == 1
            || checkForShip[x][ch4] == 1 || checkForShip[ch1][ch3] == 1
            || checkForShip[ch1][ch4] == 1 || checkForShip[x][y] == 2 || checkForShip[ch1][y] == 2
            || checkForShip[x][ch3] == 2 || checkForShip[x][ch4] == 2 || checkForShip[ch1][ch3] == 2
            || checkForShip[ch1][ch4] == 2 || checkForShip[x][y] == 3 || checkForShip[ch1][y] == 3
            || checkForShip[x][ch3] == 3 || checkForShip[x][ch4] == 3 || checkForShip[ch1][ch3] == 3
            || checkForShip[ch1][ch4] == 4 || checkForShip[ch1][ch4] == 3 || checkForShip[x][y] == 4
            || checkForShip[ch1][y] == 4 || checkForShip[x][ch3] == 4 || checkForShip[x][ch4] == 4
            || checkForShip[ch1][ch3] == 4) {
          i = i - 1;
        } else {
          setShipColor(ship, x, y, buttle);
          checkForShip[x][y] = 1;
        }
      } else {
        if (x == 9) {
          int ch2 = x - 1;
          int ch3 = y + 1;
          int ch4 = y - 1;
          if (checkForShip[x][y] == 1 || checkForShip[ch2][y] == 1 || checkForShip[x][ch3] == 1
              || checkForShip[x][ch4] == 1 || checkForShip[ch2][ch4] == 1
              || checkForShip[ch2][ch3] == 1 || checkForShip[x][y] == 2 || checkForShip[ch2][y] == 2
              || checkForShip[x][ch3] == 2 || checkForShip[x][ch4] == 2
              || checkForShip[ch2][ch4] == 2 || checkForShip[ch2][ch3] == 2
              || checkForShip[x][y] == 3 || checkForShip[ch2][y] == 3 || checkForShip[x][ch3] == 3
              || checkForShip[x][ch4] == 3 || checkForShip[ch2][ch4] == 3
              || checkForShip[ch2][ch3] == 3 || checkForShip[x][y] == 4 || checkForShip[ch2][y] == 4
              || checkForShip[x][ch3] == 4 || checkForShip[x][ch4] == 4
              || checkForShip[ch2][ch4] == 4 || checkForShip[ch2][ch3] == 4) {
            i = i - 1;
          } else {
            setShipColor(ship, x, y, buttle);
            checkForShip[x][y] = 1;
          }
        } else {
          if (y == 0) {
            int ch1 = x + 1;
            int ch2 = x - 1;
            int ch3 = y + 1;

            if (checkForShip[x][y] == 1 || checkForShip[ch1][y] == 1 || checkForShip[ch2][y] == 1
                || checkForShip[x][ch3] == 1 || checkForShip[ch1][ch3] == 1
                || checkForShip[ch2][ch3] == 1 || checkForShip[x][y] == 2
                || checkForShip[ch1][y] == 2 || checkForShip[ch2][y] == 2
                || checkForShip[x][ch3] == 2 || checkForShip[ch1][ch3] == 2
                || checkForShip[ch2][ch3] == 2 || checkForShip[x][y] == 3
                || checkForShip[ch1][y] == 3 || checkForShip[ch2][y] == 3
                || checkForShip[x][ch3] == 3 || checkForShip[ch1][ch3] == 3
                || checkForShip[ch2][ch3] == 3 || checkForShip[x][y] == 4
                || checkForShip[ch1][y] == 4 || checkForShip[ch2][y] == 4
                || checkForShip[x][ch3] == 4 || checkForShip[ch1][ch3] == 4
                || checkForShip[ch2][ch3] == 4) {
              i = i - 1;
            } else {
              setShipColor(ship, x, y, buttle);
              checkForShip[x][y] = 1;
            }

          } else {
            if (y == 9) {
              int ch1 = x + 1;
              int ch2 = x - 1;
              int ch4 = y - 1;
              if (checkForShip[x][y] == 1 || checkForShip[ch1][y] == 1 || checkForShip[ch2][y] == 1
                  || checkForShip[x][ch4] == 1 || checkForShip[ch2][ch4] == 1
                  || checkForShip[ch1][ch4] == 1 || checkForShip[x][y] == 2
                  || checkForShip[ch1][y] == 2 || checkForShip[ch2][y] == 2
                  || checkForShip[x][ch4] == 2 || checkForShip[ch2][ch4] == 2
                  || checkForShip[ch1][ch4] == 2 || checkForShip[x][y] == 3
                  || checkForShip[ch1][y] == 3 || checkForShip[ch2][y] == 3
                  || checkForShip[x][ch4] == 3 || checkForShip[ch2][ch4] == 3
                  || checkForShip[ch1][ch4] == 3 || checkForShip[x][y] == 4
                  || checkForShip[ch1][y] == 4 || checkForShip[ch2][y] == 4
                  || checkForShip[x][ch4] == 4 || checkForShip[ch2][ch4] == 4
                  || checkForShip[ch1][ch4] == 4) {
                i = i - 1;
              } else {
                setShipColor(ship, x, y, buttle);
                checkForShip[x][y] = 1;
              }
            } else {
              if (x == 0) {
                int ch1 = x + 1;
                int ch3 = y + 1;
                int ch4 = y - 1;
                if (checkForShip[x][y] == 1 || checkForShip[ch1][y] == 1
                    || checkForShip[x][ch3] == 1 || checkForShip[x][ch4] == 1
                    || checkForShip[ch1][ch3] == 1 || checkForShip[ch1][ch4] == 1
                    || checkForShip[x][y] == 2 || checkForShip[ch1][y] == 2
                    || checkForShip[x][ch3] == 2 || checkForShip[x][ch4] == 2
                    || checkForShip[ch1][ch3] == 2 || checkForShip[ch1][ch4] == 2
                    || checkForShip[x][y] == 3 || checkForShip[ch1][y] == 3
                    || checkForShip[x][ch3] == 3 || checkForShip[x][ch4] == 3
                    || checkForShip[ch1][ch3] == 3 || checkForShip[ch1][ch4] == 3
                    || checkForShip[x][y] == 4 || checkForShip[ch1][y] == 4
                    || checkForShip[x][ch3] == 4 || checkForShip[x][ch4] == 4
                    || checkForShip[ch1][ch3] == 4 || checkForShip[ch1][ch4] == 4) {
                  i = i - 1;
                } else {
                  setShipColor(ship, x, y, buttle);
                  checkForShip[x][y] = 1;
                }
              }
              int ch1 = x + 1;
              int ch2 = x - 1;
              int ch3 = y + 1;
              int ch4 = y - 1;
              if (checkForShip[x][y] == 1 || checkForShip[ch1][y] == 1 || checkForShip[ch2][y] == 1
                  || checkForShip[x][ch3] == 1 || checkForShip[x][ch4] == 1
                  || checkForShip[ch2][ch4] == 1 || checkForShip[ch1][ch3] == 1
                  || checkForShip[ch1][ch4] == 1 || checkForShip[ch2][ch3] == 1
                  || checkForShip[x][y] == 2 || checkForShip[ch1][y] == 2
                  || checkForShip[ch2][y] == 2 || checkForShip[x][ch3] == 2
                  || checkForShip[x][ch4] == 2 || checkForShip[ch2][ch4] == 2
                  || checkForShip[ch1][ch3] == 2 || checkForShip[ch1][ch4] == 2
                  || checkForShip[ch2][ch3] == 2 || checkForShip[x][y] == 3
                  || checkForShip[ch1][y] == 3 || checkForShip[ch2][y] == 3
                  || checkForShip[x][ch3] == 3 || checkForShip[x][ch4] == 3
                  || checkForShip[ch2][ch4] == 3 || checkForShip[ch1][ch3] == 3
                  || checkForShip[ch1][ch4] == 3 || checkForShip[ch2][ch3] == 3
                  || checkForShip[x][y] == 4 || checkForShip[ch1][y] == 4
                  || checkForShip[ch2][y] == 4 || checkForShip[x][ch3] == 4
                  || checkForShip[x][ch4] == 4 || checkForShip[ch2][ch4] == 4
                  || checkForShip[ch1][ch3] == 4 || checkForShip[ch1][ch4] == 4
                  || checkForShip[ch2][ch3] == 4) {
                i = i - 1;
              } else {
                setShipColor(ship, x, y, buttle);
                checkForShip[x][y] = 1;
              }
            }
          }
        }
      }
    }
    return i;
  }

  private static class MenuItem extends StackPane {
    public MenuItem(String name) {
      Rectangle choice = new Rectangle(220, 28, Color.LIGHTBLUE);
      choice.setOpacity(0.5);

      Text text = new Text(name);
      text.setFill(Color.FIREBRICK);
      text.setOpacity(0.7);

      text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
      setAlignment(Pos.CENTER);
      getChildren().addAll(choice, text);

      FillTransition st = new FillTransition(Duration.seconds(0.5), choice);
      setOnMouseEntered(event -> {
        st.setFromValue(Color.LIGHTBLUE);
        st.setToValue(Color.CORNSILK);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);
        st.play();
      });
      setOnMouseExited(event -> {
        st.stop();
        choice.setFill(Color.LIGHTBLUE);
      });
    }
  }

  private static class MenuBox extends Pane {
    public MenuBox(Menu subMenu) {
      setVisible(false);
      Rectangle bg = new Rectangle(WEIGHT, HEIGHT);
      bg.setOpacity(0.0);
      getChildren().addAll(bg, subMenu);
    }
  }

  private static class Menu extends VBox {
    public Menu(MenuItem... items) {
      setSpacing(30);
      setTranslateY(40);
      setTranslateX(550);
      for (MenuItem item : items) {
        getChildren().addAll(item);
      }
    }
  }

  public void rememberItem() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (nextPlayer == 1) {
          checkForShipFirst[i][j] = checkForShip[i][j];
        }
        if (nextPlayer == 2) {
          checkForShipSecond[i][j] = checkForShip[i][j];
        }
      }
    }
  }

  public void setShipColor(Button[][] ship, int x, int y, int buttle) {
    if (buttle == 0 || buttle == 2) {
      ship[x][y].setStyle("-fx-base: lightblue");
    }
    if (buttle == 1) {
      ship[x][y].setStyle("-fx-base: lightgreen");
    }
  }
}
