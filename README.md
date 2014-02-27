Program to auto-generate crosswords.

Version 1 has following limitations:   

(1) Clues are a bit naff. I have downloaded a wordlist of >100,000 "words" and then loaded these into the Cambridge online dictionary (dictionary.cambridge.org). If it found a definition I stored this as the "clue" and ignored the word otherwise. Left me with about 17,000 "words". Could get clues also from other dictionaries / a thesaurus / anagram generator.

(2) I haven't learned how to use Java's swing library properly yet. In a later version you will be able to enter answers into a grid and be able to check them as you go along. For now, it just prints a crossword grid and the clues onto the screen. Doesn't even have a scroll bar...

(3) Algorithm for fitting words could be improved. At the moment it will always try and fit th largest possible word which leads to the same words appearing more often than you would expect from a list of 17,000 words.   

  
   
   
Following files perform the following functions:
