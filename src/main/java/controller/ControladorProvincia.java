package controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;


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
	public static Object[][] getDatosDeTabla() {
		// Obtengo todas las personas
		List<Provincias> provincias = ControladorProvincia.getAllPro(null);
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

}
