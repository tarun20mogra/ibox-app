package edu.csupomona.cs585.ibox;

import junit.framework.TestCase;
import org.junit.*;
import org.mockito.internal.listeners.MockingProgressListener;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;

import com.google.api.services.drive.Drive.Comments.Update;

import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;

public class Integration_Test_For_GoogleDriveFileSyncManager extends TestCase {

	GoogleDriveFileSyncManager google_drive_file_sync_manager = new GoogleDriveFileSyncManager(
			GoogleDriveServiceProvider.get().getGoogleDriveClient());
	@Test
	public void test_addFile_For_Adding_a_FileToDrive() throws IOException {

		boolean result1 = false;
		java.io.File addfileobj = new java.io.File("E://tarun.txt");
		google_drive_file_sync_manager.addFile(addfileobj);
		String result = google_drive_file_sync_manager.getFileId("tarun.txt");

		if (result != null) {
			result1 = true;
			}
		assertEquals(true, result1);
		System.out.println(result);
	}

	@Test
	public void test_UpdateFile_For_Updating_A_FileToDrive() throws IOException {
		
		boolean result1 = false;
		java.io.File updatefileobj = new java.io.File("E://tarun.txt");
		File fileobj = new File();
		FileContent Content = new FileContent("hi there", updatefileobj);
		google_drive_file_sync_manager.updateFile(updatefileobj);	
		String Filename = "tarun.txt";
		fileobj.setTitle(Filename);
		String FileID = google_drive_file_sync_manager.getFileId(Filename);
		if(FileID != null)
		{
			result1 = true;
			
		}
		assertEquals(true, result1);
		System.out.println(FileID);
		
	}
	
	
	@Test
	public void test_DeleteFile_For_Deleting_A_FileFromDrive() throws IOException
	{
		
		java.io.File deletefileobj = new java.io.File("E:\\tarun.txt");
		google_drive_file_sync_manager.deleteFile(deletefileobj);
		String FileID = google_drive_file_sync_manager.getFileId("tarun.txt");
		if(FileID != null)
		{
			System.out.println("File Exists");
		
		}
		
		
		
	}

}
