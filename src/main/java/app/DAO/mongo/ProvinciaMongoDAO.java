package app.DAO.mongo;

import app.models.mongo.Departamento;
import app.models.mongo.Provincia;
import app.models.mysql.Distrito;
import com.google.gson.Gson;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class ProvinciaMongoDAO {

    private final String COLLECTION_NAME = "pais";
    private MongoCollection<Document> paisCollection;
    private MongoDatabase db;

    public ProvinciaMongoDAO(MongoDatabase db) {
        this.db = db;
        this.paisCollection = db.getCollection(COLLECTION_NAME);
    }

    public Document insertProvincia(Document departamento, Provincia provincia, List<Distrito> distritos) {

        Gson gson = new Gson();
        String json = gson.toJson(provincia);
        //Document doc = (Document) JSON.parse(gson.toJson(pais));
        Document doc = Document.parse(json);

        List<Document> distritosToInsert = new ArrayList<>();
        for (Distrito distrito : distritos) {
            String di = gson.toJson(distrito);
            distritosToInsert.add(Document.parse(di));
        }

        doc.append("distritos", distritosToInsert);

        /*Document query = new Document();
        query.append("departamentos.$.provincias._id", new Document("$ne", provincia.get_id()));
        Document update = new Document("$push", new Document("departamentos.$.provincias", doc));*/
        Document querySeach = new Document("departamentos.provincias._id", provincia.get_id());
        Document searchedProvincia = paisCollection.find(querySeach).first();

        System.out.println("searchedProvincia: " + searchedProvincia);
        if (searchedProvincia == null) {
            System.out.println("No hay provincia, se iserta");

            Document query = new Document("departamentos._id", departamento.getString("_id"));
            Document update = new Document("$addToSet", new Document("departamentos.$.provincias", doc));
            paisCollection.updateOne(query, update);

        }



        return doc;
    }
}
