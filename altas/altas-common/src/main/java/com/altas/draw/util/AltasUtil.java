package com.altas.draw.util;

import java.util.UUID;

public class AltasUtil {

    public static String getUUID(){
        return  UUID.randomUUID().toString().toUpperCase();
    }
}
