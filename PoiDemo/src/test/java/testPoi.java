import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class testPoi {

    @Test
    public void testObjcet(){
        List<Object> list  = new ArrayList<>();
        list.add("test");
        list.add(123);

        for (Object object : list){
            if (object instanceof String){
                System.out.println("String");
            }
            if (object instanceof Integer){
                System.out.println("Integer");
            }
        }

    }


    @Test
    public void testPoi1() throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = new FileInputStream("d:\\测试.xls");
        File file = new File("d:\\out2.csv");
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);//试用XSSF的原因可以读取 XLSX，否则会报错
        XSSFSheet dataSheet = wb.getSheetAt(0);//获取第一个sheet
        if (dataSheet == null){
            return;
        }

        Iterator<Row> iterator = dataSheet.iterator();
        while (iterator.hasNext()){
            Row row = iterator.next();//获取一行
            Iterator<Cell> cellIterator = row.iterator();
            while(cellIterator.hasNext()){
                Cell cell = cellIterator.next();//获取一行中的一列
                System.out.print(PoiUtil.getCellValue(cell) + " ");
                String result = PoiUtil.getCellValue(cell);//根据cell的类型获取值
                if(result.indexOf(".")>0){
                    sb.append(   result.substring(0,result.indexOf(".")-1)).append(",");
                }else{
                    sb.append(result);
                }
            }
            System.out.println();
            sb.append("\n");
        }

        FileUtils.writeStringToFile(file,sb.toString(),"UTF-8",false);
    }


}
