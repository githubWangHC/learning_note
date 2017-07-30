/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laudukang
 * @param <E>
 */

public class Stack<E extends Object> {

    private List<E> pool = new ArrayList<>();

    public Stack() {
    }

    public void clear() {
        pool.clear();
    }

    public boolean isEmpty() {
        return pool.isEmpty();
    }

    /**
     * 获取栈顶元素 
     *
     * @return 
     */
    public E getTopObjcet() {
        if (isEmpty()) {
            return null;
        }
        return pool.get(0);
    }

    /**
     * 弹出栈操作 
     *
     * @return 
     */
    public E pop() {
      
        return pool.remove(pool.size() - 1);
    }

    /**
     * 压入栈 
     *
     * @param e
     */
    public void push(E e) {
     //   if (isEmpty()) {
         
      //  }
        pool.add(e);
    }

    /**
     * 获取当前栈大小 
     *
     * @return 
     */
    public int getStatckSize() {
        if (isEmpty()) {
          return 0;
        }
        return pool.size();
    }

}
