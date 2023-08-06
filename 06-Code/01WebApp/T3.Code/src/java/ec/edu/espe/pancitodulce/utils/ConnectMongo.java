/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.pancitodulce.utils;
import com.mongodb.client.MongoDatabase;
/**
 *
 * @author pc
 */
public class ConnectMongo {
    MongoDBManagement mongoDB = new MongoDBManagement();
    
    public static MongoDatabase database;
    public void connectData(){
        database = mongoDB.conecction();
       
    }
}
