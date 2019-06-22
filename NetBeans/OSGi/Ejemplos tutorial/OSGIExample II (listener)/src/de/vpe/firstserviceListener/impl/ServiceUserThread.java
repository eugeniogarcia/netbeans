package de.vpe.firstserviceListener.impl;


import de.vpe.firstservice.FirstService;
import java.util.Date;

public class ServiceUserThread extends Thread {
    //Servicio que queremos ejecutar
    private FirstService service = null;
    //Indica si estamos ejecutandolo
    private boolean running = true;

    public ServiceUserThread(FirstService service) {
    this.service = service;
    }

    public void run() {
        Date date = null;

        String formattedDate = null;

        while (running) {
            date = new Date();
            try {
                formattedDate = this.service.getFormattedDate(date);
            }
            catch (RuntimeException e) {
                System.out.println("Se produjo una excepción: "+ e);
            }
            System.out.println("ServiceUserThread: La fecha convertida es : "    + formattedDate);

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.out.println("ServiceUserThread ERROR: " + e);
            }
        }
    }

    //Para la ejecución
    public void stopThread() {
        this.running = false;
    }
}