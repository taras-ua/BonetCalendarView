package codes.taras.bonetcalendar.material;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.bonet.example.bonetcalendarviewactivity.CustomDayGridAdapter;
import com.bonet.example.bonetcalendarviewactivity.CustomMonthProvider;
import com.bonet.example.bonetcalendarviewactivity.CustomYearProvider;
import com.bonet.example.bonetcalendarviewactivity.R;
import com.bonet.views.BtCalendarView;
import com.bonet.views.BtDate;
import com.bonet.views.BtMonth;
import com.bonet.views.Provider.GridBtMonthViewProvider;

/**
 *
 * @author Taras Rogov
 *
 */
public class MaterialGridCalendar extends BtCalendarView {

    MaterialDayGridAdapter mDayAdapter;

	public MaterialGridCalendar(Context context) {
		this(context, null);
	}

    public MaterialGridCalendar(Context context, AttributeSet attr) {
        super(context, attr);

        BtDate selectedDay = BtDate.today();
        GridBtMonthViewProvider provider = new MaterialMonthProvider(context,
                BtMonth.fromDay(getContext(), selectedDay));
        mDayAdapter = new MaterialDayGridAdapter(context,
                BtMonth.fromDay(getContext(), selectedDay),
                getMinDate(), getMaxDate(), selectedDay);
        provider.setAdapter(mDayAdapter);

        TextView head = getTitleTextView();
        head.setTextAppearance(getContext(),
                android.R.style.TextAppearance_DeviceDefault_Large);
        head.setAllCaps(true);
        head.setTypeface(null, Typeface.BOLD);

        getLeftButton().setBackgroundResource(R.drawable.material_left);
        getRightButton().setBackgroundResource(R.drawable.material_right);

        initialize(provider, new MaterialYearProvider(this, selectedDay.getYear()));
	}

	public void setSelectedDate(int year, int month, int day){
		mDayAdapter.setSelectedDay(new BtDate(year, month, day));
	}

    public BtDate getSelectedDate() {
        return mDayAdapter.getSelectedDay();
    }
	
	@Override
	public void onDateSelected(int year, int month, int day) {
		setSelectedDate(year, month, day);
		super.onDateSelected(year, month, day);
	}
	
}





