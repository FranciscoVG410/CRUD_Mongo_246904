/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectomongolo;


import Colecciones.Persona;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author franc
 */
public class PersonaDAO {
    private final MongoDatabase dataBase; // Declara una variable para la base de datos MongoDB

    public PersonaDAO() {
        // Configura el proveedor de códecs para trabajar con objetos Java y MongoDB
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

        // Combina el proveedor de códecs POJO con el registro de códecs predeterminado de MongoDB
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        // Configura la cadena de conexión a la base de datos MongoDB local
        ConnectionString cadenaConexion = new ConnectionString("mongodb://localhost/27017");

        // Configura los ajustes del cliente MongoDB, incluyendo la cadena de conexión y el registro de códecs
        MongoClientSettings clientsSettings = MongoClientSettings.builder()
                .applyConnectionString(cadenaConexion)
                .codecRegistry(codecRegistry)
                .build();

        // Crea un cliente MongoDB utilizando los ajustes configurados
        MongoClient dbServer = MongoClients.create(clientsSettings);

        // Obtiene la base de datos específica ("personas") del cliente MongoDB
        this.dataBase = dbServer.getDatabase("admin");   
    }
    public void leerPersonas() {
    // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Persona> coleccion = dataBase.getCollection("personas", Persona.class);

        List<Persona> personasLista = new LinkedList<>();
        Bson mayores = Filters.gte("edad", 18);
        Bson ordenar = Sorts.descending("edad", "nombre");
        
        // Obtiene todos los documentos de la colección y los almacena en una lista de personas
        coleccion.find(mayores).sort(ordenar).into(personasLista);

        // Verifica si la lista está vacía
        if (personasLista.isEmpty()) {
            System.out.println("HO HAY REGISTROS EN LA BD");
        } else {
            // Itera sobre la lista de personas e imprime cada una
            for (Persona persona : personasLista) {
                System.out.println("Nombre: " + persona.getNombre() + ", Edad: " + persona.getEdad());
            }
        }
}
    
    
    public void agregarPersona(Persona nuevaPersona){
        // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Persona> coleccion = dataBase.getCollection("personas", Persona.class);
        
        coleccion.insertOne(nuevaPersona);

        // Imprime un mensaje para indicar que la persona se ha creado exitosamente
        System.out.println("Persona creada exitosamente: " + nuevaPersona);
    }
    
    public void actualizarPersona(Persona actualizaPersona){
        // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Persona> coleccion = dataBase.getCollection("personas", Persona.class);
        
        // Crea un filtro para encontrar la persona que se va a actualizar
        Bson encontrar = Filters.eq("_id", new ObjectId("6622c14ab3866b3c58a713d1")); 

        // Crea un documento con los campos actualizados
        Document docActualizado = new Document("$set", new Document("nombre", actualizaPersona.getNombre()).append("edad", actualizaPersona.getEdad()));

        // Actualiza la persona en la colección
        UpdateResult update = coleccion.updateOne(encontrar, docActualizado);

        // Verifica si la actualización fue exitosa
        if (update.getModifiedCount() > 0) {
            System.out.println("Se actualizo correctamente: " + actualizaPersona);
        }
    }
    
    public void elimiarPersona(ObjectId idPersona) {
        // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Persona> coleccion = dataBase.getCollection("personas", Persona.class);

        // Crea un filtro para encontrar la persona que se va a eliminar
        Bson encontrar = Filters.eq("_id", idPersona); 

        // Actualiza la persona en la colección
        DeleteResult delete = coleccion.deleteOne(encontrar);

        // Verifica si la eliminación fue exitosa
        if (delete.getDeletedCount() > 0) {
            System.out.println("Se eliminó correctamente la persona");
        } else {
            System.out.println("No se encontró ninguna persona");
        }
    }
}
