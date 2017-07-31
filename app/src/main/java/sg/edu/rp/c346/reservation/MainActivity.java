package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etTel;
    EditText etSize;
    CheckBox ckBoxSmoke;
    EditText etDay;
    EditText etTime;
    Button btnReserve;
    Button btnReset;
    Button btnSetting;
    String Smoke;
    EditText etFontSize;
    EditText etFontColour;
    EditText etBackColour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id. editTextName);
        etTel = (EditText) findViewById(R.id. editTextTel);
        etSize = (EditText) findViewById(R.id. editTextSize);
        ckBoxSmoke = (CheckBox) findViewById(R.id. checkBoxSmoke);
        etDay = (EditText) findViewById(R.id. editTextDay);
        etTime = (EditText) findViewById(R.id. editTextTime);
        btnReserve = (Button)findViewById(R.id. buttonReserve);
        btnReset = (Button)findViewById(R.id. buttonReset);
        btnSetting = (Button)findViewById(R.id. buttonSetting);
        etFontSize = (EditText) findViewById(R.id. editTextFontSize);
        etFontColour = (EditText) findViewById(R.id. editTextFontColour);
        etBackColour = (EditText) findViewById(R.id. editTextBackColour);


        Calendar firstOpenCal = Calendar.getInstance();
        int year = firstOpenCal.get(Calendar.YEAR);
        int monthOfYear = firstOpenCal.get(Calendar.MONTH);
        int dayOfMonth = firstOpenCal.get(Calendar.DAY_OF_MONTH);
        etDay.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);

        etDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int monthOfYear = c.get(Calendar.MONTH);
                int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        etDay.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);

                    }
                };
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                        myDateListener, year,monthOfYear,dayOfMonth+1);

                myDateDialog.show();
            }
        });

        Calendar firstOpenTime = Calendar.getInstance();
        int hour = firstOpenTime.get(Calendar.HOUR_OF_DAY);
        int minute = firstOpenTime.get(Calendar.MINUTE);
        etTime.setText(hour + ":" + minute);

        etTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        etTime.setText(hourOfDay + ":" + minute);
                    }
                };

                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, hour, minute, true);
                myTimeDialog.show();
            }
        });

        ckBoxSmoke.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(ckBoxSmoke.isChecked() == true){
                    Smoke = "Smoking";
                }
                else{
                    Smoke = "Non Smoking";
                }
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);


                myBuilder.setTitle("Reservation");
                myBuilder.setMessage("Name: " + etName.getText().toString() + "\n"
                        + "Tel: " + etTime.getText().toString() + "\n"
                        + "Size: " + etSize.getText().toString() + "\n"
                        + "Smoke: " + Smoke + "\n"
                        + "Day: " + etDay.getText().toString() + "\n"
                        + "Time: " + etTime.getText().toString() + "\n");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Reservation Confirm", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText("");
                etTel.setText("");
                etSize.setText("");
                ckBoxSmoke.setChecked(false);
                etDay.setText("");
                etTime.setText("");
                etDay.setHint("Click here to select day");
                etTime.setHint("Click here to select time");
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater =
                        (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.setting, null);

                etFontSize =(EditText) viewDialog.findViewById(R.id.editTextFontSize);
                etFontColour =(EditText) viewDialog.findViewById(R.id.editTextFontColour);
                etBackColour =(EditText) viewDialog.findViewById(R.id.editTextBackColour);

                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Setting");
                myBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                myBuilder.setNegativeButton("Cancel",null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        String name = etName.getText().toString();
        String telephone = etTel.getText().toString();
        String size = etSize.getText().toString();
        Boolean smoking = ckBoxSmoke.isChecked();
        String day = etDay.getText().toString();
        String time = etTime.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("name", name);
        prefEdit.putString("telephone", telephone);
        prefEdit.putString("size", size);
        prefEdit.putBoolean("smoking", smoking);
        prefEdit.putString("day", day);
        prefEdit.putString("time", time);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = etName.getText().toString();
        String telephone = etTel.getText().toString();
        String size = etSize.getText().toString();
        Boolean smoking = ckBoxSmoke.isChecked();
        String day = etDay.getText().toString();
        String time = etTime.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        EditText etName = (EditText) findViewById(R.id.editTextName);
        etName.setText(prefs.getString("name", name));
        EditText etTel = (EditText) findViewById(R.id.editTextTel);
        etTel.setText(prefs.getString("telephone", telephone));
        EditText etSize = (EditText) findViewById(R.id.editTextSize);
        etSize.setText(prefs.getString("size", size));
        CheckBox ckBoxSmoke = (CheckBox) findViewById(R.id.checkBoxSmoke);
        ckBoxSmoke.setChecked(prefs.getBoolean("smoking", smoking));
        EditText etDay = (EditText) findViewById(R.id.editTextDay);
        etDay.setText(prefs.getString("day", day));
        EditText etTime = (EditText) findViewById(R.id.editTextTime);
        etTime.setText(prefs.getString("time", time));
    }
}
