import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import objectdraw.*;
import java.awt.*;
/**
 * Displays the initial look of the canvas,
 * calls methods when mouse is clicked,
 * calls method when the cheat button is clicked.
 * 
 * @author Tseki Lhamo 
 * @version 21st November 2016
 */
public class Concentration extends WindowController implements ActionListener
{
    //creating variable of type cardCollection 
    public CardCollection cardCollection;
    //card variables for specific cards
    private Card firstCard, 
                 secondCard;
    //size of tempCardArray's size             
    private int arraySize = 36;
    //horizontal and vertical spacing for cards that don't change 
    private static final int VERTICAL_SPACING = 60,
                             HORIZONTAL_SPACING = 65;
    //coordinates for the cardtop and cardleft that get updated
    private int cardTop = 10; 
    private int cardLeft = 10;
     
    public void begin(){
        //creating a new CardCollection object 
        cardCollection = new CardCollection();
        //using this temporary array, that will be filled, and then will equate it to the official array in CardCollection class
        Card[] tempCardArray =  new Card[arraySize]; 
        //setting index value to zero
        int index = 0;
        //creating char value starting at a
        char c = 'a';
        //creating array for symbols that will hold char elements
        char[] symbols = new char [36];
        //for loop to add char values to the symbols array
        for(int i = 0; i < symbols.length; i++){
            symbols[i] = c;
            c++; 
            if (c == 's'){ //condition that allows c to have values only till 'r'
                c = 'a';   //updates the c to begin at 'a' again. This will create pairs of each letter
            }
        }
        
        //variable for randIntGenerator to get a value
        RandomIntGenerator randomChar;
        randomChar = new RandomIntGenerator(0,35);  //creating a random integer generator that ranges from 0 to 35 
        
        //shuffles the card by looping 100 times 
        for(int i = 0; i <= 100; i++){   
            //objects for the two random values that are generated
            //these objects will be treated as index values
            int firstRandNum = randomChar.nextValue();
            int secondRandNum = randomChar.nextValue();
            //temporary object to save the value at the random index that was generated
            char temp = symbols[firstRandNum]; 
            //sets the value of the symbol at index firstRandNum to the value at the symbol at index secondRandNum
            symbols[firstRandNum] = symbols[secondRandNum];
            //sets the value of the 
            symbols[secondRandNum] = temp;
           
        }
        //displaying the cards
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                //creating a new card with a symbol using the symbol index which is randomized
                tempCardArray[index] = new Card(symbols[index], cardLeft, cardTop, canvas); 
                //incrementing index to move to the next index 
                index++;
                //updating the cardLeft to a new position after a card is created
                cardLeft = cardLeft + HORIZONTAL_SPACING;   

            }
            cardLeft = 10;    //resetting the cardLeft to 10 so that the next row begins where x = 10
            cardTop = cardTop + VERTICAL_SPACING;         //card spacing on y axis
        }
        //calling the setCard method that equates this array in this class to the empty array in the cardCollection class
        cardCollection.setCard(tempCardArray);
        //panel to add the cheat button created
        JPanel northPanel = new JPanel();    
        JButton cheatButton = new JButton("Cheat");
        //adds the cheatButton to the northPanel
        northPanel.add(cheatButton);
        //adds the panel to the canvas on the north orientation 
        add(northPanel, BorderLayout.NORTH);
        cheatButton.addActionListener(this);
    }

    public void onMouseClick(Location point){
        //when the mouse is first clicked, all the cards are null
        //so this condition will satisfy
        if(firstCard == null){
            //shows the symbol of card that contains point that the mouse was clicked. That card becomes firstCard
            firstCard = cardCollection.getCardAt(point); 
        } else {   
            //at the second click, first condition will not satisfy and the if statement bellow will execute 
            if (secondCard == null){      
                //shows the symbol of card that contains point that the mouse was clicked. That card becomes secondCard
                secondCard =  cardCollection.getCardAt(point);
            } else { //the third click chekcs to see if symbols on cards equal to each other               
                
                //condition for thrid click if the symbol of firstCard is not the same as the symbol of the secondCard,               
                if (firstCard.getSymbol() != secondCard.getSymbol() ){
                    //the symbols of both cards will be hidden
                    firstCard.hideSymbol();
                    secondCard.hideSymbol();
                    //resets the cards to null so that the conditions can work again
                    firstCard = null;
                    secondCard = null;
                   
                } else { 
                    //condition that works only if the firstCard does not equal to the secondCard
                    //this prevents a card from disappearing if it is double clicked
                    //condition satisfies if the symbols of firstCard and secondCard are the same                                   
                    if(!firstCard.equals(secondCard)){
                        //removes card from the cardArray array in the CardCollection class
                        cardCollection.removeCard(firstCard.getSymbol());
                        cardCollection.removeCard(secondCard.getSymbol());
                        //removes the first and second card from the canvas
                        firstCard.removeFromCanvas();
                        secondCard.removeFromCanvas();
                        //resets the cards to null so that the conditions can work again
                        firstCard = null;
                        secondCard = null;

                    }
                    else{ //condition for when the same card is clicked nothing will happen, the cards will be null
                          //till a different card is clicked for another condition to satisfy
                        firstCard = null;
                        secondCard = null;

                    }

                }
            }
        }
    }
    //method that makes the cheat button face all the cards up
    public void actionPerformed(ActionEvent event){  
        cardCollection.allFaceUp();
    }
}
