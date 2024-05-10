package sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import sample.bean.Dice;
import sample.bean.Game;
import sample.bean.Player;

import java.util.List;

//@ComponentScan(basePackages = "sample")
@PropertySource({"classpath:game.properties"})  // properties 파일 불러오기
public class GameConfig {
    // 어노테이션을 사용하지 않았을 때
    @Bean
    public Dice dice(@Value("${face}") int face) {  // ${key}의 value를 변수에 할당
        return new Dice(face);
    }

    @Bean
    public Player kim(Dice dice) {  // id가 dice인 Bean 주입
        Player player = new Player();
        player.setDice(dice);   // setter를 통한 주입
        player.setName("kim");

        return player;
    }

    @Bean
    public Player lee(Dice dice) {  // id가 dice인 Bean 주입
        Player player = new Player();
        player.setDice(dice);   // setter를 통한 주입
        player.setName("lee");

        return player;
    }

    @Bean
    public Player park(Dice dice) {  // id가 dice인 Bean 주입
        Player player = new Player();
        player.setDice(dice);   // setter를 통한 주입
        player.setName("park");

        return player;
    }

    @Bean
    public Game game(List<Player> players) {    // Player 타입을 리스트에 넣음
        return new Game(players);   // 생성자를 통한 주입

        // setter를 통한 주입
//        Game game = new Game();
//        game.setList(players);
//        return game;
    }
}
