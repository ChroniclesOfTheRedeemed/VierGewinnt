/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Factories and listeners are nicu for advanced java programming
 *
 */
package Enums;

import Statistics.*;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public enum StatisticEnum {

    WinStatistic((String Player1name, String Player2name) -> new WinStatistic(Player1name, Player2name)),
    TimePerMoveStatistic((String Player1name, String Player2name) -> new TimePerMove(Player1name, Player2name)),
    TimePerMoveNanoStatistic((String Player1name, String Player2name) -> new TimePerMoveNano(Player1name, Player2name));

    StatisticEnum(StatFactory factory) {
        statFactory = factory;
    }

    public final StatFactory statFactory;
}
