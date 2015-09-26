package widgets;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;

public class TextDatePicker implements OnClickListener, OnDateSetListener {
	TextView dateTextView;
	private int _day;
	private int _month;
	private int yearSelected;
	private Context _context;

	public TextDatePicker(Context context, int editTextViewID) {
		Activity act = (Activity) context;
		this.dateTextView = (TextView) act.findViewById(editTextViewID);
		this.dateTextView.setOnClickListener(this);
		this._context = context;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		yearSelected = year;
		_month = monthOfYear;
		_day = dayOfMonth;
		updateDisplay();
	}

	@Override
	public void onClick(View v) {
		DatePickerDialog dialog = new DatePickerDialog(_context, this,
				Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		dialog.getDatePicker().setMinDate(System.currentTimeMillis());
		dialog.show();
	}

	// updates the date in the textView
	private void updateDisplay() {

		dateTextView.setText(new StringBuilder("Estimated Date is ")
				// Month is 0 based so add 1
				.append(_day).append("-").append(_month + 1).append("-")
				.append(yearSelected));
	}
}