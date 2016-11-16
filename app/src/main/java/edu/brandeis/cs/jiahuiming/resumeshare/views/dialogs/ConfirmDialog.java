package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;

import edu.brandeis.cs.jiahuiming.resumeshare.R;


public class ConfirmDialog extends BaseDialog {

	private TextView mTextView;

	public ConfirmDialog(Context context) {
		super(context);
		setDialogContentView(R.layout.include_dialogconfirmtextview);
		mTextView = (TextView) findViewById(R.id.dialog_textview);
	}
	
	
	public void setmax(int max)
	{
		mTextView.setMaxEms(max);
		mTextView.setMaxLines(max);
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

/*	public String getText() {
		if (isNull(mTextView)) {
			return null;
		}
		return mTextView.getText().toString().trim();
	}*/
	
	public void setTextNull() {
		mTextView.setText(null);
	}
	
	public void setText(CharSequence text) {
		mTextView.setText(text);
	}
	
	public void setHint(CharSequence text) {
		mTextView.setHint(text);
	}

	private boolean isNull(EditText editText) {
		String text = editText.getText().toString().trim();
		if (text != null && text.length() > 0) {
			return false;
		}
		return true;
	}

	public void requestFocus() {
		mTextView.requestFocus();
	}
}
