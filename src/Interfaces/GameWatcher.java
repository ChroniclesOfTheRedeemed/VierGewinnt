/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Interfaces.Game.GameResult;

/**
 * Extensionalität ist es ein perfektes System mit Leichtigkeit zu einem neuen perfekten System zu verändern, dass neuen Anforderungen standhält
 * @author Mike
 * @param <Zug>
 */
public interface GameWatcher < Zug >{
    //<editor-fold defaultstate="collapsed" desc="gameRef Security"> 
    /*Problematics:
    - handing out the changing gameref makes every participant able to actively manipulate it, and therefore very insecure and
    potencially instable
    - could be resolved with giving a copy of the playNewGame reference and forcing everyone to interpret the next move into a new map
    - is done that way
    - for future work, what can also be done: make a new class "Spielfeld" with the desired type as private and the necessary access as public, or anything of that sort
    */
    //</editor-fold>
    void gameStarted(Game gameRef);
    //No more information required due to alternating nature of the game
    void moveSet(Zug move);
    void gameEnded(Zug finishingMove, GameResult gameResult);
}
