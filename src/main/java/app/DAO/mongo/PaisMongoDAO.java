package app.DAO.mongo;

import app.Application;
import app.models.mongo.Pais;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PaisMongoDAO {

    private final String COLLECTION_NAME = "pais";
    private MongoCollection<Document> paisCollection;
    private MongoDatabase db;

    public PaisMongoDAO(MongoDatabase db) {
        this.db = db;
        this.paisCollection = db.getCollection(COLLECTION_NAME);
    }



    public List<Pais> getAllPaises () {
        List<Pais> paises = new ArrayList<>();
        List<Document> docs = paisCollection.find().into(new ArrayList<Document>());
        Iterator<Document> i = docs.iterator();
        while(i.hasNext()) {
            Document doc = i.next();
            Pais tmpPais = new Pais();
            tmpPais.setNombre(doc.getString("nombre"));
            tmpPais.set_id(doc.getString("_id"));
            tmpPais.setPbi(doc.getInteger("pbi"));

            paises.add(tmpPais);

        }

        return paises;
    }

    public Document insertPais(Pais pais) {
        System.out.println("Inserting pais");
        Gson gson = new Gson();
        String json = gson.toJson(pais);
        System.out.println("pais json: " + json);
        //Document doc = (Document) JSON.parse(gson.toJson(pais));
        Document doc = Document.parse(json);


        /*System.out.println("pais to Doc: " + doc.toString());
        System.out.println("Generated doc: " + doc.toString());*/

        try {
            paisCollection.insertOne(doc);
        }catch (MongoWriteException e ) {
            System.out.println("No se pudo insertar");
            return doc;
        }


        System.out.println("Inserted id: " + pais.get_id() + "\tpais: " + pais.getNombre());
        return doc;
    }
}
