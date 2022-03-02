package com.example.medicationreminder.workmanger;

import static com.example.medicationreminder.medications.view.MedicationsFragment.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DailyWorker extends Worker {
    long[]drugTimes;
    Context context;
    public DailyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG, "doWork: " );
        drugTimes = getInputData().getLongArray("drugTimes");
        String medName = getInputData().getString("medName");
        long currentTime = Calendar.getInstance().getTimeInMillis();

        if (drugTimes!=null) {
            for (long drugTime : drugTimes) {

                long delayTime = drugTime / 1000l-currentTime / 1000l ;
                Log.e(TAG, "doWork:calender time "+delayTime/1000l);
                OneTimeWorkRequest createRequest = new OneTimeWorkRequest.Builder(ReminderWorkerDrugs.class)
                        .addTag(medName).setInputData(
                                new Data.Builder().putLong("alarmTime", drugTime)
                                        .putString("medName", medName)
                                        .putLongArray("drugList", drugTimes).build()
                        ).setInitialDelay(delayTime, TimeUnit.SECONDS).build();
                WorkManager.getInstance(context).enqueue(createRequest);
            }

            return Result.success();
        }
        return Result.failure();
    }
}
