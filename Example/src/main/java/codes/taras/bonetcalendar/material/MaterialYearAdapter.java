package codes.taras.bonetcalendar.material;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bonet.example.bonetcalendarviewactivity.R;
import com.bonet.views.Adapter.MonthListAdapter;
import com.bonet.views.BtCalendarView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MaterialYearAdapter extends MonthListAdapter {

	public MaterialYearAdapter(BtCalendarView parent, int year) {
		super(parent, year);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.material_weekday_textview, parent, false);
            ((TextView) convertView).setTextColor(Color.BLACK);
            convertView.setPadding(0, 10, 0, 10);
        }

        Locale locale = getContext().getResources().getConfiguration().locale;
        Calendar calendar = Calendar.getInstance(locale);
        calendar.set(Calendar.MONTH, position);
        calendar.set(Calendar.YEAR, getYear());
        ((TextView) convertView).setText(new SimpleDateFormat("LLL")
                .format(calendar.getTime()).toUpperCase());

        return convertView;
	}
}
