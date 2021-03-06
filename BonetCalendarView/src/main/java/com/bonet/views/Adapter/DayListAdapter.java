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

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.bonet.views.BtDate;
import com.bonet.views.BtMonth;
import com.bonet.views.Constants;
import com.bonet.views.bonetcalendarview.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * The ListDayAdapter class provides a sample adapter to display
 * dates as a string. This can be easily extended by overriding the 
 * getView method. This is the default adapter.
 *  
 * @author Eduardo
 */
public class DayListAdapter extends BaseAdapter{

	// The calendar to help us display the dates
	private GregorianCalendar mCalendar;

	// The Number of days in the current month
	private int numberOfDays;

	// The Month
	private BtMonth mMonth;

	// The context
	private Context mContext;

	// The boundarie dates
	private BtDate mMinDay, mMaxDay;
	
	public DayListAdapter(Context context, BtMonth month, BtDate minDay, BtDate maxDay) {

		mCalendar = (GregorianCalendar) GregorianCalendar.getInstance();

		setMonth(month);

		mContext = context;
		mMinDay = minDay;
		mMaxDay = maxDay;
		
	}

	@Override
	public int getCount() {
		return numberOfDays;
	}

	@Override
	public Object getItem(int position) {
		return position + 1;
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

		mCalendar.set(GregorianCalendar.DAY_OF_MONTH, position + 1);

        viewHolder.textView.setText( (new SimpleDateFormat(Constants.DAY_LIST_DATEFORMATE, Locale.getDefault())).format(mCalendar.getTime()));

		if(!mMonth.getDate(position + 1).isWithinBounds(mMinDay, mMaxDay)) {
            viewHolder.textView.setTextAppearance(mContext, R.style.BonetCalendar_Text_DateInactive);
        } else {
            viewHolder.textView.setTextAppearance(mContext, R.style.BonetCalendar_Text_DateActive);
        }

		return view;
	}

	public void setMonth(BtMonth month) {
		
		mMonth = month;
		
		// Sets the calendar to the current month
		mCalendar.set(mMonth.getYear(), mMonth.getMonth(), 1);

		// Gets the number of maximum days in the month from the GregorianCalendar
		numberOfDays = mCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
	}

	public void setMaxDate(BtDate day) {
		mMaxDay = day;	
	}

	public void setMinDate(BtDate day) {
		mMinDay = day;
	}

    static class ViewHolder {
        public TextView textView;
    }
}