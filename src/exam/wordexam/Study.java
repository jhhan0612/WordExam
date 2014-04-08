package exam.wordexam;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Study extends Activity implements OnClickListener{
	TextView mText;
	ListView listEng;
	ListView listKorea;
	static ArrayList<String> englist = new ArrayList<String>();
	static ArrayList<String> meaninglist = new ArrayList<String>();
	ArrayList<Integer> wrongTest = Test.wrongTest;
	ArrayList<Integer> wrong = TestKorean.wrong;

	private ArrayList<Custom_List_Data> Array_Data;
	private Custom_List_Data data;
	private Custom_List_Adapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.study);

		mText = (TextView)findViewById(R.id.selecteddays);

		mText.setText("공부");
		
		ArrayList<String> eng = MainActivity.eng;
		ArrayList<String> meaning = MainActivity.meaning;
		
		Array_Data = new ArrayList<Custom_List_Data>();

		for(int i = 0; i < eng.size(); i++){
			data = new Custom_List_Data(eng.get(i), meaning.get(i));
			Array_Data.add(data);
		}

		adapter = new Custom_List_Adapter(this, android.R.layout.simple_list_item_1, Array_Data);

		ListView custom_list = (ListView) findViewById(R.id.listView1);

		custom_list.setAdapter(adapter);


		Button btnTest2 = (Button)findViewById(R.id.test2);
		btnTest2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		new AlertDialog.Builder(this)
		.setTitle("단어 시험")
		.setMessage("어떤 형식으로 치시겠습니까?")
		.setPositiveButton("영단어", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int whichButton){
				Intent testIntent = new Intent(Study.this, Test.class);
				startActivity(testIntent);
			}
		})
		.setNegativeButton("단어뜻", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int whichButton){
				Intent testKoreaIntent = new Intent(Study.this, TestKorean.class);
				startActivity(testKoreaIntent);
			}
		})
		.show();

	}

}