package org.lenzi.algorithm.text.levenshtein;

import org.lenzi.algorithm.math.util.MathUtil;

public abstract class OptimalStringAlignmentDistance {

	
	public static int[][] getDistanceMatrix(String a, String b){
		
		int[][] ed = new int[a.length() + 1][b.length() + 1];
		
		int i = 0, j = 0, cost = 0;
		
		for(i = 0; i< a.length(); i++){
			ed[i][0] = i;
		}
		for(j = 0; j < b.length(); j++){
			ed[0][j] = j;
		}
		
		for (i = 1; i <= a.length(); i++) {
            for (j = 1; j <= b.length(); j++) {
	
				cost = ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1);

				ed[i][j] = MathUtil.min(
							ed[i - 1][j    ] + 1, 	// deletion
							ed[i    ][j - 1] + 1,	// insertion
							ed[i - 1][j - 1] + cost // substitution
						);
				
				// character transpose. e.d.  "ab" => "ba"
				if(i > 1 && j > 1 && a.charAt(i - 1) == b.charAt(j - 2) && a.charAt(i - 2) == b.charAt(j - 1)){
					ed[i][j] = MathUtil.min(
							ed[i][j],
							ed[i-2][j-2] + cost
						);
				}
					
			}
			
		}
		return ed;
		
	}

}
