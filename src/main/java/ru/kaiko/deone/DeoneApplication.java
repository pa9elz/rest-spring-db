package ru.kaiko.deone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DeoneApplication {

    public static void main(String[] args) {

//            char ch = '5';
//            int ch2 = 6;
//            System.out.printf("Marks for my assignment is %d%d",ch, ch2);

        ApplicationContext ctx = SpringApplication.run(DeoneApplication.class, args);
        //HorseSound horseSound = (HorseSound) ctx.getBean("horseSound");
//        SoundAnimals horseSound =  (HorseSound) ctx.getBean("horseSound");
//        System.out.println(horseSound.sound());
    }

}
