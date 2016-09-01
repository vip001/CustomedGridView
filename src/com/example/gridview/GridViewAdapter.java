package com.example.gridview;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

public class GridViewAdapter implements IStaticAutoSizeGridViewAdapter {
	private ArrayList<View> mViews;
	private Context mContext;

	public GridViewAdapter(Context context) {
		mViews = new ArrayList<View>();
		this.mContext = context;
	}

	@Override
	public int getNumOfColumn() {
		return 4;
	}

	@Override
	public int getNumOfRow() {
		return 4;
	}

	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public View getView(int position) {
		if (position >= getCount())
			return null;
		View itemView = mViews.size() > position ? mViews.get(position) : null;
		if (itemView == null) {
			itemView = new ImageView(mContext);
			itemView.setBackgroundResource(R.drawable.ic_launcher);
			mViews.add(itemView);
		} else {
			ViewParent vp = itemView.getParent();
			if (vp != null && vp instanceof ViewGroup) {
				((ViewGroup) vp).removeView(itemView);
			}
		}
		return itemView;
	}

	@Override
	public int getVerticalSpacing() {
		return 2;
	}

	@Override
	public int getHorizontalSpacing() {
		// TODO Auto-generated method stub
		return 2;
	}

}
