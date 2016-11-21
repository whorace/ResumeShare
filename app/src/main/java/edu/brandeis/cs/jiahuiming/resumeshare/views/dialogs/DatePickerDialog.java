package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.NumberPicker;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.NumberPickerHelper;

/**
 * Created by jiahuiming on 11/17/16.
 */

public class DatePickerDialog extends BaseDialog {
    private NumberPicker mStartYear;
    private NumberPicker mFinishYear;
    private NumberPickerHelper mNumberPickerHelper;

    public DatePickerDialog(Context context) {
        super(context);
        setDialogContentView(R.layout.include_dialog_yearpicker);
        mStartYear = (NumberPicker) findViewById(R.id.np_startyear);
        mFinishYear= (NumberPicker) findViewById(R.id.np_endyear);
        mNumberPickerHelper.setNumberPickerDividerColor(mStartYear,context.getResources().getColor(R.color.white));
        mNumberPickerHelper.setNumberPickerTextColor(mStartYear,context.getResources().getColor(R.color.white));
        mNumberPickerHelper.setNumberPickerDividerColor(mFinishYear,context.getResources().getColor(R.color.white));
        mNumberPickerHelper.setNumberPickerTextColor(mFinishYear,context.getResources().getColor(R.color.white));

        mStartYear.setMaxValue(2050);
        mStartYear.setMinValue(1950);
        mStartYear.setValue(2016);

        mFinishYear.setMaxValue(2050);
        mFinishYear.setMinValue(1950);
        mFinishYear.setValue(2016);
    }

    @Override
    public void setTitle(CharSequence text) {
        super.setTitle(text);
    }

    public void setButton(CharSequence text,
                          DialogInterface.OnClickListener listener) {
        super.setButton1(text, listener);
    }

    public void setButton(CharSequence text1,
                          DialogInterface.OnClickListener listener1, CharSequence text2,
                          DialogInterface.OnClickListener listener2) {
        super.setButton1(text1, listener1);
        super.setButton2(text2, listener2);
    }

    public void setStartYear(String value){
        Integer startvalue=new Integer(value);
        mStartYear.setValue(startvalue.intValue());

    }

    public void setFinishYear(String value){
        Integer finishvalue=new Integer(value);
        mFinishYear.setValue(finishvalue.intValue());

    }

    public int getStartYear(){
        return mStartYear.getValue();
    }

    public int getEndyear(){
        return mFinishYear.getValue();

    }
}
