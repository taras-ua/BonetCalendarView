package codes.taras.bonetcalendar.material;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bonet.example.bonetcalendarviewactivity.R;
import com.bonet.views.Adapter.DayGridAdapter;
import com.bonet.views.BtDate;
import com.bonet.views.BtMonth;

/**
 *
 * @author Taras Rogov
 *
 */
public class MaterialDayGridAdapter extends DayGridAdapter {

	private BtDate mSelectedDay;
    private Integer mColor = null;

	public MaterialDayGridAdapter(Context context, BtMonth month,
                                  BtDate minDay, BtDate maxDay, BtDate selectedDay) {
		super(context, month, minDay, maxDay);
		mSelectedDay = selectedDay;
	}

    public MaterialDayGridAdapter(Context context, BtMonth month,
                                  BtDate minDay, BtDate maxDay,
                                  BtDate selectedDay, int color) {
        super(context, month, minDay, maxDay);
        mSelectedDay = selectedDay;
        mColor = color;
    }
	
	public void setSelectedDay(BtDate day){
		mSelectedDay = day;
        notifyDataSetChanged();
	}

    public BtDate getSelectedDay() {
        return mSelectedDay;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {

        MaterialDateTextView tv;

		if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.material_day_layout, parent, false);
            tv = (MaterialDateTextView) convertView.findViewById(R.id.bt_grid_cell_text);
            if(mColor != null) {
                tv.setSelectorColor(mColor);
            }
            convertView.setTag(tv);
        } else {
            tv = (MaterialDateTextView) convertView.getTag();
        }

		// mark if the day is selected
		int fp = getDisplayHelper().getRowOf(1) * 7 + getDisplayHelper().getColumnOf(1);
        tv.setChecked(getMonth().getDate(position + 1 - fp).equals(mSelectedDay));

        // Transform the 1D position two 2D
        int column = position % 7;
        int row = position / 7;
        int day = getDisplayHelper().getDayAt(row, column);

        tv.setText(Integer.toString(day));

        // Whether the current cell represents a valid date
        boolean isValid = (getDisplayHelper().isWithinCurrentMonth(row, column)) &&
                getMonth().getDate(day).isWithinBounds(getMinDate(), getMaxDate()) ;

        // And disables the click
        tv.setEnabled(isValid);

        BtDate btDate = new BtDate(getMonth().getYear(), getMonth().getMonth(), day);

        tv.setVisibility(isValid ? View.VISIBLE : View.INVISIBLE);
        tv.setTextColor(btDate.equals(mSelectedDay) ? Color.WHITE : Color.BLACK);

		return convertView;
	}
	

}
