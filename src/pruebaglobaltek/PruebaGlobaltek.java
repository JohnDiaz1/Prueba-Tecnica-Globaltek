/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaglobaltek;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Esteban Diaz
 */
public class PruebaGlobaltek {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String arregloObjetos;
        String objetoJson;

        //Se utiliza un scanner para recibir los argumentos
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Introduzca el arreglo de objetos: ");
            arregloObjetos = sc.nextLine();
            System.out.print("Introduzca el segundo argumento: ");
            objetoJson = sc.nextLine();
        }

        JSONArray arregloJson = new JSONArray(arregloObjetos); //El String se convierte en un array de objetos
        JSONObject compararJson = new JSONObject(objetoJson); //Segundo parametro

        //Llamada de la funcion que itera
        ArrayObjetos(arregloJson, compararJson);

    }
    
    

    public static void ArrayObjetos(JSONArray arregloJson, JSONObject segundoArgumento) {

        ArrayList<JSONObject> personas = new ArrayList<>();
        Iterator keys = segundoArgumento.keys(); // Obtiene todas las llaves del segundo argumento
        

        while (keys.hasNext()) { // Mientras hayan argumentos el va a seguir buscando
            
            String llaveActual = (String) keys.next(); //Obtiene el argumento actual

            for (int i = 0; i < arregloJson.length(); i++) {

                JSONObject persona = arregloJson.getJSONObject(i);

                try {

                    if (persona.has(llaveActual)) { 
                        // Si el primer argumento contiene esta llave ejecuta                       
                        if(segundoArgumento.getString(llaveActual).equals(persona.getString(llaveActual))){
                            //Si el contenido de ambas llaves es igual guarda el objeto actual en el ArrayList             
                            personas.add(arregloJson.getJSONObject(i));
                        }
                    }
                    
                } catch (JSONException exc) {
                }
            }   
        }
        
            Set<JSONObject> sinDuplicados = new LinkedHashSet<>(personas); //Elimina algunos elementos duplicados
        
            JSONArray jsonObject = new JSONArray(sinDuplicados); 
            System.out.println(jsonObject);
    }
}