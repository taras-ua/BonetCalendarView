package codes.taras.bonetcalendar.material;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.bonet.example.bonetcalendarviewactivity.CustomYearAdapter;
import com.bonet.example.bonetcalendarviewactivity.R;
import com.bonet.views.Adapter.MonthListAdapter;
import com.bonet.views.BtCalendarView;
import com.bonet.views.BtMonth;
import com.bonet.views.Provider.BtYearViewProvider;

/**
 *
 * @author Taras Rogov
 *
 */
public class MaterialYearProvider extends BtYearViewProvider {

	private GridView mGridView;

	private MonthListAdapter mAdapter;

	public MaterialYearProvider(BtCalendarView parentCalendar, int year) {
		super(parentCalendar, year);
		mAdapter = new MaterialYearAdapter(parentCalendar, year);
	}

	@Override
	public View getView() {
        if(mGridView == null) {
			// inflates the grid view
			mGridView = (GridView) LayoutInflater.from(getCalendar().getContext()).inflate(R.layout.grid_view, null);
			mGridView.setNumColumns(3);
			mGridView.setAdapter(mAdapter);
			mGridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
					if(! (getCalendar() == null)) {
                        getCalendar().notifyMonthChanged(new BtMonth(mAdapter.getContext(), getYear(), position));
                    }
				}
			});
		}
		
		return mGridView;
	}

	@Override
	public void updateView() {
		mAdapter.notifyDataSetChanged();
	}

}
