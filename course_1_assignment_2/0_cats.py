#!/bin/python

import re
import numpy as np
# import scipy
import scipy.spatial.distance as distance
from operator import itemgetter

IN_FILE = "sentences.txt"
BASE_LINE_NUM = 0

with open(IN_FILE, "r") as f:
  utokens = {}
  mtokens = []
  curIdx = 0
  for line in f:
    tokens = re.split('[^a-z]', line.lower())
    mtokensEntry = {}
    for token in tokens:
      if bool(token.strip()):
        if not token in utokens:
          idx = curIdx
          utokens[token] = curIdx
          curIdx += 1
        else:
          idx = utokens[token]

        if not idx in mtokensEntry:
          mtokensEntry[idx] = 1
        else:
          mtokensEntry[idx] += 1
    mtokens.append(mtokensEntry)

  atokens = []
  for i in range(len(mtokens)):
    atokensEntry = [0 for idx in utokens]
    for idx in mtokens[i]:
      atokensEntry[idx] = mtokens[i][idx]
    atokens.append(atokensEntry)

  mtrx = np.array(atokens)

  dists = [(idx, distance.cosine(mtrx[BASE_LINE_NUM], mtrx[idx])) for idx in range(mtrx.shape[0])]
  distsSorted = sorted(dists, key=itemgetter(1))

  # print(utokens, '\n')
  # print(mtokens, '\n')
  # print(atokens, '\n')
  print(mtrx, '\n')
  print(distsSorted, '\n')

  if len(distsSorted) > 2:
    idx0 = BASE_LINE_NUM
    (idx1, _) = distsSorted[1]
    (idx2, _) = distsSorted[2]
    midx = max(idx0, idx1)
    with open(IN_FILE, "r") as f:
      for i, line in enumerate(f):
        if i == idx0 or i== idx1 or i == idx2:
          print(line)
        if i > midx:
          break
        
