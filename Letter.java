package com.shamansoft.scramba;

import android.content.Context;
import android.widget.Button;

public class Letter extends Button 
{
	private char character;
	
	public Letter(Context context, final char inChar) 
	{
		super(context);
		
		character = inChar;
	}
	
	public char GetCharacter()
	{
		return character;
	}
}
