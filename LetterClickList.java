package com.shamansoft.scramba;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
public class LetterClickList
{
	private int[] companion1 = {-1, -1, -1};
	private int[] companion2 = {-1, -1, -1};
	private LetterClick firstCompanion = null;
	private LetterClick secondCompanion = null;
	
	public SoundPool soundPool = null;
	public int soundPopOn, soundPopOff, soundCongrats, soundSwosh;
	
	private LettersSubInterface letters = null;
	
	private MainActivity activity = null;
	
	//Singleton elemanlari
	private static LetterClickList instance = null;
	public static LetterClickList Get(final MainActivity inAct)
	{
		if(instance == null)
		{
			instance = new LetterClickList(inAct);
		}
		return instance;
	}
	private LetterClickList(final MainActivity inAct)
	{
		activity = inAct;
		
		letters = LettersSubInterface.Get();

		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    	soundPopOn = soundPool.load(activity, R.raw.pop_on, 1);
    	soundPopOff = soundPool.load(activity, R.raw.pop_on, 1);
    	soundCongrats = soundPool.load(activity, R.raw.congrats, 1);
    	soundSwosh = soundPool.load(activity, R.raw.swosh, 1);
    	
    	AudioManager audioManager = (AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);
	}
	
	public int NotifyClicked(LetterClick instance, final int position, final int letterNoHoverID, final int letterWithHoverID)
	{
		int result = 0;
		
		if(position != -1 && letterNoHoverID != -1 && letterWithHoverID != -1)
		{
			if(companion1[0] == -1)
			{
				companion1[2] = letterWithHoverID;
				companion1[1] = letterNoHoverID;
				companion1[0] = position;
				
				firstCompanion = instance;
				
				result = 1;
			}
			else if(companion2[0]  == -1)//Eger bu tiklanan ikinci harf ise
			{
				companion2[2]  = letterWithHoverID;
				companion2[1] = letterNoHoverID;
				companion2[0] = position;
				
				secondCompanion = instance;
				
				result = 2;
			}
		}
	
		return result;
	}
	
	public LetterClick GetFirstCompanion()
	{
		return firstCompanion;
	}
	public LetterClick GetSecondCompanion()
	{
		return secondCompanion;
	}
	
	public int[] GetCompanion1()
	{
		return companion1;
	}
	public int[] GetCompanion2()
	{
		return companion2;
	}
	public void SetSecondToFirst()
	{
		firstCompanion = secondCompanion;
		secondCompanion = null;
		
		for(int i=0; i<3; i++)
		{
			companion1[i] = companion2[i];
			companion2[i] = -1;
		}
	}
	public void ResetAllStuff()
	{
		firstCompanion = null;
		secondCompanion = null;
		
		for(int i=0; i<3; i++)
		{
			companion1[i] = -1;
			companion2[i] = -1;
		}	
	}
	
	public void AnimPressPlay(View v)
  	{
  		AlphaAnimation alphaDown = new AlphaAnimation(1.0f, 0.3f);
  		alphaDown.setDuration(50);
  		alphaDown.setFillAfter(true);
  		AlphaAnimation alphaUp = new AlphaAnimation(0.3f, 1.0f);
  		alphaUp.setDuration(50);
  		alphaUp.setFillAfter(true);
  	    v.startAnimation(alphaUp);
  	}
	
	public void CheckIfDone()
	{
		if(activity.getWord() != null)
		{
			int count = 0;
			
			for(int i = 0; i < activity.getWord().length(); i++)
			{
				if(activity.GetCurrentLetterOrders().get(i) == letters.letterOrder.get(activity.getWord().charAt(i)))
				{
					Log.d("BURAK", "Current Char: " + activity.GetCurrentLetterOrders().get(i)+ " + CompareTo: " + activity.getWord().charAt(i));
					count++;
				}
			}
	
			//Eger hepsi tamamsa
			if(count == activity.getWord().length())
			{	
				soundPool.play(soundCongrats, 1.0f, 1.0f, 0, 0, 1.0f);
				
				activity.solve(null);
			}
		}		
	}
}
