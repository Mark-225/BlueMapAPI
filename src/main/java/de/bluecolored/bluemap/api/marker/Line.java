/*
 * This file is part of BlueMap, licensed under the MIT License (MIT).
 *
 * Copyright (c) Blue (Lukas Rieger) <https://bluecolored.de>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.bluecolored.bluemap.api.marker;

import com.flowpowered.math.vector.Vector3d;

import java.util.Arrays;

/**
 * A line consisting of 2 or more {@link Vector3d}-points.
 */
public class Line {

    private final Vector3d[] points;
    private Vector3d min = null;
    private Vector3d max = null;

    public Line(Vector3d... points) {
        if (points.length < 2) throw new IllegalArgumentException("A line has to have at least 2 points!");
        this.points = points;
    }

    /**
     * Getter for the amount of points in this line.
     * @return the amount of points
     */
    public int getPointCount() {
        return points.length;
    }

    public Vector3d getPoint(int i) {
        return points[i];
    }

    /**
     * Getter for <b>a copy</b> of the points array.<br>
     * <i>(A line is immutable once created)</i>
     * @return the points of this line
     */
    public Vector3d[] getPoints() {
        return Arrays.copyOf(points, points.length);
    }

    /**
     * Calculates and returns the minimum corner of the axis-aligned-bounding-box of this line.
     * @return the min of the AABB of this line
     */
    public Vector3d getMin() {
        if (this.min == null) {
            Vector3d min = points[0];
            for (int i = 1; i < points.length; i++) {
                min = min.min(points[i]);
            }
            this.min = min;
        }
        return this.min;
    }

    /**
     * Calculates and returns the maximum corner of the axis-aligned-bounding-box of this line.
     * @return the max of the AABB of this line
     */
    public Vector3d getMax() {
        if (this.max == null) {
            Vector3d max = points[0];
            for (int i = 1; i < points.length; i++) {
                max = max.max(points[i]);
            }
            this.max = max;
        }
        return this.max;
    }

}
