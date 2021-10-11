#!/bin/sh
git init my_repo
cd my_repo

echo "# Introduction" > README.md
git add README.md

git commit -m "1st commit"
