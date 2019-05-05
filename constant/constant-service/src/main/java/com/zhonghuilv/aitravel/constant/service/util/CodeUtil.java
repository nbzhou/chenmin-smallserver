package com.chemin.smallserver.constant.service.util;

import java.util.Random;

public class CodeUtil {

    public static String getRandom(){
        Random r = new Random();
        return Integer.toString(r.nextInt(9000)+1000);
    }
}
