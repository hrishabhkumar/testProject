package com.cogxio.util;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;

public class Utility 
{
	@SuppressWarnings("deprecation")
	public static Set<String> getTokensForIndexingOrQuery(String index_raw, int maximumNumberOfTokensToReturn, int minimumNumberOfCharacters)
	{
		Set<String> returnSet = new HashSet<String>();
		String[]	stopWords	= new String[] { "" };
		try
		{
			Analyzer analyzer = new SnowballAnalyzer(
					org.apache.lucene.util.Version.LUCENE_CURRENT, "English",
					stopWords);
			String inputArray[] = index_raw.split(" ");
			char[] charSentence;
			StringBuilder builder;
			int inputArraySize = inputArray.length;
			
			for (int i = 0; i < inputArraySize; i++)
			{
				charSentence = inputArray[i].toCharArray();
				builder = new StringBuilder();
				for (int j = 0; j < charSentence.length; j++)
				{
					builder.append(charSentence[j]);
					TokenStream tokenStream = analyzer.tokenStream("content",
							new StringReader(builder.toString()));
					Token token = new Token();
					while (((token = tokenStream.next()) != null) && (returnSet.size() < maximumNumberOfTokensToReturn))
					{
						if(token.term().length() >= minimumNumberOfCharacters)
						{
							returnSet.add(token.term());
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			
		}
		return returnSet;
	}
}
