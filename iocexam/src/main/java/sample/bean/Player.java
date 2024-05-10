package sample.bean;

import org.springframework.stereotype.Component;

//@Component
public class Player {
    private String name;
    private Dice dice;  // 실행될 때 dice를 injection 받아야 함

    // DI 방법: 필드, 생성자, setter


    public Player() {
        System.out.println("Player() 생성");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public void play() {
        System.out.println(name + "(는)은 주사위를 던져서 " + dice.getNumber() + "(이)가 나왔습니다.");
    }
}
