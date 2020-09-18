package ru.kaiko.deone.service;

import org.springframework.stereotype.Service;

@Service
public class DogSound implements SoundAnimals {
    @Override
    public String sound() {
        return "woof";
    }
}
