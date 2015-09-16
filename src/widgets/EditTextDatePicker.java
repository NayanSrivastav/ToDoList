package widgets;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextDatePicker implements OnClickListener, OnDateSetListener {
	TextView _editText;
	private int _day;
	private int _month;
	private int yearSelected;
	private Context _context;

	public EditTextDatePicker(Context context, int editTextViewID) {
		Activity act = (Activity) context;
		this._editText = (TextView) act.findViewById(editTextViewID);
		this._editText.setOnClickListener(this);
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
		dialog.show();

	}

	// updates the date in the birth date EditText
	private void updateDisplay() {

		_editText.setText(new StringBuilder("Estimated Date is ")
				// Month is 0 based so add 1
				.append(_day).append("-").append(_month + 1).append("-")
				.append(yearSelected));
	}
}