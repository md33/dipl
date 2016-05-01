package com.example.md.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author amulya
 * @datetime 14 Oct 2014, 5:20 PM
 */
public class ColorGenerator {

    List<Integer> DEF = new ArrayList<Integer>();
    int[] def = { 0xfff16364,
            0xfff58559,
            0xfff9a43e,
            0xffe4c62e,
            0xff67bf74,
            0xff59a2be,
            0xff2093cd,
            0xffad62a7,
            0xff805781,
            0xffe57373,
            0xfff06292,
            0xffba68c8,
            0xff9575cd,
            0xff7986cb,
            0xff64b5f6,
            0xff4fc3f7,
            0xff4dd0e1,
            0xff4db6ac,
            0xff81c784,
            0xffaed581,
            0xffff8a65,
            0xffd4e157,
            0xffffd54f,
            0xffffb74d,
            0xffa1887f,
            0xff90a4ae
    };
    ColorGenerator(){


            for(int index = 0; index < def.length; index++) {
                DEF.add(def[index]);
            }
    }
    public int ColorGenerator( ){
        int color = 0;
        Random random = new Random();

            int i = random.nextInt(def.length);
            color  = DEF.get(i);

        return color;
    }

}
