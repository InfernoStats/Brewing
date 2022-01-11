package com.infernostats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InfoBoxState
{
    COMPLETED("Completed"),
    PREP_OR_COMPLETED("Prep/Completed"),
    ALWAYS("Always");

    private final String type;

    @Override
    public String toString()
    {
        return type;
    }
}
