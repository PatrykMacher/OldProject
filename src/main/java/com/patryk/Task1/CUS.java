package com.patryk.Task1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.*;

@Component
class CUS {
    @Autowired
    Possibilities possibilities;



    public String createUniqueString(Request request) {

        int id = request.getId();
        int max = request.getMax();
        int min = request.getMin();
        if(min > max){
            max = min;
            min = max;
        }
        long quantity = request.getQuantity();
        String characters = request.getCharacters();
        BigInteger quan = BigInteger.valueOf(request.getQuantity());
        BigInteger poss = possibilities.count(min, max, quantity);
        if (poss.compareTo(quan) == -1) {
            return "There isn't enough soultions for your request";
        } else {

            char[] sym = characters.toCharArray();
            String[] arr = new String[characters.length()];
            for (int i = 0; i < characters.length(); i++) {
                arr[i] = String.valueOf(sym[i]);
            }


            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);


            ArrayList<String> UStrings = new ArrayList<>();
            String[] sa = arr;

                for (int z = 0; z < max - 1; z++) {


                    Vector<String> tmp = new Vector<>();
                    for (String s : arr) {
                        for (String value : sa) {

                            tmp.add(String.valueOf(value + s));


                        }
                    }
                    for (String s : tmp) {
                        if (s.length() >= min) {
                            if (!UStrings.contains(s))
                                UStrings.add(s);

                        }
                    }
                    sa = tmp.toArray(new String[tmp.size()]);

                }

                Collections.shuffle(UStrings);
                long count = 0;
                for (String uString : UStrings) {
                    if (count < quantity) {
                        printWriter.println(uString);
                        count++;

                    }

                }

            return stringWriter.toString();

        }
    }

}
