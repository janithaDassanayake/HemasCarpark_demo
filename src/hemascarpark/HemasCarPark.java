/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hemascarpark;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 *
 * @author janith
 */
public class HemasCarPark extends Application {
    
    Connection connection;
    PreparedStatement pStatement= null;
    ResultSet resultSet= null;
    
    TextField id,fn,ln ,em,un,mt;
    PasswordField pw;
                
    final ObservableList options =FXCollections.observableArrayList();
    final ObservableList<User>data=FXCollections.observableArrayList();
    TableView<User>tv;
    @Override
    public void start(Stage primaryStage) {
    
        
        primaryStage.setTitle("Hemas Car-Park System");
        CheckConnection();
        fillcomboFields();
       
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        BorderPane layout = new BorderPane();
        Scene scene1 = new Scene(layout,1100,600,Color.rgb(153,255,204));
        
        Group root = new Group();
        Scene scene = new Scene(root,250,150,Color.rgb(255,153,0));
        
        layout.setStyle("-fx-background-color:#e49b0f;");
        //layout.setStyle("-fx-background-image:j1.png;");
        
        
        //.getStylesheets().add("/styless.css");
        Color foreground = Color.rgb(255,153,0);
        Rectangle background = new Rectangle(250,150);
        background.setX(0);
        background.setY(0);
        
        background.setArcHeight(15);
        background.setArcWidth(15);
        
        background.setFill(Color.rgb(255,153,0));
        background.setStroke(foreground);
        background.setStrokeWidth(1.5);  
        
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(10,0,0,10));
        Label label = new Label(" ");
        label.setTextFill(Color.WHITESMOKE);
        label.setFont(new Font("SanSerif",12));
        
        TextField username= new TextField();
        username.setFont(Font.font("SanSerif",12));
        username.setPromptText("username");
        
        PasswordField password= new PasswordField();
        password.setFont(Font.font("SanSerif",12));
        password.setPromptText("Password");
       
        password.setOnKeyPressed(e->{ 
            
        if(e.getCode()==KeyCode.ENTER){
            
             try {
                
                String query="select * from User where username=? and password=?";
                pStatement=connection.prepareStatement(query);
                pStatement.setString(1,username.getText());
                pStatement.setString(2,password.getText());
                
                resultSet=pStatement.executeQuery();
                
                if(resultSet.next()){
                    
                    label.setText("Login to continue");
                    primaryStage.setScene(scene1);
                    primaryStage.show();
                    
                }else{
                    
                     label.setText("Login failed");
                     
                }
                
                username.clear();
                password.clear();
                
                pStatement.close();
                resultSet.close();
                
            } catch (Exception e1) {
                
                 label.setText("SQL error ");
                 System.err.println(e1);
            }
            
        }    
            
        });
        
        Button btn= new Button("   Login ");
        btn.setFont(Font.font("SanSerif",12));
        
        btn.setOnAction(e ->{
           
        });
        
        
        Button logout = new Button("Logout");
        logout.setFont(Font.font("SanSerif",12));
        logout.setOnAction(e->{
        
            
             primaryStage.setScene(scene);
             primaryStage.show();
                    
            
    });
        
        layout.setTop(logout);
        BorderPane.setAlignment(logout, Pos.TOP_RIGHT);
       
        BorderPane.setMargin(logout,new Insets(10,20,20,10));
        
        vBox.getChildren().addAll(label,username,password,btn);
        root.getChildren().addAll(background,vBox);
        
        
        
        
        
        
        
        
        VBox field = new VBox(5);
        Label label1= new Label("Staff Registration");
        label1.setFont(Font.font("SanSerif",15));
        
        //----------------------------------------(1)------------------
        id = new TextField();
        id.setFont(Font.font("SanSerif",15));
        id.setPromptText("ID");
        id.setMaxWidth(300);
        
        
          //----------------------------------------(1)------------------
       fn = new TextField();
       fn.setFont(Font.font("SanSerif",15));
       fn.setPromptText("first name");
       fn.setMaxWidth(300);
        
        
          //----------------------------------------(1)------------------
        ln = new TextField();
        ln.setFont(Font.font("SanSerif",15));
        ln.setPromptText("last name");
        ln.setMaxWidth(300);
        
        
          //----------------------------------------(1)------------------
         em = new TextField();
         em.setFont(Font.font("SanSerif",13));
         em.setPromptText("email");
         em.setMaxWidth(300);
       
