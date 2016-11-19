package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.widget.EditText;
import android.widget.NumberPicker;

import edu.brandeis.cs.jiahuiming.resumeshare.R;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class AddEducationDialog extends BaseDialog {
    private EditText mAddSchool;
    private EditText mAddMajor;
    private EditText mAddDegree;
    private NumberPicker mAddStartYear;
    private NumberPicker mAddEndYear;

    public AddEducationDialog(Context context) {
        super(context);
        setDialogContentView(R.layout.include_dialog_addeducation);
        mAddSchool = (EditText) findViewById(R.id.et_addschool);
        mAddMajor = (EditText) findViewById(R.id.et_addmajor);
        mAddDegree = (EditText) findViewById(R.id.et_adddegree);
        mAddStartYear = (NumberPicker) findViewById(R.id.np_addstartyear);
        mAddEndYear=(NumberPicker)findViewById(R.id.np_endyear);
    }

    @Override
    public void setTitle(CharSequence text) {
        super.setTitle(text);
    }

    public void setButton(CharSequence text, OnClickListener listener) {
        super.setButton1(text, listener);
    }

    public void setButton(CharSequence text1, OnClickListener listener1, CharSequence text2, OnClickListener listener2) {
        super.setButton1(text1, listener1);
        super.setButton2(text2, listener2);
    }

    public String getAddSchoolText() {
        if (isNull(mAddSchool)) {
            return null;
        }
        return mAddSchool.getText().toString().trim().replace(" ","%20");
    }

    public String getAddMajorText() {
        if (isNull(mAddMajor)) {
            return null;
        }
        return mAddMajor.getText().toString().trim().replace(" ","%20");
    }

    public String getAddDegreeText() {
        if (isNull(mAddDegree)) {
            return null;
        }
        return mAddDegree.getText().toString().trim().replace(" ","%20");
    }

    public String getAddStartText() {

        return new Integer(mAddStartYear.getValue()).toString().trim().replace(" ","%20");
    }
    public String getAddEndYearText() {
        return new Integer(mAddEndYear.getValue()).toString().trim().replace(" ","%20");
    }

    public void setTextNull() {
        mAddSchool.setText("");
        mAddMajor.setText("");
        mAddDegree.setText("");
        mAddStartYear.setValue(2016);
        mAddEndYear.setValue(2020);
    }

    public void setAddSchoolHint(CharSequence text) {
        mAddSchool.setHint(text);
    }
    public void setAddMajorHint(CharSequence text) {
        mAddMajor.setHint(text);
    }
    public void setAddDegreeHint(CharSequence text) {
        mAddDegree.setHint(text);
    }

    private boolean isNull(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text != null && text.length() > 0) {return false;
        }
        return true;
    }
}
