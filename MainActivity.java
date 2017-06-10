package com.shamansoft.scramba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.shamansoft.scramba.R;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity 
{
	static Activity activity;
	
	private XMLParser xmlParser = null;
	private List<Word> words = null;
	
	private RandomSelector randomSelector = null;
	
	private Handler handler = null;
	
	private LettersSubInterface letters = null;
	
	private List<Integer> listLettersNoHover = null;
	private List<Integer> listLettersWithHover = null;
	
	private List<Integer> currentLetterOrders = null;
	public List<Integer> GetCurrentLetterOrders()
	{
		return currentLetterOrders;
	}
	
	private int sdk = 0;
	
	private Word randomWord = null;
	public String getWord()
	{
		String result = null;
		
		if(randomWord != null)
		{
			result = randomWord.word;
		}
		return result;
	}
	
	public void NewLevel()
	{
		setTask.run();//Once ayarlamalari tamamlatalim.
		handler.postDelayed(viewTask, 3000);//En son view ayarlamalarini tamamlasin.
	}
	
	@SuppressWarnings("deprecation")
	public void solve(View v)
	{
		if(randomWord != null)
		{
			LinearLayout lettersLayout = (LinearLayout)findViewById(R.id.harfler_layout);
			if(lettersLayout != null)
			{
				for(int i = 0; i < lettersLayout.getChildCount(); i++)
				{
					if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) 
					{
						lettersLayout.getChildAt(i).setBackgroundDrawable(getResources().getDrawable(letters.drawableWithHoverAddresses[letters.letterOrder.get(randomWord.word.charAt(i))]));
					} 
					else 
					{
						lettersLayout.getChildAt(i).setBackground(getResources().getDrawable(letters.drawableWithHoverAddresses[letters.letterOrder.get(randomWord.word.charAt(i))]));
					}
				}
				
				NewLevel();
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		activity = this;
		
		xmlParser = new XMLParser();
		words = new ArrayList<Word>();
		
		sdk = android.os.Build.VERSION.SDK_INT;
		
		letters = LettersSubInterface.Get();
		
		handler = new Handler();
		
		LogoPlay();
	}

 	private void LogoPlay()
  	{
 		setContentView(R.layout.logo);
 		
 	    final View logoView = (ImageView)findViewById(R.id.logo);
 	    
 	    final AlphaAnimation alphaDown = new AlphaAnimation(1.0f, 0.0f);
 	    alphaDown.setDuration(250);
 	    alphaDown.setFillAfter(true);
 	    alphaDown.setAnimationListener(new Animation.AnimationListener()
	   	{
			public void onAnimationRepeat(Animation arg0) {}
			public void onAnimationStart(Animation arg0) {}
			public void onAnimationEnd(Animation arg0) 
			{
				activity.setContentView(R.layout.activity_call);
				viewTask.run();
			}
		    	
	   	});
 		
 	    final AlphaAnimation alphaUp = new AlphaAnimation(0.0f, 1.0f);
 	    alphaUp.setDuration(1250);
 	    alphaUp.setFillAfter(true);
 	    alphaUp.setAnimationListener(new Animation.AnimationListener()
    	{
			public void onAnimationRepeat(Animation arg0) {}
			public void onAnimationStart(Animation arg0) 
			{
				parseTask.run();
			}
			public void onAnimationEnd(Animation arg0) 
			{
				logoView.startAnimation(alphaDown);
			}
 	    	
    	});
  	    logoView.startAnimation(alphaUp);
  	}
 	
 	private Runnable parseTask = new Runnable()
 	{
 		public void run()
 		{
 			try 
 			{
				words = xmlParser.Parse(getResources().getXml(R.xml.words));
			} 
 			catch (NotFoundException | XmlPullParserException | IOException e) 
 			{
				e.printStackTrace();
			}
 			
 			setTask.run();
 		}
 	};
 	
 	private Runnable viewTask = new Runnable()
 	{
 		@SuppressWarnings("deprecation")
		public void run()
 		{
 			LinearLayout lettersLayout = (LinearLayout)findViewById(R.id.harfler_layout);
            if(lettersLayout.getChildCount() > 0)
            {
            	lettersLayout.removeAllViewsInLayout();
            }
            
            LayoutParams lettersParams = new LayoutParams((int)getResources().getDimension(R.dimen.harfWidth), (int)getResources().getDimension(R.dimen.harfWidth));
    		for(int i=0; i<randomWord.word.length(); i++)
    		{
    			Letter letterButton = new Letter(activity, randomWord.word.charAt(i));
    			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) 
    			{
    				letterButton.setBackgroundDrawable(getResources().getDrawable(listLettersNoHover.get(i)));
    			} 
    			else 
    			{
    				letterButton.setBackground(getResources().getDrawable(listLettersNoHover.get(i)));
    			}
    			LetterClick newClickListener = new LetterClick(i, randomWord.word.charAt(i), listLettersNoHover.get(i), listLettersWithHover.get(i), (MainActivity)activity);
    			
    			letterButton.setOnClickListener(newClickListener);
    			lettersLayout.addView(letterButton, lettersParams);	
    		}
    		
    		ListView meaningList = (ListView)findViewById(R.id.kelime_aciklama_liste);
    		MeaningListAdapter mlAdapter = new MeaningListAdapter(activity, randomWord.meaning, R.layout.meaninglist);
    		meaningList.setAdapter(mlAdapter);
 		}
 	};
    private Runnable setTask = new Runnable() 
    {
		public void run() 
        {  
        	//Parse ettigimize gore artik RandomSelector sinifindan obje init edebiliriz.
    		randomSelector = new RandomSelector(words);
    		
    		randomWord = randomSelector.GetRandom();
    		
    		Integer[] letterOrders = new Integer[randomWord.word.length()];
    		for(int i=0; i<randomWord.word.length(); i++)
    		{
    			letterOrders[i] = letters.letterOrder.get(randomWord.word.charAt(i));
    		}
    		listLettersNoHover = new ArrayList<Integer>(Arrays.asList(letterOrders));
    		List<Integer> listLettersBackupToCompare = new ArrayList<Integer>(listLettersNoHover);
    		listLettersWithHover = new ArrayList<Integer>();
    		
    		while(listLettersNoHover.equals(listLettersBackupToCompare))
    		{
    			Collections.shuffle(listLettersNoHover);
    		}
    		
    		//Karistirilmis halini sirasal olarak tutalim.
    		currentLetterOrders = new ArrayList<Integer>(listLettersNoHover);
    		
    		for(int i=0; i<randomWord.word.length(); i++)
    		{
    			listLettersWithHover.add(letters.drawableWithHoverAddresses[listLettersNoHover.get(i)]);
    			listLettersNoHover.set(i, letters.drawableNoHoverAddresses[listLettersNoHover.get(i)]);
    		}
        }
    };
}