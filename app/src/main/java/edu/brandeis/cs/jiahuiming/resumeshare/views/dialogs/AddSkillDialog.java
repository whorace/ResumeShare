package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.widget.EditText;

import edu.brandeis.cs.jiahuiming.resumeshare.R;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class AddSkillDialog extends BaseDialog {
    private EditText mAddSkill;

    public AddSkillDialog(Context context) {
        super(context);
        setDialogContentView(R.layout.include_dialog_addskill);
        mAddSkill = (EditText) findViewById(R.id.et_addskill);
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

    public String getAddSkillText() {
        if (isNull(mAddSkill)) {
            return null;
        }
        return mAddSkill.getText().toString().trim();
    }



    public void setTextNull() {
        mAddSkill.setText("");
    }

    public void setAddSkillHint(CharSequence text) {
        mAddSkill.setHint(text);
    }

    private boolean isNull(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text != null && text.length() > 0) {return false;
        }
        return true;
    }
}
