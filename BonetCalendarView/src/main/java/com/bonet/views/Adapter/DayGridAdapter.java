/* Copyright 2014 Eduardo Bonet

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.bonet.views.Adapter;

import com.bonet.views.BtDate;
import com.bonet.views.BtMonth;
import com.bonet.views.bonetcalendarview.R;

import android.content.Context;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;


/**
 * A adapter that displays the month days as a grid. Heavily depends on
 * the MonthDisplayHelper class
 * 
 * @author Eduardo
 * @author Taras Rogov (contributor)
 */

public class DayGridAdapter extends BaseAdapter {

	
	/* number of cells of the current month */
	private int mNumCells;
	
	/* The grid display helper */
	private MonthDisplayHelper mMonthDisplay;
	
	/* The context */
	private Context mContext;
	
	/* The current month */
	private BtMonth mMonth;
	
	/* The boundary dates */
	private BtDate mMinDate, mMaxDate;
	
	/**
	 * Creates the adapter
	 * @param context
	 * @param month
	 * @param minDate
	 * @param maxDate
	 */
	public DayGridAdapter(Context context, BtMonth month, BtDate minDate, BtDate maxDate){
		mContext = context;
		mMinDate = minDate;
		mMaxDate = maxDate;
		
		setMonth(month);
	}
	
	@Override
	public int getCount() {
		return mNumCells;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View view = null;

        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.calendar_day_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.bt_grid_cell_text);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        // Transform the 1D position two 2D
        int column = position % 7;
        int row = position / 7;

		// Gets the date for the position
		int day = mMonthDisplay.getDayAt(row, column);

		// Sets the Text
        viewHolder.textView.setText(day + "");
		
		// Whether the current cell represents a valid date
        boolean isValid = (mMonthDisplay.isWithinCurrentMonth(row, column)) && mMonth.getDate(day).isWithinBounds(mMinDate, mMaxDate) ;
		
		// And disables the click
		view.setEnabled(isValid);

        BtDate btDate = new BtDate(mMonth.getYear(), mMonth.getMonth(), day);
        if(isValid && btDate.equals(BtDate.today())) {
            viewHolder.textView.setTextAppearance(mContext, R.style.BonetCalendar_Text_Today);
        } else if (isValid) {
            viewHolder.textView.setTextAppearance(mContext, R.style.BonetCalendar_Text_DateActive);
        } else {
            viewHolder.textView.setTextAppearance(mContext, R.style.BonetCalendar_Text_DateInactive);
        }
		
		return view;
	}
	
	/**
	 * Changes the content data to display the given month
	 * @param month
	 */
	public void setMonth(BtMonth month) {
		
		Log.d("", month + "");
		mMonth = month;
		
		// Starts at Locale defined day of week.
        Locale locale = mContext.getResources().getConfiguration().locale;
		mMonthDisplay = new MonthDisplayHelper(mMonth.getYear(), mMonth.getMonth(),
                Calendar.getInstance(locale).getFirstDayOfWeek());
		
		// number of cells is 7 times the row of the last day plus 1.
		mNumCells = (mMonthDisplay.getRowOf(mMonthDisplay.getNumberOfDaysInMonth()) + 1) * 7;
	}
	
	public BtMonth getMonth(){
		return mMonth;
	}
	
	public Context getContext(){
		return mContext;
	}
	
	public MonthDisplayHelper getDisplayHelper(){
		return mMonthDisplay;
	}
	
	/**
	 * Sets the max date
	 * @param date the date
	 */
	public void setMaxDate(BtDate date){
		mMaxDate = date;
	}
	
	/**
	 * Removes the calendar's max date if there was any.
	 */
	public void unsetMaxDate() {
		mMaxDate = BtDate.MAX_BTDATE;
	}

	/**
	 * @return The calendar's max day
	 */
	public BtDate getMaxDate(){
		return mMaxDate;
	}
	
	/**
	 * Sets the min date
	 * @param date the date
	 */
	public void setMinDate(BtDate date){
		mMinDate = date;
	}

	/**
	 * Removes the min date if there was any.
	 */
	public void unsetMinDate() {
		mMinDate = BtDate.MIN_BTDATE;
	}
	
	/**
	 * @return The calendar's min day
	 */
	public BtDate getMinDate(){
		return mMinDate;
	}

    static class ViewHolder {
        public TextView textView;
    }
}