package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import edu.brandeis.cs.jiahuiming.resumeshare.R;

public class DoubleEditDialog extends BaseDialog {

	private EditText mEtNameEnter;
	private EditText mEtDescEnter;

	public DoubleEditDialog(Context context) {
		super(context);
		setDialogContentView(R.layout.include_dialog_double_edittext);
		mEtNameEnter = (EditText) findViewById(R.id.dialog_edittext_name_enter);
		mEtDescEnter = (EditText) findViewById(R.id.dialog_edittext_desc_enter);
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

	public String getNameText() {
		if (isNull(mEtNameEnter)) {
			return null;
		}
		return mEtNameEnter.getText().toString().trim();
	}

	public String getDescText() {
		if (isNull(mEtDescEnter)) {
			return null;
		}
		return mEtDescEnter.getText().toString().trim();
	}

	public void setTextNull() {
		mEtNameEnter.setText("");
		mEtDescEnter.setText("");
	}

	public void setNameHint(CharSequence text) {
		mEtNameEnter.setHint(text);
	}

	public void setDescHint(CharSequence text) {
		mEtDescEnter.setHint(text);
	}

	private boolean isNull(EditText editText) {
		String text = editText.getText().toString().trim();
		if (text != null && text.length() > 0) {
			return false;
		}
		return true;
	}

	public void requestNameFocus() {
		mEtNameEnter.requestFocus();
	}
	
	public void requestDescFocus() {
		mEtDescEnter.requestFocus();
	}
}
