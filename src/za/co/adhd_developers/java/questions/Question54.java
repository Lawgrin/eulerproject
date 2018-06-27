package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class Question54 implements Question
{
    private int answer = 0;
    @Override
    public void doWork()
    {
        try
        {
            InputStream inputStream = getClass().getResourceAsStream("/externalResources/Question54_Cards.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            ArrayList<String[]> games = new ArrayList<>();
            String s = "";
            while ((s = bufferedReader.readLine()) != null)
            {
                String[] cards = s.split(" ");
                games.add(cards);
            }

            bufferedReader.close();

            for (String[] gameStr : games)
            {
                Game game = new Game(gameStr);

                if (game.getWinner() == 1)
                {
                    this.answer++;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 54");
        System.out.println("The total number of winning games player 1 got, is: " + this.answer);
        System.out.println("=================================");
    }

    class Game
    {
        private PlayerHand player1;
        private PlayerHand player2;

        public Game(String[] cards)
        {
            String[] player1_Cards = new String[5];
            String[] player2_Cards = new String[5];

            for (int i = 0; i < cards.length; i++)
            {
                if (i < 5)
                {
                    player1_Cards[i] = cards[i];
                }
                else
                {
                    player2_Cards[i-5] = cards[i];
                }
            }

            this.player1 = new PlayerHand(player1_Cards);
            this.player2 = new PlayerHand(player2_Cards);
        }

        @Override
        public String toString()
        {
            String ret = "";

            ret += "== Player 1 ==";
            ret += "\n";
            if (null != this.player1)
            {
                ret += this.player1.toString();
            }
            else
            {
                ret += "Empty";
            }
            ret += "\n";
            ret += "== ======== ==";


            ret += "\n";

            ret += "== Player 2 ==";
            ret += "\n";
            if (null != this.player2)
            {
                ret += this.player2.toString();
            }
            else
            {
                ret += "Empty";
            }
            ret += "\n";
            ret += "== ======== ==";

            return ret;
        }

        public int getWinner()
        {
            PlayerHandComparator playerHandComparator = new PlayerHandComparator();
            int compResult =  playerHandComparator.compare(this.player1, this.player2);

            if (compResult < 0)
            {
                return 2;
            }
            else if (compResult > 0)
            {
                return 1;
            }

            return 0;
        }
    }

    class PlayerHand
    {
        private ArrayList<Card> cards;
        private HandType handType;
        private ArrayList<Card> handTypeCards;

        public PlayerHand(String[] cards)
        {
            this.cards = new ArrayList<>();

            for (String cardStr : cards)
            {
                Card card = new Card(cardStr);
                this.cards.add(card);
            }

            Collections.sort(this.cards, new CardComparator());

            determineHandType();
        }

        public HandType getHandType()
        {
            return handType;
        }

        private void determineHandType()
        {
            boolean sequential = isSequential();
            boolean sameSuit = isSameSuit();
            boolean startTen = this.cards.get(0).getCardValue() == CardValue.TEN;
            int usedCardsCount = 0;
            ArrayList<ArrayList<Card>> groups = groupCards();
            if (groups.size() > 0)
            {
                for (ArrayList<Card> group : groups)
                {
                    usedCardsCount += group.size();
                }
            }

            if (sequential && sameSuit)
            {
                if (startTen)
                {
                    this.handType = HandType.ROYAL_FLUSH;
                    handTypeCards = new ArrayList<>(this.cards);
                }
                else
                {
                    this.handType = HandType.STRAIGHT_FLUSH;
                    handTypeCards = new ArrayList<>(this.cards);
                }
            }
            else if (groups.size() == 1 && usedCardsCount == 4)
            {
                this.handType = HandType.FOUR_OF_A_KIND;
                handTypeCards = new ArrayList<>(groups.get(0));
            }
            else if (groups.size() == 2 && usedCardsCount == 5)
            {
                this.handType = HandType.FULL_HOUSE;
                handTypeCards = new ArrayList<>(this.cards);
            }
            else if (sameSuit)
            {
                this.handType = HandType.FLUSH;
                handTypeCards = new ArrayList<>(this.cards);
            }
            else if (sequential)
            {
                this.handType = HandType.STRAIGHT;
                handTypeCards = new ArrayList<>(this.cards);
            }
            else if (groups.size() == 1 && usedCardsCount == 3)
            {
                this.handType = HandType.THREE_OF_A_KIND;
                handTypeCards = new ArrayList<>(groups.get(0));
            }
            else if (groups.size() == 2 && usedCardsCount == 4)
            {
                this.handType = HandType.TWO_PAIRS;
                handTypeCards = new ArrayList<>();
                handTypeCards.addAll(groups.get(0));
                handTypeCards.addAll(groups.get(1));
            }
            else if (groups.size() == 1 && usedCardsCount == 2)
            {
                this.handType = HandType.ONE_PAIR;
                handTypeCards = new ArrayList<>(groups.get(0));
            }
            else
            {
                this.handType = HandType.HIGH_CARD;
                handTypeCards = new ArrayList<>();
                handTypeCards.add(getHighestCard());
            }


        }

        private ArrayList<ArrayList<Card>> groupCards()
        {
            Hashtable<String, ArrayList<Card>> groups = new Hashtable<>();

            for (Card card : this.cards)
            {
                if (groups.containsKey(card.getCardValue().getRawValue()))
                {
                    continue;
                }

                ArrayList<Card> group = new ArrayList<>();

                for (Card tmp_card : this.cards)
                {
                    if (card.getCardValue().getRankingValue() == tmp_card.getCardValue().getRankingValue())
                    {
                        group.add(tmp_card);
                    }
                }

                groups.put(card.getCardValue().getRawValue(), group);
            }

            ArrayList<ArrayList<Card>> finalGroups = new ArrayList<>();

            for (String key : groups.keySet())
            {
                if (groups.get(key).size() > 1)
                {
                    finalGroups.add(groups.get(key));
                }
            }

            return finalGroups;
        }

        private int countSameKind()
        {
            int finalCount = 0;
            for (Card card : this.cards)
            {
                int count = 0;
                for (Card tmp_card : this.cards)
                {
                    if (card.getCardValue().getRankingValue() == tmp_card.getCardValue().getRankingValue())
                    {
                        count++;
                    }
                }

                if (finalCount < count)
                {
                    finalCount = count;
                }
            }

            return finalCount;
        }

        private boolean isSequential()
        {
            CardValue startValue = this.cards.get(0).getCardValue();

            for (int i = 1; i < this.cards.size(); i++)
            {
                CardValue tmp = this.cards.get(i).getCardValue();

                CardValue nextValue = CardValue.getCardValueFromRanking(startValue.getRankingValue()+i);

                if (tmp.getRankingValue() != nextValue.getRankingValue())
                {
                    return false;
                }
            }

            return true;
        }

        private boolean isSameSuit()
        {
            Suit suit = this.cards.get(0).getSuit();

            for (Card card : this.cards)
            {
                if (suit.getRawText().compareTo(card.getSuit().getRawText()) != 0)
                {
                    return false;
                }
            }

            return true;
        }

        public Card getHighestCard()
        {
            Card highest = null;

            CardValueComparator cardValueComparator = new CardValueComparator();
            for (Card card : cards)
            {
                if (null == highest)
                {
                    highest = card;
                }

                if (cardValueComparator.compare(highest.getCardValue(), card.getCardValue()) < 0)
                {
                    highest = card;
                }

            }

            return highest;
        }

        public Card getHighestCardFromHandType()
        {
            Card highest = null;

            CardValueComparator cardValueComparator = new CardValueComparator();
            for (Card card : this.handTypeCards)
            {
                if (null == highest)
                {
                    highest = card;
                }

                if (cardValueComparator.compare(highest.getCardValue(), card.getCardValue()) < 0)
                {
                    highest = card;
                }

            }

            return highest;
        }

        @Override
        public String toString()
        {
            String ret = "";

            if (null == this.cards)
            {
                ret += "Cards is null";
            }
            else if (this.cards.size() == 0)
            {
                ret += "No Cards";
            }
            else
            {
                int count = 1;

                Card highest = getHighestCard();

                for (Card card : this.cards)
                {
                    ret += card.toString();
                    if (highest.getCardValue() == card.getCardValue() && highest.getSuit() == card.getSuit())
                    {
                        ret += " *";
                    }
                    if (count != 5)
                    {
                        ret += "\n";
                    }
                    count++;
                }
            }

            return ret;
        }
    }

    class PlayerHandComparator implements Comparator<PlayerHand>
    {
        @Override
        public int compare(PlayerHand o1, PlayerHand o2)
        {
            HandTypeComparator handTypeComparator = new HandTypeComparator();
            int compareResult = handTypeComparator.compare(o1.getHandType(), o2.getHandType());

            if (compareResult == 0)
            {
                CardValueComparator cardValueComparator = new CardValueComparator();

                compareResult = cardValueComparator.compare(o1.getHighestCardFromHandType().getCardValue(), o2.getHighestCardFromHandType().getCardValue());
            }

            return compareResult;
        }
    }

    class Card
    {
        private Suit suit;
        private CardValue cardValue;

        public Card(String card)
        {
            String[] cardData = card.split("|");

            this.cardValue = CardValue.getCardFromValue(cardData[0]);
            this.suit = Suit.getSuitFromLetter(cardData[1]);
        }

        public Suit getSuit()
        {
            return suit;
        }

        public CardValue getCardValue()
        {
            return cardValue;
        }

        @Override
        public String toString()
        {
            String ret = "";

            if (null == this.cardValue)
            {
                ret += "?";
            }
            else
            {
                ret += this.cardValue.toString();
            }
            ret += " of ";
            if (null == this.suit)
            {
                ret += "?";
            }
            else
            {
                ret += this.suit.toString();
            }

            return  ret;
        }
    }

    class CardComparator implements Comparator<Card>
    {
        @Override
        public int compare(Card o1, Card o2)
        {
            int result = 0;

            CardValueComparator cardValueComparator = new CardValueComparator();

            result = cardValueComparator.compare(o1.getCardValue(), o2.getCardValue());

//            if (result == 0)
//            {
//                SuitComparator suitComparator = new SuitComparator();
//                suitComparator.compare(o1.getSuit(), o2.getSuit());
//            }

            return result;
        }
    }

    enum Suit
    {
        HEART("Heart"),
        CLUB("Club"),
        SPADE("Spade"),
        DIAMOND("Diamond");

        private String rawText;

        Suit(String rawText)
        {
            this.rawText = rawText;
        }

        public String getRawText()
        {
            return rawText;
        }

        public static Suit getSuitFromLetter(String letter)
        {
            if (null == letter)
            {
                return null;
            }
            if (letter.isEmpty())
            {
                return null;
            }

            for (Suit suit : Suit.values())
            {
                if (suit.getRawText().toLowerCase().startsWith(letter.toLowerCase()))
                {
                    return suit;
                }
            }

            return null;
        }

        @Override
        public String toString()
        {
            return this.getRawText();
        }
    }

    static class SuitComparator implements Comparator<Suit>
    {
        @Override
        public int compare(Suit o1, Suit o2)
        {
            return o1.getRawText().compareTo(o2.getRawText());
        }
    }

    enum CardValue
    {
        TWO("2", 0, "Two"),
        THREE("3",1, "Three"),
        FOUR("4",2, "Four"),
        FIVE("5",3, "Five"),
        SIX("6",4, "Six"),
        SEVEN("7",5, "Seven"),
        EIGHT("8",6, "Eight"),
        NINE("9",7, "Nine"),
        TEN("T",8, "Ten"),
        JACK("J",9, "Jack"),
        QUEEN("Q",10, "Queen"),
        KING("K",11, "King"),
        ACE("A",12, "Ace");

        private String rawValue;
        private int rankingValue;
        private String textValue;

        CardValue(String rawValue, int rankingValue, String textValue)
        {
            this.rawValue = rawValue;
            this.rankingValue = rankingValue;
            this.textValue = textValue;
        }

        public String getRawValue()
        {
            return rawValue;
        }

        public int getRankingValue()
        {
            return rankingValue;
        }

        public String getTextValue() {
            return textValue;
        }

        public static CardValue getCardFromValue(String value)
        {
            if (null == value)
            {
                return null;
            }
            if (value.isEmpty())
            {
                return null;
            }

            for (CardValue cardValue : CardValue.values())
            {
                if (value.equalsIgnoreCase(cardValue.getRawValue()))
                {
                    return cardValue;
                }
            }

            return null;
        }

        public static CardValue getCardValueFromRanking(int ranking)
        {
            if (ranking < 0)
            {
                return CardValue.TWO;
            }
            else if (ranking > 13)
            {
                return CardValue.ACE;
            }

            for (CardValue cardValue : CardValue.values())
            {
                if (ranking == cardValue.getRankingValue())
                {
                    return cardValue;
                }
            }

            return null;
        }

        @Override
        public String toString()
        {
            return this.getTextValue();
        }
    }

    class CardValueComparator implements Comparator<CardValue>
    {
        @Override
        public int compare(CardValue o1, CardValue o2)
        {
            return Integer.compare(o1.getRankingValue(), o2.getRankingValue());
        }
    }

    enum HandType
    {
        HIGH_CARD("High Card","Highest value card",0),
        ONE_PAIR("One Pair","Two cards of the same value",1),
        TWO_PAIRS("Two Pairs","Two different pairs",2),
        THREE_OF_A_KIND("Three of a Kind","Three cards of the same value",3),
        STRAIGHT("Straight","All cards are consecutive values",4),
        FLUSH("Flush","All cards of the same suit",5),
        FULL_HOUSE("Full House","Three of a kind and a pair",6),
        FOUR_OF_A_KIND("Four of a Kind","Four cards of the same value",7),
        STRAIGHT_FLUSH("Straight Flush","All cards are consecutive values of same suit",8),
        ROYAL_FLUSH("Royal Flush","Ten, Jack, Queen, King, Ace, in same suit",9);

        private String name;
        private String description;
        private int ranking;

        HandType(String name, String description, int ranking)
        {
            this.name = name;
            this.description = description;
            this.ranking = ranking;
        }

        public String getName()
        {
            return name;
        }

        public String getDescription()
        {
            return description;
        }

        public int getRanking()
        {
            return ranking;
        }

//        public static HandType getHandType(ArrayList<Card> cards)
//        {
//            if (null == cards)
//            {
//                return null;
//            }
//
//            if (cards.size() == 0)
//            {
//                return null;
//            }
//
//            if (cards.get(0).getCardValue() == CardValue.TEN &&
//                    cards.get(1).getCardValue() == CardValue.JACK &&
//                    cards.get(2).getCardValue() == CardValue.QUEEN &&
//                    cards.get(3).getCardValue() == CardValue.KING &&
//                    cards.get(4).getCardValue() == CardValue.ACE)
//            {
//                boolean hasSameSuit = false;
//                Suit firstSuit = cards.get(0).getSuit();
//
//                for (Card item : cards)
//                {
//                    SuitComparator suitComparator = new SuitComparator();
//
//                    hasSameSuit = suitComparator.compare(firstSuit, item.getSuit()) == 0;
//
//                    if (!hasSameSuit)
//                    {
//                        break;
//                    }
//                }
//
//                if (hasSameSuit)
//                {
//                    return ROYAL_FLUSH;
//                }
//            }
//
//            return HIGH_CARD;
//        }
    }

    class HandTypeComparator implements Comparator<HandType>
    {
        @Override
        public int compare(HandType o1, HandType o2)
        {
            return Integer.compare(o1.getRanking(), o2.getRanking());
        }
    }
}
