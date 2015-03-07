/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import org.junit.Assert;

/**
 *
 * @author bitter_fox
 */
public class Tests
{
    public interface Operator
    {
        void op();
    }

    public static void assertException(Operator op, Class<? extends Exception> clazz)
    {
        try
        {
            op.op();
            Assert.fail("Successed. It would be " + clazz.getName());
        }
        catch (Exception e)
        {
            if (!clazz.isInstance(e))
            {
                Assert.fail("Another exception: " + e.toString());
            }
        }
    }
}
