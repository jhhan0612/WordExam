package exam.wordexam;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class History extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.histroy);
	    
	    ArrayList<String> timeList = new ArrayList<String>();
	    
	    
	}

}