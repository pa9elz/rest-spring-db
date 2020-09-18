package ru.kaiko.deone.service;

import org.springframework.stereotype.Service;

@Service
public class HorseSound implements SoundAnimals{
    @Override
    public String sound() {
        return "neigh";
    }
}