          //----------------------------------------(1)------------------
        un = new TextField();
        un.setFont(Font.font("SanSerif",15));
        un.setPromptText("user name");
        un.setMaxWidth(300);
        
          //----------------------------------------(1)------------------
        mt = new TextField();
        mt.setFont(Font.font("SanSerif",15));
        mt.setPromptText("member Type");
        mt.setMaxWidth(300);
        
            //----------------------------------------(1)------------------
        pw = new PasswordField();
        pw.setFont(Font.font("SanSerif",15));
        pw.setPromptText("password");
        pw.setMaxWidth(300);
        
        
        Button button= new Button("    SAVE    ");
        button.setFont(Font.font("SanSerif",12));
        button.setTextFill(Color.WHITE);
        
        BorderPane.setMargin(button,new Insets(10,60,60,10));
        
        //button.setStyle("-fx-normal-background:#3cbc53; -fx-hovered-color:#aaaaaa;");
        
        button.styleProperty().bind(Bindings.when(button.hoverProperty())
                                      .then("-fx-background-color: #06660b")
                                      .otherwise("-fx-background-color:#108f16"));
        
  //      button.getStylesheets().add("styless.css");
  //        button.setStyle("-fx-normal-background: #101010; -fx-hovered-background: #aaaaaa;");
        
        button.setOnAction(e->{
            
            if(validateFildes()){
            try {
                
                String query="INSERT INTO User (ID,firstname ,lastname,email,username,memberType,password)VALUES(?,?,?,?,?,?,?)";
                pStatement=connection.prepareStatement(query);
                
                pStatement.setString(1,id.getText());
                 pStatement.setString(2,fn.getText());
                  pStatement.setString(3,ln.getText());
                   pStatement.setString(4,em.getText());
                    pStatement.setString(5,un.getText());
                     pStatement.setString(6,mt.getText());
                      pStatement.setString(7,pw.getText());
               
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Information Dialog");
                      alert.setHeaderText(null);
                      alert.setContentText("User has been created");
                      alert.showAndWait();
                           
                pStatement.execute();
                pStatement.close();
              
                clearFields();
                
            } catch (Exception e1) {
                
                 label.setText("SQL error ");
                 System.err.println(e1);
            }
            
             refreshTable();
             
            
            }
        });
        
        field.getChildren().addAll(label1,id,fn,ln,em,un,mt,pw,button);
        layout.setCenter(field);
       
        BorderPane.setMargin(field,new Insets(5,30,5,40));
        
        
      //-------------------------------------------(table view)-------------------------------------------------  
        
        
        tv= new TableView<User>();
        
        TableColumn col0 = new TableColumn("ID");
        col0.setMinWidth(20);
        col0.setCellValueFactory(new PropertyValueFactory<>("ID"));
       //---------1 Column
        TableColumn col1 = new TableColumn("First Name");
        col1.setMinWidth(60);
        col1.setCellValueFactory(new PropertyValueFactory<>("firstname"));
       //---------2 Column
        TableColumn col2 = new TableColumn("last name");
        col2.setMinWidth(60);
        col2.setCellValueFactory(new PropertyValueFactory<>("lastname"));
       //---------3 Column
        TableColumn col3 = new TableColumn("email");
        col3.setMinWidth(190);
        col3.setCellValueFactory(new PropertyValueFactory<>("email"));
        //--------4 Column
        TableColumn col4 = new TableColumn("User name");
        col4.setMinWidth(100);
        col4.setCellValueFactory(new PropertyValueFactory<>("username"));
        //---------5 Column
        TableColumn col5 = new TableColumn("member Type");
        col5.setMinWidth(80);
        col5.setCellValueFactory(new PropertyValueFactory<>("memberType"));
        //---------6 Column
        TableColumn col6 = new TableColumn("password");
        col6.setMinWidth(80);
        col6.setCellValueFactory(new PropertyValueFactory<>("password"));
      
     
        
        tv.getColumns().addAll(col0, col1, col2, col3, col4, col5, col6);
        tv.setTableMenuButtonVisible(true);
        
        layout.setRight(tv);
        BorderPane.setMargin(tv,new Insets(5,20,20,5));
        //tv.getColumns().addAll(col0, col1, col2, col3, col4, col5, col6);
        
        
        Button showbtn=new Button("show Users");
        showbtn.setFont(Font.font("SanSerif",13));
        
        
        
