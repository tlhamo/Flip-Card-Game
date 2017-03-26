import objectdraw.*;
import java.awt.*;
/**
 * Contains contructor and methods for a single card
 * 
 * @author Tseki Lhamo 
 * @version 21st November 2016
 */
public class Card
{   
    //variable for text on a card
    private Text cardText;    
    //variable for a card
    private FilledRect singleCard;
    //height and width constants for the cards
    private static final int CARD_WIDTH = 50,
                             CARD_HEIGHT = 50;
    //char variable that holds the same value as the symbol variable in the Card constructor                         
    private char symbol;
   public Card( char symbol, int left, int top, DrawingCanvas canvas){
       //setting the symbol in the constructor parameter to the instance variable so it can be used
       //elsewhere in the code
       this.symbol = symbol;
       singleCard = new FilledRect(left, top, CARD_WIDTH, CARD_HEIGHT, canvas);
       //setting the color of the card to green
       singleCard.setColor(Color.GREEN);
       //text object that with the details for the text 
       cardText = new Text(symbol, left + CARD_WIDTH/2, top + CARD_HEIGHT/2, canvas); 
       //setting font size for the symbols
       cardText.setFontSize(30);
       //centering the text on the card 
       cardText.move(-cardText.getWidth()/2, -cardText.getHeight()/2);
       //initially hides cardText when the card constructor is called
       cardText.hide(); 
    }
   //shows symbol on a card
    public void showSymbol(){
        cardText.show();
    }
    //hides symbol of a card 
    public void hideSymbol(){
        cardText.hide();
    }
    //returns symbol that was passed in as a parameter in the card constructor
    public char getSymbol(){
        return symbol;
    }
    //removes the text and the retangle from canvas
    public void removeFromCanvas(){
        //method will only work if the text is on the card and if there is a card
        if (cardText != null && singleCard != null){
        cardText.removeFromCanvas();
        singleCard.removeFromCanvas();
    }
}
    //checks to see if the individual card contains point (point referring to point that was clicked)
    public boolean contains(Location point){
        if(singleCard.contains(point)){
            return true;
        }else{
            return false;
    }
}
}
