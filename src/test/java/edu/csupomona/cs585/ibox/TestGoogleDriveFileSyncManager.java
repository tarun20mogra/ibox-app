package edu.csupomona.cs585.ibox;

import junit.framework.TestCase;
import org.junit.*;
import org.mockito.internal.listeners.MockingProgressListener;

import static org.mockito.Mockito.*;


import java.io.IOException;
import java.util.ArrayList;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;

import com.google.api.services.drive.Drive.Comments.Update;

import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

public class TestGoogleDriveFileSyncManager extends TestCase {
	public Drive mockDrive = mock(Drive.class);
	public File file = new File();
	public java.io.File mockFile = mock(java.io.File.class);
	public Files files = mock(Files.class);
	public Insert mockInsert = mock(Insert.class);
	public GoogleDriveFileSyncManager google_drive_file_sync_manager = new GoogleDriveFileSyncManager(mockDrive);
	public ArrayList<File> arrayList = new ArrayList<File>();
	public com.google.api.services.drive.Drive.Files.List mockList = mock(
			com.google.api.services.drive.Drive.Files.List.class);
	public FileList fileList_obj = new FileList();
	public File Fileobj = new File();
	public com.google.api.services.drive.Drive.Files.Update mockUpdate = mock(com.google.api.services.drive.Drive.Files.Update.class);
	public com.google.api.services.drive.Drive.Files.Delete mockDelete = mock(com.google.api.services.drive.Drive.Files.Delete.class);
	public File Fileobj2 = new File();
	
	

	@Test

	public void test_addFile_For_Adding_a_FileToDrive() throws IOException {

		System.out.println("This is Test method for adding a file to mock drive");
		when(mockDrive.files()).thenReturn(files);
		when(files.insert(isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(mockInsert);
		when(mockInsert.execute()).thenReturn(file);
		google_drive_file_sync_manager.addFile(mockFile);
		verify(mockDrive).files();
		verify(files).insert(isA(File.class), isA(AbstractInputStreamContent.class));
		verify(mockInsert).execute();
	}

	@Test
	public void test_updateFile_For_Updating_a_FileOnDrive() throws IOException {

		System.out.println("This is Test method for updating a file on mock drive");
		String fileID = "1235";
		String FileName = "FileNameForTest.txt";

		Fileobj.setTitle(FileName);
		Fileobj.setId(fileID);
		arrayList.add(Fileobj);
		fileList_obj.setItems(arrayList);
		when(mockFile.getName()).thenReturn(FileName);
		when(files.list()).thenReturn(mockList);
		when(mockList.execute()).thenReturn(fileList_obj);
		when(mockDrive.files()).thenReturn(files);
		when(files.update(isA(String.class), isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(mockUpdate);
		when(mockUpdate.execute()).thenReturn(Fileobj2);
		google_drive_file_sync_manager.updateFile(mockFile);
		verify(mockFile, atLeast(1)).getName();
		verify(files).list();
		verify(mockList).execute();
		verify(mockDrive, atLeast(1)).files();
		verify(files).update(isA(String.class), isA(File.class), isA(AbstractInputStreamContent.class));
		verify(mockUpdate).execute();

	}

	 @Test
	 public void test_deleteFile_For_Deleting_a_FileFromDrive() throws
	 IOException {
	
		 System.out.println("This is Test method for deleting a file from mock drive");
		 
		String fileID = "1235";
		String FileName = "FileNameForTest.txt";
		Fileobj.setTitle(FileName);
		Fileobj.setId(fileID);
		fileList_obj.setItems(arrayList);
		arrayList.add(Fileobj);
		when(mockFile.getName()).thenReturn(FileName);
		when(files.list()).thenReturn(mockList);
		when(mockList.execute()).thenReturn(fileList_obj);
		when(mockDrive.files()).thenReturn(files);
		when(files.delete(fileID)).thenReturn(mockDelete);
		when(mockDelete.execute()).thenReturn(null);
		google_drive_file_sync_manager.deleteFile(mockFile);
		verify(mockList).execute();
		verify(files).delete(fileID);
		verify(mockDelete).execute();
			 
	 }

}