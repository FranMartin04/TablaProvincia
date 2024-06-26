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

import entities.Provincias;



public class ControladorProvincia {
	private static List<Provincias> getAllPro(MongoCollection<Document> col) {
        System.out.println("Obteniendo todas las pro de la colección");
 
        // Performing a read operation on the collection.
        FindIterable<Document> fi = col.find();
        MongoCursor<Document> cursor = fi.iterator();

        List<Provincias> allPro = new ArrayList<Provincias>();
        try {
            while(cursor.hasNext()) {
            	allPro.add(documentToPro(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        
        return allPro;
    }
	private static Provincias documentToPro(Document doc) {
    	Provincias pro = new Provincias();
    	pro.setParentCode(doc.getString("parent_code"));
    	pro.setCode(doc.getString("code"));
    	pro.setLabel(doc.getString("label"));
    	return pro;
    }
	public static String[] getTitulosColumnas() {
		return new String[] { "Code", "Label", "Parent_Code"};
	}
	public static Object[][] getDatosDeTabla(MongoCollection<Document> coll) {
		// Obtengo todas las personas
		List<Provincias> provincias = ControladorProvincia.getAllPro(coll);
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[provincias.size()][3];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < provincias.size(); i++) {
			Provincias provincia = provincias.get(i);
			datos[i][0] = provincia.getCode();
			datos[i][1] = provincia.getLabel();
			datos[i][2] = provincia.getParentCode();

		}

		return datos;
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
