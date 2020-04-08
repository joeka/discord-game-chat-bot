package net.verplanmich.bot.game.alienencounter;

import net.verplanmich.bot.game.Deck;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class GameDecks {

    static final String NAME = "alienencounter";

    static final String CHIEF_ENGINEER_PARKER = "chiefengineerparker";
    static final String ENGINEER_BRETT = "engineerbrett";
    static final String EXECUTIV_OFFICER_KANE = "executivofficerkane";
    static final String WARRANT_OFFICER_RIPLEY = "warrantofficerripley";
    static final String NAVIGATOR_LAMBERT = "navigatorlambert";
    static final String CAPTAIN_DALLAS = "captaindallas";

    static final String CHARS = "chars";

    private HashMap<Mission, List<Deck>> crews = new HashMap();
    private Deck chars;

    public GameDecks() throws IOException {
        chars = Deck.getFor(NAME,CHARS);
        List nostromoCrew = new ArrayList();
        crews.put(Mission.NOSTROMO,nostromoCrew);
        nostromoCrew.add(Deck.getFor(NAME, CHIEF_ENGINEER_PARKER));
        nostromoCrew.add(Deck.getFor(NAME, ENGINEER_BRETT));
        nostromoCrew.add(Deck.getFor(NAME, EXECUTIV_OFFICER_KANE));
        nostromoCrew.add(Deck.getFor(NAME, WARRANT_OFFICER_RIPLEY));
        nostromoCrew.add(Deck.getFor(NAME, NAVIGATOR_LAMBERT));
        nostromoCrew.add(Deck.getFor(NAME, CAPTAIN_DALLAS));
    }

    public Deck barracksFor(Mission mission){
        List<String> barrackCards = new ArrayList();
        List<Deck> nostromoCrew = new ArrayList();
        nostromoCrew.addAll(crews.get(mission));
        Collections.shuffle(nostromoCrew);
        nostromoCrew.stream().limit(4).forEach(
            d-> barrackCards.addAll(d.getAvailableCards())
        );
        return new Deck(barrackCards);
    }

    public Deck getChars(){
        return new Deck(chars.getAvailableCards());
    }

}