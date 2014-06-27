package app42.fileupload;

import java.io.InputStream;
import java.util.Vector;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.shephertz.app42.paas.sdk.jme.App42BadParameterException;
import com.shephertz.app42.paas.sdk.jme.App42Exception;
import com.shephertz.app42.paas.sdk.jme.App42SecurityException;
import com.shephertz.app42.paas.sdk.jme.ServiceAPI;
import com.shephertz.app42.paas.sdk.jme.upload.Upload;
import com.shephertz.app42.paas.sdk.jme.upload.Upload.File;
import com.shephertz.app42.paas.sdk.jme.upload.UploadFileType;
import com.shephertz.app42.paas.sdk.jme.upload.UploadService;

public class FileUpload extends MIDlet {

	public FileUpload() {
		// TODO Auto-generated constructor stub
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		// Method Called File Upload
		fileUpload();

	}

	public void fileUpload() {
		// Enter your Public Key and Private Key Here in Constructor. You can
		// get it once you will create a app in app42 console
		ServiceAPI sp = new ServiceAPI("YOUR_API_KEY","YOUR_SECRET_KEY");
		// Create Instance of Upload Service
		UploadService uploadService = sp.buildUploadService();

		try {
			  // This test.txt File pic from res folder.
			InputStream stream = getClass().getResourceAsStream("/test.txt");
			System.out.println("Input Stream " + stream);
			UploadFileType filetype = null;
			String uploadFiletype = filetype.IMAGE;
			Upload upload = uploadService.uploadFile("Dummy File", stream,
					uploadFiletype, "My File Description.");
			// Fetch the returned JSON response
			System.out
					.println("File Upload  Successfull !!! JSON Response is : "
							+ upload);

			// Fetch file details from upload object.
			Vector fileList = upload.getFileList();
			for (int i = 0; i < fileList.size(); i++) {
				File file = (File) fileList.elementAt(i);
				String fileName = file.getName();
				System.out.println("File Name " + fileName);
				String fileType = file.getType();
				System.out.println("File Type " + fileType);
				String name = file.getName();
				System.out.println("Name " + name);
				String url = file.getUrl();
				System.out.println("URL  " + url);
			}
		} catch (App42BadParameterException ex) {
			System.out.println("App42BadParameterException ");
		} catch (App42SecurityException e) {
			System.out
					.println("Please verify your API_KEY and SECRET_KEY from AppHq Management console(apphq.shephertz.com).");
		} catch (App42Exception ex) {
			System.out.println("App42Exception ");
		}
	}
}
