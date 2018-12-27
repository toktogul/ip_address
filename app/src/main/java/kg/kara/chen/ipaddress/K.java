package kg.kara.chen.ipaddress;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class K extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null && connManager.isDefaultNetworkActive()) {
            NetworkInfo types = connManager.getActiveNetworkInfo();
            if (types.getType() == ConnectivityManager.TYPE_WIFI) {
                final WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (mWifiManager != null && mWifiManager.isWifiEnabled()) {
                    int ip = mWifiManager.getConnectionInfo().getIpAddress();
                    String builder = String.valueOf(ip & 0xFF) +
                            "." +
                            ((ip >> 8) & 0xFF) +
                            "." +
                            ((ip >> 16) & 0xFF) +
                            "." +
                            ((ip >> 24) & 0xFF);
                    textView.setText(builder);
                } else {
                    textView.setText("error");
                }
            } else textView.setText("there is no wi-fi connection");
        } else {
            textView.setText("there is no any connection");
        }
        setContentView(textView);
    }
}
