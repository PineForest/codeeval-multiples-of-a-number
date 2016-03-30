/*
 * Copyright © 2016  David Williams
 *
 * This file is part of the codeeval-multiples-of-a-number project.
 *
 * codeeval-multiples-of-a-number is free software: you can redistribute it and/or modify it under the terms of the
 * Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * codeeval-multiples-of-a-number is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the Lesser GNU General Public
 * License for more details.
 *
 * You should have received a copy of the Lesser GNU General Public License along with codeeval-multiples-of-a-number.
 * If not, see <a href="http://www.gnu.org/licenses/">www.gnu.org/licenses/</a>.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <p>The "Multiples of a Number" code challenge found at <a href="https://www.codeeval.com/browse/18/">CodeEval</a>.
 * </p>
 *
 * Some thoughts:
 * <ul>
 *     <li>I assumed that "greater or equal" is valid for positive and negative integer values of n, but only positive
 *     integer values of the power of two integer.</li>
 *     <li>I assumed that the goal is to demonstrate the implementation of the fastest algorithm for finding the result,
 *     not to demonstrate the fastest speed to read and process large files. Due to this, the implementation is
 *     performance constrained by disk IO.</li>
 *     <li>I assumed that user friendly error messages, comments, and test code are a non-goal, though I did include a
 *     couple of these.</li>
 * </ul>
 *
 * The algorithm I used can be found at this link on <a href="https://en.wikipedia.org/wiki/Power_of_two#Fast_algorithms_to_round_any_integer_to_a_multiple_of_a_given_power_of_two">Wikipedia</a>.
 *
 * @author PineForest (see https://github.com/PineForest) 9/25/2015
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(
                    "Usage: java codeeval\\challenge\\project\\MultiplesOfANumber.class <path to input file>");
            System.exit(-1);
        }
        Path path = FileSystems.getDefault().getPath(args[0]);
        try {
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            while (reader.ready()) {
                // Read in next line of integers
                String line = reader.readLine().trim();
                if (line.equals("")) {
                    continue;
                }
                String[] numbers = line.split(",");
                int n = Integer.parseInt(numbers[0]);
                int powerOf2 = Integer.parseInt(numbers[1]);

                // Calculate the multiple of a power of 2 greater than or equal to a given integer
                int oneLessPowerOf2 = powerOf2 - 1;
                int result = (n + oneLessPowerOf2) & (~oneLessPowerOf2);
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return;
        }
    }
}
