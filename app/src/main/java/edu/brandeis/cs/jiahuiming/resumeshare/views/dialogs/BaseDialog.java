package edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.views.widgets.HandyTextView;

public class BaseDialog extends Dialog implements
		View.OnClickListener {

	private Context mContext;//
	private LinearLayout mLayoutRoot;//
	private LinearLayout mLayoutTitle;//
	private TextView mTvTitle;//
	private View mViewTitleLine;//
	private LinearLayout mLayoutContent;//
	private HandyTextView mHtvMessage;//
	private LinearLayout mLayoutBottom;//
	private Button mBtnButton1;//
	private Button mBtnButton2;//
	private Button mBtnButton3;//

	private static BaseDialog mBaseDialog;//
	private OnClickListener mOnClickListener1;//
	private OnClickListener mOnClickListener2;//
	private OnClickListener mOnClickListener3;//

	public BaseDialog(Context context) {
		super(context, R.style.Theme_Light_FullScreenDialogAct);
		mContext = context;
		setContentView(R.layout.dialog_common);
		initViews();
		initEvents();
		setCancelable(true);
		setCanceledOnTouchOutside(true);
	}

	private void initViews() {
		mLayoutRoot = (LinearLayout) findViewById(R.id.ly_dg_root);
		mLayoutTitle = (LinearLayout) findViewById(R.id.ly_dg_title);
		mTvTitle = (TextView) findViewById(R.id.tv_title);
		mViewTitleLine = findViewById(R.id.view_titleline);
		mLayoutContent = (LinearLayout) findViewById(R.id.ly_dialog_content);
		mHtvMessage = (HandyTextView) findViewById(R.id.tv_htv);
		mLayoutBottom = (LinearLayout) findViewById(R.id.ly_dialog_bottom);
		mBtnButton1 = (Button) findViewById(R.id.btn_button1);
		mBtnButton2 = (Button) findViewById(R.id.btn_button2);
		mBtnButton3 = (Button) findViewById(R.id.btn_button3);
		mLayoutRoot.setVisibility(View.VISIBLE);
		setTitleLineVisibility(View.VISIBLE);

	}

	private void initEvents() {
		mBtnButton1.setOnClickListener(this);
		mBtnButton2.setOnClickListener(this);
		mBtnButton3.setOnClickListener(this);
	}

	public void setDialogContentView(int resource) {
		View v = LayoutInflater.from(mContext).inflate(resource, null);
		if (mLayoutContent.getChildCount() > 0) {
			mLayoutContent.removeAllViews();
		}
		mLayoutContent.addView(v);
	}

	public void setDialogContentView(int resource,
			LinearLayout.LayoutParams params) {
		View v = LayoutInflater.from(mContext).inflate(resource, null);
		if (mLayoutContent.getChildCount() > 0) {
			mLayoutContent.removeAllViews();
		}
		mLayoutContent.addView(v, params);
	}

	public static BaseDialog getDialog(Context context, CharSequence title,
			CharSequence message) {
		return getDialog(context, title, message, null, null, null, null, null,
				null);
	}

	public static BaseDialog getDialog(Context context, CharSequence title,
			CharSequence message, CharSequence button1,
			OnClickListener listener1) {
		return getDialog(context, title, message, button1, listener1, null,
				null, null, null);
	}

	public static BaseDialog getDialog(Context context, CharSequence title,
			CharSequence message, CharSequence button1,
			OnClickListener listener1, CharSequence button2,
			OnClickListener listener2) {
		return getDialog(context, title, message, button1, listener1, button2,
				listener2, null, null);
	}

	public static BaseDialog getDialog(Context context, CharSequence title,
			CharSequence message, CharSequence button1,
			OnClickListener listener1, CharSequence button2,
			OnClickListener listener2, CharSequence button3,
			OnClickListener listener3) {
		mBaseDialog = new BaseDialog(context);
		if (mBaseDialog.titleAndMessageIsExist(title, message)) {
			mBaseDialog.setTitle(title);
			mBaseDialog.setMessage(message);
		}
		if (mBaseDialog.buttonIsExist(button1, listener1, button2, listener2,
				button3, listener3)) {
			mBaseDialog.setButton1(button1, listener1);
			mBaseDialog.setButton2(button2, listener2);
			mBaseDialog.setButton3(button3, listener3);
		}
		mBaseDialog.setCancelable(true);
		mBaseDialog.setCanceledOnTouchOutside(true);
		return mBaseDialog;
	}

	public boolean titleAndMessageIsExist(CharSequence title,
			CharSequence message) {
		if (title == null && message == null) {
			mTvTitle.setVisibility(View.GONE);
			return false;
		} else {
			mTvTitle.setVisibility(View.VISIBLE);
			return true;
		}
	}

	@Override
	public void setTitle(CharSequence text) {
		if (text != null) {
			mLayoutTitle.setVisibility(View.VISIBLE);
			mTvTitle.setText(text);
		} else {
			mLayoutTitle.setVisibility(View.GONE);
		}
	}

	public void setMessage(CharSequence text) {
		if (text != null) {
			mLayoutContent.setVisibility(View.VISIBLE);
			mHtvMessage.setText(text);
		} else {

			mLayoutContent.setVisibility(View.GONE);
		}
	}

	public boolean buttonIsExist(CharSequence button1, OnClickListener listener1, CharSequence button2, OnClickListener listener2, CharSequence button3, OnClickListener listener3) {
		if ((button1 != null && listener1 != null) || (button2 != null && listener2 != null) || (button3 != null && listener3 != null)) {
			mLayoutBottom.setVisibility(View.VISIBLE);
			return true;
		} else {
			mLayoutBottom.setVisibility(View.GONE);
			return false;
		}
	}

	public void setButton1(CharSequence text, OnClickListener listener) {
		if (text != null && listener != null) {
			mLayoutBottom.setVisibility(View.VISIBLE);
			mBtnButton1.setVisibility(View.VISIBLE);
			mBtnButton1.setText(text);
			mOnClickListener1 = listener;
		} else {
			mBtnButton1.setVisibility(View.GONE);
		}
	}

	public void setButton2(CharSequence text, OnClickListener listener) {
		if (text != null && listener != null) {
			mLayoutBottom.setVisibility(View.VISIBLE);
			mBtnButton2.setVisibility(View.VISIBLE);
			mBtnButton2.setText(text);
			mOnClickListener2 = listener;
		} else {
			mBtnButton2.setVisibility(View.GONE);
		}
	}

	public void setButton3(CharSequence text, OnClickListener listener) {
		if (text != null && listener != null) {
			mLayoutBottom.setVisibility(View.VISIBLE);
			mBtnButton3.setVisibility(View.VISIBLE);
			mBtnButton3.setText(text);
			mOnClickListener3 = listener;
		} else {
			mBtnButton3.setVisibility(View.GONE);
		}
	}

	public void setButton1Background(int id) {
		mBtnButton1.setBackgroundResource(id);
	}
	public void setButton2Background(int id) {
		mBtnButton2.setBackgroundResource(id);
	}
	public void setButton3Background(int id) {
		mBtnButton3.setBackgroundResource(id);
	}

	public void setTitleLineVisibility(int visibility) {
		mViewTitleLine.setVisibility(visibility);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_button1:
			if (mOnClickListener1 != null) {
				mOnClickListener1.onClick(this, 0);
			}
			break;

		case R.id.btn_button2:
			if (mOnClickListener2 != null) {
				mOnClickListener2.onClick(this, 1);
			}
			break;

		case R.id.btn_button3:
			if (mOnClickListener3 != null) {
				mOnClickListener3.onClick(this, 2);
			}
			break;
		}
	}
}
