package com.shamansoft.scramba;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MeaningListAdapter extends ArrayAdapter<String>
{
	private final Activity context;
	private final List<String> meaningList;
	private final int layoutID;
	
	public MeaningListAdapter(Activity context, List<String> meanings, int id) 
	{
		super(context, id, meanings);
		this.context = context;
		this.meaningList = meanings;
		this.layoutID = id;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) 
	{
		LayoutInflater inflater = context.getLayoutInflater();
		
		View rowView = inflater.inflate(layoutID, null, true);
		
		if(meaningList != null)
		{
			TextView text1 = (TextView) rowView.findViewById(R.id.TextMeaning);
			text1.setText(meaningList.get(position));
		}
		return rowView;
	}
}