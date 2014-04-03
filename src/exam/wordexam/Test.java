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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends Activity implements OnClickListener {

	Button b1;
	Button b2;
	Button b3;
	Button b4;
	ArrayList<String> eng = Study.eng;
	ArrayList<String> meaning = Study.meaning;
	TextView question;
	int testnumber;
	ArrayList<Integer> randomNumber = new ArrayList<Integer>();
	static ArrayList<Integer> wrongTest = new ArrayList<Integer>();
	ArrayList<String> englist = Wrong.englist;
	ArrayList<String> meaninglist = Wrong.meaninglist;

	ArrayList<String> selectedeng = new ArrayList<String>();
	ArrayList<String> selectedmeaning = new ArrayList<String>();

	ProgressBar prog;
	public static Toast toast;
	
	public void setQuestion(int testnumber, TextView question, ArrayList<String> english, ArrayList<String> korean, ArrayList<Integer> random, Button b1, Button b2, Button b3, Button b4){

		question.setText(english.get(random.get(testnumber)));


		ArrayList<Button> buttons = new ArrayList<Button>();
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);

		ArrayList<Integer> count = new ArrayList<Integer>();
		for(int i = 0; i < 4; i ++){
			count.add(i);
		}
		Collections.shuffle(count);

		ArrayList<Integer> que = new ArrayList<Integer>();
		for(int m = 0; m < english.size(); m++){
			que.add(m);
		}
		Collections.shuffle(que);

		buttons.get(count.get(0)).setText(korean.get(que.get(0)));
		buttons.get(count.get(1)).setText(korean.get(que.get(1)));
		buttons.get(count.get(2)).setText(korean.get(que.get(2)));
		buttons.get(count.get(3)).setText(korean.get(random.get(testnumber)));
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test);

		b1 = (Button)findViewById(R.id.b1);
		b2 = (Button)findViewById(R.id.b2);
		b3 = (Button)findViewById(R.id.b3);
		b4 = (Button)findViewById(R.id.b4);
		question = (TextView)findViewById(R.id.engtest);
		prog = (ProgressBar)findViewById(R.id.prog);

		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);


		testnumber = 0;

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
			randomNumber.add(i);
		}
		Collections.shuffle(randomNumber);
		
		if(selectedeng.size()!=eng.size()){
			for(int i = wrongTest.size()-1; i >= 0; i--){
				wrongTest.remove(i);
			}
		}

		question.setText(selectedeng.get(randomNumber.get(testnumber)));
		setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
	}

	public void onClick(View v) {
		String b1text = b1.getText().toString();
		String b2text = b2.getText().toString();
		String b3text = b3.getText().toString();
		String b4text = b4.getText().toString();
		String dab = selectedmeaning.get(randomNumber.get(testnumber));
		switch(v.getId()){
		case R.id.b1:
			prog.incrementProgressBy(1);
			if(b1text.equals(dab)){
				if(toast != null){
					toast.cancel();
				}
				toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
				toast.setText("정답");
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				testnumber++;
				if(testnumber < selectedeng.size()){
					setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
				}
				else{
					Intent History = new Intent(Test.this, Wrong.class);
					startActivity(History);
				}
			}
			else{
				String correct = selectedmeaning.get(randomNumber.get(testnumber));
				if(toast != null){
					toast.cancel();
				}
				toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
				toast.setText("오답, 정답은 : " + correct);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				testnumber++;
				if(testnumber < selectedeng.size()){
					setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
					if( selectedeng.size() == eng.size() ) {
						wrongTest.add( randomNumber.get(testnumber) );
					}
					else{
						for(int i = 0; i < eng.size(); i ++){
							if(eng.get(i).equals(selectedeng.get(randomNumber.get(testnumber)))){
								wrongTest.add(i);
							}
						}
					}
				}
				else{
					Intent History = new Intent(Test.this, Wrong.class);
					startActivity(History);
				}
			}
			break;
		case R.id.b2:
			prog.incrementProgressBy(1);
			if(b2text.equals(dab)){
				if(toast != null){
					toast.cancel();
				}
				toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
				toast.setText("정답");
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				testnumber++;
				if(testnumber < selectedeng.size()){
					setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
				}
				else{
					Intent History = new Intent(Test.this, Wrong.class);
					startActivity(History);
				}
			}
			else{
				String correct = selectedmeaning.get(randomNumber.get(testnumber));
				if(toast != null){
					toast.cancel();
				}
				toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
				toast.setText("오답, 정답은 : " + correct);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				testnumber++;
				if(testnumber < selectedeng.size()){
					setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
					if(selectedeng.size()==eng.size()){
						wrongTest.add(randomNumber.get(testnumber));
					}
					else{
						for(int i = 0; i < eng.size(); i ++){
							if(eng.get(i).equals(selectedeng.get(randomNumber.get(testnumber)))){
								wrongTest.add(i);
							}
						}
					}
				}
				else{
					Intent History = new Intent(Test.this, Wrong.class);
					startActivity(History);
				}
			}
			break;
		case R.id.b3:
			prog.incrementProgressBy(1);
			if(b3text.equals(dab)){
				if(toast != null){
					toast.cancel();
				}
				toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
				toast.setText("정답");
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				testnumber++;
				if(testnumber < selectedeng.size()){
					setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
				}
				else{
					Intent History = new Intent(Test.this, Wrong.class);
					startActivity(History);
				}			}
			else{
				String correct = selectedmeaning.get(randomNumber.get(testnumber));
				if(toast != null){
					toast.cancel();
				}
				toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
				toast.setText("오답, 정답은 : " + correct);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				testnumber++;
				if(testnumber < selectedeng.size()){
					setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
					if(selectedeng.size()==eng.size()){
						wrongTest.add(randomNumber.get(testnumber));
					}
					else{
						for(int i = 0; i < eng.size(); i ++){
							if(eng.get(i).equals(selectedeng.get(randomNumber.get(testnumber)))){
								wrongTest.add(i);
							}
						}
					}
				}
				else{
					Intent History = new Intent(Test.this, Wrong.class);
					startActivity(History);
				}
			}
			break;
		case R.id.b4:
			prog.incrementProgressBy(1);
			if(b4text.equals(dab)){
				if(toast != null){
					toast.cancel();
				}
				toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
				toast.setText("정답");
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				testnumber++;
				if(testnumber < selectedeng.size()){
					setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
				}
				else{
					Intent History = new Intent(Test.this, Wrong.class);
					startActivity(History);
				}
			}
			else{
				String correct = selectedmeaning.get(randomNumber.get(testnumber));
				if(toast != null){
					toast.cancel();
				}
				toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
				toast.setText("오답, 정답은 : " + correct);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				testnumber++;
				if(testnumber < selectedeng.size()){
					setQuestion(testnumber, question, selectedeng, selectedmeaning, randomNumber, b1,b2,b3,b4);
					if(selectedeng.size()==eng.size()){
						wrongTest.add(randomNumber.get(testnumber));
					}
					else{
						for(int i = 0; i < eng.size(); i ++){
							if(eng.get(i).equals(selectedeng.get(randomNumber.get(testnumber)))){
								wrongTest.add(i);
							}
						}
					}
				}
				else{
					Intent History = new Intent(Test.this, Wrong.class);
					startActivity(History);
				}
			}
			break;
		}
	}

}