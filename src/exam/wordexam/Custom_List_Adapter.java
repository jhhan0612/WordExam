package exam.wordexam;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Custom_List_Adapter extends ArrayAdapter<Custom_List_Data> {
	private ArrayList<Custom_List_Data> items;

	public Custom_List_Adapter(Context context, int textViewResourceId, ArrayList<Custom_List_Data> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {

			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list, null);
		}
		Custom_List_Data custom_list_data = items.get(position);

		if (custom_list_data != null) {
			TextView tv_school = (TextView) v.findViewById(R.id.clist_school);
			TextView tv_teacher = (TextView) v.findViewById(R.id.clist_teacher);

			tv_school.setText(custom_list_data.getTitle3());
			tv_teacher.setText(custom_list_data.getTitle4());
		}

		return v;
	}
}

class Custom_List_Data {
	private String Title3;
	private String Title4;

	public Custom_List_Data(String _Title3, String _Title4) {
		this.setTitle3(_Title3);
		this.setTitle4(_Title4);

	}

	public String getTitle3() { return Title3; }
	public void setTitle3(String _Title) { Title3 = _Title; }

	public String getTitle4() { return Title4; }
	public void setTitle4(String _Title) { Title4 = _Title; }
}