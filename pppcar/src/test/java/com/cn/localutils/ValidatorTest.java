package com.cn.localutils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nurmemet on 5/27/2016.
 */
public class ValidatorTest {

    Validator validator=new Validator();
    @Test
    public void testIsInvalidEmail() throws Exception {
        assertTrue(validator.isInvalidEmail("nameemail.com"));
    }
}