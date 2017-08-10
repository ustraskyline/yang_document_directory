import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileCompress {
	public static void main(String[] args) throws IOException {
		// 需要被压缩的文件输入流
		FileInputStream fis = new FileInputStream("D:\\yangjia.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);

		// 压缩后的文件输出流
		// D:\\yangjia.zip : 外部zip文件的路径和名字
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\yangjia.zip"));
		BufferedOutputStream bos = new BufferedOutputStream(zos);

		// 设置zip内部被压缩文件的名字
		zos.putNextEntry(new ZipEntry("compress.txt"));

		// 设置压缩文件的注释
		zos.setComment("by yangjia COMPRESS");

		byte[] b = new byte[100];
		while (true) {
			int len = bis.read(b); // 将文件输入流读取的数据放在数组b中
			if (len == -1)
				break;
			bos.write(b, 0, len); // 将数组b中的数据写出到文件输出流
		}
		fis.close();
		zos.close();
	}
}
