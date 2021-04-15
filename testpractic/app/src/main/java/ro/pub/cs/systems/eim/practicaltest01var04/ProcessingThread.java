package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread
{
    private Context context = null;
    private String message = null;

    public ProcessingThread(Context context, String message)
    {
        this.context = context;
        this.message = message;
    }

    @Override
    public void run()
    {
        Log.d(Constants.TAG, "SERVICE STARTED");
        sleep();
        sendMessage();

        Log.d(Constants.TAG, "SERVICE SENT MESSAGE");
    }

    private void sendMessage()
    {
        Intent intent = new Intent();
        intent.setAction(Constants.INTENT_ACTION);
        intent.putExtra(Constants.BROADCAST_TAG,  message);

        context.sendBroadcast(intent);
    }

    private void sleep()
    {
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }


}