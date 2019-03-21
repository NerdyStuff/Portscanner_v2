/**
 * Portscanner;
 *
 * Copyright by @author Marcel Mertens
 * Website: https://mertens-web.ddns.net
 *
 * Date: 21.03.2019
 */

public class Portscanner
{
    private static int MULTIPLIER = 1; //Multiplier for Threads

    int zCores = Runtime.getRuntime().availableProcessors(); //Get cores for Thread calculation
    int zThreads = zCores * MULTIPLIER; //Calculate Threads

    static int zSharedPort = 0;
    int zMaxport = 0;
    String zHost = "";

    public Portscanner(String pHost, int pStartport, int pEndport)
    {
        zHost = pHost;
        zSharedPort = pStartport;
        zMaxport = pEndport;

        System.out.println("The machine has " + zCores + " Cores");
        System.out.println("Use " + zThreads + " Threads");

        //Thread Array
        Thread[] lThreadArray = new Thread[zThreads+1];

        this.scanTargets(lThreadArray, zThreads);
    }

    private void scanTargets(Thread[] pThreadArray, int pThreads)
    {
        for(int i = 0; i < pThreads; i++) //create Target objects
        {
            pThreadArray[i] = new Thread(new Target(zHost, zSharedPort, zMaxport, i));
        }

        for(int i = 0; i < pThreads; i++) //start Threads
        {
            pThreadArray[i].start();
        }
    }
}
