/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectomongolo;

import Colecciones.Persona;
import org.bson.types.ObjectId;
/**
 *
 * @author franc
 */
public class ProyectoMongolo {

    public static void main(String[] args) {
        
        PersonaDAO personaDAO = new PersonaDAO();
        
        // CREATE
        // creamos un nuevo usuario con los datos siguientes
//        Persona nuevaPersona = new Persona("UsarioCreado", 12); // Suponiendo que tengas un constructor en Persona que reciba nombre y edad
//        // se llama al metodo de CREAR
//        personaDAO.agregarPersona(nuevaPersona);



        // READ
        // Llama al método LEER
//        personaDAO.leerPersonas();
//        System.out.println(personaDAO);



        // UPDATE
        // ingresamos los nuevos datos del usuari a actualizar
//        String nuevoNombre = "modificado";
//        int nuevaEdad = 18;
//        Persona personaActualizada = new Persona(nuevoNombre, nuevaEdad);
//        // Llamar al método ACTUALIZAR
//        personaDAO.actualizarPersona(personaActualizada);
//        


        // DELETE
        // el usuario a eliminar es el que tiene el _id:
//        ObjectId idPersonaAEliminar = new ObjectId("6622c14ab3866b3c58a713d1");
//        // llamamos al metodo ELIMINAR
//        personaDAO.elimiarPersona(idPersonaAEliminar);
        }
}
