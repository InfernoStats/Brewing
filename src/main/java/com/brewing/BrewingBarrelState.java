package com.brewing;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BrewingBarrelState {
	EMPTY(0),
	BAD_ALE(1),
	BAD_CIDER(2),
	UNFERMENTED(4),

	/* Kelda Stout */
	KELDA_STOUT_1_PINT(3),

	/* Dwarven Stout */
	DWARVEN_STOUT_8_PINTS(8),
	DWARVEN_STOUT_7_PINTS(9),
	DWARVEN_STOUT_6_PINTS(10),
	DWARVEN_STOUT_5_PINTS(11),
	DWARVEN_STOUT_4_PINTS(12),
	DWARVEN_STOUT_3_PINTS(13),
	DWARVEN_STOUT_2_PINTS(14),
	DWARVEN_STOUT_1_PINT(15),

	/* Asgarnian Ale */
	ASGARNIAN_ALE_8_PINTS(16),
	ASGARNIAN_ALE_7_PINTS(17),
	ASGARNIAN_ALE_6_PINTS(18),
	ASGARNIAN_ALE_5_PINTS(19),
	ASGARNIAN_ALE_4_PINTS(20),
	ASGARNIAN_ALE_3_PINTS(21),
	ASGARNIAN_ALE_2_PINTS(22),
	ASGARNIAN_ALE_1_PINT(23),

	/* Greenman's Ale */
	GREENMANS_ALE_8_PINTS(24),
	GREENMANS_ALE_7_PINTS(25),
	GREENMANS_ALE_6_PINTS(26),
	GREENMANS_ALE_5_PINTS(27),
	GREENMANS_ALE_4_PINTS(28),
	GREENMANS_ALE_3_PINTS(29),
	GREENMANS_ALE_2_PINTS(30),
	GREENMANS_ALE_1_PINT(31),

	/* Wizard's Mind Bomb */
	WIZARDS_MIND_BOMB_8_PINTS(32),
	WIZARDS_MIND_BOMB_7_PINTS(33),
	WIZARDS_MIND_BOMB_6_PINTS(34),
	WIZARDS_MIND_BOMB_5_PINTS(35),
	WIZARDS_MIND_BOMB_4_PINTS(36),
	WIZARDS_MIND_BOMB_3_PINTS(37),
	WIZARDS_MIND_BOMB_2_PINTS(38),
	WIZARDS_MIND_BOMB_1_PINT(39),

	/* Dragon Bitter */
	DRAGON_BITTER_8_PINTS(40),
	DRAGON_BITTER_7_PINTS(41),
	DRAGON_BITTER_6_PINTS(42),
	DRAGON_BITTER_5_PINTS(43),
	DRAGON_BITTER_4_PINTS(44),
	DRAGON_BITTER_3_PINTS(45),
	DRAGON_BITTER_2_PINTS(46),
	DRAGON_BITTER_1_PINT(47),

	/* Moonlight Mead */
	MOONLIGHT_MEAD_8_PINTS(48),
	MOONLIGHT_MEAD_7_PINTS(49),
	MOONLIGHT_MEAD_6_PINTS(50),
	MOONLIGHT_MEAD_5_PINTS(51),
	MOONLIGHT_MEAD_4_PINTS(52),
	MOONLIGHT_MEAD_3_PINTS(53),
	MOONLIGHT_MEAD_2_PINTS(54),
	MOONLIGHT_MEAD_1_PINT(55),

	/* Axeman's Folly */
	AXEMANS_FOLLY_8_PINTS(56),
	AXEMANS_FOLLY_7_PINTS(57),
	AXEMANS_FOLLY_6_PINTS(58),
	AXEMANS_FOLLY_5_PINTS(59),
	AXEMANS_FOLLY_4_PINTS(60),
	AXEMANS_FOLLY_3_PINTS(61),
	AXEMANS_FOLLY_2_PINTS(62),
	AXEMANS_FOLLY_1_PINT(63),

	/* Chef's Delight */
	CHEFS_DELIGHT_8_PINTS(64),
	CHEFS_DELIGHT_7_PINTS(65),
	CHEFS_DELIGHT_6_PINTS(66),
	CHEFS_DELIGHT_5_PINTS(67),
	CHEFS_DELIGHT_4_PINTS(68),
	CHEFS_DELIGHT_3_PINTS(69),
	CHEFS_DELIGHT_2_PINTS(70),
	CHEFS_DELIGHT_1_PINT(71),

	/* Slayer's Respite */
	SLAYERS_RESPITE_8_PINTS(72),
	SLAYERS_RESPITE_7_PINTS(73),
	SLAYERS_RESPITE_6_PINTS(74),
	SLAYERS_RESPITE_5_PINTS(75),
	SLAYERS_RESPITE_4_PINTS(76),
	SLAYERS_RESPITE_3_PINTS(77),
	SLAYERS_RESPITE_2_PINTS(78),
	SLAYERS_RESPITE_1_PINT(79),

	/* Cider */
	CIDER_8_PINTS(80),
	CIDER_7_PINTS(81),
	CIDER_6_PINTS(82),
	CIDER_5_PINTS(83),
	CIDER_4_PINTS(84),
	CIDER_3_PINTS(85),
	CIDER_2_PINTS(86),
	CIDER_1_PINT(87),

	/* Mature Dwarven Stout */
	MATURE_DWARVEN_STOUT_8_PINTS(136),
	MATURE_DWARVEN_STOUT_7_PINTS(137),
	MATURE_DWARVEN_STOUT_6_PINTS(138),
	MATURE_DWARVEN_STOUT_5_PINTS(139),
	MATURE_DWARVEN_STOUT_4_PINTS(140),
	MATURE_DWARVEN_STOUT_3_PINTS(141),
	MATURE_DWARVEN_STOUT_2_PINTS(142),
	MATURE_DWARVEN_STOUT_1_PINT(143),

	/* Mature Asgarnian Ale */
	MATURE_ASGARNIAN_ALE_8_PINTS(144),
	MATURE_ASGARNIAN_ALE_7_PINTS(145),
	MATURE_ASGARNIAN_ALE_6_PINTS(146),
	MATURE_ASGARNIAN_ALE_5_PINTS(147),
	MATURE_ASGARNIAN_ALE_4_PINTS(148),
	MATURE_ASGARNIAN_ALE_3_PINTS(149),
	MATURE_ASGARNIAN_ALE_2_PINTS(150),
	MATURE_ASGARNIAN_ALE_1_PINT(151),

	/* Mature Greenman's Ale */
	MATURE_GREENMANS_ALE_8_PINTS(152),
	MATURE_GREENMANS_ALE_7_PINTS(153),
	MATURE_GREENMANS_ALE_6_PINTS(154),
	MATURE_GREENMANS_ALE_5_PINTS(155),
	MATURE_GREENMANS_ALE_4_PINTS(156),
	MATURE_GREENMANS_ALE_3_PINTS(157),
	MATURE_GREENMANS_ALE_2_PINTS(158),
	MATURE_GREENMANS_ALE_1_PINT(159),

	/* Mature Wizard's Mind Bomb */
	MATURE_WIZARDS_MIND_BOMB_8_PINTS(160),
	MATURE_WIZARDS_MIND_BOMB_7_PINTS(161),
	MATURE_WIZARDS_MIND_BOMB_6_PINTS(162),
	MATURE_WIZARDS_MIND_BOMB_5_PINTS(163),
	MATURE_WIZARDS_MIND_BOMB_4_PINTS(164),
	MATURE_WIZARDS_MIND_BOMB_3_PINTS(165),
	MATURE_WIZARDS_MIND_BOMB_2_PINTS(166),
	MATURE_WIZARDS_MIND_BOMB_1_PINT(167),

	/* Mature Dragon Bitter */
	MATURE_DRAGON_BITTER_8_PINTS(168),
	MATURE_DRAGON_BITTER_7_PINTS(169),
	MATURE_DRAGON_BITTER_6_PINTS(170),
	MATURE_DRAGON_BITTER_5_PINTS(171),
	MATURE_DRAGON_BITTER_4_PINTS(172),
	MATURE_DRAGON_BITTER_3_PINTS(173),
	MATURE_DRAGON_BITTER_2_PINTS(174),
	MATURE_DRAGON_BITTER_1_PINT(175),

	/* Mature Moonlight Mead */
	MATURE_MOONLIGHT_MEAD_8_PINTS(176),
	MATURE_MOONLIGHT_MEAD_7_PINTS(177),
	MATURE_MOONLIGHT_MEAD_6_PINTS(178),
	MATURE_MOONLIGHT_MEAD_5_PINTS(179),
	MATURE_MOONLIGHT_MEAD_4_PINTS(180),
	MATURE_MOONLIGHT_MEAD_3_PINTS(181),
	MATURE_MOONLIGHT_MEAD_2_PINTS(182),
	MATURE_MOONLIGHT_MEAD_1_PINT(183),

	/* Mature Axeman's Folly */
	MATURE_AXEMANS_FOLLY_8_PINTS(184),
	MATURE_AXEMANS_FOLLY_7_PINTS(185),
	MATURE_AXEMANS_FOLLY_6_PINTS(186),
	MATURE_AXEMANS_FOLLY_5_PINTS(187),
	MATURE_AXEMANS_FOLLY_4_PINTS(188),
	MATURE_AXEMANS_FOLLY_3_PINTS(189),
	MATURE_AXEMANS_FOLLY_2_PINTS(190),
	MATURE_AXEMANS_FOLLY_1_PINT(191),

	/* Mature Chef's Delight */
	MATURE_CHEFS_DELIGHT_8_PINTS(192),
	MATURE_CHEFS_DELIGHT_7_PINTS(193),
	MATURE_CHEFS_DELIGHT_6_PINTS(194),
	MATURE_CHEFS_DELIGHT_5_PINTS(195),
	MATURE_CHEFS_DELIGHT_4_PINTS(196),
	MATURE_CHEFS_DELIGHT_3_PINTS(197),
	MATURE_CHEFS_DELIGHT_2_PINTS(198),
	MATURE_CHEFS_DELIGHT_1_PINT(199),

	/* Mature Slayer's Respite */
	MATURE_SLAYERS_RESPITE_8_PINTS(200),
	MATURE_SLAYERS_RESPITE_7_PINTS(201),
	MATURE_SLAYERS_RESPITE_6_PINTS(202),
	MATURE_SLAYERS_RESPITE_5_PINTS(203),
	MATURE_SLAYERS_RESPITE_4_PINTS(204),
	MATURE_SLAYERS_RESPITE_3_PINTS(205),
	MATURE_SLAYERS_RESPITE_2_PINTS(206),
	MATURE_SLAYERS_RESPITE_1_PINT(207),

	/* Mature Cider */
	MATURE_CIDER_8_PINTS(208),
	MATURE_CIDER_7_PINTS(209),
	MATURE_CIDER_6_PINTS(210),
	MATURE_CIDER_5_PINTS(211),
	MATURE_CIDER_4_PINTS(212),
	MATURE_CIDER_3_PINTS(213),
	MATURE_CIDER_2_PINTS(214),
	MATURE_CIDER_1_PINT(215),

	UNKNOWN(-1),
	UNINITIALIZED(-2);

	private static final Set<BrewingBarrelState> DRAIN_STATES = Sets.immutableEnumSet(
			BAD_ALE,
			BAD_CIDER,
			UNFERMENTED
	);

	private static final Set<BrewingBarrelState> NORMAL_CONTENTS = Sets.immutableEnumSet(
			KELDA_STOUT_1_PINT,
			DWARVEN_STOUT_8_PINTS,
			DWARVEN_STOUT_7_PINTS,
			DWARVEN_STOUT_6_PINTS,
			DWARVEN_STOUT_5_PINTS,
			DWARVEN_STOUT_4_PINTS,
			DWARVEN_STOUT_3_PINTS,
			DWARVEN_STOUT_2_PINTS,
			DWARVEN_STOUT_1_PINT,
			ASGARNIAN_ALE_8_PINTS,
			ASGARNIAN_ALE_7_PINTS,
			ASGARNIAN_ALE_6_PINTS,
			ASGARNIAN_ALE_5_PINTS,
			ASGARNIAN_ALE_4_PINTS,
			ASGARNIAN_ALE_3_PINTS,
			ASGARNIAN_ALE_2_PINTS,
			ASGARNIAN_ALE_1_PINT,
			GREENMANS_ALE_8_PINTS,
			GREENMANS_ALE_7_PINTS,
			GREENMANS_ALE_6_PINTS,
			GREENMANS_ALE_5_PINTS,
			GREENMANS_ALE_4_PINTS,
			GREENMANS_ALE_3_PINTS,
			GREENMANS_ALE_2_PINTS,
			GREENMANS_ALE_1_PINT,
			WIZARDS_MIND_BOMB_8_PINTS,
			WIZARDS_MIND_BOMB_7_PINTS,
			WIZARDS_MIND_BOMB_6_PINTS,
			WIZARDS_MIND_BOMB_5_PINTS,
			WIZARDS_MIND_BOMB_4_PINTS,
			WIZARDS_MIND_BOMB_3_PINTS,
			WIZARDS_MIND_BOMB_2_PINTS,
			WIZARDS_MIND_BOMB_1_PINT,
			DRAGON_BITTER_8_PINTS,
			DRAGON_BITTER_7_PINTS,
			DRAGON_BITTER_6_PINTS,
			DRAGON_BITTER_5_PINTS,
			DRAGON_BITTER_4_PINTS,
			DRAGON_BITTER_3_PINTS,
			DRAGON_BITTER_2_PINTS,
			DRAGON_BITTER_1_PINT,
			MOONLIGHT_MEAD_8_PINTS,
			MOONLIGHT_MEAD_7_PINTS,
			MOONLIGHT_MEAD_6_PINTS,
			MOONLIGHT_MEAD_5_PINTS,
			MOONLIGHT_MEAD_4_PINTS,
			MOONLIGHT_MEAD_3_PINTS,
			MOONLIGHT_MEAD_2_PINTS,
			MOONLIGHT_MEAD_1_PINT,
			AXEMANS_FOLLY_8_PINTS,
			AXEMANS_FOLLY_7_PINTS,
			AXEMANS_FOLLY_6_PINTS,
			AXEMANS_FOLLY_5_PINTS,
			AXEMANS_FOLLY_4_PINTS,
			AXEMANS_FOLLY_3_PINTS,
			AXEMANS_FOLLY_2_PINTS,
			AXEMANS_FOLLY_1_PINT,
			CHEFS_DELIGHT_8_PINTS,
			CHEFS_DELIGHT_7_PINTS,
			CHEFS_DELIGHT_6_PINTS,
			CHEFS_DELIGHT_5_PINTS,
			CHEFS_DELIGHT_4_PINTS,
			CHEFS_DELIGHT_3_PINTS,
			CHEFS_DELIGHT_2_PINTS,
			CHEFS_DELIGHT_1_PINT,
			SLAYERS_RESPITE_8_PINTS,
			SLAYERS_RESPITE_7_PINTS,
			SLAYERS_RESPITE_6_PINTS,
			SLAYERS_RESPITE_5_PINTS,
			SLAYERS_RESPITE_4_PINTS,
			SLAYERS_RESPITE_3_PINTS,
			SLAYERS_RESPITE_2_PINTS,
			SLAYERS_RESPITE_1_PINT,
			CIDER_8_PINTS,
			CIDER_7_PINTS,
			CIDER_6_PINTS,
			CIDER_5_PINTS,
			CIDER_4_PINTS,
			CIDER_3_PINTS,
			CIDER_2_PINTS,
			CIDER_1_PINT
	);

	private static final Set<BrewingBarrelState> MATURE_CONTENTS = Sets.immutableEnumSet(
			MATURE_DWARVEN_STOUT_8_PINTS,
			MATURE_DWARVEN_STOUT_7_PINTS,
			MATURE_DWARVEN_STOUT_6_PINTS,
			MATURE_DWARVEN_STOUT_5_PINTS,
			MATURE_DWARVEN_STOUT_4_PINTS,
			MATURE_DWARVEN_STOUT_3_PINTS,
			MATURE_DWARVEN_STOUT_2_PINTS,
			MATURE_DWARVEN_STOUT_1_PINT,
			MATURE_ASGARNIAN_ALE_8_PINTS,
			MATURE_ASGARNIAN_ALE_7_PINTS,
			MATURE_ASGARNIAN_ALE_6_PINTS,
			MATURE_ASGARNIAN_ALE_5_PINTS,
			MATURE_ASGARNIAN_ALE_4_PINTS,
			MATURE_ASGARNIAN_ALE_3_PINTS,
			MATURE_ASGARNIAN_ALE_2_PINTS,
			MATURE_ASGARNIAN_ALE_1_PINT,
			MATURE_GREENMANS_ALE_8_PINTS,
			MATURE_GREENMANS_ALE_7_PINTS,
			MATURE_GREENMANS_ALE_6_PINTS,
			MATURE_GREENMANS_ALE_5_PINTS,
			MATURE_GREENMANS_ALE_4_PINTS,
			MATURE_GREENMANS_ALE_3_PINTS,
			MATURE_GREENMANS_ALE_2_PINTS,
			MATURE_GREENMANS_ALE_1_PINT,
			MATURE_WIZARDS_MIND_BOMB_8_PINTS,
			MATURE_WIZARDS_MIND_BOMB_7_PINTS,
			MATURE_WIZARDS_MIND_BOMB_6_PINTS,
			MATURE_WIZARDS_MIND_BOMB_5_PINTS,
			MATURE_WIZARDS_MIND_BOMB_4_PINTS,
			MATURE_WIZARDS_MIND_BOMB_3_PINTS,
			MATURE_WIZARDS_MIND_BOMB_2_PINTS,
			MATURE_WIZARDS_MIND_BOMB_1_PINT,
			MATURE_DRAGON_BITTER_8_PINTS,
			MATURE_DRAGON_BITTER_7_PINTS,
			MATURE_DRAGON_BITTER_6_PINTS,
			MATURE_DRAGON_BITTER_5_PINTS,
			MATURE_DRAGON_BITTER_4_PINTS,
			MATURE_DRAGON_BITTER_3_PINTS,
			MATURE_DRAGON_BITTER_2_PINTS,
			MATURE_DRAGON_BITTER_1_PINT,
			MATURE_MOONLIGHT_MEAD_8_PINTS,
			MATURE_MOONLIGHT_MEAD_7_PINTS,
			MATURE_MOONLIGHT_MEAD_6_PINTS,
			MATURE_MOONLIGHT_MEAD_5_PINTS,
			MATURE_MOONLIGHT_MEAD_4_PINTS,
			MATURE_MOONLIGHT_MEAD_3_PINTS,
			MATURE_MOONLIGHT_MEAD_2_PINTS,
			MATURE_MOONLIGHT_MEAD_1_PINT,
			MATURE_AXEMANS_FOLLY_8_PINTS,
			MATURE_AXEMANS_FOLLY_7_PINTS,
			MATURE_AXEMANS_FOLLY_6_PINTS,
			MATURE_AXEMANS_FOLLY_5_PINTS,
			MATURE_AXEMANS_FOLLY_4_PINTS,
			MATURE_AXEMANS_FOLLY_3_PINTS,
			MATURE_AXEMANS_FOLLY_2_PINTS,
			MATURE_AXEMANS_FOLLY_1_PINT,
			MATURE_CHEFS_DELIGHT_8_PINTS,
			MATURE_CHEFS_DELIGHT_7_PINTS,
			MATURE_CHEFS_DELIGHT_6_PINTS,
			MATURE_CHEFS_DELIGHT_5_PINTS,
			MATURE_CHEFS_DELIGHT_4_PINTS,
			MATURE_CHEFS_DELIGHT_3_PINTS,
			MATURE_CHEFS_DELIGHT_2_PINTS,
			MATURE_CHEFS_DELIGHT_1_PINT,
			MATURE_SLAYERS_RESPITE_8_PINTS,
			MATURE_SLAYERS_RESPITE_7_PINTS,
			MATURE_SLAYERS_RESPITE_6_PINTS,
			MATURE_SLAYERS_RESPITE_5_PINTS,
			MATURE_SLAYERS_RESPITE_4_PINTS,
			MATURE_SLAYERS_RESPITE_3_PINTS,
			MATURE_SLAYERS_RESPITE_2_PINTS,
			MATURE_SLAYERS_RESPITE_1_PINT,
			MATURE_CIDER_8_PINTS,
			MATURE_CIDER_7_PINTS,
			MATURE_CIDER_6_PINTS,
			MATURE_CIDER_5_PINTS,
			MATURE_CIDER_4_PINTS,
			MATURE_CIDER_3_PINTS,
			MATURE_CIDER_2_PINTS,
			MATURE_CIDER_1_PINT
	);

	private static final Set<BrewingBarrelState> FULL = Sets.immutableEnumSet(
			KELDA_STOUT_1_PINT,
			DWARVEN_STOUT_8_PINTS,
			ASGARNIAN_ALE_8_PINTS,
			GREENMANS_ALE_8_PINTS,
			WIZARDS_MIND_BOMB_8_PINTS,
			DRAGON_BITTER_8_PINTS,
			MOONLIGHT_MEAD_8_PINTS,
			AXEMANS_FOLLY_8_PINTS,
			CHEFS_DELIGHT_8_PINTS,
			SLAYERS_RESPITE_8_PINTS,
			CIDER_8_PINTS,
			MATURE_DWARVEN_STOUT_8_PINTS,
			MATURE_ASGARNIAN_ALE_8_PINTS,
			MATURE_GREENMANS_ALE_8_PINTS,
			MATURE_WIZARDS_MIND_BOMB_8_PINTS,
			MATURE_DRAGON_BITTER_8_PINTS,
			MATURE_MOONLIGHT_MEAD_8_PINTS,
			MATURE_AXEMANS_FOLLY_8_PINTS,
			MATURE_CHEFS_DELIGHT_8_PINTS,
			MATURE_SLAYERS_RESPITE_8_PINTS,
			MATURE_CIDER_8_PINTS
	);


	private final int value;

	private static final Map<Integer, BrewingBarrelState> map;
	static {
		map = Arrays.stream(values())
				.collect(Collectors.toMap(e -> e.value, e -> e));
	}

	public static BrewingBarrelState fromInt(int value) {
		return Optional.ofNullable(map.get(value)).orElse(UNKNOWN);
	}

	public static boolean isDrainable(int value)
	{
		return Stream.of(BrewingBarrelState.fromInt(value)).anyMatch(BrewingBarrelState.DRAIN_STATES::contains);
	}

	public static boolean hasMatureContents(int value)
	{
		return Stream.of(BrewingBarrelState.fromInt(value)).anyMatch(BrewingBarrelState.MATURE_CONTENTS::contains);
	}

	public static boolean hasNormalContents(int value)
	{
		return Stream.of(BrewingBarrelState.fromInt(value)).anyMatch(BrewingBarrelState.NORMAL_CONTENTS::contains);
	}

	public static boolean isFull(int value)
	{
		return Stream.of(BrewingBarrelState.fromInt(value)).anyMatch(BrewingBarrelState.FULL::contains);
	}

	public static String toString(int value)
	{
		switch (BrewingBarrelState.fromInt(value)) // Can this just be modulo'd somehow?
		{
			case EMPTY:
				return "Empty";
			case BAD_ALE:
				return "Bad ale";
			case BAD_CIDER:
				return "Bad cider";
			case UNFERMENTED:
				return "Unfermented";
			case KELDA_STOUT_1_PINT:
				return "1 Pint: Kelda Stout";
			case DWARVEN_STOUT_8_PINTS:
				return "8 Pints: Dwarven Stout";
			case DWARVEN_STOUT_7_PINTS:
				return "7 Pints: Dwarven Stout";
			case DWARVEN_STOUT_6_PINTS:
				return "6 Pints: Dwarven Stout";
			case DWARVEN_STOUT_5_PINTS:
				return "5 Pints: Dwarven Stout";
			case DWARVEN_STOUT_4_PINTS:
				return "4 Pints: Dwarven Stout";
			case DWARVEN_STOUT_3_PINTS:
				return "3 Pints: Dwarven Stout";
			case DWARVEN_STOUT_2_PINTS:
				return "2 Pints: Dwarven Stout";
			case DWARVEN_STOUT_1_PINT:
				return "1 Pint: Dwarven Stout";
			case ASGARNIAN_ALE_8_PINTS:
				return "8 Pints: Asgarnian Ale";
			case ASGARNIAN_ALE_7_PINTS:
				return "7 Pints: Asgarnian Ale";
			case ASGARNIAN_ALE_6_PINTS:
				return "6 Pints: Asgarnian Ale";
			case ASGARNIAN_ALE_5_PINTS:
				return "5 Pints: Asgarnian Ale";
			case ASGARNIAN_ALE_4_PINTS:
				return "4 Pints: Asgarnian Ale";
			case ASGARNIAN_ALE_3_PINTS:
				return "3 Pints: Asgarnian Ale";
			case ASGARNIAN_ALE_2_PINTS:
				return "2 Pints: Asgarnian Ale";
			case ASGARNIAN_ALE_1_PINT:
				return "1 Pint: Asgarnian Ale";
			case GREENMANS_ALE_8_PINTS:
				return "8 Pints: Greenman's Ale";
			case GREENMANS_ALE_7_PINTS:
				return "7 Pints: Greenman's Ale";
			case GREENMANS_ALE_6_PINTS:
				return "6 Pints: Greenman's Ale";
			case GREENMANS_ALE_5_PINTS:
				return "5 Pints: Greenman's Ale";
			case GREENMANS_ALE_4_PINTS:
				return "4 Pints: Greenman's Ale";
			case GREENMANS_ALE_3_PINTS:
				return "3 Pints: Greenman's Ale";
			case GREENMANS_ALE_2_PINTS:
				return "2 Pints: Greenman's Ale";
			case GREENMANS_ALE_1_PINT:
				return "1 Pint: Greenman's Ale";
			case WIZARDS_MIND_BOMB_8_PINTS:
				return "8 Pints: Wizard's Mind Bomb";
			case WIZARDS_MIND_BOMB_7_PINTS:
				return "7 Pints: Wizard's Mind Bomb";
			case WIZARDS_MIND_BOMB_6_PINTS:
				return "6 Pints: Wizard's Mind Bomb";
			case WIZARDS_MIND_BOMB_5_PINTS:
				return "5 Pints: Wizard's Mind Bomb";
			case WIZARDS_MIND_BOMB_4_PINTS:
				return "4 Pints: Wizard's Mind Bomb";
			case WIZARDS_MIND_BOMB_3_PINTS:
				return "3 Pints: Wizard's Mind Bomb";
			case WIZARDS_MIND_BOMB_2_PINTS:
				return "2 Pints: Wizard's Mind Bomb";
			case WIZARDS_MIND_BOMB_1_PINT:
				return "1 Pint: Wizard's Mind Bomb";
			case DRAGON_BITTER_8_PINTS:
				return "8 Pints: Dragon Bitter";
			case DRAGON_BITTER_7_PINTS:
				return "7 Pints: Dragon Bitter";
			case DRAGON_BITTER_6_PINTS:
				return "6 Pints: Dragon Bitter";
			case DRAGON_BITTER_5_PINTS:
				return "5 Pints: Dragon Bitter";
			case DRAGON_BITTER_4_PINTS:
				return "4 Pints: Dragon Bitter";
			case DRAGON_BITTER_3_PINTS:
				return "3 Pints: Dragon Bitter";
			case DRAGON_BITTER_2_PINTS:
				return "2 Pints: Dragon Bitter";
			case DRAGON_BITTER_1_PINT:
				return "1 Pint: Dragon Bitter";
			case MOONLIGHT_MEAD_8_PINTS:
				return "8 Pints: Moonlight Mead";
			case MOONLIGHT_MEAD_7_PINTS:
				return "7 Pints: Moonlight Mead";
			case MOONLIGHT_MEAD_6_PINTS:
				return "6 Pints: Moonlight Mead";
			case MOONLIGHT_MEAD_5_PINTS:
				return "5 Pints: Moonlight Mead";
			case MOONLIGHT_MEAD_4_PINTS:
				return "4 Pints: Moonlight Mead";
			case MOONLIGHT_MEAD_3_PINTS:
				return "3 Pints: Moonlight Mead";
			case MOONLIGHT_MEAD_2_PINTS:
				return "2 Pints: Moonlight Mead";
			case MOONLIGHT_MEAD_1_PINT:
				return "1 Pint: Moonlight Mead";
			case AXEMANS_FOLLY_8_PINTS:
				return "8 Pints: Axeman's Folly";
			case AXEMANS_FOLLY_7_PINTS:
				return "7 Pints: Axeman's Folly";
			case AXEMANS_FOLLY_6_PINTS:
				return "6 Pints: Axeman's Folly";
			case AXEMANS_FOLLY_5_PINTS:
				return "5 Pints: Axeman's Folly";
			case AXEMANS_FOLLY_4_PINTS:
				return "4 Pints: Axeman's Folly";
			case AXEMANS_FOLLY_3_PINTS:
				return "3 Pints: Axeman's Folly";
			case AXEMANS_FOLLY_2_PINTS:
				return "2 Pints: Axeman's Folly";
			case AXEMANS_FOLLY_1_PINT:
				return "1 Pint: Axeman's Folly";
			case CHEFS_DELIGHT_8_PINTS:
				return "8 Pints: Chef's Delight";
			case CHEFS_DELIGHT_7_PINTS:
				return "7 Pints: Chef's Delight";
			case CHEFS_DELIGHT_6_PINTS:
				return "6 Pints: Chef's Delight";
			case CHEFS_DELIGHT_5_PINTS:
				return "5 Pints: Chef's Delight";
			case CHEFS_DELIGHT_4_PINTS:
				return "4 Pints: Chef's Delight";
			case CHEFS_DELIGHT_3_PINTS:
				return "3 Pints: Chef's Delight";
			case CHEFS_DELIGHT_2_PINTS:
				return "2 Pints: Chef's Delight";
			case CHEFS_DELIGHT_1_PINT:
				return "1 Pint: Chef's Delight";
			case SLAYERS_RESPITE_8_PINTS:
				return "8 Pints: Slayer's Respite";
			case SLAYERS_RESPITE_7_PINTS:
				return "7 Pints: Slayer's Respite";
			case SLAYERS_RESPITE_6_PINTS:
				return "6 Pints: Slayer's Respite";
			case SLAYERS_RESPITE_5_PINTS:
				return "5 Pints: Slayer's Respite";
			case SLAYERS_RESPITE_4_PINTS:
				return "4 Pints: Slayer's Respite";
			case SLAYERS_RESPITE_3_PINTS:
				return "3 Pints: Slayer's Respite";
			case SLAYERS_RESPITE_2_PINTS:
				return "2 Pints: Slayer's Respite";
			case SLAYERS_RESPITE_1_PINT:
				return "1 Pint: Slayer's Respite";
			case CIDER_8_PINTS:
				return "8 Pints: Cider";
			case CIDER_7_PINTS:
				return "7 Pints: Cider";
			case CIDER_6_PINTS:
				return "6 Pints: Cider";
			case CIDER_5_PINTS:
				return "5 Pints: Cider";
			case CIDER_4_PINTS:
				return "4 Pints: Cider";
			case CIDER_3_PINTS:
				return "3 Pints: Cider";
			case CIDER_2_PINTS:
				return "2 Pints: Cider";
			case CIDER_1_PINT:
				return "1 Pint: Cider";
			case MATURE_DWARVEN_STOUT_8_PINTS:
				return "8 Pints: Mature Dwarven Stout";
			case MATURE_DWARVEN_STOUT_7_PINTS:
				return "7 Pints: Mature Dwarven Stout";
			case MATURE_DWARVEN_STOUT_6_PINTS:
				return "6 Pints: Mature Dwarven Stout";
			case MATURE_DWARVEN_STOUT_5_PINTS:
				return "5 Pints: Mature Dwarven Stout";
			case MATURE_DWARVEN_STOUT_4_PINTS:
				return "4 Pints: Mature Dwarven Stout";
			case MATURE_DWARVEN_STOUT_3_PINTS:
				return "3 Pints: Mature Dwarven Stout";
			case MATURE_DWARVEN_STOUT_2_PINTS:
				return "2 Pints: Mature Dwarven Stout";
			case MATURE_DWARVEN_STOUT_1_PINT:
				return "1 Pint: Mature Dwarven Stout";
			case MATURE_ASGARNIAN_ALE_8_PINTS:
				return "8 Pints: Mature Asgarnian Ale";
			case MATURE_ASGARNIAN_ALE_7_PINTS:
				return "7 Pints: Mature Asgarnian Ale";
			case MATURE_ASGARNIAN_ALE_6_PINTS:
				return "6 Pints: Mature Asgarnian Ale";
			case MATURE_ASGARNIAN_ALE_5_PINTS:
				return "5 Pints: Mature Asgarnian Ale";
			case MATURE_ASGARNIAN_ALE_4_PINTS:
				return "4 Pints: Mature Asgarnian Ale";
			case MATURE_ASGARNIAN_ALE_3_PINTS:
				return "3 Pints: Mature Asgarnian Ale";
			case MATURE_ASGARNIAN_ALE_2_PINTS:
				return "2 Pints: Mature Asgarnian Ale";
			case MATURE_ASGARNIAN_ALE_1_PINT:
				return "1 Pint: Mature Asgarnian Ale";
			case MATURE_GREENMANS_ALE_8_PINTS:
				return "8 Pints: Mature Greenman's Ale";
			case MATURE_GREENMANS_ALE_7_PINTS:
				return "7 Pints: Mature Greenman's Ale";
			case MATURE_GREENMANS_ALE_6_PINTS:
				return "6 Pints: Mature Greenman's Ale";
			case MATURE_GREENMANS_ALE_5_PINTS:
				return "5 Pints: Mature Greenman's Ale";
			case MATURE_GREENMANS_ALE_4_PINTS:
				return "4 Pints: Mature Greenman's Ale";
			case MATURE_GREENMANS_ALE_3_PINTS:
				return "3 Pints: Mature Greenman's Ale";
			case MATURE_GREENMANS_ALE_2_PINTS:
				return "2 Pints: Mature Greenman's Ale";
			case MATURE_GREENMANS_ALE_1_PINT:
				return "1 Pint: Mature Greenman's Ale";
			case MATURE_WIZARDS_MIND_BOMB_8_PINTS:
				return "8 Pints: Mature Wizard's Mind Bomb";
			case MATURE_WIZARDS_MIND_BOMB_7_PINTS:
				return "7 Pints: Mature Wizard's Mind Bomb";
			case MATURE_WIZARDS_MIND_BOMB_6_PINTS:
				return "6 Pints: Mature Wizard's Mind Bomb";
			case MATURE_WIZARDS_MIND_BOMB_5_PINTS:
				return "5 Pints: Mature Wizard's Mind Bomb";
			case MATURE_WIZARDS_MIND_BOMB_4_PINTS:
				return "4 Pints: Mature Wizard's Mind Bomb";
			case MATURE_WIZARDS_MIND_BOMB_3_PINTS:
				return "3 Pints: Mature Wizard's Mind Bomb";
			case MATURE_WIZARDS_MIND_BOMB_2_PINTS:
				return "2 Pints: Mature Wizard's Mind Bomb";
			case MATURE_WIZARDS_MIND_BOMB_1_PINT:
				return "1 Pint: Mature Wizard's Mind Bomb";
			case MATURE_DRAGON_BITTER_8_PINTS:
				return "8 Pints: Mature Dragon Bitter";
			case MATURE_DRAGON_BITTER_7_PINTS:
				return "7 Pints: Mature Dragon Bitter";
			case MATURE_DRAGON_BITTER_6_PINTS:
				return "6 Pints: Mature Dragon Bitter";
			case MATURE_DRAGON_BITTER_5_PINTS:
				return "5 Pints: Mature Dragon Bitter";
			case MATURE_DRAGON_BITTER_4_PINTS:
				return "4 Pints: Mature Dragon Bitter";
			case MATURE_DRAGON_BITTER_3_PINTS:
				return "3 Pints: Mature Dragon Bitter";
			case MATURE_DRAGON_BITTER_2_PINTS:
				return "2 Pints: Mature Dragon Bitter";
			case MATURE_DRAGON_BITTER_1_PINT:
				return "1 Pint: Mature Dragon Bitter";
			case MATURE_MOONLIGHT_MEAD_8_PINTS:
				return "8 Pints: Mature Moonlight Mead";
			case MATURE_MOONLIGHT_MEAD_7_PINTS:
				return "7 Pints: Mature Moonlight Mead";
			case MATURE_MOONLIGHT_MEAD_6_PINTS:
				return "6 Pints: Mature Moonlight Mead";
			case MATURE_MOONLIGHT_MEAD_5_PINTS:
				return "5 Pints: Mature Moonlight Mead";
			case MATURE_MOONLIGHT_MEAD_4_PINTS:
				return "4 Pints: Mature Moonlight Mead";
			case MATURE_MOONLIGHT_MEAD_3_PINTS:
				return "3 Pints: Mature Moonlight Mead";
			case MATURE_MOONLIGHT_MEAD_2_PINTS:
				return "2 Pints: Mature Moonlight Mead";
			case MATURE_MOONLIGHT_MEAD_1_PINT:
				return "1 Pint: Mature Moonlight Mead";
			case MATURE_AXEMANS_FOLLY_8_PINTS:
				return "8 Pints: Mature Axeman's Folly";
			case MATURE_AXEMANS_FOLLY_7_PINTS:
				return "7 Pints: Mature Axeman's Folly";
			case MATURE_AXEMANS_FOLLY_6_PINTS:
				return "6 Pints: Mature Axeman's Folly";
			case MATURE_AXEMANS_FOLLY_5_PINTS:
				return "5 Pints: Mature Axeman's Folly";
			case MATURE_AXEMANS_FOLLY_4_PINTS:
				return "4 Pints: Mature Axeman's Folly";
			case MATURE_AXEMANS_FOLLY_3_PINTS:
				return "3 Pints: Mature Axeman's Folly";
			case MATURE_AXEMANS_FOLLY_2_PINTS:
				return "2 Pints: Mature Axeman's Folly";
			case MATURE_AXEMANS_FOLLY_1_PINT:
				return "1 Pint: Mature Axeman's Folly";
			case MATURE_CHEFS_DELIGHT_8_PINTS:
				return "8 Pints: Mature Chef's Delight";
			case MATURE_CHEFS_DELIGHT_7_PINTS:
				return "7 Pints: Mature Chef's Delight";
			case MATURE_CHEFS_DELIGHT_6_PINTS:
				return "6 Pints: Mature Chef's Delight";
			case MATURE_CHEFS_DELIGHT_5_PINTS:
				return "5 Pints: Mature Chef's Delight";
			case MATURE_CHEFS_DELIGHT_4_PINTS:
				return "4 Pints: Mature Chef's Delight";
			case MATURE_CHEFS_DELIGHT_3_PINTS:
				return "3 Pints: Mature Chef's Delight";
			case MATURE_CHEFS_DELIGHT_2_PINTS:
				return "2 Pints: Mature Chef's Delight";
			case MATURE_CHEFS_DELIGHT_1_PINT:
				return "1 Pint: Mature Chef's Delight";
			case MATURE_SLAYERS_RESPITE_8_PINTS:
				return "8 Pints: Mature Slayer's Respite";
			case MATURE_SLAYERS_RESPITE_7_PINTS:
				return "7 Pints: Mature Slayer's Respite";
			case MATURE_SLAYERS_RESPITE_6_PINTS:
				return "6 Pints: Mature Slayer's Respite";
			case MATURE_SLAYERS_RESPITE_5_PINTS:
				return "5 Pints: Mature Slayer's Respite";
			case MATURE_SLAYERS_RESPITE_4_PINTS:
				return "4 Pints: Mature Slayer's Respite";
			case MATURE_SLAYERS_RESPITE_3_PINTS:
				return "3 Pints: Mature Slayer's Respite";
			case MATURE_SLAYERS_RESPITE_2_PINTS:
				return "2 Pints: Mature Slayer's Respite";
			case MATURE_SLAYERS_RESPITE_1_PINT:
				return "1 Pint: Mature Slayer's Respite";
			case MATURE_CIDER_8_PINTS:
				return "8 Pints: Mature Mature Cider";
			case MATURE_CIDER_7_PINTS:
				return "7 Pints: Mature Mature Cider";
			case MATURE_CIDER_6_PINTS:
				return "6 Pints: Mature Mature Cider";
			case MATURE_CIDER_5_PINTS:
				return "5 Pints: Mature Mature Cider";
			case MATURE_CIDER_4_PINTS:
				return "4 Pints: Mature Mature Cider";
			case MATURE_CIDER_3_PINTS:
				return "3 Pints: Mature Mature Cider";
			case MATURE_CIDER_2_PINTS:
				return "2 Pints: Mature Mature Cider";
			case MATURE_CIDER_1_PINT:
				return "1 Pint: Mature Mature Cider";

			case UNKNOWN:
				return "Unknown";
			case UNINITIALIZED:
				return "Uninitialized";
			default:
				return "?";
		}
	}
}
