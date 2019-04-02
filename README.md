# Musica-Automata
This project uses "Elementary Cellular Automata" to generate music.

Cellular automata in general are best described here: 
https://en.wikipedia.org/wiki/Cellular_automaton

Elementary cellular automata (one dimensional cellular automata): http://mathworld.wolfram.com/ElementaryCellularAutomaton.html

Using only a few simple rules, complex structures can be generated, many of which are seemingly random in nature. By following the middle index of the repeatedly generated array (which can either have a "0" or "1" state at each index) 4-bit, 8-bit, and 16-bit numbers are calculated. The 4-bit number is converted to an int and multiplied by 50 to determine the rhythm delay between notes and array generation. The 8-bit number is converted and % by 14 to produce a note. This note is played at every iteration of the array. The 16-bit number is also modded by 14 to produce a note an octave lower than the 8-bit number. This note is only played when the last digit of the converted 4-bit number is equal to the last digit of the converted 16-bit number. This keeps the sounds interesting.

The program experiences some slowdown problems on my computer, but still runs OK.

When generating the array, using a size greater than 50 makes the generated patterns more apparent. Changing the size of the array can also change the sounds produced.

Some rules produce more interesting sounds than others. 30, 45, and 135 produce some cool sounds in particular.

