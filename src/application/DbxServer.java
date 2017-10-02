package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

public class DbxServer {

	private static final String ACCESS_TOKEN = "qDy35j9ltjAAAAAAAAAAKnbj4h82xOBZBxSBMFKO076JkmBz1Dd3wHWdXzX-k88Z";
	@SuppressWarnings("deprecation")
	static DbxRequestConfig config = new DbxRequestConfig("dropbox/TSA-2017", "en_US");
    static DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    
    public static void filesDownload(String user) throws DownloadErrorException, DbxException, IOException {
    	File file = new File(user);
    	
    	
        if (file.exists() && !file.isDirectory()) {
        	System.out.println("File already present");
		} else {
            DbxDownloader<FileMetadata> dl = client.files().download("/" + user);
            FileOutputStream fout = new FileOutputStream(user);
            dl.download(fout);
            System.out.println("File retrived");
		}
    }
    
    public static void filesUpload(String user) throws UploadErrorException, DbxException, IOException {
    	try (InputStream in = new FileInputStream(user)) {
    	    FileMetadata metadata = client.files().uploadBuilder("/" + user)
    	        .uploadAndFinish(in);
    	}
    }
}
