package com.bonet.example.bonetcalendarviewactivity;

import java.text.DateFormatSymbols;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bonet.views.BtCalendarView;
import com.bonet.views.Adapter.MonthListAdapter;

public class CustomYearAdapter extends MonthListAdapter {

	public CustomYearAdapter(BtCalendarView parent, int year) {
		super(parent, year);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View  view = convertView;
		if(null == view) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.weekday_textview, null);
		}
		
		TextView tv = (TextView) view;
		tv.setText(DateFormatSymbols.getInstance().getShortMonths()[position]);
		
		return view;
	}
}
