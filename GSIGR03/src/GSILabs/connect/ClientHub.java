/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import GSILabs.BModel.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author alumno
 */

public class ClientHub implements Remote {

    public static void main(String[] args) {

        // Step 1- Reading from the keyboard the address of the remote machine
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {

            // Step 2-  Connecting to the remote registry
            System.out.print("Intoduzca la direccion a la que te quieres conectar: ");
            String remoteMachine = br.readLine();
            System.out.print("\nIntroduzca al puerto que se va a conectar: ");
            int port = Integer.parseInt(br.readLine());
            Registry registry = LocateRegistry.getRegistry(remoteMachine, port);
            System.out.println("\nConectado al servidor correctamente.");

            // Step 3- Linking the remote object as if it was a local one
            System.out.print("\nIntroduzca el tag al que quieres contactar: ");
            String tag = br.readLine();
            ClientGateway client = (ClientGateway) registry.lookup(tag);
            System.out.println(tag + ": funcionando.");

            // Step 4- Just using the object!
            
            Bar b = new Bar("Cañas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16", "Bueno bonito barato");
            Review r = new Review("efseven2", "la comida estaba muy mala", b);  
            
            System.out.println("\n\tProcedo a insertar una nueva review.");
            client.insertaReview(r);
            System.out.println("\tReview insertada correctamente.");
            System.out.println("\n\tElimino la review insertada anteriormente.");
            client.quitaReview(r);
            System.out.println("\tReview eliminada.");
            System.out.println("El mejor bar es:" + client.mejorBar("Comunidad Foral de Navarra").nombre);
            
        } catch (IOException | NumberFormatException | NotBoundException ioe) {
            System.out.println("Exception when reading : " + ioe.getMessage());
            exit(0);
        }

    }

}