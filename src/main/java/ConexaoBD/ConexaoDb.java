package ConexaoBD;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConexaoDb {
    //password of db: hwgyrBaQSpvJpQO4
    //compass: mongodb+srv://dev:hwgyrBaQSpvJpQO4@cluster0.jpcti.mongodb.net/test
    private final String CONEX  ="mongodb+srv://dev:hwgyrBaQSpvJpQO4@cluster0.jpcti.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
    private final String DB  ="gerador-voucher";
    private final String COLLECTION = "gerador";
    private MongoClient mongoClient;

    private static  ConexaoDb instance;
    private ConexaoDb(){

        try {
            MongoClientURI uri = new MongoClientURI(CONEX);
            mongoClient = new MongoClient(uri);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ConexaoDb getInstance(){
        if(instance ==null){
            instance = new ConexaoDb();
        }
        return instance;
    }

    public void insertCodigo(String cod){
        MongoDatabase database = mongoClient.getDatabase(DB);
        MongoCollection<Document> collection = database.getCollection(COLLECTION);
        Document doc = new Document();
        doc.put("codigo", cod);
        collection.insertOne(doc);
    }

    public void closeConex(){
        this.mongoClient.close();
    }

}
