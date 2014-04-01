package exam.wordexam;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TestKorean extends Activity implements OnClickListener{
	ArrayList<String> meaning = Study.meaning;
	ArrayList<String> eng = Study.eng;
	int a;
	ArrayList<Integer> count = new ArrayList<Integer>();
	static ArrayList<Integer> wrong = new ArrayList<Integer>();
	EditText answer;
	TextView testKorea;
	ArrayList<String> englist = Wrong.englist;
	ArrayList<String> meaninglist = Wrong.meaninglist;
	
	ArrayList<String> seng = new ArrayList<String>();
	ArrayList<String> smeaning = new ArrayList<String>();
	
	ProgressBar prog;

	public void setQuestion(int a, TextView b, ArrayList<String> c, ArrayList<Integer> d){
		b.setText(c.get(d.get(a)));
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testkorean);

		testKorea = (TextView)findViewById(R.id.testKorean);
		answer = (EditText)findViewById(R.id.answerEng);
		
		prog = (ProgressBar)findViewById(R.id.testProgress);
		
		int a = 0;
		
		if(englist.size()==0){
			for(int p = 0; p < eng.size(); p++){
				seng.add(eng.get(p));
				smeaning.add(meaning.get(p));
			}
		}
		else{
			for(int e = 0; e < englist.size(); e++){
				seng.add(englist.get(e));
				smeaning.add(meaning.get(e));
			}
		}
		
		prog.setMax(seng.size());
		prog.setProgress(0);
		prog.setSecondaryProgress(0);
		for(int i = 0; i < seng.size(); i ++){
			count.add(i);
		}
		Collections.shuffle(count);
		
		testKorea.setText(smeaning.get(count.get(a)));

		Button check = (Button)findViewById(R.id.check);
		check.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		prog.incrementProgressBy(1);
		if(answer.getText().toString().equals(seng.get(count.get(a)))){
			Toast toast = Toast.makeText(this, "null", Toast.LENGTH_LONG);
			toast.setText("Á¤´ä");
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			a++;
			if(a<eng.size()){
				setQuestion(a, testKorea, smeaning, count);
				answer.setText("");
			}
			else{
				Intent History = new Intent(TestKorean.this, Wrong.class);
				startActivity(History);
			}
		}
		else{
			wrong.add(count.get(a));
			String cor = seng.get(count.get(a));
			Toast toast = Toast.makeText(this, "null", Toast.LENGTH_LONG);
			toast.setText(cor);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();

			a++;
			if(a<eng.size()){
				setQuestion(a, testKorea , smeaning,count);
				answer.setText("");
			}
			else{
				Intent History = new Intent(TestKorean.this, Wrong.class);
				startActivity(History);
			}
		}
	}
}
