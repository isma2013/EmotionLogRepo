package bg.android.isma.emotionlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread chrono = new Thread(){
            /* To set how long the splash screen will remain on the screen*/
            public void run(){
                try {
                    sleep(5000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent intentLogin = new Intent(Splash.this, LoginActivity.class);
                    Splash.this.startActivity(intentLogin);
                    Splash.this.finish();
                }
            }
        };
        chrono.start();
    }
}