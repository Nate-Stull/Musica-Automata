# Musica-Automata
This project uses "Elementary Cellular Automata" to generate music.

Cellular automata in general are best described here: 
https://en.wikipedia.org/wiki/Cellular_automaton

Elementary cellular automata (one dimensional cellular automata): http://mathworld.wolfram.com/ElementaryCellularAutomaton.html

Using only a few simple rules, complex structures can be generated, many of which are seemingly random in nature. By following the middle index of the repeatedly generated array that forms the structure (which can either have a "0" or "1" state at each index) a 4-bit number is created and used to calculate rhythm and notes.

The program experiences some slowdown problems on my computer, but still runs OK.

When generating the array, using a size greater than 50 makes the generated patterns more apparent. Changing the size of the array can also change the sounds produced.

Some rules produce more interesting sounds than others. 30, 45, and 135 produce some cool sounds in particular.

