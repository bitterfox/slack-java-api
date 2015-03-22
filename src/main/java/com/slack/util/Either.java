/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.util;

import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 * @author bitter_fox
 */
public class Either<L, R>
{
    private L left;
    private R right;
    private boolean isLeft;
    private boolean isRight;

    private Either() {}

    public static <L, R> Either<L, R> left(L left)
    {
        Either<L, R> either = new Either<>();

        either.left = left;
        either.isLeft = true;

        return either;
    }

    public static <L, R> Either<L, R> right(R right)
    {
        Either<L, R> either = new Either<>();

        either.right = right;
        either.isRight = true;

        return either;
    }

    public void accept(Consumer<? super L> leftConsumer, Consumer<? super R> rightConsumer)
    {
        if (isLeft)
        {
            leftConsumer.accept(left);
        }
        else
        {
            rightConsumer.accept(right);
        }
    }

    public Optional<L> getLeft()
    {
        return isLeft ? Optional.of(left) : Optional.empty();
    }

    public Optional<R> getRight()
    {
        return isRight ? Optional.of(right) : Optional.empty();
    }
}
