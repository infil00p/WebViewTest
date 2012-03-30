package org.infil00p.phoneTest;

import java.io.IOException;

import com.infil00p.phoneTest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.res.AssetManager;

public class TestActivity extends Activity {
    
    WebView appView;
    TestWebViewClient testClient;
    String TAG="FAILTAG";
    Bolt data = new Bolt();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        testClient = new TestWebViewClient();
        
        appView = (WebView) findViewById(R.id.appView);
        appView.getSettings().setDatabaseEnabled(true);
        appView.getSettings().setJavaScriptEnabled(true);
        appView.addJavascriptInterface(data, "test");
        
        appView.setWebViewClient(testClient);
        
        appView.loadUrl("file:///android_asset/index.html");
        
    }
    
    class TestWebViewClient extends WebViewClient {
     
      @Override
      public void onReceivedError(WebView view, int errCode, String desc, String url)
      {
        Log.d(TAG, "Error: " + desc);
        Log.d(TAG, "ErrorURL: " + url);
      }
    }
    
    /**
     * Called when a key is pressed.
     * 
     * @param keyCode
     * @param event
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.appView == null) {
            return super.onKeyDown(keyCode, event);
        }

        // If back key
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if(this.appView.canGoBack())
            {
               this.appView.goBack();
               return true;
            }
            else
            {
                return super.onKeyDown(keyCode, event);
            }
        }
        return false;
    }
}