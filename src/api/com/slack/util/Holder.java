/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.util;

import java.util.Optional;
import java.util.function.Supplier;

/**
 *
 * @author bitter_fox
 */
public class Holder<T>
{
    public Optional<T> value = Optional.empty();

    public T computeIfAbsent(Supplier<? extends T> supplier)
    {
        return value.isPresent()
            ? value.get()
            : (value = Optional.of(supplier.get())).get();
    }

    @Override
    public String toString()
    {
        return "Holder{" + "value=" + value + '}';
    }
}
