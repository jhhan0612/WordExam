package exam.wordexam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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
	ArrayList<String> meaning = MainActivity.meaning;
	ArrayList<String> eng = MainActivity.eng;
	int testnum;
	ArrayList<Integer> count = new ArrayList<Integer>();
	static ArrayList<Integer> wrong = new ArrayList<Integer>();
	EditText answer;
	TextView testKorea;
	ArrayList<String> englist = Wrong.englist;
	ArrayList<String> meaninglist = Wrong.meaninglist;

	ArrayList<String> selectedeng = new ArrayList<String>();
	ArrayList<String> selectedmeaning = new ArrayList<String>();

	ProgressBar prog;
	public static Toast toast;

	public void setQuestion(int testnum, TextView question, ArrayList<String> englist, ArrayList<Integer> random){
		question.setText(englist.get(random.get(testnum)));
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testkorean);

		testKorea = (TextView)findViewById(R.id.testKorean);
		answer = (EditText)findViewById(R.id.answerEng);

		prog = (ProgressBar)findViewById(R.id.testProgress);

		int testnum = 0;

		if(englist.size()==0){
			for(int p = 0; p < eng.size(); p++){
				selectedeng.add(eng.get(p));
				selectedmeaning.add(meaning.get(p));
			}
		}
		else{
			for(int e = 0; e < englist.size(); e++){
				selectedeng.add(englist.get(e));
				selectedmeaning.add(meaninglist.get(e));
			}
		}

		prog.setMax(selectedeng.size());
		prog.setProgress(0);
		prog.setSecondaryProgress(0);
		for(int i = 0; i < selectedeng.size(); i ++){
			count.add(i);
		}
		Collections.shuffle(count);

		testKorea.setText(selectedmeaning.get(count.get(testnum)));

		if(selectedeng.size()!=eng.size()){
			if(wrong.size() != 0){
				for(int i = wrong.size(); i >= 0; i--){
					wrong.remove(i);
				}
			}
		}
		Button check = (Button)findViewById(R.id.check);
		check.setOnClickListener(this);
	}

	public void onClick(View v) {
		prog.incrementProgressBy(1);
		if(answer.getText().toString().equals(selectedeng.get(count.get(testnum)))){
			if(toast != null){
				toast.cancel();
			}
			toast = Toast.makeText(this, null, Toast.LENGTH_LONG);
			toast.setText("Á¤´ä");
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			testnum++;
			if(testnum < selectedeng.size()) {
				setQuestion(testnum, testKorea, selectedmeaning, count);
				answer.setText("");
			}
			else{
				long now = System.currentTimeMillis();
				Date date = new Date(now);
				SimpleDateFormat formatTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String endTime = formatTime.format(date);

				Intent History = new Intent(TestKorean.this, Wrong.class);
				startActivity(History);
			}
		}
		else{
			if(selectedeng.size() == eng.size()){
				wrong.add(count.get(testnum));
			}
			else{
				for(int i = 0; i < eng.size(); i ++) {
					if(eng.get(i).equals(selectedeng.get(count.get(testnum)))){
						wrong.add(i);
					}
				}
			}
			String cor = selectedeng.get(count.get(testnum));
			if(toast != null){
				toast.cancel();
			}
			toast = Toast.makeText(this, null, Toast.LENGTH_LONG);
			toast.setText(cor);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();

			testnum++;
			if(testnum < selectedeng.size()) {
				setQuestion(testnum, testKorea , selectedmeaning,count);
				answer.setText("");
			}
			else{
				long now = System.currentTimeMillis();
				Date date = new Date(now);
				SimpleDateFormat formatTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String endTime = formatTime.format(date);

				Intent History = new Intent(TestKorean.this, Wrong.class);
				startActivity(History);
			}
		}
	}
}

