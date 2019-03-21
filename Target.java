import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Portscanner;
 *
 * Copyright by @author Marcel Mertens
 * Website: https://mertens-web.ddns.net
 *
 * Date: 21.03.2019
 */

public class Target implements Runnable
{
    String zHost = "";
    static AtomicInteger zSharedport; //Threadsafe Integer
    int zThreadNo = 0;
    int zMaxPort = 0;

    public Target(String pHost, int pSharedPort, int pMaxPort, int pThreadNo)
    {
        zHost = pHost;
        zSharedport = new AtomicInteger(pSharedPort);
        zMaxPort = pMaxPort;
        zThreadNo = pThreadNo;
    }

    public void run()
    {
        while (zSharedport.get() <= zMaxPort)
        {
            int lPort = zSharedport.getAndIncrement(); //get Integer and increment

            try // Try to create Socket on given port
            {
                Socket s = new Socket(zHost, lPort);
                System.out.println("Thread #" + zThreadNo + ": Port " + lPort + " at Host " + zHost + " is open!");
                s.close(); //Close Socket
            }

            catch (Exception ex) //If Port is closed throw exception
            {
               System.out.println("Thread #" + zThreadNo + ": Port " + lPort + " at Host " + zHost + " is closed!");
            }
        }
    }
}
