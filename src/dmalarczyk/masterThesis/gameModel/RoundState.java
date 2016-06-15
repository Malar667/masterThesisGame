package dmalarczyk.masterThesis.gameModel;

import java.util.*;

public class RoundState {

    public enum TurnState{
        notStarted, playerA, playerB, playerAWon, playerBWon, playerAWonByCompare, playerBWonByCompare, draw
    }

    public ArrayList<CardType> deck;
    public PlayerSpace spaceOfPlayerA;
    public PlayerSpace spaceOfPlayerB;
    public ArrayList<CardType> openDiscardedCards;
    public CardType hiddenDiscardedCard;

    public TurnState turnState;

    public RoundState(){
        initVariables();
        initDeck();
    }

    private void initVariables(){
        deck = new ArrayList<>();
        spaceOfPlayerA = new PlayerSpace();
        spaceOfPlayerB = new PlayerSpace();
        openDiscardedCards = new ArrayList<>();
        turnState = TurnState.notStarted;
    }

    private void initDeck(){
        deck.addAll(
                Arrays.asList(CardType.guard, CardType.guard, CardType.guard, CardType.guard, CardType.guard,
                        CardType.priest, CardType.priest,
                        CardType.baron, CardType.baron,
                        CardType.handmaid, CardType.handmaid,
                        CardType.prince, CardType.prince,
                        CardType.king,
                        CardType.countess,
                        CardType.princess
                )
        );
        Collections.shuffle(deck);
    }

    public void switchState(){
        if (turnState == TurnState.playerA){
            turnState = RoundState.TurnState.playerB;
        }
        else if (turnState == TurnState.playerB){
            turnState = RoundState.TurnState.playerA;
        }
        else if (turnState == TurnState.notStarted){
            turnState = RoundState.TurnState.playerA;
        }
    }


    public RoundState copyRoundState(){
        Random rnd = new Random();
        List<CardType> allHiddenCards = getAllHiddenCards();

        RoundState roundStateCopy = new RoundState();

        roundStateCopy.spaceOfPlayerA = new PlayerSpace( spaceOfPlayerA );
        roundStateCopy.openDiscardedCards = new ArrayList<>(openDiscardedCards);
        roundStateCopy.spaceOfPlayerB = spaceOfPlayerB.hiddenClone( allHiddenCards.remove(rnd.nextInt(deck.size())), spaceOfPlayerB );
        roundStateCopy.hiddenDiscardedCard = allHiddenCards.remove( rnd.nextInt(deck.size()) );
        roundStateCopy.deck.addAll(allHiddenCards);

        return roundStateCopy;
    }

    public List<CardType> getAllHiddenCards(){
        List<CardType> allHiddenCards = new ArrayList<>();
        allHiddenCards.addAll( deck );
        allHiddenCards.addAll( spaceOfPlayerB.hand );
        allHiddenCards.add( hiddenDiscardedCard );

        return  allHiddenCards;
    }

    public Map<CardType, Double> getProbabilityMap(){
        List<CardType> allHiddenCards = getAllHiddenCards();
        Double nrOfHiddenCards = new Double(allHiddenCards.size());
        HashMap<CardType, Double> probabilityMap = new HashMap<>();
        HashMap<CardType, Double> occurenceMap = new HashMap<>();

        for(CardType cardType : EnumSet.allOf(CardType.class)){
            Double tmpCount = new Double(0.0);
            for(CardType card : allHiddenCards) {
                if (card == cardType) {
                    tmpCount+=1.0;
                }
            }
            occurenceMap.put(cardType, tmpCount);
            probabilityMap.put(cardType, tmpCount / nrOfHiddenCards );
        }

        return probabilityMap;
    }
}
