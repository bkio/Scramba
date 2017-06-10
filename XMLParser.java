package com.shamansoft.scramba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;

public class XMLParser 
{
    private static final String ns = null;
   
	public List<Word> Parse(XmlResourceParser parser) throws XmlPullParserException, IOException 
    {
        try 
        {
            parser.next();
            parser.next();
            return ReadWords(parser);
        } 
        finally 
        {
            parser.close();
        }
    }
    private List<Word> ReadWords(XmlPullParser parser) throws XmlPullParserException, IOException 
    {
        List<Word> words = new ArrayList<Word>();

        parser.require(XmlPullParser.START_TAG, ns, "words");
        while (parser.next() != XmlPullParser.END_TAG) 
        {
            if (parser.getEventType() != XmlPullParser.START_TAG) 
            {
                continue;
            }
            String name = parser.getName();
            
            if (name.equals("word")) 
            {
                words.add(ReadWord(parser));
            } 
            else 
            {
                Skip(parser);
            }
        }  
        return words; 	
    }
    
    private void Skip(XmlPullParser parser) throws XmlPullParserException, IOException 
    {
        if (parser.getEventType() != XmlPullParser.START_TAG) 
        {
            throw new IllegalStateException();
        }
        
        int depth = 1;
        while (depth != 0) 
        {
            switch (parser.next()) 
            {
	            case XmlPullParser.END_TAG:
	                depth--;
	                break;
	            case XmlPullParser.START_TAG:
	                depth++;
	                break;
            }
        }
    }
    private Word ReadWord(XmlPullParser parser) throws XmlPullParserException, IOException 
    {
        parser.require(XmlPullParser.START_TAG, ns, "word");
        String word = parser.getAttributeValue(null, "word");  
        String frequency = parser.getAttributeValue(null, "frequency");
        List<String> meaning = new ArrayList<String>();
        while (parser.next() != XmlPullParser.END_TAG) 
        {
            if (parser.getEventType() != XmlPullParser.START_TAG) 
            {
                continue;
            }
            
            String name = parser.getName();
            if (name.equals("meaning")) 
            {
                meaning.add(ReadMeaning(parser));
            } 
            else 
            {
                Skip(parser);
            }
        }
        return new Word(word, frequency, meaning);
    }

    private String ReadMeaning(XmlPullParser parser) throws IOException, XmlPullParserException 
    {
        parser.require(XmlPullParser.START_TAG, ns, "meaning");
        String meaning = ReadText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "meaning");
        return meaning;
    }

    private String ReadText(XmlPullParser parser) throws IOException, XmlPullParserException 
    {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) 
        {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
