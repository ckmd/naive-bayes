/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rachmad
 */
public class NaiveBayes {

    static FileInputStream filestream;
    static DataInputStream in;
    static BufferedReader br;
    static String[] label;
    static String[] attribut;
    static Scanner scan;
    static ArrayList<String[]> data;
    static ArrayList<String[]> konsep;
    static int[] value;
    static int[] valueno;
    static int[] result;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        filestream = new FileInputStream("data.txt");
        in = new DataInputStream(filestream);
        br = new BufferedReader(new InputStreamReader(in));
        data = new ArrayList<>();
        konsep = new ArrayList<>();
        scan = new Scanner(System.in);

        String strLine;
        int i = 1;

        while ((strLine = br.readLine()) != null) {//memecah label, attribut, dan data
            if (i == 1) {
                label = strLine.split(",");
            } else {
                data.add(strLine.split(","));//data get didapat per baris
            }
            i++;
        }
        System.out.println(label[3] + data.get(1)[2]);
        //proses menanyakan 
        System.out.print("masukkan data : ");
        String input = scan.nextLine();
        String[] dataTest = input.split(",");
//        for(int j = 0; j < label.length-1; j++){
//            System.out.print("masukkan "+label[j]+" : ");
//        }
        value = new int[dataTest.length];
        valueno = new int[dataTest.length];
        result = new int[dataTest.length];
        for (int a = 0; a < value.length; a++) {
            value[a] = 0;
            valueno[a] = 0;
            result[a] = 0;
        }
        //proses mengecek data dan membandingkan
        for (int j = 0; j < label.length - 1; j++) {
            if (dataTest[j].equalsIgnoreCase(" ")) {
                continue;
            } else {
//                System.out.println(dataTest[j]);
                for (int k = 0; k < data.size(); k++) {
                    if (dataTest[j].equals(data.get(k)[j]) && data.get(k)[label.length - 1].equalsIgnoreCase("ya")) {
                        System.out.println(dataTest[j]);
                        value[j] += 1;
                    } else if (dataTest[j].equals(data.get(k)[j]) && data.get(k)[label.length - 1].equalsIgnoreCase("tidak")) {
                        valueno[j] += 1;
                    }
                }
            }
        }
        for (int b = 0; b < value.length; b++) {
            System.out.println(value[b]);
        }
        System.out.println("");
        for (int b = 0; b < value.length; b++) {
            System.out.println(valueno[b]);
        }
        //proses menghitung nilai ya dan tidak
        for (int k = 0; k < data.size(); k++) {
            if (data.get(k)[label.length - 1].equalsIgnoreCase("ya")) {
                result[0] += 1;//untuk yes
            } else if (data.get(k)[label.length - 1].equalsIgnoreCase("tidak")) {
                result[1] += 1;//untuk no
            }
        }
            System.out.println(result[0]);
            System.out.println(result[1]);
//        System.out.println("");
//        for (int b = 0; b < 2; b++) {
//            System.out.println(resultyes[b]);
//        }        
        
//menghitung hasil
        float hasilya = 1;
        float hasilno = 1;
        for(int a = 0; a < dataTest.length; a++){
            if(dataTest[a].equals(" ")){
                continue;
            }
            else{
                hasilya*=(float)value[a]/result[0];
                hasilno*=(float)valueno[a]/result[1];
            }
        }
        hasilya*=(float)result[0]/(result[0]+result[1]);                
        hasilno*=(float)result[0]/(result[0]+result[1]);                
//        hasilya = ((float)value[0]/result[0])*((float)value[2]/result[0])*((float)result[0]/(result[0]+result[1]));
        System.out.println(hasilya);
        System.out.println(hasilno);
        
        //pengecekan nilai tinggi
        System.out.print("olahraga ? : ");
        if(hasilya>=hasilno){
            System.out.println("ya");
        }else if(hasilno>=hasilya){
            System.out.println("tidak");
        }else{
            System.out.println("Terserah");
        }
    }

}
