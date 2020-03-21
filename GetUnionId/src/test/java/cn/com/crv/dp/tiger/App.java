package cn.com.crv.dp.tiger;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Hello world!
 *
 */
public class App 
{
	private String fileDir = "F:\\unionid\\test\\";
	
    public static void main( String[] args )
    {
    	ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(19);
    	App app = new App();
    	String[] openIdFileList = app.getOpenIdFileList();
    	for (String fileName : openIdFileList) {
    		System.out.println(fileName);
    		FileRunner fileRunner = new FileRunner();
    		fileRunner.setOpenIdFileName(app.getFileDir() + fileName);
    		fileRunner.setUnionIdFileName(app.getFileDir() + fileName + ".txt");
    		scheduledThreadPool.execute(fileRunner);
    	}
    }
    
    public void process() {
    	
    }
    
    public String[] getOpenIdFileList() {
    	File file = new File(fileDir);
    	String[] files = file.list();
    	return files;
    }

	public String getFileDir() {
		return fileDir;
	}
    
}
