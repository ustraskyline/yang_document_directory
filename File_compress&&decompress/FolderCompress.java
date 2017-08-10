import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FolderCompress {
	public static void main(String[] args) throws IOException {
		// the file path need to compress
		// 需要被压缩的文件夹
		File file = new File("D:\\yang"); 
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\test.zip"));

		// 对文件夹的压缩，是分别对各文件的压缩，然后再将文件夹打包成一个Zip文件
		if (file.isDirectory()) {
			// get the every file in the directory
			File[] files = file.listFiles();

			for (int i = 0; i < files.length; i++) {
				// 为每一个文件分别建立一个输入流
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(files[i]));
				
				// 设置zip文件夹内各文件被压缩后的名字
				zos.putNextEntry(new ZipEntry(file.getName() + file.separator + files[i].getName()));
				while (true) {
					byte[] b = new byte[100];
					int len = bis.read(b); // 将文件夹内各文件的数据读入此数组
					if (len == -1)
						break;
					zos.write(b, 0, len); // 将数组中的数据写出到输出流
				}
				bis.close();
			}

		}
		zos.close();
	}
}