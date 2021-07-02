/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

/**
 *
 * @author Mike
 */
public interface StatFactory {
    public Statistic createStatistic(String Player1name, String Player2name);
}
