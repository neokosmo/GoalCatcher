package kosmo.neo.goalcatcher;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.ImageView;


public class AddGoal extends AppCompatActivity {

    private BottomSheetDialog dpDialog;
    private Context context = this;
    private View bottomSheetLayout;
    private TextView tvDateHandler;

    private String dateText;

    private DateDisplay dateDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setEnterTransition(new Slide());
        getWindow().setExitTransition(new Fade());

        setContentView(R.layout.activity_add_goal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView ivDate = findViewById(R.id.ivDate);
        Button btSave = findViewById(R.id.btSave);
        tvDateHandler = findViewById(R.id.tvDateHandler);

        tvDateHandler.setText(new DateDisplay().dateToday());

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
                Dialog diag = new Dialog(context);
                diag.setContentView(R.layout.goal_list);
                diag.show();
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

                dateDisplay = new DateDisplay(year,month,dayOfMonth);

                dateText = dateDisplay.toString();

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
                tvDateHandler.setText(dateText);
                dpDialog.dismiss();
            }
        });
    }
}
