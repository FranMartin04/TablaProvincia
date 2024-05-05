package controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import entities.Ccaa;



public class ControladorCCAA {
	public static List<Ccaa> getAllCCAA(MongoCollection<Document> col) {
        System.out.println("Obteniendo todas las CCAA de la colección");
 
        // Performing a read operation on the collection.
        FindIterable<Document> fi = col.find();
        MongoCursor<Document> cursor = fi.iterator();

        List<Ccaa> allCCAA = new ArrayList<Ccaa>();
        try {
            while(cursor.hasNext()) {
            	allCCAA.add(documentToCCAA(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        
        return allCCAA;
    }
	private static Ccaa documentToCCAA(Document doc) {
    	Ccaa ccaa = new Ccaa();
    	ccaa.setParentCode(doc.getString("parent_code"));
    	ccaa.setLabel(doc.getString("label"));
    	ccaa.setCode(doc.getString("code"));
    	
    	return ccaa;
    }
	public static void updateDocument (MongoCollection<Document> col,String code, String label, String parent_code) {
        try {
        	Document query = new Document().append("code",  code);
        	Bson update = Updates.combine(Updates.set("label", label));
        	Bson update2 = Updates.combine(Updates.set("parent_code", parent_code));
        	UpdateResult result = col.updateOne(query, update);
        	UpdateResult resultado = col.updateOne(query, update2);
        	
        	System.out.println("Modificados");
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
        
    }
}
