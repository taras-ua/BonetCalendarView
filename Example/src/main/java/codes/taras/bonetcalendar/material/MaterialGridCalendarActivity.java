package codes.taras.bonetcalendar.material;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bonet.example.bonetcalendarviewactivity.CustomGridCalendar;
import com.bonet.example.bonetcalendarviewactivity.R;
import com.bonet.views.OnDateSelectedListener;

/**
 *
 * @author Taras Rogov
 *
 */
public class MaterialGridCalendarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.empty_layout);
		
		int minDay = getIntent().getIntExtra("MIN_DAY", 1);
        int minMonth = getIntent().getIntExtra("MIN_MONTH", 0);
        int minYear = getIntent().getIntExtra("MIN_YEAR", 2014);

        int maxDay = getIntent().getIntExtra("MAX_DAY", 31);
        int maxMonth = getIntent().getIntExtra("MAX_MONTH", 11);
        int maxYear = getIntent().getIntExtra("MAX_YEAR", 2015);
		
		MaterialGridCalendar cv = new MaterialGridCalendar(this);

		((LinearLayout) findViewById(R.id.main_layout)).addView(cv);
		
		cv.setMinDate(minYear, minMonth, minDay);
		cv.setMaxDate(maxYear, maxMonth, maxDay);
		
//		cv.setOnDateSelectedListener(new OnDateSelectedListener() {
//			@Override
//			public void onDateSelected(int year, int month, int day) {
//				Toast.makeText(MaterialGridCalendarActivity.this,
//                        year + "/" + month + "/" + day,
//                        Toast.LENGTH_SHORT).show();
//			}
//		});
	}
}
