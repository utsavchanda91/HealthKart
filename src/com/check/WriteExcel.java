package com.check;

import jxl.CellView;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.Workbook;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 22/11/13
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class WriteExcel {
    String fileName;
    private WritableCellFormat times;
    private WritableCellFormat timesBoldUnderline;
    WriteExcel(String fileNames){
        this.fileName = fileNames;
    }
    public void writeData(ArrayList<ArrayList<String>> outcomes) throws IOException,WriteException{
        File file = new File(fileName);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook  workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet, outcomes);
        workbook.write();
        workbook.close();
    }
    private void createLabel(WritableSheet sheet) throws WriteException{
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        times = new WritableCellFormat(times10pt);
        times.setWrap(true);

        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        //cv.setAutosize(true);

        addCaption(sheet, 0, 0, "Account");
        addCaption(sheet, 1, 0, "Destination URL");
        addCaption(sheet, 2, 0, "Campaign");
        addCaption(sheet, 3, 0, "Ad group");
        addCaption(sheet, 4, 0, "Status Code");
    }
    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }
    private void createContent(WritableSheet sheet, ArrayList<ArrayList<String>> outcomes) throws WriteException{
        for(int i=0; i<outcomes.size(); i++){
            for(int j=0; j<outcomes.get(i).size(); j++){
                Label label= new Label(j, i+1, outcomes.get(i).get(j));
                sheet.addCell(label);
            }
        }
        //System.out.println("write content");

    }

}
