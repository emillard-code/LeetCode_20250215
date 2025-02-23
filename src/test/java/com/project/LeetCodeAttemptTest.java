package com.project;

import com.project.attempt.LeetCodeAttempt;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeAttemptTest {

    @Test
    public void findThePunishmentNumberOfAnIntegerTest() {

        assertEquals(182, LeetCodeAttempt.findThePunishmentNumberOfAnInteger(10));
        assertEquals(1478, LeetCodeAttempt.findThePunishmentNumberOfAnInteger(37));

    }

}
