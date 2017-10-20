package comm.yang.uiauto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Environment;
import android.os.RemoteException;
import android.util.Log;
import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;

@SuppressWarnings("deprecation")
public class UiEmulate extends UiAutomatorTestCase {

	UiDevice device = UiDevice.getInstance();
	LinkedList<String> list = new LinkedList<String>();

	public void test() throws RemoteException, UiObjectNotFoundException, IOException, SQLException {
		Log.i("yang", "===============================================>开始执行脚本<===============================================");
		Log.i("yang", "---->开始读入公司名单");
		
		String path = getRootPath();
		File rootFolder = new File(path); // 存放图片目录
		if (!rootFolder.exists()) {
			rootFolder.mkdirs();
		}
		
		File file = new File(rootFolder, "name.txt");
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String data = null;
			while ((data = reader.readLine()) != null) {
				list.add(data.replace("\"", ""));
			}
			
			reader.close();
		}else {
			Log.i("yang", "Error:公司名单文件不存在，请将其放入到XScreenShot目录下");
			return;
		}
		
		Log.i("yang", "---->公司名单读入完毕");

		// adb shell
		Runtime.getRuntime().exec("am start com.android.icredit/.ui.SplashActivity");

		// 首页输入框
		UiObject obj_1 = new UiObject(new UiSelector().resourceId("com.android.icredit:id/zh"));// 6.0:com.android.icredit:id/zh
																								// mi :
																								// com.android.icredit:id/xd
		obj_1.click();

		for (String param : list) {
			// 查询页面输入框
			UiObject obj_2 = new UiObject(new UiSelector().resourceId("com.android.icredit:id/yt"));// 6.0:com.android.icredit:id/yt
																									// MI:com.android.icredit:id/wq
			obj_2.click();
			obj_2.setText(Utf7ImeHelper.e(param.substring(0, 9)));// 中交一公局第一工程有限公司
			// sleep(2000);

			UiObject obj_3 = new UiObject(new UiSelector().resourceId("com.android.icredit:id/ug"));// 6.0:com.android.icredit:id/ug
																									// MI:com.android.icredit:id/se
			Log.i("yang", "=======>获得条数为：" + getItemCount(obj_3.getText()));
			int count = Integer.valueOf(getItemCount(obj_3.getText()));


			if (count > 0) {
				for (int i = 2; i < 5; i++) {
					UiObject item = new UiObject(new UiSelector().className("android.widget.RelativeLayout").instance(i));
					UiObject itemName = item.getChild(new UiSelector().resourceId("com.android.icredit:id/s9"));
					if (param.equals(itemName.getText())) {
						item.click();
						sleep(1000);
						// 6.0:com.android.icredit:id/s8 MI:com.android.icredit:id/qb
						UiObject icon = new UiObject(new UiSelector().resourceId("com.android.icredit:id/s8"));
						crateBitmap(icon, param);

						device.pressBack();

						// 6.0:com.android.icredit:id/yv MI:com.android.icredit:id/ws
						UiObject delKey = new UiObject(new UiSelector().resourceId("com.android.icredit:id/yv"));
						delKey.click();

						break;
					} else if(i == 4) {
						// 第三次仍未找到，写入到文件中
						unfoundToTxt(param);
					}
				}
			}
		}
		
		Log.i("yang", "===============================================>脚本执行完毕<===============================================");
	}

	// 创建bitmap
	public void crateBitmap(UiObject icon, String companyName) throws UiObjectNotFoundException {
		String path = getRootPath();

		File rootFolder = new File(path); // 存放图片目录
		if (!rootFolder.exists()) {
			rootFolder.mkdirs();
		}

		File imageFile = new File(rootFolder, companyName + ".png"); // 代表图片的File对象
		if (!imageFile.exists()) {
			try {
				imageFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 将截图保存在File对象中
		device.takeScreenshot(imageFile); // 截图操作, 此操作与Bitmap有关联的是参数 imageFile，是代表图片的File对象
		Log.i("yang", "****>截图成功");
		sleep(1000);

		String filePath = imageFile.getAbsolutePath();

		Log.i("yang", "*****>图片绝对路径为：" + filePath);
		Bitmap bitmap = BitmapFactory.decodeFile(filePath);
		Rect rect = icon.getBounds();
		bitmap = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());

		saveBitMapToSdcard(bitmap, imageFile);
	}

	// 将代表图片的File对象包装成文件输出流，压缩保存bitmap图
	public void saveBitMapToSdcard(Bitmap bitmap, File file) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);

			if (out != null) {
				// 三个参数分别为格式、保存的文件质量100为原图的100%、文件流
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
				out.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getItemCount(String content) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(content);
		while (m.find()) {
			return m.group();
		}
		return null;
	}

	private void unfoundToTxt(String param) throws IOException, UiObjectNotFoundException {
		// 3次循环都未找到目标公司，将其写入到文件中，后续再进行处理
		String path = getRootPath();

		File rootFolder = new File(path);
		if (!rootFolder.exists()) {
			rootFolder.mkdirs();
		}

		File unfoundList = new File(rootFolder, "未找到图片公司名单.txt");
		if (!unfoundList.exists()) {
			try {
				unfoundList.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileWriter fw = new FileWriter(unfoundList, true);
		fw.write(param + "\r\n");

		Log.i("yang", "未发现Icon的公司为：" + param + ", 已将其写入名单");
		fw.close();
		
		// 6.0:com.android.icredit:id/yv MI:com.android.icredit:id/ws
		UiObject delKey = new UiObject(new UiSelector().resourceId("com.android.icredit:id/yv"));
		delKey.click();
	}

	private String getRootPath() {
		String rootPath = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : null;
		String path = rootPath + File.separator + "XScreenShot" + File.separator; // 存放图片根目录路径
		
		return path;
	}
}
