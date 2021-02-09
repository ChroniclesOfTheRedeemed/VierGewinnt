/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

//Ein Studium Modul ist wie eine Map eines Rollenspiels.

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Exceptions.MoveNotAvailableException;

//Der Professor zeigt Ihnen alle wichtigen, sehenswerten und relevanten Orte und was man zu Ihnen wissen muss.

//Das Ziel ist aber dass jeder Student alles über diese Orte weiß und am besten immer wissen wird.
//Am naheliegensten ist es daher sich den Ort mal mit eigenen Augen anzuschauen, damit prägt er sich besser ein.
//Aber am eigentlichsten sollte man den Ort auf eigener Suche finden können, damit man sich allzeit wieder über alles vergewissern kann.

//Weiterer hinweis: Schnellreisen. Besuchte Orte können mit schnellreise wieder besucht werden. Der Professor hat alle Orte bereits tonnenfach besucht, und das 
//schnellreisen fällt ihm für die meisten Orte sehr, sehr leicht. DAher kann er auch afast auf knopfdruck alle wesentlichen Fragen zu Ihnen beantworten, können tut er es aber usually immer,
//weil er sie immer weiß wie man zu Ihnen gelangt (für gewöhnlich)
//Der Student braucht die Schnellreise funktion meist aber für sein Studium, da das reine Wissen leicht zerfällt und unvollständig wird, und das ganze Modul unnütz machen würde.

//aber jeden Ort einzeln zu Fuß zu besuchen, oder gar den Studenten selbst auf die Suche schicken, ist wahrscheinlich der nachhaltigste aber am 
//wenigsten zu realisierende Weg. Studenten sind unterschiedlich schnell zu Fuß, manche schaffen manche Wege schneller als andere und Pausenregeleung ist auch oft 
//sehr eigen. Und ihn auf eigene Faust erkunden lassen birgt hohes Verlust Risiko, besonders Motivationsmäßig, wird der Student evtl faul und verkümmert auf der Strecke
//oder andere Gefahren, vor dem Ihn ein Professor hätte bewahren können.


/**
 *
 * @author Mike
 */
public interface Game {
    
    //Constants
    public int getSpielFeldBreite();
    public int getSpielFeldHöhe();
    public final String GameName = "Vier Gewinnt";
    public static final boolean Player1hasFirstMove = true;

    public static enum GameResult {
        GameWonForPlayer1, GameWonForPlayer2, Draw
    }

    public String getNameOfPlayer(boolean player1);
    /* Returns the amount of valid moves that have
     * been made in the current game.
     * 
     * Since this is just a function for reference and can easily be simulated by the game participants it
     * will likely be discarded somewhere in the future.
     * Please keep that in mind when utilizing this function.
     * 
     * @return 
     */
    public int movesDone();

    /** Can be called anytime and advises the Game Instance to check upon the Player who should 
     * make a move. If a move has indeed been made, the Game Instance will commence the game.
     * This Function has to be directly or indirectly called by the player when he made his move,
     * because else the game will not progress.
     *
     * @throws GameStateException
     * @throws InvalidMoveException
     * @throws MoveNotAvailableException 
     */
   // public void playerMadeMove() throws GameStateException, InvalidMoveException;

    
    /** Initiates a new game and will progress in doing so until the game ends.
     * Game particioipants will be notified of the events that happpen during the game.
     * 
     * @throws GameStateException 
     */
    public void playNewGame() throws GameStateException;
}
