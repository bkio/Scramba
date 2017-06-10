package com.shamansoft.scramba;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class LetterClick implements OnClickListener
{	
	private int sdk = 0;
	private int letterPosition = -1;
	private int letterNoHoverID = -1;
	private int letterWithHoverID = -1;
	private boolean isHover = false;
	
	private LinearLayout layout = null;
	private MainActivity activity = null;
	
	private LetterClickList allClicks = null;
	
	private ViewSwapper swapper = null;
	
	private char character;
	
	public boolean GetIsHover()
	{
		return isHover;
	}

	public LetterClick(final int position, final char inChar, final int noHover, final int withHover, final MainActivity inAct)
	{
		letterPosition = position;
		letterNoHoverID = noHover;
		letterWithHoverID = withHover;
		
		character = inChar;
		
		activity = inAct;
		layout = (LinearLayout)inAct.findViewById(R.id.harfler_layout);
		
		swapper = new ViewSwapper(layout);
		
		sdk = android.os.Build.VERSION.SDK_INT;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) 
	{
		if(!isHover)
		{
			allClicks = LetterClickList.Get(activity);
			
			int hoveredNo = allClicks.NotifyClicked(this, letterPosition, letterNoHoverID, letterWithHoverID);
			if(hoveredNo > 0)
			{
				allClicks.AnimPressPlay(v);
				
				isHover = true;
				
				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) 
				{
					v.setBackgroundDrawable(v.getResources().getDrawable(letterWithHoverID));
				} 
				else 
				{
					v.setBackground(v.getResources().getDrawable(letterWithHoverID));
				}	
				
				if(hoveredNo == 2)
				{
					allClicks.soundPool.play(allClicks.soundSwosh, 1.0f, 1.0f, 0, 0, 1.0f);
					
					swapper.Swap(allClicks.GetCompanion1(), allClicks.GetCompanion2());
					hoveredNo = 0;
					
					//Degisimler
					int tmp = activity.GetCurrentLetterOrders().get(letterPosition);
					activity.GetCurrentLetterOrders().set(letterPosition, activity.GetCurrentLetterOrders().get(allClicks.GetFirstCompanion().letterPosition));
					activity.GetCurrentLetterOrders().set(allClicks.GetFirstCompanion().letterPosition, tmp);
					
					int tmpPos = letterPosition;
					letterPosition = allClicks.GetFirstCompanion().letterPosition;
					allClicks.GetFirstCompanion().letterPosition = tmpPos;
					
					char tmpChr = character;
					character = allClicks.GetFirstCompanion().character;
					allClicks.GetFirstCompanion().character = tmpChr;
					
					isHover = false;
					allClicks.GetFirstCompanion().isHover = false;
					
					allClicks.CheckIfDone();
					
					allClicks.ResetAllStuff();
				}
				else
				{
					allClicks.soundPool.play(allClicks.soundPopOn, 1.0f, 1.0f, 0, 0, 1.0f);
				}
			}
		}
		else//Eger harfe tiklanmissa
		{
			isHover = false;
			
			allClicks.soundPool.play(allClicks.soundPopOff, 1.0f, 1.0f, 0, 0, 1.0f);
			
			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) 
			{
				v.setBackgroundDrawable(v.getResources().getDrawable(letterNoHoverID));
			} 
			else 
			{
				v.setBackground(v.getResources().getDrawable(letterNoHoverID));
			}	
			
			//Eger bu ilk tiklanan ise
			if(allClicks.GetFirstCompanion() == this)
			{
				//Eger ikincisi de varsa
				if(allClicks.GetSecondCompanion() != null)
				{
					allClicks.SetSecondToFirst();
				}
				else
				{
					allClicks.ResetAllStuff();
				}
			}
		}
	}
}
