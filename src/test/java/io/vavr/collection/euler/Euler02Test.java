/* ____  ______________  ________________________  __________
 * \   \/   /      \   \/   /   __/   /      \   \/   /      \
 *  \______/___/\___\______/___/_____/___/\___\______/___/\___\
 *
 * The MIT License (MIT)
 *
 * Copyright 2025 Vavr, https://vavr.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.vavr.collection.euler;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class Euler02Test {

    /**
     * <strong>Problem 2: Even Fibonacci numbers</strong>
     * <p>
     * Each new term in the Fibonacci sequence is generated by adding the previous
     * two terms. By starting with 1 and 2, the first 10 terms will be: 1, 2, 3, 5,
     * 8, 13, 21, 34, 55, 89, ...
     * <p>
     * By considering the terms in the Fibonacci sequence whose values do not exceed
     * four million, find the sum of the even-valued terms.
     * <p>
     * See also <a href="https://projecteuler.net/problem=2">projecteuler.net problem 2</a>.
     */
    @Test
    public void shouldSolveProblem2() {
        assertThat(sumOfEvenFibonacciValuesNotExceeding(90)).isEqualTo(2 + 8 + 34);
        assertThat(sumOfEvenFibonacciValuesNotExceeding(4_000_000)).isEqualTo(4_613_732);
    }

    private static long sumOfEvenFibonacciValuesNotExceeding(final int max) {
        return Utils.fibonacci()
                .map(BigInteger::longValue)
                .takeWhile(f -> f <= max)
                .filter(f -> f % 2 == 0)
                .sum().longValue();
    }
}
