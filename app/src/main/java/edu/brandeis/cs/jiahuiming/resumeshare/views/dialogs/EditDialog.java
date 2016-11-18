package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.widget.EditText;

import edu.brandeis.cs.jiahuiming.resumeshare.R;

/**
 * Created by jiahuiming on 11/17/16.
 */

public class EditDialog extends BaseDialog {
    private EditText mEditText;

    public EditDialog(Context context) {
        super(context);
        setDialogContentView(R.layout.include_dialog_edittext);
        mEditText = (EditText) findViewById(R.id.dialog_edittext);
    }

    @Override
    public void setTitle(CharSequence text) {
        super.setTitle(text);
    }

    public void setButton(CharSequence text,
                          OnClickListener listener) {
        super.setButton1(text, listener);
    }

    public void setButton(CharSequence text1,
                          OnClickListener listener1, CharSequence text2,
                          OnClickListener listener2) {
        super.setButton1(text1, listener1);
        super.setButton2(text2, listener2);
    }

    public String getEditText() {
        if (isNull(mEditText)) {
            return null;
        }
        return mEditText.getText().toString().trim();
    }

    public void setTextNull() {
        mEditText.setText("");
    }

    public void setEditTextHint(CharSequence text) {
        mEditText.setHint(text);
    }

    private boolean isNull(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text != null && text.length() > 0) {
            return false;
        }
        return true;
    }
    public void requestEditTextFocus() {
        mEditText.requestFocus();
    }
}
