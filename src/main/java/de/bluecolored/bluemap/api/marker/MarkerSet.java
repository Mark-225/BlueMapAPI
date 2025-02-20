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

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector3d;
import de.bluecolored.bluemap.api.BlueMapMap;

import java.util.Collection;
import java.util.Optional;

/**
 * A set of {@link Marker}s that are displayed on the maps in the web-app.
 *
 * <p>Each {@link MarkerSet} has an unique id.</p>
 */
public interface MarkerSet {

    /**
     * Getter for the id of this {@link MarkerSet}.
     * @return the id of this {@link MarkerSet}
     */
    String getId();

    /**
     * Getter for the label of this {@link MarkerSet}.
     * <p>The label is used in the web-app to name the toggle-button of this {@link MarkerSet} if it is toggleable. ({@link #isToggleable()})</p>
     * @return the label of this {@link MarkerSet}
     */
    String getLabel();

    /**
     * Sets the label of this {@link MarkerSet}.
     * <p>The label is used in the web-app to name the toggle-button of this {@link MarkerSet} if it is toggleable. ({@link #isToggleable()})</p>
     * @param label the new label
     */
    void setLabel(String label);

    /**
     * Checks if the {@link MarkerSet} is toggleable.
     * <p>If this is <code>true</code>, the web-app will display a toggle-button for this {@link MarkerSet} so the user can choose to enable/disable all markers of this set.</p>
     * @return whether this {@link MarkerSet} is toggleable
     */
    boolean isToggleable();

    /**
     * Changes if this {@link MarkerSet} is toggleable.
     * <p>If this is <code>true</code>, the web-app will display a toggle-button for this {@link MarkerSet} so the user can choose to enable/disable all markers of this set.</p>
     * @param toggleable whether this {@link MarkerSet} should be toggleable
     */
    void setToggleable(boolean toggleable);

    /**
     * @deprecated method name has a typo, use {@link #isDefaultHidden()} instead.
     */
    default boolean isDefautHidden() {
        return isDefaultHidden();
    }

    /**
     * Checks if this {@link MarkerSet} is hidden by default.
     * <p>This is basically the default-state of the toggle-button from {@link #isToggleable()}. If this is <code>true</code> the markers of this marker set will initially be hidden and can be displayed using the toggle-button.</p>
     *
     * @return whether this {@link MarkerSet} is hidden by default
     * @see #isToggleable()
     */
    boolean isDefaultHidden();

    /**
     * Sets if this {@link MarkerSet} is hidden by default.
     * <p>This is basically the default-state of the toggle-button from {@link #isToggleable()}. If this is <code>true</code> the markers of this marker set will initially be hidden and can be displayed using the toggle-button.</p>
     *
     * @param defaultHide whether this {@link MarkerSet} should be hidden by default
     * @see #isToggleable()
     */
    void setDefaultHidden(boolean defaultHide);

    /**
     * Getter for an <b>unmodifiable</b> {@link Collection} of all {@link Marker}s in this {@link MarkerSet}.
     *
     * @return a {@link Collection} with all {@link Marker}s of this {@link MarkerSet}.
     */
    Collection<Marker> getMarkers();

    /**
     * Getter for a {@link Marker} with the given id.<br>
     * Returns an empty {@link Optional} if no {@link Marker} exists with the given id.
     *
     * @param id the id of the {@link Marker}
     * @return an {@link Optional}&lt;{@link Marker}&gt; with the given id
     */
    Optional<Marker> getMarker(String id);

    /**
     * Creates a {@link POIMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a Marker with that id already exists, it will be replaced by the new {@link POIMarker}!
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param position the position of the new marker
     * @return the created {@link POIMarker}
     */
    POIMarker createPOIMarker(String id, BlueMapMap map, Vector3d position);

    /**
     * Creates a {@link POIMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link POIMarker}!
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param posX the x-position of the new marker
     * @param posY the y-position of the new marker
     * @param posZ the z-position of the new marker
     * @return the created {@link POIMarker}
     */
    default POIMarker createPOIMarker(String id, BlueMapMap map, double posX, double posY, double posZ) {
        return createPOIMarker(id, map, new Vector3d(posX, posY, posZ));
    }



