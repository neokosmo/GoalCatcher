package kosmo.neo.goalcatcher;

import android.content.ContentValues;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import database.access.object.DatabaseManager;
import database.access.object.GoalCatcherDAO;


public class AddGoal extends AppCompatActivity {

    private Context context = this;

    private BottomSheetDialog dpDialog;
    private View bottomSheetLayout;
    private TextView tvDateHandler;
    private EditText etGoal;

    private int tYear;
    private int tMonth;
    private int tDayOfMonth;
    private int tHour;
    private int tMinute;

    private String dateText;

    private LocalDate localDate;
    private DateDisplay dateDisplay;
    private DateDisplay dateToday;
    private DateDisplay datePicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setEnterTransition(new Slide());
        getWindow().setExitTransition(new Fade());

        setContentView(R.layout.activity_add_goal);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        final ImageView ivDate = findViewById(R.id.ivDate);
        final Button btSave = findViewById(R.id.btSave);
        final TimePicker tpTime = findViewById(R.id.tpTime);

        etGoal = findViewById(R.id.etGoal);
        tvDateHandler = findViewById(R.id.tvDateHandler);

        /*Get Date Today*/
        Calendar calendar = Calendar.getInstance();
        tYear = calendar.get(Calendar.YEAR);
        tMonth = calendar.get(Calendar.MONTH);
        tDayOfMonth = calendar.get(Calendar.DATE);

        dateToday = new DateDisplay(tYear,tMonth,tDayOfMonth,calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE));
        /*Get Date Today*/

        tvDateHandler.setText(new DateDisplay(tYear,tMonth,tDayOfMonth).toString());

        /*Time Picker*/

        tpTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                tHour = hourOfDay;
                tMinute = minute;
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_key);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicked = new DateDisplay(tYear,tMonth,tDayOfMonth,tHour,tMinute);
                try {
                    if (etGoal.getText().toString().equals("")) {
                        Snackbar.make(view, R.string.error_1, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else if (datePicked.compareBefore(dateToday)) {
                        Snackbar.make(view, R.string.error_2, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        ContentValues addGoalContent = new ContentValues();

                        String insertDate = String.valueOf(tYear) + "-" +
                                            String.valueOf(tMonth) + "-" +
                                            String.valueOf(tDayOfMonth) + " " +
                                            String.valueOf(tHour) + ":" +
                                            String.valueOf(tMinute) + ":00";

                        addGoalContent.put(GoalCatcherDAO.GOAL_NAME,etGoal.getText().toString());
                        addGoalContent.put(GoalCatcherDAO.GOAL_DATE_TIME,insertDate);

                        DatabaseManager.getInstance().openDatabase();
                        boolean isInserted = DatabaseManager.getInstance().insertGoal(GoalCatcherDAO.TBL_GOAL,addGoalContent);

                        if( isInserted ){
                            Snackbar.make(view, R.string.is_inserted, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        } else {
                            Snackbar.make(view, R.string.db_error_1, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        ivDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetLayout = getLayoutInflater().inflate(R.layout.datepicker, null);
                init();
                dpDialog = new BottomSheetDialog(context);
                dpDialog.setContentView(bottomSheetLayout);
                dpDialog.show();
            }
        });


    }

    private void init(){
        TextView dpCancel = (bottomSheetLayout).findViewById(R.id.tvCancelDp);
        TextView dpOk = (bottomSheetLayout).findViewById(R.id.tvOKDp);
        CalendarView cvDate = (bottomSheetLayout).findViewById(R.id.cvDate);

        cvDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                tYear = year;
                tMonth = month;
                tDayOfMonth = dayOfMonth;
            }
        });

        dpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpDialog.dismiss();
            }
        });

        dpOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDisplay = new DateDisplay(tYear,tMonth,tDayOfMonth);
                dateText = dateDisplay.toString();
                tvDateHandler.setText(dateText);
                dpDialog.dismiss();
            }
        });
    }
}
