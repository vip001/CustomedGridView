package com.example.gridview;

import android.view.View;

public interface IStaticAutoSizeGridViewAdapter {
	int getNumOfColumn();

	int getNumOfRow();

	int getCount();

	View getView(int position);

	int getVerticalSpacing();

	int getHorizontalSpacing();

}
