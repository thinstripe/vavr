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

import io.vavr.Function1;
import io.vavr.collection.CharSeq;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.vavr.collection.euler.Utils.file;
import static io.vavr.collection.euler.Utils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Euler42Test {

    /**
     * <strong>Problem 42 Coded triangle numbers</strong>
     * <p>
     * The <i>n</i><sup>th</sup> term of the sequence of triangle numbers is
     * given by, <i>t</i><sub>n</sub> = ½<i>n</i>(<i>n</i>+1); so the first ten
     * triangle numbers are:
     * <pre>
     * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
     * </pre>
     * <p>
     * By converting each letter in a word to a number corresponding to its
     * alphabetical position and adding these values we form a word value. For
     * example, the word value for SKY is 19 + 11 + 25 = 55 = t<sub>10</sub>. If
     * the word value is a triangle number then we shall call the word a
     * triangle word.
     * <p>
     * Using p042_words.txt, a 16K text file containing nearly two-thousand
     * common English words, how many are triangle words?
     * <p>
     * See also <a href="https://projecteuler.net/problem=42">projecteuler.net
     * problem 42</a>.
     */
    @Test
    public void shouldSolveProblem42() {
        assertThat(isTriangleWord("SKY")).isTrue();
        assertThat(sumOfAlphabeticalPositions("SKY")).isEqualTo(55);
        assertThat(alphabeticalPosition('S')).isEqualTo(19);
        assertThat(alphabeticalPosition('K')).isEqualTo(11);
        assertThat(alphabeticalPosition('Y')).isEqualTo(25);
        assertThat(TRIANGLE_NUMBERS.take(10)).containsExactly(1, 3, 6, 10, 15, 21, 28, 36, 45, 55);
        List.rangeClosed(1, 60).forEach(n -> Assertions.assertThat(isTriangleNumberMemoized.apply(n)).isEqualTo(List.of(1, 3, 6, 10, 15, 21, 28, 36, 45, 55).contains(n)));

        assertThat(numberOfTriangleNumbersInFile()).isEqualTo(162);
    }

    private static int numberOfTriangleNumbersInFile() {
        return readLines(file("p042_words.txt"))
                .map(l -> l.replaceAll("\"", ""))
                .flatMap(l -> List.of(l.split(",")))
                .filter(Euler42Test::isTriangleWord)
                .length();
    }

    private static boolean isTriangleWord(String word) {
        return isTriangleNumber(sumOfAlphabeticalPositions(word));
    }

    private static boolean isTriangleNumber(int n) {
        return TRIANGLE_NUMBERS
                .takeWhile(t -> t <= n)
                .exists(t -> t == n);
    }

    private static final Function1<Integer, Boolean> isTriangleNumberMemoized = Function1.of(Euler42Test::isTriangleNumber).memoized();

    private static final Stream<Integer> TRIANGLE_NUMBERS = Stream.from(1).map(n -> 0.5 * n * (n + 1)).map(Double::intValue);

    private static int sumOfAlphabeticalPositions(String word) {
        return CharSeq.of(word)
                .map(Euler42Test::alphabeticalPosition)
                .sum().intValue();
    }

    private static int alphabeticalPosition(char c) {
        return c - 'A' + 1;
    }
}
