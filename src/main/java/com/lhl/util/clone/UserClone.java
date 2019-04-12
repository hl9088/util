package com.lhl.util.clone;

import com.lhl.util.bean.User;

/**
 * Created by lihongli on 2019/4/12
 */
public class UserClone extends User implements Cloneable {

    private User child;

    public User getChild() {
        return child;
    }

    public void setChild(User child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "{user: age = " + getAge() + ", name = \"" + getName() + "\", child = \"{age = " + getChild().getAge()
                + ", name = " + getChild().getName() + "}\"}";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
