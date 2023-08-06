/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.pancitodulce.controller;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.pancitodulce.model.Product;
import ec.edu.espe.pancitodulce.utils.MongoDBManagement;
import java.io.IOException;
import java.util.ArrayList;
import org.bson.Document;
import org.json.simple.parser.ParseException;
/**
 *
 * @author pc
 */
public class dataController {
public static ArrayList<Product> getProduct() throws ParseException, IOException {
        MongoDBManagement mongoDBManagement = new MongoDBManagement();
        ArrayList<Product> product = new ArrayList<>();
        MongoDatabase database = mongoDBManagement.conecction();
        MongoCollection collection = database.getCollection("Product");
        MongoCursor<Document> cursor = collection.find().iterator();
        
        try {
            while (cursor.hasNext()) {
                JsonObject jsonObject = new JsonParser().parse(cursor.next().toJson()).getAsJsonObject();
                Product localClass = new Product();
                //localClass.setId(jsonObject.get("ID").getAsInt());
                localClass.setFactura(jsonObject.get("Factura").getAsString());
                localClass.setNombre(jsonObject.get("Nombre").getAsString());
                localClass.setCantidad(jsonObject.get("Cantidad").getAsInt());
                localClass.setMedida(jsonObject.get("Medida").getAsString());
                localClass.setValor(jsonObject.get("Valor").getAsFloat());
                localClass.setFechaCaducidad(jsonObject.get("Caduca").getAsString());
                product.add(localClass);
            }
        } finally {
            cursor.close();
        }

        return product;
}
}