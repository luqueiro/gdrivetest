package com.example.gdrivetest;




import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DriveServiceHelper {

    private Drive service;
    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    public DriveServiceHelper(Drive mDriveService) {
        this.service = mDriveService;
    }


    public Task<FileList> queryFiles() {
        return Tasks.call(mExecutor, () ->
                service.files().list().setSpaces("drive").execute());


    }


}









