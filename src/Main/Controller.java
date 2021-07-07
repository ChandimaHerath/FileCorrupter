package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.*;

public class Controller {

    @FXML
    private Button btnSelectFile;
    @FXML
    private Button btnCorruptFile;

    public void initialize(){


    }


    public void OnClick_btnSelectFile(ActionEvent actionEvent)  {

        FileChooser fileChooser= new FileChooser();
        File file = fileChooser.showOpenDialog(btnCorruptFile.getScene().getWindow());
        System.out.println(file);

        try(FileInputStream inputStream = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inputStream)){


            byte[] data = new byte[inputStream.available()];
            bis.read(data);

            int startingOffset= 4;
            int length = 3;
            byte[] newData = new byte[data.length-length];

            for (int i = 0, j=0; i <data.length ; i++)
            {

                if((i>= startingOffset)&&(i<= (startingOffset+length)))
                {

                    continue;
                }
                 newData[j++] = data[i];
            }
            try(FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos)){

                bos.write(newData);
            }

        }
           catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void OnClick_btnCorruptFile(ActionEvent actionEvent) {


    }
}
