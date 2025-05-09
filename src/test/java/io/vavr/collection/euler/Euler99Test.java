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

import static java.util.Arrays.asList;
import static io.vavr.collection.euler.Utils.file;
import static io.vavr.collection.euler.Utils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Euler99Test {

    /**
     * <strong>Problem 99: Largest exponential</strong>
     * <p>
     * Comparing two numbers written in index form like 2<sup>11</sup> and 3<sup>7</sup> is not difficult,
     * as any calculator would confirm that 2<sup>11</sup> = 2048 &lt; 3<sup>7</sup> = 2187.
     * <p>
     * However, confirming that 632382<sup>518061</sup> &gt; 519432<sup>525806</sup> would be much more difficult,
     * as both numbers contain over three million digits.
     * <p>
     * Using p099_base_exp.txt, a 22K text file containing one thousand lines with a base/exponent pair on each line,
     * determine which line number has the greatest numerical value.
     * <p>
     * See also <a href="https://projecteuler.net/problem=99">projecteuler.net problem 99</a>.
     */
    @Test
    public void shouldSolveProblem99() {
        assertThat(solve()).isEqualTo(709);
    }

    private static long solve() {
        return readLines(file("p099_base_exp.txt"))
                .flatMap(s -> asList(s.split(",")))
                .map(Integer::parseInt)
                .grouped(2)
                .map(t -> t.get(1) * Math.log(t.get(0)))
                .zipWithIndex()
                .reduce((t1, t2) -> t1._1 > t2._1 ? t1 : t2)
                ._2 + 1;
    }
}
