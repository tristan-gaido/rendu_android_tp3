package com.example.tp2;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DownloadAndParseService extends IntentService {

    public static final String ACTION_PARSE_DATA = "com.example.tp2.action.PARSE_DATA";

    public DownloadAndParseService() {
        super("DownloadAndParseService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PARSE_DATA.equals(action)) {
                handleActionParseData();
            }
        }
    }

    private void handleActionParseData() {
        try {
            FileInputStream fis = openFileInput("UserData.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startActionParseData(Context context) {
        Intent intent = new Intent(context, DownloadAndParseService.class);
        intent.setAction(ACTION_PARSE_DATA);
        context.startService(intent);
    }

}
