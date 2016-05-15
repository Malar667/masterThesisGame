import dmalarczyk.masterThesis.gameEngine.RoundState;
import dmalarczyk.masterThesis.model.CardType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class RoundStateTest {

    private int nrOfCards;
    private CardType cardType;

    public RoundStateTest(int nrOfCards, CardType cardType){
        this.nrOfCards  = nrOfCards;
        this.cardType = cardType;
    }

    @Parameterized.Parameters( name = "{index}: deck contains {0} card(s) of {1}")
    public static Iterable<Object[]> data(){
        return Arrays.asList( new Object[][] {
                {5, CardType.guard},
                {2, CardType.priest},
                {2, CardType.baron},
                {2, CardType.handmaid},
                {2, CardType.prince},
                {1, CardType.king},
                {1, CardType.countess},
                {1, CardType.princess}
        });
    }

    @Test
    public void initDeck_ContainsProperAmountOfAllCardTypes2() {
        RoundState roundState = new RoundState();
        assertEquals(nrOfCards , roundState.deck.stream().filter( cardType -> cardType == this.cardType ).collect(Collectors.toList()).size());
    }

    @Test
    public void setRoundForPlay_isValid(){
        RoundState roundState = new RoundState();
        roundState.setRoundForPlay();

        assertEquals(0, roundState.discardedDeckPlayerA.size());
        assertEquals(0, roundState.discardedDeckPlayerB.size());
        assertEquals(3, roundState.openDiscardedCards.size());
        assertEquals(1, roundState.handPlayerA.size());
        assertEquals(1, roundState.handPlayerB.size());
        assertEquals(10, roundState.deck.size());
    }
}