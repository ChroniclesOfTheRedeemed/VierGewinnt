/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**__DATE__ , __TIME__
 *
 * for people like me who can't code properly
 * and sometimes need two returnables
 * 
 * @author Mike
 * @param <T1>
 * @param <T2>
 */
public class Pair<T1, T2> {
    private final T1 object1;
    private final T2 object2;
    
    public Pair(T1 object1, T2 object2){
        this.object1 = object1;
        this.object2 = object2;
    }
    
    public T1 getObject1(){
        return object1;
    }
    
    public T2 getObject2(){
        return object2;
    }
    
}
