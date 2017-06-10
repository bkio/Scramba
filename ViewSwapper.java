package com.shamansoft.scramba;

import android.view.View;
import android.widget.LinearLayout;

public class ViewSwapper 
{
	private int sdk = 0;
	
	private LinearLayout lettersLayout = null;
	
	//Index0 poziyonu, Index1 NoHoverID'yi, Index2 WithHoverID'yi verir.
	int[] companion1 = null;
	int[] companion2 = null;
	
	public ViewSwapper(LinearLayout layout)
	{
		this.lettersLayout = layout;
		
		this.sdk = android.os.Build.VERSION.SDK_INT;
	}
	
	@SuppressWarnings("deprecation")
	public void Swap(final int[] comp1, final int[] comp2)
	{
		companion1 = comp1;
		companion2 = comp2;
		
		View oldView = lettersLayout.getChildAt(comp1[0]);
		View newView = lettersLayout.getChildAt(comp2[0]); 
	
		lettersLayout.removeView(oldView);
	    lettersLayout.addView(oldView, comp2[0]);
		
	    lettersLayout.removeView(newView);
	    lettersLayout.addView(newView, comp1[0]);
	    
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) 
		{
			oldView.setBackgroundDrawable(oldView.getResources().getDrawable(comp1[1]));
			newView.setBackgroundDrawable(newView.getResources().getDrawable(comp2[1]));
		} 
		else 
		{
			oldView.setBackground(oldView.getResources().getDrawable(comp1[1]));
			newView.setBackground(newView.getResources().getDrawable(comp2[1]));
		}	
	}
}
