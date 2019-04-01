import javax.sound.midi.*;

public class Note {

    public static void play(int note) {
        try{
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);//load an instrument

            mChannels[0].noteOn(note, 100);//On channel 0, play note number 60 with velocity 100

        } catch (MidiUnavailableException e) {
            System.out.println(e);
        }
    }

}