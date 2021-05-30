/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

import java.util.function.Consumer;

/**
 *
 * @author absea
 */
   public class Event {
        public final int gameTime;
        public Consumer<Object> method;

        public Event(int gameTime, Consumer<Object> method) {
            this.gameTime = gameTime;
            this.method = method;
        }
    }