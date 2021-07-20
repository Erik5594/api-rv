package com.erik5594.apivr.util;

import java.util.UUID;

/**
 * @author erik_
 * Data Criacao: 20/07/2021 - 09:33
 */
public class Util {
    public static String gerarId(){
        return UUID.randomUUID().toString();
    }
}
