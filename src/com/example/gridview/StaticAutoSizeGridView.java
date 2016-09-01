package com.example.gridview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class StaticAutoSizeGridView extends LinearLayout {
	private IStaticAutoSizeGridViewAdapter mAdapter;
	private List<View> mDriverList = new ArrayList<View>();

	public StaticAutoSizeGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public StaticAutoSizeGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();

	}

	public StaticAutoSizeGridView(Context context) {
		super(context);
		initView();
	}

	private void initView() {
		this.setOrientation(LinearLayout.VERTICAL);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	private LinearLayout.LayoutParams getRowLayoutLP() {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		lp.weight = 1;
		return lp;
	}

	private LayoutParams getVerticalDriverLP() {
		LayoutParams lp = new LayoutParams(this.mAdapter.getHorizontalSpacing(), LayoutParams.MATCH_PARENT);
		return lp;
	}

	private LayoutParams getHorizontalDriverLP() {
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, this.mAdapter.getVerticalSpacing());
		return lp;
	}

	private LinearLayout.LayoutParams getGeneralItemLP() {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		lp.weight = 1;
		return lp;
	}

	public void layoutSubViews() {
		this.removeAllViews();
		int col = this.mAdapter.getNumOfColumn();
		int row = this.mAdapter.getNumOfRow();
		int count = this.mAdapter.getCount();
		LinearLayout rowViewLayout = null;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (0 == j) {
					rowViewLayout = new LinearLayout(this.getContext());
					rowViewLayout.setOrientation(LinearLayout.HORIZONTAL);
					this.addView(rowViewLayout, this.getRowLayoutLP());
					if ((row - 1) != i) {
						View rowDriver = new View(this.getContext());
						rowDriver.setBackgroundColor(Color.BLACK);
						mDriverList.add(rowDriver);
						this.addView(rowDriver, this.getHorizontalDriverLP());
					}
				}
				LinearLayout.LayoutParams itemViewLP = this.getGeneralItemLP();
				int position = i * col + j;
				View insertView = null;
				if (count > position) {
					insertView= this.mAdapter.getView(position);
					if (insertView == null) {
						insertView = new View(this.getContext());
					}

				} else {
					insertView= new View(this.getContext());
				}
				// add item view
				rowViewLayout.addView(insertView, itemViewLP);
				if ((col - 1) != j) {
					View colDriver = new View(this.getContext());
					colDriver.setBackgroundColor(Color.BLACK);
					this.mDriverList.add(colDriver);
					rowViewLayout.addView(colDriver, this.getVerticalDriverLP());
				}
			}
		}
	}

	public void setAdapter(IStaticAutoSizeGridViewAdapter adapter) {
		this.mAdapter = adapter;
		layoutSubViews();
		this.requestLayout();
	}
}
