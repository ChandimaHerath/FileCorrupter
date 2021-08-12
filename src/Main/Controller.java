package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Optional;

public class Controller {

    public Label lblFile;
    public TextField txtFile;
    @FXML
    private Button btnSelectFile;
    @FXML
    private Button btnCorruptFile;

    public void initialize(){


    }


    public void OnClick_btnSelectFile(ActionEvent actionEvent)  {

        FileChooser fileChooser= new FileChooser();
        File file = fileChooser.showOpenDialog(btnCorruptFile.getScene().getWindow());
        txtFile.setText(String.valueOf(file));


    }

    public void OnClick_btnCorruptFile(ActionEvent actionEvent) {


        if(!txtFile.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you Sure? Please double check!!");
            alert.setContentText("Do you want to destroy your file?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){
                try(FileInputStream inputStream = new FileInputStream(txtFile.getText());
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
                    try(FileOutputStream fos = new FileOutputStream(txtFile.getText());
                        BufferedOutputStream bos = new BufferedOutputStream(fos)){

                        bos.write(newData);
                        Alert alert3 = new Alert(Alert.AlertType.WARNING);
                        alert3.setTitle("Succeed!");
                        alert3.setHeaderText("Succeed..!");
                        alert3.setContentText("File Corrupted Successfully!");
                        alert3.show();

                    }

                }
                catch (IOException e) {
                    final Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setHeaderText("Error");
                    alert1.setContentText("Please Check selected file location and Try Again..! ");
                    alert1.show();
                }
            }

            else{

            }




        }

        else {
             Alert alert2 = new Alert(Alert.AlertType.ERROR);
             alert2.setHeaderText("Error");
             alert2.setContentText("No File Selected!");
             alert2.show();
        }



    }

}
