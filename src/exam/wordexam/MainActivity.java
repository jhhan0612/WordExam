package exam.wordexam;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	static ArrayList<String> meaning;
	static ArrayList<String> eng;
	
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
			meaning = new ArrayList<String>();
			eng = new ArrayList<String>();
			
			try{
				InputStream in = getResources().openRawResource(R.raw.database);
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = builder.parse(in);

				NodeList Day = doc.getElementsByTagName("day");

				for(int i = 0; i < Day.getLength(); i++) {
					Node node = Day.item(i);
					if(sb.get(i)) {
						Element personElmnt = (Element) node;
						NodeList selectedeng = personElmnt.getElementsByTagName("eng");
						NodeList selectedmeaning = personElmnt.getElementsByTagName("meaning");
						for(int a = 0; a < selectedeng.getLength(); a++){
							Element selected = (Element) selectedeng.item(a);
							Node selectedeng2 = selected.getFirstChild();
							String c = selectedeng2.getNodeValue();
							eng.add(c);
						}
						for(int e = 0; e < selectedmeaning.getLength(); e++){
							Element selectedm = (Element) selectedmeaning.item(e);
							Node selectedeng3 = selectedm.getFirstChild();
							String d = selectedeng3.getNodeValue();
							meaning.add(d);
						}
					}	
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
			Intent intent = new Intent(this, Study.class);
			startActivity(intent);
			break;
		case R.id.test:
			sb = Days.getCheckedItemPositions();
			meaning = new ArrayList<String>();
			eng = new ArrayList<String>();
			
			try{
				InputStream in = getResources().openRawResource(R.raw.database);
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = builder.parse(in);

				NodeList Day = doc.getElementsByTagName("day");

				for(int i = 0; i < Day.getLength(); i++) {
					Node node = Day.item(i);
					if(sb.get(i)) {
						Element personElmnt = (Element) node;
						NodeList selectedeng = personElmnt.getElementsByTagName("eng");
						NodeList selectedmeaning = personElmnt.getElementsByTagName("meaning");
						for(int a = 0; a < selectedeng.getLength(); a++){
							Element selected = (Element) selectedeng.item(a);
							Node selectedeng2 = selected.getFirstChild();
							String c = selectedeng2.getNodeValue();
							eng.add(c);
						}
						for(int e = 0; e < selectedmeaning.getLength(); e++){
							Element selectedm = (Element) selectedmeaning.item(e);
							Node selectedeng3 = selectedm.getFirstChild();
							String d = selectedeng3.getNodeValue();
							meaning.add(d);
						}
					}	
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
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