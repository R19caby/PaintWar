package com.paintwar.server.service.game;

import java.util.function.Function;

public final class GameConfig {
	public static double AREA_FILL_PER_TICK = 1000.;
	public static int BASE_MAX_INK = 100;
	public static double ASPECT_RATIO = 1.0;
	public static int MINIMAP_WIDTH = 100;
	public static Double MINIMUM_AREA = 6000.;
	public static int MINIMUM_AREA_WIDTH_HEIGHT = 100;
	public static double INK_AREA_COST = 0.001;
	public static int MAX_INK_PLAYER = 1000;
	public static double BASE_INK_REGEN_PERCENT_PER_TICK = 0.003;
	//public static double TEAM_SCORE_INK_REGEN = 0.000000000001;
	public static int DRAWZONE_AREA_WIDTH = 3000;
	public static Function<Integer, Integer> GET_MAX_INK_FROM_TEAM_SCORE = (Integer teamScore) -> {
		return Math.min(MAX_INK_PLAYER, (int) (GameConfig.BASE_MAX_INK + Math.pow(teamScore, 0.5)*0.3));
	};
}
