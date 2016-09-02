package systemplus.com.br.meuempregocom.converter;

import android.icu.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by elias on 31/08/2016.
 */
public class DateConverter {

    public static String brasilianFormat(String dateToConvert) {
        System.out.println("Data a ser convertida: " + dateToConvert);
//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateToConvert);
//            return new SimpleDateFormat("dd/MM/yyyy").format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return dateToConvert;
    }

    public static String dataFormat(String dateToConvert) {

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateToConvert);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateToConvert;
    }
    public static ObjectMapper getHwObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH));
        return mapper;
    }
}
