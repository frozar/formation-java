#!/bin/sh
cd my_repo

git rm note.txt
git commit -m "Remove the note.txt file"

# git diff HEAD~1
# git log -n2
# git log -p
