/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vpe.simplebundle.impl;

/**
 *
 * @author Leihta
 */
public class HelloWorldThread extends Thread{

    private boolean running=true;
    public HelloWorldThread() {
    }

    @Override
    public void run() {
        while (running)
        {
            System.out.println("Hola Amigos!");
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                System.out.println("ERROR en Hola Amigos: " + e);
            }
        }
    }

    public void stopThread() {
        this.running = false;
    }



}
