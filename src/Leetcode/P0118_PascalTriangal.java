package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0118_PascalTriangal {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) {
            return res;
        }
        for (int i = 0; i < numRows; i++) {
            List<Integer> line = new ArrayList<>();
            if (i == 0) {
                line.add(1);
            }
            else if (i == 1) {
                line.add(1);
                line.add(1);
            }
            else {
                List<Integer> lastLine = res.get(i - 1);
                for (int j = 0; j < lastLine.size() - 1; j++) {
                    line.add(lastLine.get(j) + lastLine.get(j + 1));
                }
                line.add(0, 1);
                line.add(line.size(), 1);
            }
            res.add(line);
        }
        return res;
    }

    public List<List<Integer>> generate_optimized(int numRows)
    {
        List<List<Integer>> allrows = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for(int i=0;i<numRows;i++)
        {
            row.add(0, 1);
            for(int j=1;j<row.size()-1;j++)
                row.set(j, row.get(j)+row.get(j+1));
            allrows.add(new ArrayList<Integer>(row));
        }
        return allrows;

    }
}
