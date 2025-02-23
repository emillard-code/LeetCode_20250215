package com.project;

import com.project.solution.LeetCodeSolution;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeSolutionTest {

    @Test
    public void punishmentNumberTest() {

        assertEquals(182, LeetCodeSolution.punishmentNumber(10));
        assertEquals(1478, LeetCodeSolution.punishmentNumber(37));

    }

}
