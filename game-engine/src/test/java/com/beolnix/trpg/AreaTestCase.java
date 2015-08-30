package com.beolnix.trpg;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by beolnix on 30/08/15.
 */
public class AreaTestCase {

    @Test
    public void loadAreaTest() {
        Area area = Area.loadAreaForPosition(0);

        assertEquals(1, area.getExits().size());
        assertEquals(new Integer(1), area.getExits().iterator().next());
    }
}
