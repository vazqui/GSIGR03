/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import GSILabs.BModel.*;
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
            Object o = UnicastRemoteObject.exportObject(remoteSystem,0);
            ClientGateway c = (ClientGateway) o;
            AdminGateway a = (AdminGateway) o;
            System.out.println("Creando registro...");
            Registry reg_cliente = LocateRegistry.createRegistry(RMI_PORT_CLIENT);
            Registry reg_admin = LocateRegistry.createRegistry(RMI_PORT_ADMIN);
            System.out.println("Registro creado");
            reg_cliente.rebind("ClientGateway", c);
            reg_admin.rebind("AdminGateway", a);
            System.out.println("Stub rebind done");      
      
            
            Cliente c1 = new Cliente("efseven2", "1234", "2001/12/01");
            remoteSystem.nuevoUsuario(c1);
            Bar b = new Bar("Cañas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16", "Bueno bonito barato");
            remoteSystem.nuevoLocal(b);  
            Review r = new Review("efseven2", "la comida estaba muy mala", b);  
            r.setTipoValoracion(10);
            remoteSystem.nuevaReview(r);
        }catch(RemoteException e){
            System.out.println(e);
        }
    }
}
