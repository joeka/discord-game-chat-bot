package net.verplanmich.bot.game;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Deck {

    private List<String> availableCards;
    private List<String> drawPile = new ArrayList();
    private List<String> discardPile = new ArrayList();

    public Deck(){
        this(new ArrayList());
    }

    public Deck(List availableCards) {
        this.availableCards = availableCards;
        initialize();
    }

    public static Deck getFor(String gameName, String deckName) throws IOException {
        try (
                InputStream inputStream = Deck.class.getClassLoader().getResourceAsStream(gameName + "." + deckName + ".list");
                Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8.name())
        ) {
            List<String> cards = new ArrayList();
            while (sc.hasNext()) {
                List moreCards = Arrays.asList(sc.nextLine().split(","));
                cards.addAll(moreCards);
            }
            List<String> cardIds = cards.stream().map(s-> Paths.get(s).normalize().toString()).collect(Collectors.toList());
            return new Deck(cardIds);
        }
    }

    public void initialize() {
        discardPile = new ArrayList();
        drawPile = new ArrayList(availableCards);
        Collections.shuffle(drawPile);
    }

    public String showDrawCard() {
        return drawPile.get(drawPile.size() - 1);
    }

    public String showDiscardCard() {
        return discardPile.get(discardPile.size() - 1);
    }

    public String drawCard() {
        String cardId = showDrawCard();
        drawPile.remove(drawPile.size() - 1);
        return cardId;
    }

    public void toDrawPileTop(String cardId) {
        drawPile.add(0,cardId);
    }
    public void toDrawPileBottom(String cardId) {
        drawPile.add(cardId);
    }

    public void discardCard(String cardId) {
        discardPile.add(cardId);
    }

    public void shuffleDiscard() {
        drawPile = new ArrayList(discardPile);
        discardPile = new ArrayList();
        Collections.shuffle(drawPile);
    }

    public boolean isEmpty() {
        return drawPile.isEmpty();
    }

    public List<String> getAvailableCards() {
        return new ArrayList<>(availableCards);
    }
}
