package com.check;

import jxl.write.WriteException;

import javax.naming.NamingException;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 21/11/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class UrlCheck {
    public ArrayList<ArrayList<String>> check(){
        ReadExcel readExcel = new ReadExcel("/home/utsav/Desktop/testWrite.xls");
        //ReadExcel readExcel = new ReadExcel("/home/utsav/CodeRepository/UrlTest/InputExcel/inputExcel.xls");
        ArrayList<ArrayList<String>> cells = readExcel.readData();
        int num = cells.size();
        int i=0, j=1, k=0;
        ArrayList<ArrayList<String>> outcomes = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> moutcomes = new ArrayList<ArrayList<String>>();
        while(i<num){
            String value = cells.get(i).get(1);
            try{
                URL url = new URL(value.toString());
                URLConnection connection = url.openConnection();
                connection.connect();
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                int code = httpConnection.getResponseCode();
                System.out.println(code);
                if(code != 200){
                    outcomes.add(cells.get(i));
                    outcomes.get(k).add(Integer.toString(code));
                    k++;
                    /*System.out.print(" " + j);
                    j++;*/
                }
                else{

                    /*System.out.println(j);
                    j++;*/
                }
            }catch(MalformedURLException ex){
                moutcomes.add(cells.get(i));
                ex.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
            i++;
        }
        /*i=0;
        while(i<outcomes.size()){
            System.out.println(outcomes.get(i));
            i++;
        }
        System.out.println("moutcomes size " + moutcomes.size());
        i=0;
        while(i<outcomes.size()){
            System.out.println(moutcomes.get(i));
            i++;
        }*/
        return outcomes;

    }
    public static void main(String []args){
        UrlCheck urlCheck = new UrlCheck();
        ArrayList<ArrayList<String>> outcomes = urlCheck.check();
        WriteExcel writeExcel = new WriteExcel("/home/utsav/CodeRepository/UrlTest/OutputExcel/outcome.xls" + new Date());
        try{
            writeExcel.writeData(outcomes);
        }catch (IOException ex){
            ex.printStackTrace();
        }catch(WriteException ex){
            ex.printStackTrace();
        }

    }


}
