import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileName {


    @Test
    public  void testGetFile() throws IOException {

       getFileName("E:\\work\\weixinProject\\微信公众号\\相关资料","E:\\FileName.csv");
       getFileName("E:\\work\\weixinProject\\云软新文档","E:\\FileName.csv");

    }


    public void getFileName(String rootPath,String exelPath) throws IOException {

        File file = new File(rootPath);
        File outfile = new File(exelPath);
        StringBuilder sb  = new StringBuilder();
        if(!file.exists()){
            System.out.println(rootPath + " not exists");
            return;
        }
        File[] files = file.listFiles();
        if (file.length() == 0){
            System.out.println(rootPath + " is an empty folder");
            return;
        }

        for (File childFile: files){
            if (childFile.isDirectory()){
               getFileName(childFile.getAbsolutePath(),"E:\\FileName.csv");
            }else {
                sb.append(childFile.getName()+",\n");
            }
        }

        FileUtils.writeStringToFile(outfile,sb.toString(),true);

    }

 }
