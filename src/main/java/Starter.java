import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

public class Starter extends Application {

    public static void main(String[] args) {

      //  String password = "1234";
      //  String key = "#1234";

      //  BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

     //   basicTextEncryptor.setPassword(key);

      //  String encrypt = basicTextEncryptor.encrypt(password);
     //   System.out.println("Encrupt Password : "+encrypt);

       // String decrypt = basicTextEncryptor.decrypt(encrypt);
      //  System.out.println("Decript Password : "+decrypt);


        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));
        stage.show();
    }
}
