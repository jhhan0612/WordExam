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

public class Wrong extends Activity implements OnClickListener{

	ArrayList<Integer> wrongTest = Test.wrongTest;
	ArrayList<Integer> wrong = TestKorean.wrong;
	ArrayList<String> eng = MainActivity.eng;
	ArrayList<String> meaning = MainActivity.meaning;
	
	static ArrayList<String> englist = Study.englist;
	static ArrayList<String> meaninglist = Study.meaninglist;

	private ArrayList<Custom_List_Data> Array_Data;
	private Custom_List_Data data;
	private Custom_List_Adapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wrong);

		englist = new ArrayList<String>();
		meaninglist = new ArrayList<String>();

		if(wrongTest.size()==0){
			for(int i = 0; i < wrong.size(); i ++){
				meaninglist.add(meaning.get(wrong.get(i)));
				englist.add(eng.get(wrong.get(i)));
			}
			Array_Data = new ArrayList<Custom_List_Data>();

			for(int p = 0; p < englist.size(); p++){
				data = new Custom_List_Data(englist.get(p), meaninglist.get(p));
				Array_Data.add(data);
			}
			
			adapter = new Custom_List_Adapter(this, android.R.layout.simple_list_item_1, Array_Data);

			ListView custom_list = (ListView) findViewById(R.id.listView1);

			custom_list.setAdapter(adapter);
		}
		else{
			for(int i = 0 ; i < wrongTest.size(); i ++){
				meaninglist.add(meaning.get(wrongTest.get(i)));
				englist.add(eng.get(wrongTest.get(i)));
			}

			Array_Data = new ArrayList<Custom_List_Data>();

			for(int p = 0; p < englist.size(); p++){
				data = new Custom_List_Data(englist.get(p), meaninglist.get(p));
				Array_Data.add(data);
			}

			adapter = new Custom_List_Adapter(this, android.R.layout.simple_list_item_1, Array_Data);

			ListView custom_list = (ListView) findViewById(R.id.listView1);

			custom_list.setAdapter(adapter);
		}
		Button test = (Button)findViewById(R.id.test2);
		test.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		new AlertDialog.Builder(this)
		.setTitle("단어 시험")
		.setMessage("어떤 형식으로 치시겠습니까?")
		.setPositiveButton("영단어", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int whichButton){
				Intent testIntent = new Intent(Wrong.this, Test.class);
				startActivity(testIntent);
			}
		})
		.setNegativeButton("단어뜻", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int whichButton){
				Intent testKoreaIntent = new Intent(Wrong.this, TestKorean.class);
				startActivity(testKoreaIntent);
			}
		})
		.show();


	}
	protected void onStop(){
		super.onStop();
		if(englist.size()!=0){
			for(int i = englist.size()-1; i >=0; i--){
				englist.remove(i);
				meaninglist.remove(i);
			}
		}
	}
}
