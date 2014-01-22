package varleyBrianJ.SimpleOffset;

import java.math.BigDecimal;

import varleyBrianJ.SimpleOffset.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CalcResult extends Activity implements OnClickListener{
	
TextView result1,result2,result3;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        final Intent intent1=new Intent(this,AboutActivity.class);
		final Intent intent2=new Intent(this,MainActivity.class);
		final Intent intent3=new Intent(this,MainActivity.class);
		
		final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
	            R.layout.a,
	            null);

	    // Set up your ActionBar
	    final ActionBar actionBar = getActionBar();
	    actionBar.setDisplayShowHomeEnabled(false);
	    actionBar.setDisplayShowTitleEnabled(false);
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setCustomView(actionBarLayout);

	    // You customization
	    final int actionBarColor = getResources().getColor(R.color.action_bar);
	    actionBar.setBackgroundDrawable(new ColorDrawable(actionBarColor));

	    final Button actionBarHome = (Button) findViewById(R.id.action_bar_title);
	    actionBarHome.setBackgroundResource(R.drawable.ic_action_back);
	    actionBarHome.setOnClickListener(this);
	    actionBarHome.setOnClickListener(new View.OnClickListener() {
       	 
			@Override
			public void onClick(View view) {				
				
			   startActivity(intent2);
		      
		    }
 
		});
	    
	    final Button actionBarInfo = (Button) findViewById(R.id.action_bar_staff);
	    actionBarInfo.setBackgroundResource(R.drawable.ic_action_help);
	    actionBarInfo.setOnClickListener(this);
	    actionBarInfo.setOnClickListener(new View.OnClickListener() {
       	 
			@Override
			public void onClick(View view) {				
				
			   startActivity(intent1);
		      
		    }
 
		});
	    
	    
	    final Button actionBarHoome = (Button) findViewById(R.id.action_bar_home);
	    actionBarHoome.setBackgroundResource(R.drawable.appicon);
	    actionBarHoome.setOnClickListener(this);
	    actionBarHoome.setOnClickListener(new View.OnClickListener() {
       	 
			@Override
			public void onClick(View view) {				
				
			   startActivity(intent3);
		      
		    }
 
		});
        result1 = (TextView)findViewById(R.id.markOne);
        result2 = (TextView)findViewById(R.id.markTwo);
        result3 = (TextView)findViewById(R.id.markThree);

        

        Intent intent = getIntent();
        double markOne = intent.getDoubleExtra("number1", 0);
        double markTwo = intent.getDoubleExtra("number2", 0);
        double markThree = intent.getDoubleExtra("number3", 0);

       
        result1.setText(String.valueOf(markOne)+"mm");
        result2.setText(String.valueOf(markTwo)+"mm");
        result3.setText(String.valueOf(markThree)+"mm");
        
        //Amit,I commented out the round method as it has caused errors in the calculations,also 
        //the input crashes if I put example: 150.1 or 160.22
        
        /*
        result1.setText(String.valueOf(round(markTwo,2))+"mm");
        result2.setText(String.valueOf(round(markTwo,2))+"mm");
        result3.setText(String.valueOf(round(markThree,2))+"mm");
       */
    }

	/*
    public static String round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bd.doubleValue());
    }
*/
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
 	
	

}
