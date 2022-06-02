package comp3350.srsys.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.srsys.R;
import comp3350.srsys.business.AccessStudents;
import comp3350.srsys.objects.Student;

public class StudentsActivity extends Activity {

    private AccessStudents accessStudents;
    private ArrayList<Student> studentList;
    private ArrayAdapter<Student> studentArrayAdapter;
    private int selectedStudentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        accessStudents = new AccessStudents();

        studentList = new ArrayList<Student>();
        String result = accessStudents.getStudents(studentList);
        if (result != null)
        {
        	Messages.fatalError(this, result);
        }
        else
        {
            studentArrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, studentList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(studentList.get(position).getStudentID() + ": " + studentList.get(position).getStudentName());
                    text2.setText(studentList.get(position).getStudentAddress());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listStudents);
            listView.setAdapter(studentArrayAdapter);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_students, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private String validateStudentData(Student student, boolean isNewStudent) {
        if (student.getStudentID().length() == 0) {
            return "Student ID required";
        }

        if (student.getStudentName().length() == 0) {
            return "Student name required";
        }

        if (isNewStudent && accessStudents.getRandom(student.getStudentID()) != null) {
            return "Student ID " + student.getStudentID() + " already exists.";
        }

        return null;
    }
}
