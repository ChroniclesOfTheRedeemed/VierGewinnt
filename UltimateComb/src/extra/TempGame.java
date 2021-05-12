/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

/**
 *
 * @author Mike
 * @param <Zug>
 */
public interface TempGame<Zug> extends Core.Game2D2P<Zug> {
    void enemyMadeMove();
}
