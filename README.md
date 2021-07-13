# SkyJo-Simulation
A simulation to determine the best cutoff for the SkyJo card game

Current settings: 1,000,000 games of SkyJo.

It works off the following pseudocode:

```
if discard <= cutoff
  take from discard pile
  cardBelowCutoff()
else
  take from deck
  if new card <= cutoff
    cardBelowCutoff()
  else
    discard and expose card from hand

void cardBelowCutoff()
  if any cards are above cutoff
    replace highest card in hand
  else
    replace covered card in hand and discard
```

Running this code outputs the following, showing that 3 is the best cutoff
```
Cutoff:  3 | Wins: 151792 | Average Score:  68
Cutoff:  4 | Wins: 151783 | Average Score:  67
Cutoff:  2 | Wins: 134510 | Average Score:  73
Cutoff:  5 | Wins: 132760 | Average Score:  71
Cutoff:  1 | Wins: 105154 | Average Score:  81
Cutoff:  6 | Wins: 99374 | Average Score:  79
Cutoff:  0 | Wins: 65303 | Average Score:  93
Cutoff:  7 | Wins: 64466 | Average Score:  88
Cutoff:  8 | Wins: 37410 | Average Score:  98
Cutoff:  9 | Wins: 19370 | Average Score: 109
Cutoff: -2 | Wins: 11991 | Average Score: 119
Cutoff: -1 | Wins: 11730 | Average Score: 119
Cutoff: 10 | Wins: 9042 | Average Score: 123
Cutoff: 11 | Wins: 3848 | Average Score: 137
Cutoff: 12 | Wins: 1467 | Average Score: 148
```
