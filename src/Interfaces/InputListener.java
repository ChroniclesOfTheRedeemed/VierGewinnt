/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;

/**__DATE__ , __TIME__
 *
 * @author Mike
 * @param <Zug>
 */

public interface InputListener <Zug>{
    //Expects Valid Input, because your Field will be updated with this input
    void inputGiven(Zug validMove) throws InvalidMoveException, GameStateException;
}
