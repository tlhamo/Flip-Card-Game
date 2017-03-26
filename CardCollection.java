import objectdraw.*;
import java.awt.*;
/**
 * This class contains the collection of cards in an array 
 * and methods that evaluates all the array indexes.
 * 
 * @author Tseki Lhamo 
 * @version (a version number or a date)
 */
public class CardCollection 
{
    public Card[] cardArray;  //collection or array that has all the cards 

    public CardCollection(){
        cardArray  = new Card [36];         //initializing cardArray to hold 36 indexes or spaces for information 
    }

    public void allFaceUp(){ //method to make all the cards face up by using the showSymbol method 

        for (int i = 0; i < cardArray.length; i++){   //loop to go through all the indexes and make all of them face up
            //prevents nullpointerexception that occurs when showSymbol is called on cards that are removed
            //faces the cards up if card is there
            if( cardArray[i] != null ){      
                cardArray[i].showSymbol();          

            }
        }
    }
    //method to determine which card was clicked 
    public Card getCardAt(Location point){
        for (int i = 0; i < cardArray.length; i++){  
            //shows symbol for indexes that aren't null or are on the canvas
            //this condition prevents the nullpointerexception
            //the condition also satifies only when the 
            if ( cardArray[i] != null  && cardArray[i].contains(point) ){   
                cardArray[i].showSymbol();
                return cardArray[i];  
            } 

        }
        return null;    //why does this return null
    }
    //method to remove card from cardArray
    public void removeCard(char symbol){
        for (int i = 0; i < cardArray.length; i++){
            //removes card when card is on the canvas or when card is not null
            // and removes card from the the cardArray at the index in cardArray whose symbol the same as the symbol of firstCard
            if(cardArray[i] != null && cardArray[i].getSymbol() == symbol){
                //sets that index to null so that it doesn't exist or have a value anymore
                cardArray[i] = null;  

            }

        }
    }

    public void setCard(Card[] array){  //method that takes in the array from the Concentration calls and equates it to the array in this class
        cardArray = array;
    }
}
