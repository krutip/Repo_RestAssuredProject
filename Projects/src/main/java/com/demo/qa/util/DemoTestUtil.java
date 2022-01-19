package com.demo.qa.util;
import com.demo.qa.base.DemoTestBase;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DemoTestUtil extends DemoTestBase {

    public static long PAGE_LOAD_TIMEOUT = 7000;
    public static long IMPLICIT_WAIT = 5000;



    public static String TESTDATA_SHEET_PATH = "D:\\Git projects\\ZinniaXTest\\src\\main\\java\\com\\zinniax\\qa\\testdata\\Zinniax.xls";
    public static Workbook book;
    static Sheet sheet;


    public static Object[][] getTestData(String sheetName) throws IOException, BiffException {
        FileInputStream Excelfile = new FileInputStream(TESTDATA_SHEET_PATH);

        //Read workbook
        Workbook Exbook = Workbook.getWorkbook(Excelfile);

        //Read sheet
        Sheet ExSheet = Exbook.getSheet(sheetName);

        int Rows = ExSheet.getRows();
        int Columns = ExSheet.getColumns();

        // Save both rows and columns in string array
        String zinniax[][] = new String[Rows - 1][Columns];
        int count = 0;


        //for loop to get the cell data
        for (int i = 1; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                Cell Excell = ExSheet.getCell(j, i);

                //get contents from string array
                zinniax[count][j] = Excell.getContents();
            }
            count++;
        }
        //ExSheet.removeRow(Rows);
        Excelfile.close();
        return zinniax;
    }


    public static void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");

        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
    }

    public static void ExplicitWaitSendkeys(WebElement element, int timeout, String value) {
        new WebDriverWait(driver, timeout).
                until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(value);

    }

}



