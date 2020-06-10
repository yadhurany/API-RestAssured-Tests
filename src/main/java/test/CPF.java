package test;

import java.util.Date;

public class CPF {

    public static String createCPF() {
        return String.format("%011d", new Date().getTime());
    }
}