    /**
     * Creates a {@link HtmlMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a Marker with that id already exists, it will be replaced by the new {@link HtmlMarker}!
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param position the position of the new marker
     * @param html the html-content of the new marker
     * @return the created {@link HtmlMarker}
     */
    HtmlMarker createHtmlMarker(String id, BlueMapMap map, Vector3d position, String html);

    /**
     * Creates a {@link HtmlMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link HtmlMarker}!
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param posX the x-position of the new marker
     * @param posY the y-position of the new marker
     * @param posZ the z-position of the new marker
     * @param html the html-content of the new marker
     * @return the created {@link HtmlMarker}
     */
    default HtmlMarker createHtmlMarker(String id, BlueMapMap map, double posX, double posY, double posZ, String html) {
        return createHtmlMarker(id, map, new Vector3d(posX, posY, posZ), html);
    }

    /**
     * Creates a {@link ShapeMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link ShapeMarker}!
     *
     * <p><i>(Since the shape has its own positions, the position is only used to determine e.g. the distance to the camera)</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param position the position of the new marker
     * @param shape the Shape of the marker (See: {@link ShapeMarker#setShape(Shape, float)})
     * @param y the height (y-position on the map) of shape of the marker (See: {@link ShapeMarker#setShape(Shape, float)})
     * @return the created {@link ShapeMarker}
     */
    ShapeMarker createShapeMarker(String id, BlueMapMap map, Vector3d position, Shape shape, float y);

    /**
     * Creates a {@link ShapeMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a Marker with that id already exists, it will be replaced by the new {@link ShapeMarker}!
     *
     * <p><i>(Since the shape has its own positions, the position is only used to determine e.g. the distance to the camera)</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param posX the x-position of the new marker
     * @param posY the y-position of the new marker
     * @param posZ the z-position of the new marker
     * @param shape the Shape of the marker (See: {@link ShapeMarker#setShape(Shape, float)})
     * @param y the height (y-position on the map) of shape of the marker (See: {@link ShapeMarker#setShape(Shape, float)})
     * @return the created {@link ShapeMarker}
     */
    default ShapeMarker createShapeMarker(String id, BlueMapMap map, double posX, double posY, double posZ, Shape shape, float y) {
        return createShapeMarker(id, map, new Vector3d(posX, posY, posZ), shape, y);
    }

    /**
     * Creates a {@link ShapeMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a Marker with that id already exists, it will be replaced by the new {@link ShapeMarker}!
     *
     * <p><i>(The position of the marker will be the center of the shape (it's bounding box))</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param shape the Shape of the marker (See: {@link ShapeMarker#setShape(Shape, float)})
     * @param y the height of shape of the marker (See: {@link ShapeMarker#setShape(Shape, float)})
     * @return the created {@link ShapeMarker}
     */
    default ShapeMarker createShapeMarker(String id, BlueMapMap map, Shape shape, float y) {
        Vector2d center = shape.getMin().add(shape.getMax()).div(2);
        return createShapeMarker(id, map, new Vector3d(center.getX(), y, center.getY()), shape, y);
    }

    /**
     * Creates a {@link ExtrudeMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link ExtrudeMarker}!
     *
     * <p><i>(Since the shape has its own positions, the position is only used to determine e.g. the distance to the camera)</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param position the position of the new marker
     * @param shape the {@link Shape} of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @param minY the min-height (y-position on the map) of the shape of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @param maxY the max-height (y-position on the map) of the shape of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @return the created {@link ExtrudeMarker}
     */
    ExtrudeMarker createExtrudeMarker(String id, BlueMapMap map, Vector3d position, Shape shape, float minY, float maxY);

