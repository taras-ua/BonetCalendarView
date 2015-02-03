package codes.taras.bonetcalendar.material;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.bonet.example.bonetcalendarviewactivity.R;
import com.bonet.views.BtMonth;
import com.bonet.views.Provider.GridBtMonthViewProvider;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Taras Rogov
 *
 */
public class MaterialMonthProvider extends GridBtMonthViewProvider {

	public MaterialMonthProvider(Context context, BtMonth month) {
		super(context, month);
	}

	@Override
	public View getView() {
		
		View view = LayoutInflater.from(getContext()).inflate(R.layout.material_month_view, null);
		
		GridView weekdaysContainer = (GridView) view.findViewById(R.id.weekdays_container);
		
		// Sets the names 
		weekdaysContainer.setAdapter(new ArrayAdapter<String>(getContext(),
                R.layout.material_weekday_textview, getWeekdayNames()));
		
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
			defaultWeekNames.add(weekdayNames[i % 7 == 0 ? 7 : i % 7]
                    .substring(0, 1).toUpperCase());
		}
		
		return defaultWeekNames;
	}
	
	

}
