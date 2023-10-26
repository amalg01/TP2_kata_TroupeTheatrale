import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) { 

// 1er exemple        
        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", Play.PlayType.TRAGEDY));
        //plays.put("as-like",  new Play("As You Like It", Play.PlayType.COMEDY));
        plays.put("othello",  new Play("Othello", Play.PlayType.TRAGEDY));

        Invoice invoice = new Invoice("Amal", List.of(
                new Performance("hamlet", 55),
                //new Performance("as-like", 35),
                new Performance("othello", 40)));
        
// 2eme exemple
        HashMap<String, Play> plays2 = new HashMap<>();
        plays2.put("as-like",  new Play("As You Like It", Play.PlayType.COMEDY));
        plays2.put("othello",  new Play("Othello", Play.PlayType.TRAGEDY));
        plays2.put("romeo",  new Play("Romeo and Juliet", Play.PlayType.TRAGEDY));
        plays2.put("macbeth",  new Play("Macbeth", Play.PlayType.TRAGEDY));

        Invoice invoice2 = new Invoice("Siham", List.of(
                new Performance("as-like", 35),
                new Performance("othello", 40),
                new Performance("romeo", 50),
                new Performance("macbeth", 30)));
                
        StatementPrinter statementPrinter = new StatementPrinter();
        StatementPrinter statementPrinter2 = new StatementPrinter();
        var r = statementPrinter.print(invoice, plays); 
        r = statementPrinter2.print(invoice2, plays2);
    } 
}