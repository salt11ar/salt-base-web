package com.example.postgres.springbootpostgresdocker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import org.junit.jupiter.api.Test;
import com.example.postgres.springbootpostgresdocker.utils.MoneyUtil;
import org.junit.Test;


public class MoneyUtilTests {
    
    /**
     *
     */
    private static final String $1000_00 = "$1000.00";

    @Test
    public void moneyTestPositiveValue(){
        String money = MoneyUtil.format(1000);
        assertEquals($1000_00, money);
    }

    @Test
    public void moneyTestNegativeValue(){
        String money = MoneyUtil.format(-1000);
        assertEquals("-$1000.00", money);
    }

    @Test
    public void moneyTestEuro(){
        String money = MoneyUtil.format(1000, "€");
        assertEquals("€1000.00", money);
    }

    @Test
    public void moneyIllegalArgException() {
        assertThrows(
            IllegalArgumentException.class, 
         () -> {MoneyUtil.format(1000, null);} 
        );
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullExceptionMoneyTest() {
        MoneyUtil.format(-1000.0, null);
    }

}
