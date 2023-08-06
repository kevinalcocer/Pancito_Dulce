/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.pancitodulce.utils;

/**
 *
 * @author pc
 */
import ec.edu.espe.pancitodulce.model.MongoDB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoSecurityException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Arrays;

public class MongoDBManagement {
        MongoDB mongoDB = new MongoDB();

    public MongoDatabase conecction() {
        
        String URI = "mongodb+srv://juanillo:12345@datapanaderia.iml8x7y.mongodb.net/?retryWrites=true&w=majority";
        try {
            mongoDB.setUri(new MongoClientURI(URI));
            mongoDB.setMongoClient(new MongoClient(mongoDB.getUri()));
            mongoDB.setDatabase(mongoDB.getMongoClient().getDatabase("dataPanaderia"));
            mongoDB.setCollection(mongoDB.getDatabase().getCollection("Comprobation"));
            mongoDB.getCollection().drop();
        } catch (MongoSecurityException a) {
            mongoDB.setDatabase(null);
        }
        return mongoDB.getDatabase();
    }

    public void save(Document document, String collection, MongoDatabase database) {
        MongoCollection<Document> collectionDocument = database.getCollection(collection);
        collectionDocument.insertOne(document);
    }
                    //("Product","nombre",valor, "valor","" base de datos )
    public void update(String col, String key1, Object value1, String key2, Object value2, Object newValue, MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection(col);
        Bson filter = new Document("$and", Arrays.asList(
            new Document(key1, value1),
            new Document(key2, value2)
        ));
        Bson update = new Document("$set", new Document(key1, newValue));
        collection.findOneAndUpdate(filter, update);
    }
    /*
                       "Product","nombre", "valor", "factura,nuevo valor, "base de datos )
    public void update(String col, String key, Object value, Object newValue, MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection(col);
        Bson filter = eq(key, value);
        Bson update = set(key, newValue);
        collection.findOneAndUpdate(filter, update);
    }*/

    public String find(String col, String key, Object value, MongoDatabase database) {
        String find = "";
        MongoCollection<Document> collections = database.getCollection(col);
        FindIterable<Document> iterable = collections.find(gte(key, value));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            find = cursor.next().toJson();
        }
        return find;
    }
    public void delete(String col, String key1, Object value1, String key2, Object value2, MongoDatabase database) {
    MongoCollection<Document> collection = database.getCollection(col);
    Bson filter = Filters.and(eq(key1, value1), eq(key2, value2));
    collection.findOneAndDelete(filter);
    }
                        /*("Product","nombre", "valor", base de datos )
    public void delete(String col, String key, Object value, MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection(col);
        Bson filter = eq(key, value);
        collection.findOneAndDelete(filter);
    }*/

    public String completeModel(String col, MongoDatabase database) throws ParseException {
        String json = "";
        JSONArray jsonArray = new JSONArray();
        MongoCollection<Document> collection = database.getCollection(col);
        MongoCursor<Document> resultDocument = collection.find().iterator();
        while (resultDocument.hasNext()) {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(resultDocument.next().toJson());
            jsonObject.remove("_id");
            jsonArray.add(jsonObject);
            json = jsonArray.toJSONString();
        }
        return json;
    }
}
