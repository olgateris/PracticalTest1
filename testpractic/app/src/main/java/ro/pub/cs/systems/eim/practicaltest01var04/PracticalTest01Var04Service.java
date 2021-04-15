package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var04Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String message = intent.getStringExtra(Constants.second_nume) +" "+ intent.getStringExtra(Constants.second_grupa);

        processingThread = new ProcessingThread(this, message);
        processingThread.start();
        Log.d(Constants.TAG, "mesaj service");

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}

