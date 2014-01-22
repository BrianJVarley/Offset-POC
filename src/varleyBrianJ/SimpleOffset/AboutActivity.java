package varleyBrianJ.SimpleOffset;

import varleyBrianJ.SimpleOffset.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;


public class AboutActivity extends Activity implements OnClickListener, OnTouchListener{
	
	
	private ImageSwitcher imageSwitcher;
	Button btnNext;
	TextView tView;

	private Vibrator myVib;
	
	int imageIds[]=
	{R.drawable.mark1,R.drawable.mark2,R.drawable.mark3};
	int messageCount=imageIds.length;
	// to keep current Index of ImageID array
	int currentIndex=-1;
	GestureDetector gestureDetector;
	private int clicks = 1;
	 int height,width;
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
         height = size.y;
        
        final Intent intent1=new Intent(this,AboutActivity.class);
		final Intent intent2=new Intent(this,CalcResult.class);
		final Intent intent3=new Intent(this,MainActivity.class);
		
		final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.a,null);
		
		// get The references
	    btnNext=(Button)findViewById(R.id.buttonNext);
	    imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
	    tView = (TextView) findViewById(R.id.textView1);
	    // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
	    imageSwitcher.setFactory(new ViewFactory() {
	    public View makeView() {
	    // TODO Auto-generated method stub
	    // Create a new ImageView set it's properties
	    ImageView imageView = new ImageView(getApplicationContext());
	    imageView.setScaleType(ImageView.ScaleType.FIT_START);
	    imageView.setAdjustViewBounds(true);
	    imageView.setLayoutParams(new
	    ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,height/2));
	    return imageView;
	    }
	    });
	   
	 // Declare the animations and initialize them
	    Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
	    Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
	    // set the animation type to imageSwitcher
	    imageSwitcher.setInAnimation(in);
	    imageSwitcher.setOutAnimation(out);
	    
	    
	    imageSwitcher.setImageResource(imageIds[++currentIndex]);
	    tView.setText(getMyString(clicks++, this));
	    // ClickListener for NEXT button
	    // When clicked on Button ImageSwitcher will switch between Images
	    // The current Image will go OUT and next Image will come in with specified animation
	    btnNext.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {
	    // TODO Auto-generated method stub
	    currentIndex++;
	    // If index reaches maximum reset it
	    if(currentIndex==messageCount)
	    currentIndex=0;
	    imageSwitcher.setImageResource(imageIds[currentIndex]);
	    tView.setText(getMyString(clicks++, v.getContext()));
	    
	    }
	       
	    });
	   imageSwitcher.setOnTouchListener(this);
	    
	    // Set up your ActionBar
	    final ActionBar actionBar = getActionBar();
	    actionBar.setDisplayShowHomeEnabled(false);
	    actionBar.setDisplayShowTitleEnabled(false);
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setCustomView(actionBarLayout);

	    // You customization
	    final int actionBarColor = getResources().getColor(R.color.action_bar);
	    actionBar.setBackgroundDrawable(new ColorDrawable(actionBarColor));

	    final Button actionBarHome = (Button)actionBarLayout. findViewById(R.id.action_bar_title);
	   actionBarHome.setBackgroundResource(R.drawable.ic_action_back);
	    actionBarHome.setOnClickListener(this);
	    actionBarHome.setOnClickListener(new View.OnClickListener() {
       	 
			@Override
			public void onClick(View view) {				
				
			   startActivity(intent2);
		      
		    }
 
		});
	   
	    final Button actionBarInfo = (Button)actionBarLayout. findViewById(R.id.action_bar_staff);
	   actionBarInfo.setBackgroundResource(R.drawable.ic_action_help);
	    actionBarInfo.setOnClickListener(this);
	    actionBarInfo.setOnClickListener(new View.OnClickListener() {
       	 
			@Override
			public void onClick(View view) {				
				
			   startActivity(intent1);
		      
		    }
 
		});
	    
	    final Button actionBarHomeIcon = (Button) findViewById(R.id.action_bar_home);
	    actionBarHomeIcon.setBackgroundResource(R.drawable.appicon);
	    actionBarHomeIcon.setOnClickListener(this);
	    actionBarHomeIcon.setOnClickListener(new View.OnClickListener() {
       	 
			@Override
			public void onClick(View view) {				
				
			   startActivity(intent3);
		      
		    }
 
		});
	    
     
	  gestureDetector = new GestureDetector(new GestureListener());
	    
	}
	
	//method called to update textview strings.
	public String getMyString(int variable, Context applicationContext){
	    String text = "";  // create a String variable to return and give it whatever 
	                       // value you want in case none of the cases return true
	    switch(variable){  // assign the variable below
	        case 1:
	            text = applicationContext.getResources().getString(R.string.cutString);
	            break;
	        case 2:
	            text = applicationContext.getResources().getString(R.string.markString);
	            break;
	        case 3:
	            text = applicationContext.getResources().getString(R.string.measureString);
	            clicks = 1;
	            break;
	    }
	  return text; //just one return

	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		if (gestureDetector.onTouchEvent(event)) {
			System.out.println("");
            return true;      
        }
		
		
		return false;
	}
	 private final class GestureListener extends SimpleOnGestureListener {

	        private static final int SWIPE_THRESHOLD = 100;
	        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

	        @Override
	        public boolean onDown(MotionEvent e) {
	            return true;
	        }

	        @Override
	        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	            boolean result = false;
	            try {
	                float diffY = e2.getY() - e1.getY();
	                float diffX = e2.getX() - e1.getX();
	                if (Math.abs(diffX) > Math.abs(diffY)) {
	                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
	                        if (diffX > 0) {
	                            onSwipeRight();
	                            
	                            
	                        } else {
	                            onSwipeLeft();
	                        }
	                    }
	                } else {
	                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
	                        if (diffY > 0) {
	                            onSwipeBottom();
	                        } else {
	                            onSwipeTop();
	                        }
	                    }
	                }
	            } catch (Exception exception) {
	                exception.printStackTrace();


	      }
	        return result;
	    }
	}

	public void onSwipeRight() {
		
		currentIndex++;
	    // If index reaches maximum reset it
	    if(currentIndex==messageCount)
	    currentIndex=0;
	    imageSwitcher.setImageResource(imageIds[currentIndex]);
	    tView.setText(getMyString(clicks++,AboutActivity.this));
		
	}

	public void onSwipeLeft() {
	}

	public void onSwipeTop() {
	}

	public void onSwipeBottom() {
	}
	 public void showToast(String str)
	 {
		 Toast.makeText(AboutActivity.this,str, 1000).show();
	 }
}