        showbtn.setOnAction(e->{
        
            refreshTable();
        
        });
        
        
        //cambo box
            ComboBox comboBox = new ComboBox(options);
            comboBox.setMaxHeight(30);
        
            comboBox.setOnAction(e->{
                
                
                //this one for get date from the databse and past in to textfilds
                
            try {
                String query="select * from User where firstname= ?";
                pStatement=connection.prepareStatement(query);
                pStatement.setString(1,(String)comboBox.getSelectionModel().getSelectedItem());
                resultSet=pStatement.executeQuery();
                
                while (resultSet.next()) {                    
                    
                    id.setText(resultSet.getString("ID"));
                     fn.setText(resultSet.getString("firstname"));
                      ln.setText(resultSet.getString("lastname"));
                       em.setText(resultSet.getString("email"));
                        un.setText(resultSet.getString("username"));
                         mt.setText(resultSet.getString("memberType"));
                          pw.setText(resultSet.getString("password"));
                    
                           //ID,firstname ,lastname,email,username,memberType,password
                          
                }
                
                pStatement.close();
                resultSet.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(HemasCarPark.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                
            });
            
//-----------------------------------delete user--------------------------------------------------
        Button deleteBtn = new Button("Delete record");
        deleteBtn.setFont(Font.font("SanSerif",12));
        
        
        deleteBtn.styleProperty().bind(Bindings.when(deleteBtn.hoverProperty())
                                      .then("-fx-background-color: #b5291f")
                                      .otherwise("-fx-background-color:#ff3e30"));
        deleteBtn.setTextFill(Color.WHITE);
        
        deleteBtn.setOnAction(e->{
            
            
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                      alert.setTitle("Confirmation !");
                      alert.setHeaderText(null);
                      alert.setContentText("Are You sure to Delete ?");
                      Optional<ButtonType>action=alert.showAndWait();
            
                      if(action.get()==ButtonType.OK){
                          
              try {
                String query= "delete from User where ID = ?";
                pStatement=connection.prepareStatement(query);
                pStatement.setString(1, id.getText());
                pStatement.executeUpdate();
                pStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(HemasCarPark.class.getName()).log(Level.SEVERE, null, ex);
            }
                          
                          
           }
           clearFields();
           refreshTable();
         
        });
            
            HBox hBox = new HBox(5);
            hBox.getChildren().addAll(showbtn,deleteBtn,comboBox);
         
            layout.setBottom(hBox);
            BorderPane.setMargin(hBox,new Insets(10,0,10,10));
      
            
           
           /* 
            //list view
            ListView list = new ListView(options);
            list.setMaxSize(100,250);
            layout.setLeft(list);
            BorderPane.setMargin(list, new Insets(10));
        */
            
            
        /*
        TableColumn col7 = new TableColumn("First Name");
        col7.setMinWidth(110);
        col7.setCellValueFactory(new PropertyValueFactory<>("firstname"));
       */ 
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    

//------------------------------------------------------------------------------------------------  

    /*
    
    private boolean IDvalidateNumber(){
        
        Pattern p1 = Pattern.compile("[0-9]+");
        Matcher m1= p1.matcher(id.getText());
        
        if(m1.find() && m1.group().equals(id.getText())){
            return true;
        }else{
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Warning!!");
             alert.setHeaderText(null);
             alert.setContentText("Hey! Please enter Valid ID");
             alert.showAndWait();
             
            return false;
        }
        
    }
    
    
    
    
     private boolean firstnameValidation(){
        
        Pattern p1 = Pattern.compile("[a-zA-Z]+");
        Matcher m1= p1.matcher(fn.getText());
        
        if(m1.find() && m1.group().equals(fn.getText())){
            return true;
        }else{
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Warning!!");
             alert.setHeaderText(null);
             alert.setContentText("Hey! Please enter Valid First Name");
             alert.showAndWait();
             
            return false;
        }
        
    }
    
     private boolean LastnameValidation(){
        
        Pattern p1 = Pattern.compile("[a-zA-Z]+");
        Matcher m1= p1.matcher(ln.getText());
        
        if(m1.find() && m1.group().equals(ln.getText())){
            return true;
        }else{
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Warning!!");
             alert.setHeaderText(null);
             alert.setContentText("Hey! Please enter Valid Last Name");
             alert.showAndWait();
             
            return false;
        }
        
    
     }
    
    */
    
   private boolean validateFildes(){
       
        Pattern p1 = Pattern.compile("[0-9]+");
        Matcher m1= p1.matcher(id.getText());
        
        Pattern p2 = Pattern.compile("[a-zA-Z]+");
        Matcher m2= p2.matcher(fn.getText());
  
        Pattern p3 = Pattern.compile("[a-zA-Z]+");
        Matcher m3= p3.matcher(ln.getText());
  
      if((id.getText().isEmpty()) || !(m1.find() && m1.group().equals(id.getText())) ){
           
                      Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Warning!!");
                      alert.setHeaderText(null);
                      alert.setContentText("Hey! please enter valid ID");
                      alert.showAndWait();
           
                      return false;
       }
      
      
       if(fn.getText().isEmpty() || !(m2.find() && m2.group().equals(fn.getText())) ){
           
           Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Warning!!");
                      alert.setHeaderText(null);
                       alert.setContentText("Hey! Please enter Valid First Name");
                      alert.showAndWait();
           
                      return false;
           
           
       }
        if(ln.getText().isEmpty() || !(m3.find() && m3.group().equals(ln.getText()))){
           
            Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Warning!!");
                      alert.setHeaderText(null);
                      alert.setContentText("Hey! Please enter Valid Last Name");
                      alert.showAndWait();
           
                      return false;
           
       }
        if(em.getText().isEmpty() ){
           
            Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Warning!!");
                      alert.setHeaderText(null);
                      alert.setContentText("Hey! Please enter Email ");
                      alert.showAndWait();
           
                      return false;
           
       }
        if(un.getText().isEmpty()){
           
            Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Warning!!");
                      alert.setHeaderText(null);
                      alert.setContentText("Hey! You can't Skip the user name");
                      alert.showAndWait();
           
                      return false;
           
       }
        if(mt.getText().isEmpty() ){
           
            Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Warning!!");
                      alert.setHeaderText(null);
                      alert.setContentText("Hey! Please enter member type");
                      alert.showAndWait();
           
                      return false;
           
       }
        if(pw.getText().isEmpty() ){
           
           Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Warning!!");
                      alert.setHeaderText(null);
                      alert.setContentText("Hey! Please enter password");
                      alert.showAndWait();
           
                      return false;
            
       }
       return true;
   }
    
   //------------------------------------------------------------------------------------------------  

  public void refreshTable(){
     
    data.clear();
    
    try {
                
                String query="select * from User";
                pStatement=connection.prepareStatement(query);
                resultSet=pStatement.executeQuery();
                
                while (resultSet.next()) {                    
                    
                    //copy data form the database
                    data.add(new User(
                             resultSet.getString("ID"),
                             resultSet.getString("firstname"),
                             resultSet.getString("lastname"),
                             resultSet.getString("email"),
                             resultSet.getString("username"),
                             resultSet.getString("memberType"),
                             resultSet.getString("password")
                    ));
                     //paset data form the database
                    tv.setItems(data);
                }
                
                pStatement.close();
                resultSet.close();
                //ID,firstname ,lastname,email,username,memberType,password
            } catch (Exception e2) {
                 System.err.println(e2);
            }
         
         
     }
    
//------------------------------------------------------------------------------------------------
      
    public void fillcomboFields(){
   
     
      
        
        try {
            String query ="select firstname from User";
            pStatement=connection.prepareStatement(query);
            resultSet=pStatement.executeQuery();
            
            while (resultSet.next()) {                
                
                options.add(resultSet.getString("firstname"));
                
            }
            pStatement.close();
            resultSet.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(HemasCarPark.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    
    }
//------------------------------------------------------------------------------------------------    
    
      
    public void  clearFields(){
        
        //clear text firld after inserting users;
    
        id.clear();
        fn.clear();
        ln.clear();        
        em.clear();
        un.clear();
        mt.clear();
        pw.clear();
           
    }
   
   //---------------------------------------------------------------------------------------------  
    
    public void  CheckConnection(){
    
        connection=SqlConnection.DatabaseConnection();
        if(connection==null){
            
            System.out.println("connection is not successful");
            System.exit(1);
        }else{
             System.out.println("connection successful");
        }
    
    }
    
    
    //------------------------------------------------------------------------------------------------
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
