package com.infernostats;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrewingData
{
    private BrewingLocation location;
    private BrewingState state;
    private boolean theStuff;
    private int cookingLevel;
}