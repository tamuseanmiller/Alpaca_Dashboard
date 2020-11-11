package net.jacobpeterson;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;

public class test {
    public static void main (String[] args) throws AlpacaAPIRequestException {
        // 6fa2644b-aa45-4f8e-87fc-e54e27581e9a
        // TK10J7Y632HVAXDGCYTV
        AlpacaAPI alpacaAPI = new AlpacaAPI("PKQEUTQXQFJSFSL8RFZI",
                "/udSzrZpUnBYfQXdWCP4Cy7YrB6cfBxKPnJgXNFf");
        System.out.println(alpacaAPI.getAccount());

    }
}
