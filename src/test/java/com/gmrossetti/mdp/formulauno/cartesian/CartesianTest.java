package com.gmrossetti.mdp.formulauno.cartesian;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CartesianTest {

    @Test
    void testAbstractPointDistance() {
        AbstractPoint<Double> point1 = new AbstractPoint<>(0.0, 0.0) {};
        AbstractPoint<Double> point2 = new AbstractPoint<>(3.0, 4.0) {};
        double distance = point1.distanceTo(point2);
        assertEquals(5.0, distance, 0.001, "Distance should be 5.0");
    }

    @Test
    void testAbstractPointEquality() {
        AbstractPoint<Integer> point1 = new AbstractPoint<>(1, 2) {};
        AbstractPoint<Integer> point2 = new AbstractPoint<>(1, 2) {};
        AbstractPoint<Integer> point3 = new AbstractPoint<>(2, 3) {};
        assertEquals(point1, point2, "Points with the same coordinates should be equal");
        assertNotEquals(point1, point3, "Points with different coordinates should not be equal");
    }

    @Test
    void testAbstractPointToString() {
        AbstractPoint<Integer> point = new AbstractPoint<>(5, 10) {};
        assertEquals("(5, 10)", point.toString(), "String representation should match the format (x, y)");
    }

    @Test
    void testGridPointDistance() {
        GridPoint point1 = new GridPoint(0, 0);
        GridPoint point2 = new GridPoint(3, 4);
        double distance = point1.distanceTo(point2);
        assertEquals(5.0, distance, 0.001, "Distance should be 5.0");
    }

    // aggiungere altri test per GridPoint se necessario
    @Test
    void testGridPointEquality() {
        GridPoint point1 = new GridPoint(1, 2);
        GridPoint point2 = new GridPoint(1, 2);
        GridPoint point3 = new GridPoint(2, 3);
        assertEquals(point1, point2, "GridPoints with the same coordinates should be equal");
        assertNotEquals(point1, point3, "GridPoints with different coordinates should not be equal");
    }

    @Test
    void testGridPointToString() {
        GridPoint point = new GridPoint(5, 10);
        assertEquals("(5, 10)", point.toString(), "String representation should match the format (x, y)");
    }

    @Test
    void testGridPointGetNearestDiscretePoints() {
        Point point = new Point(5.5, 10.5);
        Set<GridPoint> nearestPoints = point.getNearestDiscretePoints();
        assertEquals(4, nearestPoints.size(), "There should be 4 nearest discrete points");
        assertTrue(nearestPoints.contains(new GridPoint(5, 10)), "Should contain (5, 10)");
        assertTrue(nearestPoints.contains(new GridPoint(5, 11)), "Should contain (5, 11)");
        assertTrue(nearestPoints.contains(new GridPoint(6, 10)), "Should contain (6, 10)");
        assertTrue(nearestPoints.contains(new GridPoint(6, 11)), "Should contain (6, 11)");
    }

    @Test
    void testGridPointGetNearestDiscretePointsWithNegativeCoordinates() {
        Point point = new Point(-5.5, -10.5);
        Set<GridPoint> nearestPoints = point.getNearestDiscretePoints();
        assertEquals(4, nearestPoints.size(), "There should be 4 nearest discrete points");
        assertTrue(nearestPoints.contains(new GridPoint(-5, -10)), "Should contain (-5, -10)");
        assertTrue(nearestPoints.contains(new GridPoint(-5, -11)), "Should contain (-5, -11)");
        assertTrue(nearestPoints.contains(new GridPoint(-6, -10)), "Should contain (-6, -10)");
        assertTrue(nearestPoints.contains(new GridPoint(-6, -11)), "Should contain (-6, -11)");
    }

    @Test
    void testGridLineGetLineIntersectionsWithGrid() {
        GridPoint start = new GridPoint(1, 1);
        GridPoint end = new GridPoint(4, 4);
        GridLine gridLine = new GridLine(start, end);

        Set<Point> intersections = gridLine.getLineIntersectionsWithGrid();
        assertEquals(4, intersections.size(), "There should be 7 intersection points");
        assertTrue(intersections.contains(new Point(1.0, 1.0)), "Should contain (1.0, 1.0)");
        assertTrue(intersections.contains(new Point(2.0, 2.0)), "Should contain (2.0, 2.0)");
        assertTrue(intersections.contains(new Point(3.0, 3.0)), "Should contain (3.0, 3.0)");
        assertTrue(intersections.contains(new Point(4.0, 4.0)), "Should contain (4.0, 4.0)");
    }
}