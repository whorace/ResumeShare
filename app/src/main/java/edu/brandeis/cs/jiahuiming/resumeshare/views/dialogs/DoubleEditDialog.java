package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import edu.brandeis.cs.jiahuiming.resumeshare.R;

public class DoubleEditDialog extends BaseDialog {

	private EditText mEtFirstName;
	private EditText mEtSecondName;

	public DoubleEditDialog(Context context) {
		super(context);
		setDialogContentView(R.layout.include_dialog_double_edittext);
		mEtFirstName = (EditText) findViewById(R.id.dialog_edittext_firstname);
		mEtSecondName = (EditText) findViewById(R.id.dialog_edittext_secondname);
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

	public String getFirstNameText() {
		if (isNull(mEtFirstName)) {
			return null;
		}
		return mEtFirstName.getText().toString().trim().replace(" ","%20");
	}

	public String getSecondNameText() {
		if (isNull(mEtSecondName)) {
			return null;
		}
		return mEtSecondName.getText().toString().trim().replace(" ","%20");
	}

	public void setTextNull() {
		mEtFirstName.setText("");
		mEtSecondName.setText("");
	}

	public void setFirstNameHint(CharSequence text) {
		mEtFirstName.setHint(text);
	}

	public void setSecondNameHint(CharSequence text) {
		mEtSecondName.setHint(text);
	}

	private boolean isNull(EditText editText) {
		String text = editText.getText().toString().trim();
		if (text != null && text.length() > 0) {return false;
		}
		return true;
	}

	public void requestFirstNameNameFocus() {
		mEtFirstName.requestFocus();
	}
	public void requestSecondNameFocus() {mEtSecondName.requestFocus();
	}
}
