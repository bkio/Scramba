package com.shamansoft.scramba;

import java.util.HashMap;
import java.util.Map;

public class LettersSubInterface 
{
	public final int[] drawableNoHoverAddresses = new int[26];
	public final int[] drawableWithHoverAddresses = new int[26];
	
	private static LettersSubInterface instance = null;

	public final Map<Character, Integer> letterOrder = new HashMap<Character, Integer>();
	
	//Singleton pattern'a gore tasarlanmistir.
	public static LettersSubInterface Get()
	{
		if(instance == null)
		{
			instance = new LettersSubInterface();
		}
		return instance;
	}
	
	private LettersSubInterface() 
	{
		drawableNoHoverAddresses[0] = R.drawable.a;
		drawableNoHoverAddresses[1] = R.drawable.b;
		drawableNoHoverAddresses[2] = R.drawable.c;
		drawableNoHoverAddresses[3] = R.drawable.d;
		drawableNoHoverAddresses[4] = R.drawable.e;
		drawableNoHoverAddresses[5] = R.drawable.f;
		drawableNoHoverAddresses[6] = R.drawable.g;
		drawableNoHoverAddresses[7] = R.drawable.h;
		drawableNoHoverAddresses[8] = R.drawable.i;
		drawableNoHoverAddresses[9] = R.drawable.j;
		drawableNoHoverAddresses[10] = R.drawable.k;
		drawableNoHoverAddresses[11] = R.drawable.l;
		drawableNoHoverAddresses[12] = R.drawable.m;
		drawableNoHoverAddresses[13] = R.drawable.n;
		drawableNoHoverAddresses[14] = R.drawable.o;
		drawableNoHoverAddresses[15] = R.drawable.p;
		drawableNoHoverAddresses[16] = R.drawable.q;
		drawableNoHoverAddresses[17] = R.drawable.r;
		drawableNoHoverAddresses[18] = R.drawable.s;
		drawableNoHoverAddresses[19] = R.drawable.t;
		drawableNoHoverAddresses[20] = R.drawable.u;
		drawableNoHoverAddresses[21] = R.drawable.v;
		drawableNoHoverAddresses[22] = R.drawable.w;
		drawableNoHoverAddresses[23] = R.drawable.x;
		drawableNoHoverAddresses[24] = R.drawable.y;
		drawableNoHoverAddresses[25] = R.drawable.z;
		
		drawableWithHoverAddresses[0] = R.drawable.ahover;
		drawableWithHoverAddresses[1] = R.drawable.bhover;
		drawableWithHoverAddresses[2] = R.drawable.chover;
		drawableWithHoverAddresses[3] = R.drawable.dhover;
		drawableWithHoverAddresses[4] = R.drawable.ehover;
		drawableWithHoverAddresses[5] = R.drawable.fhover;
		drawableWithHoverAddresses[6] = R.drawable.ghover;
		drawableWithHoverAddresses[7] = R.drawable.hhover;
		drawableWithHoverAddresses[8] = R.drawable.ihover;
		drawableWithHoverAddresses[9] = R.drawable.jhover;
		drawableWithHoverAddresses[10] = R.drawable.khover;
		drawableWithHoverAddresses[11] = R.drawable.lhover;
		drawableWithHoverAddresses[12] = R.drawable.mhover;
		drawableWithHoverAddresses[13] = R.drawable.nhover;
		drawableWithHoverAddresses[14] = R.drawable.ohover;
		drawableWithHoverAddresses[15] = R.drawable.phover;
		drawableWithHoverAddresses[16] = R.drawable.qhover;
		drawableWithHoverAddresses[17] = R.drawable.rhover;
		drawableWithHoverAddresses[18] = R.drawable.shover;
		drawableWithHoverAddresses[19] = R.drawable.thover;
		drawableWithHoverAddresses[20] = R.drawable.uhover;
		drawableWithHoverAddresses[21] = R.drawable.vhover;
		drawableWithHoverAddresses[22] = R.drawable.whover;
		drawableWithHoverAddresses[23] = R.drawable.xhover;
		drawableWithHoverAddresses[24] = R.drawable.yhover;
		drawableWithHoverAddresses[25] = R.drawable.zhover;

		letterOrder.put('a', 0);
		letterOrder.put('b', 1);
		letterOrder.put('c', 2);
		letterOrder.put('d', 3);
		letterOrder.put('e', 4);
		letterOrder.put('f', 5);
		letterOrder.put('g', 6);
		letterOrder.put('h', 7);
		letterOrder.put('i', 8);
		letterOrder.put('j', 9);
		letterOrder.put('k', 10);
		letterOrder.put('l', 11);
		letterOrder.put('m', 12);
		letterOrder.put('n', 13);
		letterOrder.put('o', 14);
		letterOrder.put('p', 15);
		letterOrder.put('q', 16);
		letterOrder.put('r', 17);
		letterOrder.put('s', 18);
		letterOrder.put('t', 19);
		letterOrder.put('u', 20);
		letterOrder.put('v', 21);
		letterOrder.put('w', 22);
		letterOrder.put('x', 23);
		letterOrder.put('y', 24);
		letterOrder.put('z', 25);
	}
}
