package com.example.gdrivetest;


import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DriveServiceHelper {

    private final Executor mExecutor = Executors.newSingleThreadExecutor();
    private Drive mDriveService;
    private static final String TAG = "DriveServiceHelper";

    public DriveServiceHelper(Drive mDriveService) {
        this.mDriveService = mDriveService;
    }

    public Task createFilePDF(String filePath) {
        return Tasks.call(mExecutor, () -> {
            File fileMetaData = new File();
            fileMetaData.setName("MyPDFFile");

            java.io.File file = new java.io.File(filePath);
            FileContent mediaContent = new FileContent("application/pdf", file);
            File mFile = null;
            try {
                mFile = mDriveService.files().create(fileMetaData, mediaContent).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (mFile == null) {
                throw new IOException("Null result when requesting file creation");
            }
            return mFile.getId();
        });
    }

    public Task queryFile () {

        return Tasks.call(mExecutor, () -> {

            Log.d(TAG, "luqueiro test ");
            FileList result = mDriveService.files().list().setSpaces("drive").execute();
            List<File> files = result.getFiles();
            if (files == null || files.isEmpty()) {
                //System.out.println("No files found.");
                Log.d(TAG, "No files found luqueiro");
            }else {
                System.out.println("Files: for luqueiro");
                for (File file : files) {
                    System.out.printf("%s (%s)\n", file.getName(), file.getId());
                }
            }

            return files;
        });

    }

}









