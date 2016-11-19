package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.widget.EditText;
import android.widget.NumberPicker;

import edu.brandeis.cs.jiahuiming.resumeshare.R;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class AddExperienceDialog extends BaseDialog {

    private EditText mAddCompany;
    private EditText mAddPosition;

    public AddExperienceDialog(Context context) {
        super(context);
        setDialogContentView(R.layout.include_dialog_addexperience);
        mAddCompany = (EditText) findViewById(R.id.et_addcompany);
        mAddPosition = (EditText) findViewById(R.id.et_addposition);
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

    public String getAddCompanyText() {
        if (isNull(mAddCompany)) {
            return null;
        }
        return mAddCompany.getText().toString().trim();
    }

    public String getAddPositionText() {
        if (isNull(mAddPosition)) {
            return null;
        }
        return mAddPosition.getText().toString().trim();
    }


    public void setTextNull() {
        mAddCompany.setText("");
        mAddPosition.setText("");
    }

    public void setAddCompanyHint(CharSequence text) {
        mAddCompany.setHint(text);
    }
    public void setAddPositionHint(CharSequence text) {
        mAddPosition.setHint(text);
    }
    private boolean isNull(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text != null && text.length() > 0) {return false;
        }
        return true;
    }
}
