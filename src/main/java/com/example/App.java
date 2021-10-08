package com.example;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class App
{

    public static void main( String[] args )
    {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        String s = "mongodb+srv://Bharath:bharath12345678@movie.jeait.mongodb.net/test?retryWrites=true&w=majority";
        try(MongoClient mongoClient = MongoClients.create(s))
        {
            //printDatabases(mongoClient);
            //insert(mongoClient);
            //many(mongoClient);
            //delete(mongoClient);
            //update(mongoClient);
            //printall(mongoClient);
            find(mongoClient);
        }

    }

    private static void find(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        FindIterable<Document> res = record.find(new BasicDBObject("name",6+""));
        for (Document res1 : res)
            System.out.println(res1);
    }

    private static void printall(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        FindIterable<Document> iterDoc = record.find();
        int i = 1;
        // Getting the iterator
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }

    private static void update(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        record.updateMany(new Document(), Updates.set("Value ",3+""));
    }

    private static void delete(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        record.deleteMany(new Document());
    }

    private static void many(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        List<Document> doc = new ArrayList<>();
        for (int i=2;i<10;i++)
            doc.add(new Document("name",i+""));
        record.insertMany(doc);
    }

    private static void insert(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        Document doc = new Document("name","Mine1");
        record.insertOne(doc);
    }

    private static void printDatabases(MongoClient mongoClient) {
        List<Document> db = mongoClient.listDatabases().into(new ArrayList<>());
        db.forEach(document -> System.out.println(document.toJson()));
    }
}
