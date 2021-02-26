package com.overaitis.inclass_7_2.command.sources.types;

import com.overaitis.inclass_7_2.command.sources.CommandSource;
import com.overaitis.inclass_7_2.command.sources.Locatable;
import com.sun.javafx.geom.Vec3d;

public class Player implements CommandSource, Locatable {

    private final String name;
    private boolean operator = false;
    private Vec3d position;

    public Player(String name) {
        this.name = name;
        this.position = new Vec3d();
    }

    public Player(String name, boolean operator, Vec3d position) {
        this.name = name;
        this.operator = operator;
        this.position = position;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(getName() + ": " + message);
    }

    @Override
    public boolean isOperator() {
        return operator;
    }

    public void setOperator(boolean operator) {
        this.operator = operator;
    }

    @Override
    public Vec3d getPosition() {
        return position;
    }
}
