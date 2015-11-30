/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author JRichard
 */
public class Command {
     
public static void execute(String comando) {
try {
String linea;
Process p = Runtime.getRuntime().exec(comando);
    BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
while ((linea = input.readLine()) != null) {
System.out.println(linea);
}
input.close();
}
catch (Exception err) {
err.printStackTrace();
} 
}
}