    /**
     * Creates a {@link ExtrudeMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link ExtrudeMarker}!
     *
     * <p><i>(Since the shape has its own positions, the position is only used to determine e.g. the distance to the camera)</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param posX the x-position of the new marker
     * @param posY the y-position of the new marker
     * @param posZ the z-position of the new marker
     * @param shape the {@link Shape} of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @param minY the min-height (y-position on the map) of the shape of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @param maxY the max-height (y-position on the map) of the shape of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @return the created {@link ExtrudeMarker}
     */
    default ExtrudeMarker createExtrudeMarker(String id, BlueMapMap map, double posX, double posY, double posZ, Shape shape, float minY, float maxY) {
        return createExtrudeMarker(id, map, new Vector3d(posX, posY, posZ), shape, minY, maxY);
    }

    /**
     * Creates a {@link ExtrudeMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link ExtrudeMarker}!
     *
     * <p><i>(The position of the marker will be the center of the shape (it's bounding box))</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param shape the {@link Shape} of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @param minY the min-height (y-position on the map) of the shape of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @param maxY the max-height (y-position on the map) of the shape of the marker (See: {@link ExtrudeMarker#setShape(Shape, float, float)})
     * @return the created {@link ExtrudeMarker}
     */
    default ExtrudeMarker createExtrudeMarker(String id, BlueMapMap map, Shape shape, float minY, float maxY) {
        Vector2d center = shape.getMin().add(shape.getMax()).div(2f);
        float y = (minY + maxY)/2f;
        return createExtrudeMarker(id, map, new Vector3d(center.getX(), y, center.getY()), shape, minY, maxY);
    }

    /**
     * Creates a {@link LineMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link LineMarker}!
     *
     * <p><i>(Since the line has its own positions, the position is only used to determine e.g. the distance to the camera)</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param position the position of the new marker
     * @param line the {@link Line} of the marker (See: {@link LineMarker#setLine(Line)})
     * @return the created {@link LineMarker}
     */
    LineMarker createLineMarker(String id, BlueMapMap map, Vector3d position, Line line);

    /**
     * Creates a {@link LineMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link LineMarker}!
     *
     * <p><i>(Since the line has its own positions, the position is only used to determine e.g. the distance to the camera)</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param posX the x-position of the new marker
     * @param posY the y-position of the new marker
     * @param posZ the z-position of the new marker
     * @param line the {@link Line} of the marker (See: {@link LineMarker#setLine(Line)})
     * @return the created {@link LineMarker}
     */
    default LineMarker createLineMarker(String id, BlueMapMap map, double posX, double posY, double posZ, Line line) {
        return createLineMarker(id, map, new Vector3d(posX, posY, posZ), line);
    }

    /**
     * Creates a {@link LineMarker} with the given id and adds it to this {@link MarkerSet}.<br>
     * If a {@link Marker} with that id already exists, it will be replaced by the new {@link LineMarker}!
     *
     * <p><i>(The position of the marker will be the center of the line (it's bounding box))</i></p>
     *
     * @param id the id of the new marker
     * @param map the {@link BlueMapMap} of the new marker
     * @param line the {@link Line} of the marker (See: {@link LineMarker#setLine(Line)})
     * @return the created {@link LineMarker}
     */
    default LineMarker createLineMarker(String id, BlueMapMap map, Line line) {
        Vector3d center = line.getMin().add(line.getMax()).div(2f);
        return createLineMarker(id, map, center, line);
    }

    /**
     * Removes the given Marker from this {@link MarkerSet}.<br>
     * This is equivalent to calling <code>removeMarker(marker.getId())</code>.
     *
     * @param marker the {@link Marker} to be removed
     * @return <code>true</code> if the {@link Marker} was removed, <code>false</code> if that {@link Marker} didn't exist
     */
    default boolean removeMarker(Marker marker) {
        return removeMarker(marker.getId());
    }

    /**
     * Removes the {@link Marker} with the given id.
     *
     * @param id the id of the {@link Marker} to be removed
     * @return <code>true</code> if the {@link Marker} was removed, <code>false</code> if there was no {@link Marker} with that id
     */
    boolean removeMarker(String id);

}
