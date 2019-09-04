/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hemascarpark;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author janith
 */
public class SqlConnection {
    

    
    public static Connection DatabaseConnection(){
        
        try {
            Connection connection=null;
            Class.forName("org.sqlite.JDBC");
            connection= DriverManager.getConnection("jdbc:sqlite:mydatabase.sqlite");
            return connection;
                  
        } catch (ClassNotFoundException| SQLException e) {
 
            System.out.println(e);
                    
                }
        
        return null;
        
        
    }
    
}
