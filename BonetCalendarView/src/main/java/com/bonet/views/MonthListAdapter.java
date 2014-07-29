package com.bonet.views;

import java.text.DateFormatSymbols;

import com.bonet.views.bonetcalendarview.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Adapter that displays month as strings.
 * 
 * @author Eduardo Bonet
 *
 */
public class MonthListAdapter extends BaseAdapter{
	
	// The year
	private int mYear;
	
	// The parent calendar
	private BtCalendarView mParentCalendar;
	
	public MonthListAdapter(BtCalendarView parent, int year){
		mParentCalendar = parent;
		mYear = year;
	}
	
	/**
	 * @param year
	 */
	public void setYear(int year){
		mYear = year;
	}
	
	/**
	 * @return the year
	 */
	public int getYear(){
		return mYear;
	}
	
	/**
	 * @return the context
	 */
	public Context getContext(){
		return mParentCalendar.getContext();
	}
	
	/**
	 * Sets the parent calendar
	 * @param calendar
	 */
	public void setCalendar(BtCalendarView calendar) {
		mParentCalendar = calendar;
	}
	
	public BtCalendarView getCalendar() {
		return mParentCalendar;
	}
	
	@Override
	public int getCount() {
		return 12;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		View view = null;

		if(convertView == null) {
            view = LayoutInflater.from(mParentCalendar.getContext()).inflate(R.layout.calendar_day_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.bt_grid_cell_text);
            view.setTag(viewHolder);
		} else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView.setText(mYear + Constants.MONTH_LIST_SEPERATOR + DateFormatSymbols.getInstance().getMonths()[position]);
		
		return view;
	}

    static class ViewHolder {
        public TextView textView;
    }
}
