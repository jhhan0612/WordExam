package exam.wordexam;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{

	ListView Days;
	static SparseBooleanArray sb;
	
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		Button btnStudy = (Button)findViewById(R.id.study);
		Button btnHistroy = (Button)findViewById(R.id.histroy);
		Button btnTest = (Button)findViewById(R.id.test);
		
		btnStudy.setOnClickListener(this);
		btnHistroy.setOnClickListener(this);
		btnTest.setOnClickListener(this);
		
		ArrayList<String> items = new ArrayList<String>();
		for(int i = 1; i < 17; i++){
			String b = "Day" + i;
			items.add(b);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);
		Days = (ListView)findViewById(R.id.Days);
		Days.setAdapter(adapter);
		Days.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	public void onClick(View v) {		
		switch (v.getId()) {
		case R.id.study:
			sb = Days.getCheckedItemPositions();
			
			Intent intent = new Intent(this, Study.class);
			startActivity(intent);
			break;
		case R.id.test:
			new AlertDialog.Builder(this)
			.setTitle("단어 시험")
			.setMessage("어떤 형식으로 치시겠습니까?")
			.setPositiveButton("영단어", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					Intent testIntent = new Intent(MainActivity.this, Test.class);
					startActivity(testIntent);
				}
			})
			.setNegativeButton("단어뜻", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					Intent testKoreaIntent = new Intent(MainActivity.this, TestKorean.class);
					startActivity(testKoreaIntent);
				}
			})
			.show();
			break;
		case R.id.histroy:
			Intent historyIntent = new Intent(this, History.class);
			startActivity(historyIntent);
			break;
		}
	}

}