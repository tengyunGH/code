package com.life.application.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/15 16:00
 **/
public class NestedIterator implements Iterator<Integer> {


    List<Integer> values = new ArrayList<>();
    private int position = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        serialize(nestedList);
    }

    public void serialize(List<NestedInteger> nestedList) {
        if (nestedList != null && nestedList.size() > 0) {
            for (NestedInteger integers : nestedList) {
                if (integers.isInteger()) {
                    values.add(integers.getInteger());
                } else {
                    serialize(integers.getList());
                }
            }
        }
    }

    @Override
    public Integer next() {
        return values.get(position++);
    }

    @Override
    public boolean hasNext() {
        return values.size() > position;
    }

}
