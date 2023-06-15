package com.techtest.app;

import com.techtest.app.domain.Position;
import org.apache.commons.lang3.tuple.Pair;

import static com.techtest.app.domain.values.Orientation.EAST;
import static com.techtest.app.domain.values.Orientation.NORTH;
import static com.techtest.app.domain.values.Orientation.SOUTH;
import static com.techtest.app.domain.values.Orientation.WEST;

public final class Constants {
    public static final Position POSITION_0_0_E = new Position(0, 0, EAST);
    public static final Position POSITION_0_0_S = new Position(0, 0, SOUTH);
    public static final Position POSITION_0_0_N = new Position(0, 0, NORTH);
    public static final Position POSITION_0_0_W = new Position(0, 0, WEST);
    public static final Pair<Integer, Integer> WORKPLACE_SIZE = Pair.of(5, 5);
}
