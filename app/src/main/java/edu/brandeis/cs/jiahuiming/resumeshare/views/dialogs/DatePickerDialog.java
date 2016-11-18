package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.NumberPicker;

import edu.brandeis.cs.jiahuiming.resumeshare.R;

/**
 * Created by jiahuiming on 11/17/16.
 */

public class DatePickerDialog extends BaseDialog {
    private NumberPicker mStartYear;
    private NumberPicker mFinishYear;

    public DatePickerDialog(Context context) {
        super(context);
        setDialogContentView(R.layout.include_dialog_yearpicker);
        mStartYear = (NumberPicker) findViewById(R.id.np_startyear);
        mFinishYear= (NumberPicker) findViewById(R.id.np_endyear);

        mStartYear.setMaxValue(2050);
        mStartYear.setMinValue(1950);

        mFinishYear.setMaxValue(2050);
        mFinishYear.setMinValue(1950);
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

    public void setmStartYear(String value){
        Integer startvalue=new Integer(value);
        mStartYear.setMaxValue(startvalue.intValue());

    }

    public void setmFinishYear(String value){
        Integer finishvalue=new Integer(value);
        mFinishYear.setMaxValue(finishvalue.intValue());

    }

    public int getStartYear(){
        return mStartYear.getValue();
    }

    public int getEndyear(){
        return mFinishYear.getValue();

    }
}
