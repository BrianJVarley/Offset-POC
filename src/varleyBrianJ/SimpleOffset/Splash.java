package varleyBrianJ.SimpleOffset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
                public void run() {
                        startActivity(new Intent(Splash.this, MainActivity.class));
                        finish();
                }
        }, secondsDelayed * 1000);
	}

}
