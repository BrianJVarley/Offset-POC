package varleyBrianJ.SimpleOffset;

import java.util.Locale;

import varleyBrianJ.SimpleOffset.R;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	AlertDialog alertDialog;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private Vibrator myVib;

	ViewPager mViewPager;
	EditText offsetLength,offsetDepth,ductDepth;
	ImageButton calculate;
	//DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
	String getoffsetlength; 
	String getoffsetdepth; 
	String getductdepth; 
	
	double off1,off2,off3;
	
	//button filter
	PorterDuffColorFilter greenFilter =  new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Intent intent1=new Intent(this,AboutActivity.class);
		final Intent intent2=new Intent(this,MainActivity.class);
		offsetLength = (EditText)findViewById(R.id.offLength);
	    offsetDepth = (EditText)findViewById(R.id.offDepth);
	    ductDepth = (EditText)findViewById(R.id.ductDepth);
	    calculate = (ImageButton)findViewById(R.id.calc);
	    calculate.setOnClickListener(this);
	    
		final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.a,null);

	    // Set up your ActionBar
	    final ActionBar actionBar = getActionBar();
	    actionBar.setDisplayShowHomeEnabled(false);
	    actionBar.setDisplayShowTitleEnabled(false);
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setCustomView(actionBarLayout);

	    //button vibration
	    myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
	    
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
	    //final Button actionBarSent = (Button) findViewById(R.id.action_bar_sent);
	   
	    final Button actionBarInfo = (Button) findViewById(R.id.action_bar_staff);
	    actionBarInfo.setBackgroundResource(R.drawable.ic_action_help);
	    actionBarInfo.setOnClickListener(this);
	    actionBarInfo.setOnClickListener(new View.OnClickListener() {
       	 
			@Override
			public void onClick(View view) {				
				
			   startActivity(intent1);  
		    }
 
		});
	   

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
			
			double marking1=2,marking2=2,marking3=2;
			
			
    		String getoffsetlength = offsetLength.getText().toString(); 
    		String getoffsetdepth = offsetDepth.getText().toString(); 
    		String getductdepth = ductDepth.getText().toString(); 
    		try
    		{
	    		if(Long.parseLong(getoffsetlength)==0 || Long.parseLong(getoffsetlength)==0 || Long.parseLong(getductdepth)==0)
	    		{
	    			Toast.makeText(this, "Enter number greater than 0!", Toast.LENGTH_SHORT).show();
			    
			    return;
	    		}	
    		}
    		catch (Exception e) {
				// TODO: handle exception
			}
    		
        	double tri1,tri2,tri11,tri22;
        
    	    double off1 = Double.parseDouble(getoffsetlength); //length
    	    double off2 = Double.parseDouble(getoffsetdepth); //
    	    double off3 = Double.parseDouble(getductdepth);   //
   
    	    Log.e("off1", ""+off1);
    	    Log.e("off2", ""+off2);
    	    Log.e("off3", ""+off3);
    	    
    	    
    	    
    	    tri11 = Math.atan( off3 / off1); // tri11 is radians
    	    
    	    tri1 = tri11 * 180.0 / Math.PI; // tri1 is degrees
    	    marking1 = Math.sqrt(Math.pow(1.0 * off1,2) + Math.pow(1.0 * off3,2));  
    	    
    	    
    	    tri2 = (180.0 - tri1) / 2.0; // tri2 is degrees
    	    tri22 = tri2 * Math.PI / 180.0; // tri22 is radians
    	    marking2 = 1.0 * off2 / Math.tan(tri22);
    	    marking3 = marking2 * 2;
        	
    	    
    	    
    	    Log.e("marking1", ""+marking1);
    	    Log.e("marking2", ""+marking2);
    	    Log.e("marking3", ""+marking3);
    	    
    	    
        	Intent myIntent = new Intent(MainActivity.this, CalcResult.class);
        	myIntent.putExtra("number1", marking1);
        	myIntent.putExtra("number2", marking2);
        	myIntent.putExtra("number3", marking3);
        	startActivity(myIntent);
        	
        	
        	ImageButton calculate = (ImageButton) this.findViewById(R.id.calc);
        	calculate.setColorFilter(Color.argb(255, 255, 255, 255)); // White Tint
        	
        	//vibrate button onClick
        	myVib.vibrate(20);
        	
        	Toast.makeText(getBaseContext(), "Calculating!", Toast.LENGTH_SHORT).show(); 
	}      	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onBackPressed() {
		
	    new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT)
	        .setIcon(null)
	        .setTitle("Closing Activity")
	        .setMessage("Are you sure you want to close this activity?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
	    {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            finish();    
	        }

	    })
	    .setNegativeButton("No", null)
	    .show();
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
