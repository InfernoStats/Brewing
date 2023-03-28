package com.brewing;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BrewingVatState {
	EMPTY(0),
	WATER(1),
	BARLEY(2),

	/* Dwarven Stout */
	HAMMERSTONE_HOPS(4),
	FERMENTING_DWARVEN_STOUT_STATE_1(5),
	FERMENTING_DWARVEN_STOUT_STATE_2(6),
	DWARVEN_STOUT(7),
	MATURE_DWARVEN_STOUT(8),

	/* Asgarnian Ale */
	ASGARNIAN_HOPS(10),
	FERMENTING_ASGARNIAN_ALE_STATE_1(11),
	FERMENTING_ASGARNIAN_ALE_STATE_2(12),
	ASGARNIAN_ALE(13),
	MATURE_ASGARNIAN_ALE(14),

	/* Greenman's Ale */
	HARRALANDER(16),
	FERMENTING_GREENMANS_ALE_STATE_1(17),
	FERMENTING_GREENMANS_ALE_STATE_2(18),
	GREENMANS_ALE(19),
	MATURE_GREENMANS_ALE(20),

	/* Wizard's Mind Bomb */
	YANILLIAN_HOPS(22),
	FERMENTING_WIZARDS_MIND_BOMD_STATE_1(23),
	FERMENTING_WIZARDS_MIND_BOMD_STATE_2(24),
	WIZARDS_MIND_BOMB(25),
	MATURE_WIZARDS_MIND_BOMB(26),

	/* Dragon Bitter */
	KRANDORIAN_HOPS(28),
	FERMENTING_DRAGON_BITTER_STATE_1(29),
	FERMENTING_DRAGON_BITTER_STATE_2(30),
	DRAGON_BITTER(31),
	MATURE_DRAGON_BITTER(32),

	/* Moonlight Mead */
	MUSHROOMS(34),
	FERMENTING_MOONLIGHT_MEAD_STATE_1(35),
	FERMENTING_MOONLIGHT_MEAD_STATE_2(36),
	MOONLIGHT_MEAD(37),
	MATURE_MOONLIGHT_MEAD(38),

	/* Axeman's Folly */
	OAK_ROOTS(40),
	FERMENTING_AXEMANS_FOLLY_STATE_1(41),
	FERMENTING_AXEMANS_FOLLY_STATE_2(42),
	AXEMANS_FOLLY(43),
	MATURE_AXEMANS_FOLLY(44),

	/* Chef's Delight */
	CHOCOLATE_DUST(46),
	FERMENTING_CHEFS_DELIGHT_STATE_1(47),
	FERMENTING_CHEFS_DELIGHT_STATE_2(48),
	CHEFS_DELIGHT(49),
	MATURE_CHEFS_DELIGHT(50),

	/* Slayer's Respite */
	WILDBLOOD_HOPS(52),
	FERMENTING_SLAYERS_RESPITE_STATE_1(53),
	FERMENTING_SLAYERS_RESPITE_STATE_2(54),
	SLAYERS_RESPITE(55),
	MATURE_SLAYERS_RESPITE(56),

	/* Cider */
	APPLE_MUSH(58),
	FERMENTING_CIDER_STATE_1(59),
	FERMENTING_CIDER_STATE_2(60),
	CIDER(61),
	MATURE_CIDER(62),

	/* Kelda Stout */
	KELDA_HOPS(68),
	FERMENTING_KELDA_STOUT_STATE_1(69),
	FERMENTING_KELDA_STOUT_STATE_2(70),
	KELDA_STOUT(71),

	BAD_ALE(64),
	BAD_CIDER(65),

	UNKNOWN(-1),
	UNINITIALIZED(-2);

	private static final Set<BrewingVatState> PARTIAL_STATES = Sets.immutableEnumSet(
			WATER,
			BARLEY,
			HAMMERSTONE_HOPS,
			ASGARNIAN_HOPS,
			HARRALANDER,
			YANILLIAN_HOPS,
			KRANDORIAN_HOPS,
			MUSHROOMS,
			OAK_ROOTS,
			CHOCOLATE_DUST,
			WILDBLOOD_HOPS,
			APPLE_MUSH,
			KELDA_HOPS
	);

	private static final Set<BrewingVatState> FAILURE_STATES = Sets.immutableEnumSet(
			BAD_ALE,
			BAD_CIDER
	);

	private static final Set<BrewingVatState> COMPLETE_NORMAL_STATES = Sets.immutableEnumSet(
			DWARVEN_STOUT,
			ASGARNIAN_ALE,
			GREENMANS_ALE,
			WIZARDS_MIND_BOMB,
			DRAGON_BITTER,
			MOONLIGHT_MEAD,
			AXEMANS_FOLLY,
			CHEFS_DELIGHT,
			SLAYERS_RESPITE,
			CIDER,
			KELDA_STOUT
	);

	private static final Set<BrewingVatState> COMPLETE_MATURE_STATES = Sets.immutableEnumSet(
			MATURE_DWARVEN_STOUT,
			MATURE_ASGARNIAN_ALE,
			MATURE_GREENMANS_ALE,
			MATURE_WIZARDS_MIND_BOMB,
			MATURE_DRAGON_BITTER,
			MATURE_MOONLIGHT_MEAD,
			MATURE_AXEMANS_FOLLY,
			MATURE_CHEFS_DELIGHT,
			MATURE_SLAYERS_RESPITE,
			MATURE_CIDER
	);

	private final int value;

	public static boolean isPartial(int value) // does this need a param or no?
	{
        return Stream.of(BrewingVatState.values()[value]).anyMatch(PARTIAL_STATES::contains);
	}

	public static boolean isCompleteNormal(int value) // does this need a param or no?
    {
        return Stream.of(BrewingVatState.values()[value]).anyMatch(COMPLETE_NORMAL_STATES::contains);
	}

	public static boolean isCompleteMature(int value) // does this need a param or no?
    {
		return Stream.of(BrewingVatState.values()[value]).anyMatch(COMPLETE_MATURE_STATES::contains);
	}

	public static boolean isBad(int value) // does this need a param or no?
    {
		return Stream.of(BrewingVatState.values()[value]).anyMatch(FAILURE_STATES::contains);
	}

	public static String toString(int value)
	{
		switch (BrewingVatState.values()[value])
		{
			case EMPTY:
				return "Empty";
			case WATER:
				return "Water";
			case BARLEY:
				return "Barley";

			/* Dwarven Stout */
			case HAMMERSTONE_HOPS:
				return "Hammerstone Hops";
			case FERMENTING_DWARVEN_STOUT_STATE_1:
			case FERMENTING_DWARVEN_STOUT_STATE_2:
				return "Fermenting Dwarven Stout";
			case DWARVEN_STOUT:
				return "Dwarven Stout";
			case MATURE_DWARVEN_STOUT:
				return "Mature Dwarven Stout";

			/* Asgarnian Ale */
			case ASGARNIAN_HOPS:
				return "Asgarnian Hops";
			case FERMENTING_ASGARNIAN_ALE_STATE_1:
			case FERMENTING_ASGARNIAN_ALE_STATE_2:
				return "Fermenting Asgnarian Ale";
			case ASGARNIAN_ALE:
				return "Asgarnian Ale";
			case MATURE_ASGARNIAN_ALE:
				return "Mature Asgarnian Ale";

			/* Greenman's Ale */
			case HARRALANDER:
				return "Harralander";
			case FERMENTING_GREENMANS_ALE_STATE_1:
			case FERMENTING_GREENMANS_ALE_STATE_2:
				return "Fermenting Greenman's Ale";
			case GREENMANS_ALE:
				return "Greenman's Ale";
			case MATURE_GREENMANS_ALE:
				return "Mature Greenman's Ale";

			/* Wizard's Mind Bomb */
			case YANILLIAN_HOPS:
				return "Yanillian Hops";
			case FERMENTING_WIZARDS_MIND_BOMD_STATE_1:
			case FERMENTING_WIZARDS_MIND_BOMD_STATE_2:
				return "Fermenting Wizard's Mind Bomb";
			case WIZARDS_MIND_BOMB:
				return "Wizard's Mind Bomb";
			case MATURE_WIZARDS_MIND_BOMB:
				return "Mature Wizard's Mind Bomb";

			/* Dragon Bitter */
			case KRANDORIAN_HOPS:
				return "Krandorian Hops";
			case FERMENTING_DRAGON_BITTER_STATE_1:
			case FERMENTING_DRAGON_BITTER_STATE_2:
				return "Fermenting Dragon Bitter";
			case DRAGON_BITTER:
				return "Dragon Bitter";
			case MATURE_DRAGON_BITTER:
				return "Mature Dragon Bitter";

			/* Moonlight Mead */
			case MUSHROOMS:
				return "Mushrooms";
			case FERMENTING_MOONLIGHT_MEAD_STATE_1:
			case FERMENTING_MOONLIGHT_MEAD_STATE_2:
				return "Fermenting Moonlight Mead";
			case MOONLIGHT_MEAD:
				return "Moonlight Mead";
			case MATURE_MOONLIGHT_MEAD:
				return "Mature Moonlight Mead";

			/* Axeman's Folly */
			case OAK_ROOTS:
				return "Oak Roots";
			case FERMENTING_AXEMANS_FOLLY_STATE_1:
			case FERMENTING_AXEMANS_FOLLY_STATE_2:
				return "Fermenting Axeman's Folly";
			case AXEMANS_FOLLY:
				return "Axeman's Folly";
			case MATURE_AXEMANS_FOLLY:
				return "Mature Axeman's Folly";

			/* Chef's Delight */
			case CHOCOLATE_DUST:
				return "Chocolate Dust";
			case FERMENTING_CHEFS_DELIGHT_STATE_1:
			case FERMENTING_CHEFS_DELIGHT_STATE_2:
				return "Fermenting Chef's Delight";
			case CHEFS_DELIGHT:
				return "Chef's Delight";
			case MATURE_CHEFS_DELIGHT:
				return "Chef's Delight";

			/* Slayer's Respite */
			case WILDBLOOD_HOPS:
				return "Wildblood Hops";
			case FERMENTING_SLAYERS_RESPITE_STATE_1:
			case FERMENTING_SLAYERS_RESPITE_STATE_2:
				return "Fermenting Slayer's Respite";
			case SLAYERS_RESPITE:
				return "Slayer's Respite";
			case MATURE_SLAYERS_RESPITE:
				return "Mature Slayer's Respite";

			/* Cider */
			case APPLE_MUSH:
				return "Apple Mush";
			case FERMENTING_CIDER_STATE_1:
			case FERMENTING_CIDER_STATE_2:
				return "Fermenting Cider";
			case CIDER:
				return "Cider";
			case MATURE_CIDER:
				return "Mature Cider";

			/* Kelda Stout */
			case KELDA_HOPS:
				return "Kelda Hops";
			case FERMENTING_KELDA_STOUT_STATE_1:
			case FERMENTING_KELDA_STOUT_STATE_2:
				return "Fermenting Kelda Stout";
			case KELDA_STOUT:
				return "Kelda Stout";

			case BAD_ALE:
				return "Bad Ale";
			case BAD_CIDER:
				return "Bad Cider";

			case UNKNOWN:
				return "Unknown";
			case UNINITIALIZED:
				return "Uninitialized";
			default:
				return "?";
		}
	}
}
