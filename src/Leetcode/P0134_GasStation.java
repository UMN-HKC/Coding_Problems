package Leetcode;

public class P0134_GasStation {

//    the reason why couldn't use `tank = sumGas - sumCost` is that when we update
//    startIndex when `tank < 0`, we clear the previous tank history, whereas sumGas and
//    sumCost are cummulative throughout. So if in the next iteration we continue using
//    sumGas and sumCost, we will mess up the new tank info.

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumCost = 0;
        int sumGas = 0;
        int tank = 0;
        int startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            sumCost += cost[i];
            sumGas += gas[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                tank = 0;
                startIndex = i + 1;
            }
        }
        if (sumCost <= sumGas) {
            return startIndex;
        }
        else{
            return -1;
        }
    }
}
