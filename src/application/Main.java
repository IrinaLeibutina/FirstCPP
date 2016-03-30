package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Main extends Application {

  public final static int WEIGHT = 900;
  public final static int HEIGHT = 600;

  public void start(Stage primaryStage) {
    Pane root = new Pane();
    // Create image for the background
    Image image = new Image(getClass().getResourceAsStream("Ship.jpg"));
    ImageView img = new ImageView(image);
    img.setFitHeight(610);
    img.setFitWidth(910);
    root.getChildren().add(img);

    // Create button for menu
    MenuItem newGame = new MenuItem("НОВАЯ ИГРА");
    MenuItem rule = new MenuItem("ПРАВИЛА ИГРЫ");
    MenuItem exitGame = new MenuItem("ВЫХОД");
    SubMenu mainMenu = new SubMenu(newGame, rule, exitGame);

    MenuItem players = new MenuItem("2 ИГРОКА");
    MenuItem computer = new MenuItem("ИНТЕЛЛЕКТ");
    MenuItem back = new MenuItem("НАЗАД");
    SubMenu newGameMenu = new SubMenu(players, computer, back);
    MenuBox menuBox = new MenuBox(mainMenu);

    newGame.setOnMouseClicked(event -> menuBox.setSubMenu(newGameMenu));
    exitGame.setOnMouseClicked(event -> System.exit(0));

    back.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
    root.getChildren().addAll(menuBox);

    Scene scene = new Scene(root, WEIGHT, HEIGHT);
    FadeTransition ft = new FadeTransition(Duration.seconds(3), menuBox);
    ft.setFromValue(0);
    ft.setToValue(1);
    ft.play();
    menuBox.setVisible(true);
    // Create image for the background of window
    Image image1 = new Image(getClass().getResourceAsStream("1.jpg"));
    ImageView img1 = new ImageView(image1);
    img1.setLayoutX(750);
    img1.setFitHeight(140);
    img1.setFitWidth(140);
    root.getChildren().add(img1);

    Image image2 = new Image(getClass().getResourceAsStream("5.gif"));
    primaryStage.setTitle("Морской бой");
    primaryStage.getIcons().add(image2);
    primaryStage.setScene(scene);

    // Rule of game
    rule.setOnMouseClicked(event -> {
      createRule(primaryStage, scene);
    });
    // Player game
    players.setOnMouseClicked(event -> {
      Ships ship = new Ships();
      ship.createField(primaryStage, 0);
    });
    // Computer game
    computer.setOnMouseClicked(event -> {
      Ships ship = new Ships();
      ship.createField(primaryStage, 2);
    });

    primaryStage.setResizable(false);
    primaryStage.setFullScreen(false);
    primaryStage.show();
  }

  
  public void createRule(Stage primaryStage, Scene scene){
    Pane root1 = new Pane();

    Scene scene1 = new Scene(root1, 794, 563);
    primaryStage.setScene(scene1);
    // Create image for background
    Image image3 = new Image(getClass().getResourceAsStream("img.png"));
    ImageView img3 = new ImageView(image3);
    img3.setFitHeight(HEIGHT);
    img3.setFitWidth(WEIGHT);
    root1.getChildren().add(img3);

    Rectangle comeBack = new Rectangle(130, 25, Color.DARKSALMON);
    comeBack.setOpacity(0.5);
    comeBack.setLayoutX(290);
    comeBack.setLayoutY(500);

    Text info = new Text(" На игровой площадке\n размером 10 на 10 клеток\n"
        + " пользователь расставляет один\n корабль из четы"
        + "рех клеток,\nдва корабля из трех клеток,\nтри ко"
        + "рабля из двух клеток и\nчетыре корабля размером"
        + "\nв одну клетку.\nПосле расстановки начинается\nбой."
        + "Он представляет собой\nпоочередные выстрелы игроков."
        + "\nПри попадании в корабль\nпротивника участник боя\nполучает " + "возможность");
    Text info2 = new Text(" проведения внеочередного\nвыстрела. Игра заканчивается\nпри "
        + "уничтожении одним\nиз участников всех кораблей\nпротивника. ");
    info.setFill(Color.BLACK);
    info.setOpacity(0.7);
    info.setLayoutY(34);
    info.setLayoutX(53);
    info.setFont(Font.font("Arial", FontPosture.ITALIC, 16));

    info2.setFill(Color.BLACK);
    info2.setOpacity(0.7);
    info2.setLayoutY(310);
    info2.setLayoutX(400);
    info2.setFont(Font.font("Arial", FontPosture.ITALIC, 16));

    Text text = new Text("НАЗАД");
    text.setFill(Color.FIREBRICK);
    text.setLayoutX(325);
    text.setLayoutY(520);
    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    root1.getChildren().addAll(info, text, info2, comeBack);

    FillTransition st = new FillTransition(Duration.seconds(0.5), comeBack);
    comeBack.setOnMouseEntered(event2 -> {
      st.setFromValue(Color.YELLOW);
      st.setToValue(Color.CORNSILK);
      st.setCycleCount(Animation.INDEFINITE);
      st.setAutoReverse(true);
      st.play();
    });

    comeBack.setOnMouseExited(event3 -> {
      st.stop();
      comeBack.setFill(Color.DARKSALMON);
    });

    comeBack.setOnMouseClicked(event1 -> {
      primaryStage.setScene(scene);
    });
  }
  private static class MenuItem extends StackPane {
    public MenuItem(String name) {
      Rectangle choice = new Rectangle(200, 25, Color.DARKSALMON);
      choice.setOpacity(0.5);

      Text text = new Text(name);
      text.setFill(Color.FIREBRICK);
      text.setOpacity(0.7);

      text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
      setAlignment(Pos.CENTER);
      getChildren().addAll(choice, text);
      FillTransition st = new FillTransition(Duration.seconds(0.5), choice);
      setOnMouseEntered(event -> {
        st.setFromValue(Color.YELLOW);
        st.setToValue(Color.CORNSILK);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);
        st.play();
      });
      setOnMouseExited(event -> {
        st.stop();
        choice.setFill(Color.DARKSALMON);
      });
    }
  }

  private static class MenuBox extends Pane {
    static SubMenu subMenu;

    public MenuBox(SubMenu subMenu) {
      MenuBox.subMenu = subMenu;

      setVisible(false);
      Rectangle choice = new Rectangle(WEIGHT, HEIGHT);
      choice.setOpacity(0.0);
      getChildren().addAll(choice, subMenu);
    }

    public void setSubMenu(SubMenu subMenu) {
      getChildren().remove(MenuBox.subMenu);
      MenuBox.subMenu = subMenu;
      getChildren().add(MenuBox.subMenu);
    }
  }

  private static class SubMenu extends VBox {
    public SubMenu(MenuItem... items) {
      setSpacing(20);
      setTranslateY(150);
      setTranslateX(50);
      for (MenuItem item : items) {
        getChildren().addAll(item);
      }
    }
  }
}
