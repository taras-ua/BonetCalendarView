package com.bonet.example.bonetcalendarviewactivity;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.bonet.views.BtMonth;
import com.bonet.views.Provider.GridBtMonthViewProvider;

public class CustomMonthProvider extends GridBtMonthViewProvider {

	public CustomMonthProvider(Context context, BtMonth month) {
		super(context, month);
	}

	@Override
	public View getView() {
		
		/* Let's try something funny here: Inflate a custom for the month view
		 * That will show the weekdays names. Shall we?*/
		
		View view = LayoutInflater.from(super.getContext()).inflate(R.layout.custom_month_view, null);
		
		GridView weekdaysContainer = (GridView) view.findViewById(R.id.weekdays_container);
		
		// Sets the names 
		weekdaysContainer.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.weekday_textview, getWeekdayNames()));
		
		// The actual container
		setGridView((GridView) view.findViewById(R.id.month_container));
		
		// Sets the adapter
		getGridView().setAdapter(getAdapter());
		
		// And sets the listener
		getGridView().setOnItemClickListener(getDefaultItemClickListener());
		
		return view;
	}
	
	/*
	 * Returns the list of string containing the weekday names 
	 * for the user's current locale.
	 * @return
	 */
	public List<String> getWeekdayNames() {
		List<String> defaultWeekNames = new ArrayList<String>();

        String[] weekdayNames = DateFormatSymbols
                .getInstance(getContext().getResources().getConfiguration().locale)
                .getShortWeekdays();

        Locale locale = getContext().getResources().getConfiguration().locale;
        int firstDayOfWeek = Calendar.getInstance(locale).getFirstDayOfWeek();

		for(int i = firstDayOfWeek; i < firstDayOfWeek + 7; i++) {
			defaultWeekNames.add(weekdayNames[i % 7 == 0 ? 7 : i % 7]);
		}
		
		return defaultWeekNames;
	}
	
	

}
