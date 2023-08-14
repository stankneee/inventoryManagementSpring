package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Project: demoDarbyFrameworks2-master
 * Package: com.example.demo.domain
 * <p>
 * User: carolyn.sher
 * Date: 6/24/2022
 * Time: 3:44 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
class PartTest {

    private Part part;

    @BeforeEach
    void setUp() {
        part = new Part();
    }

    @Test
    void testMinInventory() {
        int minInventory = 0;
        part.setMinInventory(minInventory);
        assertEquals(minInventory, part.getMinInventory());
    }

    @Test
    void testMaxInventory() {
        int maxInventory = 100;
        part.setMaxInventory(maxInventory);
        assertEquals(maxInventory, part.getMaxInventory());
    }

    // Existing test cases ...

    @Test
    void testToString() {
        String name = "test part";
        part.setName(name);
        assertEquals(name, part.toString());
    }

    @Test
    void testEquals() {
        part.setId(1L);
        Part newPart = new Part();
        newPart.setId(1L);
        assertEquals(part, newPart);
    }

    @Test
    void testHashCode() {
        part.setId(1L);
        Part otherPart = new Part();
        otherPart.setId(1L);
        assertEquals(part.hashCode(), otherPart.hashCode());
    }
}