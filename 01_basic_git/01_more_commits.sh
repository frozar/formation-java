#!/bin/sh
cd my_repo

echo "" >> README.md
echo "Commit 2" >> README.md
git add README.md
git commit -a -m "2nd commit"

echo "" >> README.md
echo "Commit 3.0" >> README.md
echo "Commit 3.1" >> README.md
git commit -m "3rd commit" -- README.md

echo "Nous sommes en 2021" > note.txt
git add note.txt
git commit -m "Add the note.txt file"

# git diff HEAD~1
# git log -n2
# git log -p
