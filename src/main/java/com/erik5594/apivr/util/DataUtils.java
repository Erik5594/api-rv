package com.erik5594.apivr.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 21:34
 */
public class DataUtils {

    public static Date adcionarSegundos(int segundos){
        Calendar data = Calendar.getInstance();
        data.add(Calendar.SECOND, segundos);
        return data.getTime();
    }
}
