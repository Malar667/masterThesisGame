package agh.masterThesis.gameEngine;

import agh.masterThesis.model.CardType;

/**
 * Created by Malar on 2016-05-11.
 */
public class Engine {
    public CardType[] deck = new CardType[]{CardType.guard, CardType.guard, CardType.guard, CardType.guard, CardType.guard,
            CardType.priest, CardType.priest,
            CardType.baron, CardType.baron,
            CardType.handemaid, CardType.handemaid,
            CardType.prince, CardType.prince,
            CardType.king,
            CardType.countess,
            CardType.princess
    };

    public CardType[] discardedDeckPlayerA;
    public CardType[] discardedDeckPlayerB;
    public CardType[] discardedOpen;
    public CardType hiddenCard;

    public boolean safetyFlagPlayerA;
    public boolean safetyFlagPlayerB;


}
