class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 1. Calculate the dimensions of the potential intersection
                // Width = min(rights) - max(lefts)
                int w = Math.min(topRight[i][0], topRight[j][0]) - 
                        Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                
                // Height = min(tops) - max(bottoms)
                int h = Math.min(topRight[i][1], topRight[j][1]) - 
                        Math.max(bottomLeft[i][1], bottomLeft[j][1]);

                // 2. Explicit Check: Do they actually intersect?
                // If width or height is <= 0, the rectangles are touching or disjoint.
                if (w > 0 && h > 0) {
                    int side = Math.min(w, h);
                    maxSide = Math.max(maxSide, side);
                }
            }
        }
        
        return maxSide * maxSide;
    }
}