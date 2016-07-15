package app.DAO.mongo;

import app.models.mongo.Departamento;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class DepartamentoMongoDAO {

    private final String COLLECTION_NAME = "pais";
    private MongoCollection<Document> paisCollection;
    private MongoDatabase db;

    public DepartamentoMongoDAO(MongoDatabase db) {
        this.db = db;
        this.paisCollection = db.getCollection(COLLECTION_NAME);
    }

    public Document insertDepartamento(Document pais, Departamento departamento) {

        Gson gson = new Gson();
        String json = gson.toJson(departamento);
        //Document doc = (Document) JSON.parse(gson.toJson(pais));
        Document doc = Document.parse(json);

        Document query = new Document();
        query.append("_id", pais.getString("_id"));
        query.append("departamentos._id", new Document("$ne", departamento.get_id()));
        Document update = new Document("$push", new Document("departamentos", doc));
        paisCollection.updateOne(query, update);

        return doc;
    }
}
