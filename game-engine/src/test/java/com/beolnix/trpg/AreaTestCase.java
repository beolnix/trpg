package com.beolnix.trpg;

import com.beolnix.trpg.model.Area;
import com.beolnix.trpg.utils.AreaHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by beolnix on 30/08/15.
 */
public class AreaTestCase {

    @Test
    public void loadAreaTest() {
        Area area = AreaHelper.loadAreaForPosition(0);

        assertEquals(1, area.getExits().size());
        assertEquals(new Integer(1), area.getExits().iterator().next());
    }
}
