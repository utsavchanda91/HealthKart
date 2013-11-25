package com.check;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 21/11/13
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReadExcel {
    String fileName;
    public ArrayList<ArrayList<String>> readData(){
        ArrayList<ArrayList<String>> listoflist = new ArrayList<ArrayList<String>>();
        try{
            Workbook workbook= Workbook.getWorkbook(new File(fileName));
            Sheet sheet= workbook.getSheet(0);
            int rowNum=sheet.getRows();
            int columns=sheet.getColumns();

            System.out.println("row" + rowNum + "col" + columns);
            for(int i=0;i<rowNum;i++){
                ArrayList<String> listRow = new ArrayList<String>();
                for(int j=0;j<columns;j++){
                    Cell cell = sheet.getCell(j, i);
                    listRow.add(cell.getContents());
                }
                listoflist.add(listRow);
            }


        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch(BiffException e){
            e.printStackTrace();
        }
        return listoflist;


    }

    ReadExcel(String fileName){
        this.fileName = fileName;
    }

   /* public List<String> readData(){
        List<String> cells = new ArrayList<String>();
        try{
            InputStream file = new BufferedInputStream(new FileInputStream(new File(fileName)));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if(rowIterator.hasNext())
                rowIterator.next();
            while(rowIterator.hasNext()){
                HSSFRow row = (HSSFRow) rowIterator.next();

                HSSFCell cell = row.getCell(1);

                String CellValue;
                int cellValueInt;
                *//*if(HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()){
                    cellValueInt = (int) cell.getNumericCellValue();
                    CellValue = Integer.toString(cellValueInt);
                }
                else*//* if(cell.getStringCellValue()==null){
                    break;
                }         else
                  CellValue = (String) cell.getStringCellValue();

            }
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return cells;


    }*/
}
