package cn.com.crv.dp.tiger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.FileUtils;

public class UnionIdWriter {

	private static UnionIdWriter instance;
	private Lock lock = new ReentrantLock();
	private String unionIdFileName;
	
	private UnionIdWriter() {
	}
	
	public static UnionIdWriter getInstance() {
		return instance == null ? new UnionIdWriter() : instance;
	}
	
	public void writeUnionId(String data) throws IOException {
		lock.lock();
		try {
			File file = new File(unionIdFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileUtils.writeStringToFile(file, data, "ISO-8859-1", true);
		} finally {
			lock.unlock();
		}
	}

	public String getUnionIdFileName() {
		return unionIdFileName;
	}

	public void setUnionIdFileName(String unionIdFileName) {
		this.unionIdFileName = unionIdFileName;
	}
	
}
