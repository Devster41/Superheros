package dev.cibmc.spigot.superPlugin;

import java.util.Random;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class NoteRunnable implements Runnable {
    Block nextBlock;
    Player player;

    public NoteRunnable(Player player, Block nextBlock) {
        this.nextBlock = nextBlock;
        this.player = player;
    }
    @Override
    public void run() {
        Random rn = new Random();
        int octave = rn.nextInt(2);
        int toneInt = rn.nextInt(7);
        Tone tone;

        switch(toneInt) {
            case 1:
                tone = Tone.A;
                break;

            case 2:
                tone = Tone.B;
                break;
            
            case 3:
                tone = Tone.C;
                break;
            
            case 4:
                tone = Tone.D;
                break;

            case 5:
                tone = Tone.E;
                break;
            
            case 6:
                tone = Tone.F;
                break;
            
            case 7:
                tone = Tone.G;
                break;
            
            default:
                tone = Tone.A;
                break;
        }

        player.playNote(nextBlock.getLocation(), Instrument.GUITAR, Note.natural(octave, tone));
        
    }
    
}
