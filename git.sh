#!/bin/bash
#this progran is used to sync the direction ~/github/ wiht GitHub
git add ~/githubWangHC/
date_now = $(date +%Y%m%d%H%M%S)
git commit -m "add file in '$date_now'"
git push origin master
