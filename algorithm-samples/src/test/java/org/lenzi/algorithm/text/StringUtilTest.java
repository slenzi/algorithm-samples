package org.lenzi.algorithm.text;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.lenzi.algorithm.text.util.StringUtil;

/**
 * Test StringUtil
 * 
 * @author slenzi
 */
public class StringUtilTest {

	private class OccuranceTest {
		private String input = null;
		private int count = 0;
		public OccuranceTest(String input, int count){
			this.input = input;
			this.count = count;
		}
		public String getInput(){
			return input;
		}
		public int getCount(){
			return count;
		}
	}
	
	/**
	 * Test StringUtil.removeConsecutive(String,int)
	 * 
	 */
	@Test
	public void testRemoveConsecutive(){
		
		HashMap<OccuranceTest,String> tests = new HashMap<OccuranceTest,String>();
		
		tests.put(new OccuranceTest("aaab", 2),"aab");
		tests.put(new OccuranceTest("aabb", 1),"ab");
		tests.put(new OccuranceTest("aabbaa", 1),"aba");
		tests.put(new OccuranceTest("abbbbbccceeeee", 3),"abbbccceee");
		tests.put(new OccuranceTest("aaaaaaabbbbaaa", 4),"aaaabbbbaaa");
		tests.put(new OccuranceTest("aaa  dddd    d e f g", 2),"aa  dd  d e f g");
		
		String output, expectedOutput = null;
		for(OccuranceTest test : tests.keySet()){
			
			output = StringUtil.removeConsecutive(test.getInput(), test.getCount());
			expectedOutput = tests.get(test);
			
			System.out.println(test.getInput() + ", " + test.getCount() + " => " + output);
			
			assertEquals(output,expectedOutput);
		}
		
	}

}
