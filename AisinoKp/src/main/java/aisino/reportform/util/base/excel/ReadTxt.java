package aisino.reportform.util.base.excel;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ReadTxt {public static void main(String[] args) throws IOException {
	ReadTxt app = new ReadTxt();
    app.readTxtFile("C:\\Users\\Hzp\\Desktop\\MerchData_20150504.txt");
    
}

public void compareBufferAndLineNumber() throws IOException {
    String fileName = "C:\\Users\\Hzp\\Desktop\\MerchData_20150504.txt";
    long time = System.currentTimeMillis();

    System.out.println("LineNumberReader" + this.getTotalLines(fileName));
    System.out.println(System.currentTimeMillis() - time);

    time = System.currentTimeMillis();

    System.out.println("BufferedReader" + this.getTotalLines2(fileName));
    System.out.println(System.currentTimeMillis() - time);

    time = System.currentTimeMillis();

    System.out.println("BufferedInputStream" + this.count(fileName));
    System.out.println(System.currentTimeMillis() - time);

    // 从输出结果来看，反而是BufferedInputStream是最快的。
    // LineNumberReader8663721
    // 6203
    // BufferedReader8663721
    // 6016
    // BufferedInputStream8663720
    // 1609

}

/**
 * 采用 LineNumberReader方式读取总行数
 * 
 * @param fileName
 * @return
 * @throws IOException
 */
private int getTotalLines(String fileName) throws IOException {
    FileReader in = new FileReader(fileName);
    LineNumberReader reader = new LineNumberReader(in);
    String strLine = reader.readLine();
    int totalLines = 0;
    while (strLine != null) {
        totalLines++;
        strLine = reader.readLine();
    }
    reader.close();
    in.close();
    return totalLines;
}

/**
 * 采用BufferedReader方式读取总行数
 * 
 * @param fileName
 * @return
 * @throws IOException
 */
private int getTotalLines2(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    String strLine = br.readLine();
    int totalLines = 0;
    while (strLine != null) {
        totalLines++;
        strLine = br.readLine();
    }
    br.close();
    return totalLines;
}

/**
 * 采用BufferedInputStream方式读取文件行数
 * 
 * @param filename
 * @return
 * @throws IOException
 */
public int count(String filename) throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(filename));
    byte[] c = new byte[1024];
    int count = 0;
    int readChars = 0;
    while ((readChars = is.read(c)) != -1) {
    	
        for (int i = 0; i < readChars; ++i) {
            if (c[i] == '\n')
                ++count;
        }
    }
    is.close();
    return count;
}
public  void readTxtFile(String filePath) throws IOException{
	String encoding = "GBK";
	File file = new File(filePath);
	int line=0;
	if (file.isFile() && file.exists()) { // 判断文件是否存在
		InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			line++;
			
			String[]lineTxts=lineTxt.split("\\|");
			if(line==1){
				continue;
			}else{
				//System.out.println(lineTxt);
				for (int i = 0; i < lineTxts.length; i++) {
					System.out.println(lineTxts[i].trim());
				}
			}
		}
		read.close();
	} else {
		System.out.println("找不到指定的文件");
	}
}

}
