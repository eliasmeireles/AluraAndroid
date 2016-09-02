package systemplus.com.br.aluraandroid.generator;

import java.util.Random;

/**
 * Created by elias on 26/08/2016.
 */
public class GeradorDeId {

    public Long geraId() {
        Random random = new Random();
     return random.nextLong();
    }
}
