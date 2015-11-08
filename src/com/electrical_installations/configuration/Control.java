/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit; 

/**
 *
 * @author JRichard
 * @version 1
 * @since 2015-10-29
 */
public class Control {

    
    private static final Messages messages = Messages.getInstance(); 
    //fichero TMP
    private final String appPath = System.getProperties().getProperty("user.dir");
    private final File file = new File(appPath + "\\miApp.tmp");
    //tiempo en que se actualiza el fichero TMP
    private final int seconds = 20;
     

    /**
     * Constructor de clase
     */
    public Control() {
    }

    ;

    /**
    * Comprueba que archivo TMP exista, sino lo crea e inicia valores
    * @return 
    */
    public boolean check() {
        
        if (file.exists()) {
            long time = read();//
            long res = subtractTime(time);
            if (res < seconds) { 
                 MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.INSTANCE_EXIST), MessagesStructure.justify));
                return false;
            } else {
                taskSchedule();
                return true;
            }
        } else {// no existe fichero         
            crearTMP();
            taskSchedule();
            return true;
        }
    }

    /**
     * Lee el archivo TMP y retorna su valor
     *
     * @return LONG cantidad de milisegundos
     */
    public long read() {
        String line = "0";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return Long.valueOf(line).longValue();
    }

    /**
     * Programa un proceso que se repite cada cierto tiempo
     */
    public void taskSchedule() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        crearTMP();
                    }
                }, 1000, seconds * 1000, TimeUnit.MILLISECONDS); //comienza dentro de 1 segundo y luego se repite cada N segundos
    }

    /**
     * Crea un archivo TMP con un unico valor, el tiempo en milisegundos
     */
    public void crearTMP() {
        Date date = new Date();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.valueOf(date.getTime()));
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Resta el tiempo expresado en milisegundos
     *
     * @param currentTime el tiempo actual del sistema expresado en milisegundos
     * @return tiempo el resultado expresado en segundos
     */
    public long subtractTime(long currentTime) {
        Date date = new Date();
        long timeTMP = date.getTime();
        long time = timeTMP - currentTime;
        time = time / 1000;
        return time;
    }

    /**
     * Elimina el fichero TMP si es que existe
     */
    public void closeApp() {
        if (file.exists()) {
            file.delete();
        }
        System.exit(0);
    }
}//fin clase 

