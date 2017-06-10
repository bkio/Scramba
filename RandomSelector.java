package com.shamansoft.scramba;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class RandomSelector 
{
	private int[] retiredWords = null; 
	private int retiredWordsLastIndex = 0;
	
    private Random rand = new Random();
    private int totalSum = 0;
    
    private List<Word> items = null;

    public RandomSelector(List<Word> inItems) 
    {
    	items = inItems;
    	
        for(Word item : items) 
        {
            totalSum = totalSum + Integer.parseInt(item.frequency);
        }
        
        retiredWords = new int[items.size()];
    }
    
    private int GetRandomIndex()
    {
    	int result = -1;
    	
    	int index = rand.nextInt(totalSum);
        int sum = 0;
        int i = 0;
        while(sum < index ) 
        {
             sum = sum + Integer.parseInt(items.get(i++).frequency);
        }
        
        result = i-1;
         
        int isExist = Arrays.binarySearch(retiredWords, result);
        if(isExist >= 0)//Eger daha once cikmissa
        {
        	result = GetRandomIndex();
        }
        else
        {
        	//Onceden cikmislara ekleyelim.
            retiredWords[retiredWordsLastIndex] = result;
            retiredWordsLastIndex++;
            Arrays.sort(retiredWords);//lgN zamanda erisebilmek icin surekli sort edelim.
        }
    	return result;
    }

    public Word GetRandom() 
    {
        return items.get(GetRandomIndex());
    }
}