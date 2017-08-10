import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/*
 * 该类目前缺点：
 * 		1、无法解压缩嵌套目录
 * 		2、文件名不能含有中文
 */
public class FileDecompress {
	public static void main(String[] args) throws ZipException, IOException {
		// 需要解压缩的Zip文件
		File file = new File("D:\\yang.zip");

		// get a ZipFile instance
		ZipFile zipFile = new ZipFile(file);

		// 将需要解压缩的Zip文件包装成 ZipInputStream
		ZipInputStream zis = new ZipInputStream(new FileInputStream(file));

		// create a ZipEntry instance , lay the every file from
		// decompress file temporarily
		ZipEntry entry = null;

		// a circle to get every file
		while ((entry = zis.getNextEntry()) != null) {
			System.out.println("decompress file :" + entry.getName());

			// 定义存放解压缩后各文件存放路径
			File outFile = new File("D:\\yang_decompress\\" + entry.getName());

			// 对各文件分别创建BufferedInputStream
			BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

			// 分别对应各文件的BufferedOutputStream
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));
			byte[] b = new byte[100];
			while (true) {
				int len = bis.read(b); // 从压缩文件中把数据读入到数组b
				if (len == -1)
					break;
				bos.write(b, 0, len); // 把数据从数组b写出到输出流
			}
			// close stream
			bis.close();
			bos.close();
		}
		zis.close();

	}
}