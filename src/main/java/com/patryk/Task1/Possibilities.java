package com.patryk.Task1;


import org.springframework.stereotype.Component;

import java.math.BigInteger;


@Component
public class Possibilities {

    public BigInteger count(int min, int max, long quantity){
        if(min == 0){
            min = 1;
        }
        BigInteger pos = new BigInteger("0");
        for(int i = min; i <= max; i++){
            BigInteger cas = new BigInteger("1");
            for(int z = 0; z < i; z++){
                cas = cas.multiply(BigInteger.valueOf(quantity));

            }
            pos = pos.add(cas);

        }
        return pos;

    }
}

