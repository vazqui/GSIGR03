/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import GSILabs.BSystem.BusinessSystem;
import GSILabs.BSystem.PublicBusinessSystem;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author alumno
 */
public class BusinessServer {
    private static int RMI_PORT_CLIENT=1099;
    private static int RMI_PORT_ADMIN=1100;
    public static void main(String[] args) {
        PublicBusinessSystem remoteSystem = new PublicBusinessSystem();
        try{
            //Creamos stubs
            ClientGateway c = (ClientGateway)UnicastRemoteObject.exportObject(remoteSystem,0);
            AdminGateway a = (AdminGateway)UnicastRemoteObject.exportObject(remoteSystem,0);
            System.out.println("Creando registro...");
            Registry reg_cliente = LocateRegistry.createRegistry(RMI_PORT_CLIENT);
            Registry reg_admin = LocateRegistry.createRegistry(RMI_PORT_ADMIN);
            System.out.println("Registro creado");
            reg_cliente.rebind("ClientGateway", c);
            reg_admin.rebind("AdminGateway", a);
            System.out.println("Stub rebind done");
        }catch(RemoteException e){
            System.out.println(e);
        }
    }
}
