package com.shamansoft.scramba;

import java.util.List;

public class Word 
{
	public final String word;
	public final String frequency;
    public final List<String> meaning;

    public Word(String word, String frequency, List<String> meaning) 
    {
    	this.word = word;
    	this.frequency = frequency;
        this.meaning = meaning;
    }
}